object Functions extends App{
  def hi = "Hi"  // Input less function
  def hiAgain(): String = "Hi" // empty parameter function
  def multiplier(x: Int, y: Int): Int = { x * y }
  def printInt(d: Int):Unit = println(f"Got value $d") // side effect , returning Unit

  println(hi)
  println(hiAgain()) // can use hiAgain without empty params
  println(multiplier(2,3))
  printInt(2)

  /*  Nested Function   */
  def maxOfThree(a: Int, b: Int, c: Int) = {
    def max(x: Int, y: Int) = if (x > y) x else y // nested function
    max(a, max(b, c))
  }

  println(maxOfThree(3,2,9))

  /* Calling Functions with Named Parameters */
  def greet(prefix: String, name: String) = s"$prefix $name"
  println(s"calling with names parameters: ${greet(name = "Brown", prefix = " Mr")}")

  /* Params with Default Values :
    changing greet method with having default value
    * def greet(prefix: String = "", name: String) = s"$prefix $name"
    or even better
    * def greet(name: String, prefix: String = "") = s"$prefix $name"
   */

  /* Varargs */
  def sum(items: Int*): Int = {
    var total = 0
    for (i <- items) total += i
    total
  }
  println(sum(1,2,3,4))
  print(sum(0,7,8))

  /* Type Parameter */
  def identity[A](a: A): A = a
  val s: String = identity[String]("Hello")
  val i:Int = identity[Int](1)

//  invalid_function(0) // this is defined outside the scope of the object below. will result in error.

  /* Anonymous Function */
  val anonymous_incrementer = (number: Int) => {
    println("We are in anonymous function")
    number + 1
  }

  println(anonymous_incrementer(2))

  /* Methods(def) vs Functions call */
  def randomIntMethod(): Int = scala.util.Random.nextInt
  val randomIntFunction = () => scala.util.Random.nextInt

  println(randomIntMethod) // random number is generated
  println(randomIntFunction) // function reference will be printed
  println(randomIntFunction()) // random number is generated

  val randomFunction = randomIntMethod _ // converting a method type to function type

}

/* // Uncomment to know why.
def invalid_function(a:Int) = {
  println("invalid outside a class/ object/ trait/ method")
}
*/

