# Enumeration

An enumeration refers to a group of named constants. Scala provides an abstract class called Enumeration to create and retrieve enumerations. 


``` 
object Fingers extends Enumeration {
  type Finger = Value

  val Thumb, Index, Middle, Ring, Little = Value
}
```

The Enumeration class provides a type called Value to represent each of the enumeration values. It also provides a nifty protected method by the same name to create and initialize the values.

### Retrieving the Values

``` 
def isShortest(finger: Finger) = finger == Little
```

### Values method 
to iterate over values in Enumeration
def twoLongest() =
Fingers.values.toList.filter(finger => finger == Middle || finger == Index)

### Overriding Identifier and Name
Each enumeration value has an identifier and a name. By default, each value is assigned an identifier starting from 0 and a name that is the same as the value itself.
``` 
  println(s" Identifier: ${Little.id},  Name : ${Little.toString}")
```

The Value method in the Enumeration class allows us to change the defaults
```  
val Thumb = Value(1, "Thumb Finger")
val Index = Value(2, "Pointing Finger")
val Middle = Value(3, "The Middle Finger")
val Ring = Value(4, "Finger With The Ring")
val Little = Value(5, "Shorty Finger")
```
