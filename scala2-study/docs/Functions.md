# Functions

In Scala, `functions` are named, reusable expressions. They may be parameterized and they may return a value, but neither of these features are required. These features are, however, useful for ensuring maximum reusability and composability. 

Benefits may come from following standard functional programming methodology and building pure functions when possible. In functional programming a pure function is one that:

* Has one or more input parameters
* Performs calculations using only the input parameters
* Returns a value
* Always returns the same value for the same input
* Does not use or affect any data outside the function
* Is not affected by any data outside the function

#### Skeleton of a function
`def functionName ([list of parameters]) : [return type]`

```
def hi = "Hi"  // Input less function 
def hiAgain(): String = "Hi" // empty parameter function
def multiplier(x: Int, y: Int): Int = { x * y }  
```

Function that doesn’t have a return value is called a `Procedure`. 
```
def printInt(d: Int) = println(f"Got value $d")
```
Scala compiler will infer the return type of the function to be Unit, the lack of a value.


## Nested Functions
Functions are named, parameterized expression blocks and expression blocks are nestable

``` 
def maxOfThree(a: Int, b: Int, c: Int) = {
  def max(x: Int, y: Int) = if (x > y) x else y
  max(a, max(b, c))
}
```

### Calling Functions with Named Parameters
The convention for calling functions is that the parameters are specified in the order in which they are originally defined. However, in Scala you can call parameters by name, making it possible to specify them out of order.

``` 
def greet(prefix: String, name: String) = s"$prefix $name"
val greeting2 = greet(name = "Brown", prefix = "Mr") // calling with names parameters 
```

### Parameters with Default Values 
In Scala, we can provide default values for any parameter, making the use of that parameter optional for callers.

``` 
def greet(prefix: String = "", name: String) = s"$prefix$name"

val greeting1 = greet(name = "Paul") // prefix will be empty by default
```

With above signature for greet we cannot call without named parameter. 
`greet("Paul")` does not work.

By reorganizing the function so that the required parameter comes first, we can call it without using a parameter name. 

``` 
def greet(name: String, prefix: String = "") = s"$prefix$name" 
greet("Walker")
```

### Vararg Parameters 
you can define a function with a variable number of input arguments. The vararg parameter cannot be followed by a nonvararg parameter because there would be no way to distinguish them. Inside the function, the vararg parameter, implemented as a collection, can be used as an iterator in for loops.

``` 
def sum(items: Int*): Int = {
    var total = 0
    for (i <- items) total += i
    total
 }
```

### Type Parameters

In Scala, to complement the value parameters, you can also pass type parameters, which dictate the types used for the value parameters or for the return value. 

``` 
def identity[A](a: A): A = a 
val s: String = identity[String]("Hello")
// can be written as val s:String = identity("A String")
val i:Int = identity[Int](1)
// can be written as val i:Int = identity(1)
```

The functions shall be written in scope of class/ object , else it will not compile.

###### Please Note : The functions that are declared with def are method type and can be overridden. Scala doesn’t allow us to define an anonymous method.

### Anonymous Function
An anonymous function is one without a name or an explicit handle. This can be useful when we need to provide a piece of code or action as a parameter to another function.

Anonymous function to increment a number :
`(number: Int) => number + 1`

###### Please Note : This is a kind of function type

### Method types and Function type 

A `Function Type` is (roughly) a type of the form `(T1, ..., Tn) => U`, which is a shorthand for the trait FunctionN in the standard library. Anonymous Functions and Method Values have function types, and function types can be used as part of value, variable and function declarations and definitions. In fact, it can be part of a method type.


A `Method Type` is a non-value type. That means there is no value - no object, no instance - with a method type. As mentioned above, a Method Value actually has a Function Type. A method type is a def declaration - everything about a def except its body.



###### Whenever we write a method name, the compiler will consider it as a call to this method. In contrast, a function name without parentheses is just a variable that holds a reference to a function object:
```
def randomIntMethod(): Int = scala.util.Random.nextInt
val randomIntFunction = () => scala.util.Random.nextInt

println(randomIntMethod) // random number is generated 
println(randomIntFunction) // function reference will be printed
println(randomIntFunction()) // random number is generated
```

if we need to convert a method into a function, we can use the underscore:
`val randomFunction = randomIntMethod _`



## Exercises 

1. Write a function that computes the area of a circle given its radius.
2. Write a function that computes the area of a circle given its circumference. Solve using Nested function. 
3. Write a function that that that takes integers , that can vary in count and generate xor value of all of those. Write function with and without using a collection. 
4. Write a function called multiplier that takes two parameters, 1 as Int, and other of Function type`(Int)=>Int`. This multiplies the Int value with result of the function. 
    * Create 2 anonymous functions incremeter( which increments its value by 1) and squared(which gives square of number). 
    Now the problem will be how we can input another Integer for the function multiplier. The return type of multiplier has not been provided. So try to create your own way. 
