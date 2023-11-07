package com.cmps312.bankingapp.intro

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        for (i in 10..50000)
            launch {
                delay((100..5000L).random())
                print("$i - ")
            }
    }
}


//
//suspend fun world() = coroutineScope {
//    var x = 0
//    val job = launch {
//        delay(1000L)
//        print("Hello \n")
//        x = 10
//    }
//    job.join()
//    print(100/x)
//}