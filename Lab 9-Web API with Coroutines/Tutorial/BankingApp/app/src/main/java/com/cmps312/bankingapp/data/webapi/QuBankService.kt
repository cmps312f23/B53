package com.cmps312.bankingapp.data.webapi

import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.BankService
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class QuBankService : BankService {
    //    the base url
    private val baseUrl = "https://cmps312banking.cyclic.app/api"

    //    create the client the object that allows you to communicate with the server
//    complexity is hidden under this object.
    private val client = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                },
                contentType = ContentType.Application.Json
            )
        }
    }

    override suspend fun getTransfer(cid: Int): List<Transfer> {
        val url = "$baseUrl/transfers/$cid"
        return client.get(url).body()
    }

    override suspend fun addTransfer(transfer: Transfer): Transfer {
        val url = "$baseUrl/transfers/${transfer.cid}"
        return client.post(url) {
            setBody(transfer)
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun deleteTransfer(cid: Int, transferId: String): String {
        val url = "$baseUrl/transfers/$cid/$transferId"
        return client.delete(url).body()
    }

    override suspend fun getAccounts(cid: Int): List<Account> {
        val url = "$baseUrl/accounts/$cid"
        return client.get(url).body()
    }

    override suspend fun getBeneficiary(cid: Int): List<Beneficiary> {
        val url = "$baseUrl/beneficiaries/$cid"
        return client.get(url).body()
    }

    override suspend fun addBeneficiary(cid: Int, beneficiary: Beneficiary): Beneficiary {
        val url = "$baseUrl/beneficiaries/$cid"
        return client.post(url) {
            setBody(beneficiary)
            contentType(ContentType.Application.Json)
        }.body()
    }

    override suspend fun deleteBeneficiary(cid: Int , accountNo: Int): String{
        val url = "$baseUrl/beneficiaries/$cid/$accountNo"
        return client.delete(url).body()
    }
    override suspend fun updateBeneficiary(cid: Int, updatedBeneficiary: Beneficiary): String {
        val url = "$baseUrl/beneficiaries/$cid"
        return client.post(url) {
            setBody(updatedBeneficiary)
            contentType(ContentType.Application.Json)
        }.body()
    }

}