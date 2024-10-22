package com.example.moneytransfer.ui.paymentscreen.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PaymentViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PaymentUiState())
    val uiState : StateFlow<PaymentUiState> = _uiState

    fun onAmountEntered(amount : String){
        _uiState.update {
            it.copy(
                amount = if (_uiState.value.amount != "0") {
                    _uiState.value.amount.plus(amount)
                } else amount
            )
        }
    }

    fun onPeriodEntered() {
        if (_uiState.value.amount.contains(".")) return
        else {
            _uiState.update { it.copy(amount = _uiState.value.amount.plus(".")) }
        }
    }

    fun onBackPress() {
        if (_uiState.value.amount != "0") {
            if (_uiState.value.amount.length != 1) {
                _uiState.update { it.copy(amount = (_uiState.value.amount.dropLast(1))) }
            } else _uiState.update { it.copy(amount = "0") }
        }
    }

    fun onConfirmClicked() {

    }
}
data class PaymentUiState(
    val amount : String = "0",
    val balanceAmount : String = "10,000",
)