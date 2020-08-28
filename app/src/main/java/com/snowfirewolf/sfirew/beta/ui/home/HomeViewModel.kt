package com.snowfirewolf.sfirew.beta.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "這是首頁段落"
    }

    val text: LiveData<String> = _text
}