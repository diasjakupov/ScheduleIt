package com.example.scheduleit.ui.state

sealed class CreateDialogUIState{
    object Loading:CreateDialogUIState()
    class Error(errorMessage: String):CreateDialogUIState()
    object Success:CreateDialogUIState()
}
