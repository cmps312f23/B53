package com.example.kotlinbasics

fun main() {
    val text = "Hello World"
    val text2 = "Hello World olol"

    println(text.countVowels())

    val numbers = listOf<Int>(1,2,3,4,5,6,7,8,9,10)
//    println(countVowels(text2))
}



fun List<Int>.filterOdd(): MutableList<Int> {
    val oddNumbers = mutableListOf<Int>()
    for (num in this){
        if(num % 2 !=0)
            oddNumbers.add(num)
    }
    return oddNumbers
}
fun String.countVowels() : Int {
//    val text = "Hello World"
    var count = 0
    var vowels = "aeiou"
    for (c in this){
        if(c in vowels)
            count++
    }
    return count
}