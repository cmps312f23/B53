package com.cmps312.bankingapp.ui.views.transfer

import androidx.activity.ComponentActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cmps312.bankingapp.viewmodel.BankingViewModel
import com.cmps312.bankingapp.data.model.Beneficiary
import com.cmps312.bankingapp.ui.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BeneficiaryList(onBeneficiarySelected: () -> Unit) {
    val bankingViewModel =
        viewModel<BankingViewModel>(viewModelStoreOwner = LocalContext.current as ComponentActivity)

    //we need to populate the beneficiaries
    bankingViewModel.getBeneficiaries()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Select a Beneficiary") })
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(bankingViewModel.beneficiaries) { beneficiary ->
                BeneficiaryCard(beneficiary, onSelectedBeneficiary = {
                    bankingViewModel.setTransferBeneficiaryDetails(
                        beneficiary.name,
                        beneficiary.accountNo
                    )
                    onBeneficiarySelected()
                })
            }
        }
    }
}

@Composable
fun BeneficiaryCard(beneficiary: Beneficiary, onSelectedBeneficiary: () -> Unit) {
    Card {

    }
    Card(modifier = Modifier
        .padding(8.dp)
        .clickable { onSelectedBeneficiary() }) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = Screen.Beneficiary.icon, contentDescription = "Beneficiary")
            Column(modifier = Modifier.weight(3f)) {
                Text(text = "Name : ${beneficiary.name}")
                Text(text = "AccountNo : ${beneficiary.accountNo}")
            }
        }
    }
}

