package com.cmps312.bankingapp.ui.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.cmps312.bankingapp.data.model.Account
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.data.model.Transfer
import com.cmps312.bankingapp.data.webapi.QuBankService
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlin.math.log

class BankingViewModel(appContext: Application) : AndroidViewModel(appContext) {
    private val TAG = "TransferViewModel"
    private val quBankService = QuBankService()

    val accounts = mutableStateListOf<Account>()
    val beneficiaries = mutableStateListOf<Beneficiary>()

    //it will automatically recompose the UI once the data is received
    var transfers: StateFlow<List<Transfer>> = quBankService.getTransfers(10001)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val cid = 10001
//    var transfers = mutableStateListOf<Transfer>()

    init {
        getAccounts()
    }

//    private fun getTransfers() {
//        Log.d("Transfers", "getTransfers: ")
//        viewModelScope.launch {
//            val trans = quBankService.getTransfersNoFlow(10001)
//            print(trans)
//            transfers.addAll(trans)
//        }
//    }

    // used for holding the details of new Transfer - used instead of Nav Component nav args
    lateinit var newTransfer: Transfer


    //Fund Transfer screen calls this method before navigation
    fun setTransferFromDetails(fromAccount: String, amount: Double) {
        newTransfer = Transfer(fromAccount, amount)
    }

    fun setTransferBeneficiaryDetails(beneficiaryName: String, beneficiaryAccountNo: String) {
        newTransfer.beneficiaryName = beneficiaryName
        newTransfer.beneficiaryAccountNo = beneficiaryAccountNo
        newTransfer.cid = cid
    }

    fun getAccounts() = viewModelScope.launch {
        accounts.clear()
        accounts.addAll(quBankService.getAccounts(cid))

    }

    fun addTransfer(transfer: Transfer) {
        viewModelScope.launch {
            val newTransfer = quBankService.addTransfer(transfer)
            Log.d(TAG, "addTransfer: $newTransfer")
        }
    }

//    fun getTransfer(transferId: String) = transfers.find { it.transferId == transferId }

//    fun getAccount(accountNo: String): Account? = accounts.find { it.accountNo == accountNo }

    fun getBeneficiaries() {
        viewModelScope.launch {
            beneficiaries.clear()
            beneficiaries.addAll(quBankService.getBeneficiaries(10001))
        }
    }

    fun deleteTransfer(transferId: String) {
        viewModelScope.launch {
            quBankService.deleteTransfer(10001, transferId)
        }
    }
}


