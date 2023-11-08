package com.cmps312.bankingapp.ui.views.transfer

import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmps312.bankingapp.ui.viewmodel.BankingViewModel

//Todo add the navigation
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferDetails(
    bankingViewModel: BankingViewModel,
    transferId: String,
    onNavigateBack: () -> Unit
) {

    val transfer =
        bankingViewModel.transfers
            .find { it.transferId == transferId }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Transfer Details") },
                navigationIcon = {
                    IconButton(onClick = {
                        onNavigateBack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                        )
                    }
                }
            )
        }
    ) {

        Card(
            modifier = Modifier
                .padding(it)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
                    .padding(30.dp),
            ) {
                Text(text = "From Account Number: ${transfer?.fromAccountNo}")
                Text(text = "Amount Transferred: ${transfer?.amount} QR")
                Text(text = "Beneficiary Account Number: ${transfer?.beneficiaryAccountNo}")
                Text(text = "Beneficiary Name: ${transfer?.beneficiaryName}")
            }
        }
    }
}
