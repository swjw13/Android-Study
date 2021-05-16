package com.jw.viewmodelpractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _number = MutableLiveData<Int>()
    val number: LiveData<Int>
        get() = _number

    init{
        _number.value = 0
    }

    fun plus(){
        _number.value = _number.value?.plus(1)
    }

    fun minus(){
        _number.value = _number.value?.minus(1)
    }

}