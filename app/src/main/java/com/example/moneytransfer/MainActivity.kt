package com.example.moneytransfer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.moneytransfer.ui.paymentscreen.presentation.PaymentScreen
import com.example.moneytransfer.ui.paymentscreen.viewmodel.PaymentViewModel
import com.example.moneytransfer.ui.theme.MoneyTransferTheme

class MainActivity : ComponentActivity() {
    private val viewModel : PaymentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoneyTransferTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val uiState = viewModel.uiState.collectAsState()
                    PaymentScreen(
                        amount = uiState.value.amount,
                        balance = uiState.value.balanceAmount,
                        onAmountEntered = viewModel::onAmountEntered,
                        onBackPress = viewModel::onBackPress,
                        onPeriodClick = viewModel::onPeriodEntered,
                        onConfirmClicked = viewModel::onConfirmClicked
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoneyTransferTheme {
        Greeting("Android")
    }
}