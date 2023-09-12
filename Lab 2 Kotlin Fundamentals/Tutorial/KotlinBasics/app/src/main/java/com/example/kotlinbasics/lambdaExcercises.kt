package com.example.kotlinbasics

fun main() {
    val numbers = listOf(1, 32, 3, 64, 5, 86, 7, 8, 9, 10)
//    numbers.forEach { num: Int -> println(num) }
    numbers.forEach { print(it) }

    val sum2 = numbers.reduce { acc, next -> acc + next }
    val sum = numbers.fold(0) { acc, next -> acc + next }
    val doubled = numbers.map { if (it % 2 == 0) "Pass" else "Fail" }
    val filterEven = numbers.filter { it < 0 }
    val find9 = numbers.find { it == 19 }
    val sortedWith = numbers.sortedWith { x: Int, y: Int -> y - x }
    val builtInSortWithNoPredicate = numbers.sortedDescending()

//     print the output for each
    println("Sum of the list is $sum")
    println("Sum of the list is $sum2")
    println("Doubled list is $doubled")
    println("Even numbers are $filterEven")
    println("19 is $find9")
    println("Sorted list is $sortedWith")
    println("Sorted list is $builtInSortWithNoPredicate")


//     acc = 1 curr 2
//     acc = 3 curr 3
//     acc = 6 curr 4
//     acc = 10 curr 5
//     acc = 15 curr 6
//     acc = 21 curr 7


}