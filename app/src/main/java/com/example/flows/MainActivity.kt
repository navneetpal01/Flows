package com.example.flows

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}


/**TODO Example 3 -----------------------------------------------------------
fun run() {
    GlobalScope.launch {
        val data: Flow<Int> = producer()
        data.collect {
            Log.d("Value-1", "$it fetched from the server")
        }
    }
    GlobalScope.launch {
        val data: Flow<Int> = producer()
        data.collect {
            Log.d("Value-2", "$it fetched from the server")
        }
    }

}


fun producer(): Flow<Int> {
    return flow {
        val listOf = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        listOf.forEach {
            delay(1000)
            emit(it)
        }
    }
}
**/

/** TODO Example 2 -----------------------------------------------------------
fun run() {
    val job = GlobalScope.launch {
    val data: Flow<Int> = producer()
    data.collect {
    Log.d("Value", "$it fetched from the server")
    }
}

//Cause the Coroutine which was consuming it got cancelled so the flow is automatically cancelled
GlobalScope.launch {
    delay(1000)
    job.cancel()
    }
}


fun producer(): Flow<Int> {
    return flow {
    val listOf = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    listOf.forEach {
    delay(1000)
    emit(it)
    }
  }
} **/



/** TODO Example 1 -----------------------------------------------------------
fun run() {
     GlobalScope.launch {
     val data: Flow<Int> = producer()
     data.collect {
     Log.d("Value", "$it fetched from the server")
     }
    }
}

fun producer(): Flow<Int> {
     return flow {
     val listOf = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
     listOf.forEach {
     delay(1000)
     emit(it)
     }
   }
}
 **/
