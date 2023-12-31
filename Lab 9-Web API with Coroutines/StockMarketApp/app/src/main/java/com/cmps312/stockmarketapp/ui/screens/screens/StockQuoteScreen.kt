package cmps312.coroutines.view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cmps312.stockmarketapp.ui.screens.components.Dropdown
import com.cmps312.stockmarketapp.ui.screens.components.TopBar
import com.cmps312.stockmarketapp.ui.viewmodel.JobState
import com.cmps312.stockmarketapp.ui.viewmodel.StockQuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockQuoteScreen() {
    val viewModel = viewModel<StockQuoteViewModel>()

    /*
    // Best to use ViewModel.init
    // LaunchedEffect will be executed when the composable is first launched
    // If ParallelCoroutineScreen recomposes, the companyList will not re-initialize again
    LaunchedEffect(true) {
        viewModel.getCompanies()
    }
    */

    Scaffold(
        topBar = { TopBar("Stock Quote") }
    ) {
        Column(
            Modifier.padding(paddingValues = it),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Dropdown(label = "Company",
                options = viewModel.companyList,
                selectedOption = viewModel.selectedCompany,
                onSelectionChange = {
                    viewModel.selectedCompany = it
                    viewModel.getStockQuote()
                }
            )

            when (viewModel.jobStatusGetStockQuote) {
                JobState.RUNNING -> {
                    CircularProgressIndicator()
                }
                JobState.SUCCESS -> {
                    Text(text = viewModel.stockQuote.toString())
                }
                JobState.CANCELLED -> {
                    Text(text = viewModel.errorMessage, color = Color.Red)
                }
            }
        }
    }
}