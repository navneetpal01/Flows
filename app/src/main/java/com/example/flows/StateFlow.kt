package com.example.flows

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//TODO --------------------------------------------------------------------------------
private fun Run(){
    GlobalScope.launch {
        val result = producer()
        Log.d("Action", result.value.toString())
    }
}

private fun producer() : StateFlow<Int> {
    val mutableStateFlow = MutableStateFlow(10)
    GlobalScope.launch {
        delay(2000)
        mutableStateFlow.emit(20)
        delay(2000)
        mutableStateFlow.emit(30)
    }
    return mutableStateFlow
}

/**TODO Last value will always be stored in the StateFlow even our collector was started 6 sec late still we got 30
private fun Run(){
GlobalScope.launch {
val result = producer()
delay(6000)
result.collect{
Log.d("Action", "Item - $it")
}
}
}

private fun producer() : Flow<Int>{
val mutableStateFlow = MutableStateFlow(10)
GlobalScope.launch {
delay(2000)
mutableStateFlow.emit(20)
delay(2000)
mutableStateFlow.emit(30)
}
return mutableStateFlow
}
 **/

