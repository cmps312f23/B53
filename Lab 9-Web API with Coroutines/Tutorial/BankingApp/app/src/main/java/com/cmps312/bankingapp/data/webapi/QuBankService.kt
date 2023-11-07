package com.cmps312.bankingapp.data.webapi

import android.util.Log
import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.BankService
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class QuBankService : BankService {
    private val refreshIntervalMs: Long = 5000
    private val baseUrl = "https://cmps312banking.cyclic.app/api"
    private val TAG = "QuBankService"

    private val client = HttpClient(OkHttp) {
        expectSuccess = true  //exception for non two hundred
        install(ContentNegotiation) {
            json(
                json = Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                }
            )
        }
        //Log HTTP request/response details for debugging
        install(Logging) { level = LogLevel.BODY }
    }

    override fun getTransfers(cid: Int) = flow {
        while (true) {
            val url = "$baseUrl/transfers/$cid"
            val transfers = client.get(url)
            emit(transfers.body<List<Transfer>>())
            delay(refreshIntervalMs)
        }
    }

    suspend fun getTransfersNoFlow(cid: Int): List<Transfer> {
        val url = "$baseUrl/transfers/$cid"
        return client.get(url).body<List<Transfer>>()

    }

    override suspend fun addTransfer(transfer: Transfer): Transfer {
        val url = "$baseUrl/transfers/${transfer.cid}"
        return client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(transfer)
        }.body()
    }

    override suspend fun deleteTransfer(cid: Int, transferId: String): String {
        val url = "$baseUrl/transfers/$cid/$transferId"
        return client.delete(url).body<String>()
    }

    override suspend fun getAccounts(cid: Int): List<Account> {
        val url = "$baseUrl/accounts/$cid"
        return client.get(url).body()
    }

    override suspend fun getBeneficiaries(cid: Int): List<Beneficiary> {
        return client.get("$baseUrl/beneficiaries/$cid").body()
    }

    override suspend fun updateBeneficiary(cid: Int, beneficiary: Beneficiary): Beneficiary {
        val url = "$baseUrl/beneficiaries"
        val response = client.put(url) {
            contentType(ContentType.Application.Json)
            setBody(beneficiary)
        }.body<Beneficiary>()

        return response
    }

    override suspend fun addBeneficiary(cid: Int, beneficiary: Beneficiary): String {
        val url = "$baseUrl/beneficiaries"
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(beneficiary)
        }.body<String>()

        return response
    }

    override suspend fun deleteBeneficiary(cid: Int, accountNo: Int): String {
        val url = "$baseUrl/beneficiaries/$cid/$accountNo"
        return client.delete(url).body<String>()
    }
}