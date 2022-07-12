package com.example.scheduleit.data.viewModels

import com.example.scheduleit.data.models.Note

interface IUpdateTaskViewModel {
    fun update(id: Int, status: Boolean,onTaskUpdated: suspend () -> Unit)
    fun setStatusCompletion(id:Int, status:Boolean,onTaskUpdated: suspend () -> Unit)
    fun setTaskData(task: Note)
    fun setDefaultNotifDelay()
}
