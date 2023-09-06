fun main(args: Array<String>) {
    val student = Student("Roaa" , 19)
//    student.name = "Roaa"
//    student.age = 19
    println(student.name)
    println(student.age)
    println(student)

    val names = "@123123abcddsalkfjasdk8900"

    print("The number of digits are ${names.filter { it.isLetterOrDigit() }}")
}

data class Student( var name : String = "" ,   var age : Int = 0)

//
//class Person{
//    String name;
//    int age;
//    public Person(String name , int age){
//        this.name = name
//    }
//}


////functions
////
////fun display(age : Int ,  name : String){
////    println("Your name is $name and your age is $age")
////}
////
////fun add2(x:Int , y:Int):Int {
////    return x + y
////}
////
////val  add3 = fun(x:Int , y:Int) =  x + y