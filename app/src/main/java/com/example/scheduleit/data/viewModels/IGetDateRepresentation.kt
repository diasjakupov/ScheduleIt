package com.example.scheduleit.data.viewModels

interface IGetDateRepresentation {
    fun getDateRepresentation(format:String, date: Long): String
}