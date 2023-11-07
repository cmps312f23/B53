package com.cmps312.bankingapp.data.model

//contract you need to work with the API
interface BankService {
    suspend fun getTransfer(cid: Int): List<Transfer>
    suspend fun addTransfer(transfer: Transfer): Transfer
    suspend fun deleteTransfer(cid: Int, transferId: String): String
    suspend fun getAccounts(cid: Int): List<Account>


    suspend fun getBeneficiary(cid: Int): List<Beneficiary>
    suspend fun addBeneficiary(cid: Int, beneficiary: Beneficiary): Beneficiary
    suspend fun updateBeneficiary(cid: Int, updatedBeneficiary: Beneficiary): String
}