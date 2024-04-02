package com.example.flows

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GlobalScope.launch(Dispatchers.Main) {
            provider()
                .collect {
                    Log.d("Value", "$it fetched from the server")
                }
        }
        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            provider()
                .collect {
                    Log.d("Value", "$it fetched from the server")
                }
        }

    }
}


private fun provider(): Flow<Int> {
    return flow {
        val list = listOf(1, 2, 3, 4, 5)
        list.forEach { num ->
            delay(1000)
            emit(num)
        }
    }
}