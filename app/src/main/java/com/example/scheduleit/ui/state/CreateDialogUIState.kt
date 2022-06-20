package com.example.scheduleit.ui.state

sealed class CreateDialogUIState{
    object Loading:CreateDialogUIState()
    object Error:CreateDialogUIState()
    object Success:CreateDialogUIState()
}
