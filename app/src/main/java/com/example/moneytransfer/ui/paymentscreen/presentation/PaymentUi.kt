package com.example.moneytransfer.ui.paymentscreen.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneytransfer.R
import com.example.moneytransfer.ui.theme.MoneyTransferTheme

@Composable
fun PaymentScreen(
    balance: String,
    amount: String,
    onAmountEntered: (String) -> Unit,
    onBackPress: () -> Unit,
    onPeriodClick: () -> Unit,
    onConfirmClicked: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.dark_blue))
            .padding(24.dp)
    ) {

        Icon(
            modifier = Modifier.align(Alignment.End),
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Color.Red
        )

        BalanceComponent(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            balance = balance
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.how_much_to_spend),
            color = Color.White,
        )
        Spacer(modifier = Modifier.weight(1f))

        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = "$${amount}",
            fontSize = 50.sp,
            color = colorResource(id = R.color.white)
        )
        Spacer(modifier = Modifier.padding(16.dp))

        Keypad(
            onNumberEntered = onAmountEntered,
            onPeriodClick = onPeriodClick,
            onBackPress = onBackPress,
        )
        Spacer(modifier = Modifier.padding(8.dp))

        if (amount != "0") {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = colorResource(id = R.color.green),
                        shape = RoundedCornerShape(8.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.green),
                    contentColor = colorResource(id = R.color.green),
                    disabledContainerColor = colorResource(id = R.color.green),
                    disabledContentColor = colorResource(id = R.color.green),
                ),
                onClick = { onConfirmClicked() },
            ) {
                Text(
                    text = stringResource(id = R.string.confirm),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun BalanceComponent(modifier: Modifier,balance: String) {
    Column(
        modifier = modifier
            .background(
                color = colorResource(id = R.color.white).copy(0.12f),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.balance).uppercase(),
            style = MaterialTheme.typography.bodySmall.copy(color = Color.White.copy(alpha = 0.56f))
        )
        Text(
            text = "$${balance}",
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }
}

@Composable
fun Keypad(
    onNumberEntered: (String) -> Unit,
    onPeriodClick: () -> Unit,
    onBackPress: () -> Unit,
) {
    val numbers = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "#")

    Column(modifier = Modifier.fillMaxWidth()) {
        numbers.chunked(3).forEach { items ->
            Row(modifier = Modifier.fillMaxWidth()) {
                items.forEach { item ->
                    Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.BottomCenter) {
                        if (item == "#") {
                            Icon(
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .clickable { onBackPress() },
                                painter = painterResource(id = R.drawable.ic_back_press),
                                tint = colorResource(id = R.color.white),
                                contentDescription = "back"
                            )
                        } else {
                            Text(
                                modifier = Modifier
                                    .padding(vertical = 16.dp)
                                    .clickable {
                                        if (item == ".") onPeriodClick()
                                        else onNumberEntered(item)
                                    },
                                text = item,
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun KeypadPreview() {
    MoneyTransferTheme {
        Keypad(
            onNumberEntered = {},
            onBackPress = {},
            onPeriodClick = {}
        )
    }
}