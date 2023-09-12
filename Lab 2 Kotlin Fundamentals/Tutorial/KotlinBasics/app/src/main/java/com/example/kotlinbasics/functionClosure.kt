package com.example.kotlinbasics

//Function closure is a function that can access variables of outer function
fun main() {
    superFunction { number: Int -> number % 2 == 0}
    superFunction(isOdd)
}

fun superFunction(predicate: (Int) -> Boolean) {
    for (i in 1..100) {
        if (predicate(i))
            print("$i ")
    }
    println()
}

fun isEven(number: Int): Boolean {
    return number % 2 == 0
}

val y = 20

//lambda expression
val x = { number: Int -> number + y }

val isEven = fun(number: Int) = number % 2 == 0


val isOdd = fun(number: Int) = number % 2 != 0
fun isPositive(number: Int) = number > 0