package com.example.scheduleit.ui.state

sealed class UIState<T>{
    class Loading<T> : UIState<T>()
    class Error<T>(val errorMessage: String):UIState<T>()
    class Success<T>(val value: T) : UIState<T>()
}
