import java.util.Scanner

fun main(args: Array<String>) {
    val sum = add(2, 3)
    println("The sum is $sum")

    val car = Car("Toyota" , 1988)


    print(car)
}

data class Car (var model : String, var year : Int)

fun add(x: Int, y: Int) = x + y


/*




//    val laptops = setOf<String>("Lenovo" , "Lenovo" , "MacBook", "Toshiba","IBM", "Huawei")
////    laptops.add(0,"IBM")
//    println(  laptops)

//    println("Please enter the day number")
//    val day = readlnOrNull()?.toInt()
//    val numberOfTheDay = when (day) {
//        1 -> "Sunday"
//        2 -> "Monday"
//        3 -> "Tuesday"
//        in 4..7 -> "Wednesday"
//
//
//        else -> "Please enter numbers from 1 to 7"
//    }
//    println("The day of the week number is $numberOfTheDay")

//    println("Please enter your name")
////    val name = readlnOrNull()
//    val scan = Scanner(System.`in`)
//    val name = scan.nextLine()
//
//    var lastname: String? = null
//    if(lastname !=null){
//        print(lastname.length)
//    }
////    lastname = "Hassen"
//    var age: Integer? = null
//
//    print(lastname?.length)
//
//    println(
//        """
//       My Name is $name"
//       This is a nice course
//       Please try to come on time
//    """.trimIndent()
//    )
 */