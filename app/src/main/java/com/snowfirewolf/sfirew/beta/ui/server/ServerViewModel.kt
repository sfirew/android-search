package com.snowfirewolf.sfirew.beta.ui.server

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ServerViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "這是伺服器段落"
    }
    val text: LiveData<String> = _text
}