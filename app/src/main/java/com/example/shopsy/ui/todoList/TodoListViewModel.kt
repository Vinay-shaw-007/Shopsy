package com.example.shopsy.ui.todoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "In development"
    }
    val text: LiveData<String> = _text
}