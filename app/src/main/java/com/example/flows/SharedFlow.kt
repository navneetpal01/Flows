package com.example.flows

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

//TODO Shared flow is a Hot Flow - All the consumers will get the same data
private fun Run(){
    GlobalScope.launch(Dispatchers.Main) {
        val result = provider()
        result.collect {
            Log.d("Value", "$it fetched from the server")
        }
    }
    GlobalScope.launch(Dispatchers.Main) {
        val result = provider()
        delay(2500)
        result.collect {
            Log.d("Value", "$it fetched from the server")
        }
    }
}


private fun provider(): Flow<Int> {
    val mutableSharedFlow = MutableSharedFlow<Int>(replay = 1) //Replay here means that the amount of lost values it can get
    GlobalScope.launch {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach {
            mutableSharedFlow.emit(it)
            delay(1000)
        }
    }
    return mutableSharedFlow
}
