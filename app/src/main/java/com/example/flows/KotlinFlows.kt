package com.example.flows

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

//TODO Example 10 --------------------------------------------------------- Error and Exception
fun run(){
    GlobalScope.launch(Dispatchers.Main){
        try {
            produce()
                .collect{
                    Log.d("Value", "$it collected from the server")
                }
        }catch (e : Exception){
            Log.d("Value", e.message.toString())
        }
    }
}

fun produce() : Flow<Int> {
    return flow {
        val list0fNum = listOf(1, 2, 3, 4, 5)
        list0fNum.forEach { num ->
            delay(1000)
            Log.d("Value", "$num send from the server")
            emit(num)
            throw Exception("Error in emitter")
        }
    }.catch {
        Log.d("Error", "${it.message}")
    }
}
/**TODO Example 9 --------------------------------------------------------- Error and Exception
fun run(){
GlobalScope.launch(Dispatchers.Main){
try {
produce()
.collect{
Log.d("Value", "$it collected from the server")
}
}catch (e : Exception){
Log.d("Value", e.message.toString())
}
}
}

fun produce() : Flow<Int>{
return flow {
val list0fNum = listOf(1, 2, 3, 4, 5)
list0fNum.forEach { num ->
delay(1000)
Log.d("Value", "$num send from the server")
emit(num)
throw Exception("Error in Emitter")
}
}
}
 **/



/**TODO Example 8 --------------------------------------------------------- Running on a different thread and collecting on a different thread
fun run(){
GlobalScope.launch(Dispatchers.Main){
produce()
.map {
delay(1000)
it * 2
Log.d("Value", "map is running on the ${Thread.currentThread().name}")
}
.flowOn(Dispatchers.IO) //Changes the thread of the function used before
.filter {
delay(500)
it > 10
}
.flowOn(Dispatchers.Main)
//Here the collecting will still be called on the main thread but the function which emits flows will be called on the IO thread
.collect{
Log.d("Value", "collecter is running on ${Thread.currentThread().name}")
}
}
}

fun produce() : Flow<Int>{
return flow {
val list0fNum = listOf(1, 2, 3, 4, 5)
list0fNum.forEach { num ->
delay(1000)
emit(num)
Log.d("Value", "emitter is running on ${Thread.currentThread().name}")
}
}
}
 **/



/**TODO Example 7 ---------------------------------------------------------
fun run(){
GlobalScope.launch(Dispatchers.Main){
val time = measureTimeMillis {
produce()
.buffer(3)
.collect {
//delay in the collection as our item is produced in one second and is consumed in 1.5 sec
delay(1500)
Log.d("Value", "$it fetched from the server")
}
}
Log.d("Active", "$time took")
}
}

fun produce() : Flow<Int>{
return flow {
val list0fNum = listOf(1,2,3,4,5)
list0fNum.forEach {num ->
delay(1000)
emit(num)
}
}
}
 **/


/**TODO Example 7 -----------------------------------------------------------
//Here we are formatting our note from upper case to lower case
fun run(){
GlobalScope.launch {
getNotes()
.map {
FormattedNote(id = it.id,isActive = it.isActive, title = it.title.uppercase(), description = it.description)
}
.filter {
it.isActive
}
.collect{
Log.d("Value", it.toString())
}
}
}


private fun getNotes() : Flow<Note>{
val list = listOf(
Note(
1 ,true,"First","First Description"
),
Note(
2 , false , "Second", "Second Description"
),
Note(
3 , true , "Third","Third Description"
)
)
return list.asFlow()
}

data class Note(
val id : Int,
val isActive : Boolean,
val title : String,
val description : String
)

data class FormattedNote(
val id : Int,
val isActive : Boolean,
val  title : String,
val description: String
)
 **/


/**TODO Example 6 -----------------------------------------------------------
fun run(){
GlobalScope.launch(Dispatchers.Main){
produce()
.map {
it * 2
}
.filter {
it > 8
}
.collect{
Log.d("Value", "$it fetched from the serevr")
}
}
}

fun produce() : Flow<Int> {
return flow {
val list0fNum = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
list0fNum.forEach { num ->
delay(1000)
emit(num)
}
}
}
 **/

/**TODO Example 6 -----------------------------------------------------------
fun run(){
GlobalScope.launch(Dispatchers.Main){
//Returns all the emitted flow in the List
val first = produce().toList()
Log.d("Value", "$first fetched from the server")
}
}

fun produce() : Flow<Int>{
return flow {
val list0fNum = listOf(1,2,3,4,5,6,7,8,9,10)
list0fNum.forEach {num ->
delay(1000)
emit(num)
}
}
}
 **/



/**TODO Example 5 -----------------------------------------------------------
fun run(){
GlobalScope.launch(Dispatchers.Main){
//Only collects the first value emitted
val first = produce().first()
Log.d("Value", "$first fetched from the server")
}
}

fun produce() : Flow<Int>{
return flow {
val list0fNum = listOf(1,2,3,4,5,6,7,8,9,10)
list0fNum.forEach {num ->
delay(1000)
emit(num)
}
}
}
 **/

/**TODO Example 4 -----------------------------------------------------------
fun run(){
GlobalScope.launch(Dispatchers.Main){
produce()
.onStart {
//This value don't exist in the flow still we can emit it from here
emit(-1)
Log.d("system", "Starting out")
}
.onCompletion {
Log.d("system", "Completed")
}
.onEach {
Log.d("system", "About to Emit - $it")
}
.collect{
Log.d("System", "$it fetched from the server")
}
}
}

fun produce() : Flow<Int>{
return flow {
val list0fNum = listOf(1,2,3,4,5,6,7,8,9,10)
list0fNum.forEach {num ->
delay(1000)
emit(num)
}
}
}
 **/



/**TODO Example 3 -----------------------------------------------------------
fun run() {
GlobalScope.launch(Dispatchers.Main){
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
