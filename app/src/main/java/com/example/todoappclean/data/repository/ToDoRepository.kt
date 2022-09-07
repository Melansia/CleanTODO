package com.example.todoappclean.data.repository

import androidx.lifecycle.LiveData
import com.example.todoappclean.data.ToDoDao
import com.example.todoappclean.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }
}