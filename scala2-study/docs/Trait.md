# Traits

`Traits` are reusable components that can be used to extend the behavior of classes. They are similar to interfaces and contain both abstract and concrete methods and properties.

A minimal `trait` is simply the keyword trait and an identifier:
```
trait Example
```

Traits become especially useful as generic types and with abstract methods.

```
trait Iterator[A] {
def hasNext: Boolean
def next(): A
}
```

Use the extends keyword to extend a trait. Then implement any abstract members of the trait using the override keyword. 

``` 
class IntIterator(to: Int) extends Iterator[Int] {
  private var current = 0
  override def hasNext: Boolean = current < to
  override def next(): Int = {
    if (hasNext) {
      val t = current
      current += 1
      t
    } else 0
  }
}


val iterator = new IntIterator(10)
iterator.next()  // returns 0
iterator.next()  // returns 1
```

## Subtyping
Where a given trait is required, a subtype of the trait can be used instead.

```
import scala.collection.mutable.ArrayBuffer

trait Pet {
val name: String
}

class Cat(val name: String) extends Pet
class Dog(val name: String) extends Pet

val dog = new Dog("Harry")
val cat = new Cat("Sally")

val animals = ArrayBuffer.empty[Pet]
animals.append(dog)
animals.append(cat)
animals.foreach(pet => println(pet.name))
```

## Abstract classes can extend a trait

```
trait Animal {
  def foo() {
    println("This is a type of Animal")
  }
}

abstract class Bird extends Animal {}
``` 
## Mixins
`Mixins` are traits which are used to compose a class.
`Mixins`, however, aren't inheritance – it's actually more similar to dynamically adding a set of methods into an object. Whereas inheritance says "This thing is a kind of another thing", mixins say, "This object has some traits of this other thing."
```
class Number(val num : Int) extends Ordered[Number] {
  override def compare(that : Number) = this.num - that.num
}

trait Half extends Number {
  def half() = num / 2
}

trait Increment extends Number {
  def increment() = num + 1
}

class EvenNumber(val evenNum : Int) extends Number(evenNum) with Half

class OddNumber(val oddNum : Int) extends Number(oddNum) with Increment
```

In the example above, classes EvenNumber and OddNumber share is a relationship with Number but EvenNumber does not have "is a" relation with Half neither OddNumber share "is a" relation with Increment.

Another important point is even though class Number uses extends Ordered syntax, it means that Number has an implicit is a relationship with superclass of Ordered ie Any

## Stackable modification
Class/ Trait can extend more than one trait at a time and if all of those trait have same method and we need to override that method then how we will get to know that which overridden method will be invoked. In such a situation how can we resolve that thing?


``` 
trait WithLegs {
  def legs: String = "Base"
}

trait TwoLegged extends WithLegs {
  override def legs: String = "Two -> " + super.legs
}

trait FourLegged extends WithLegs {
  override def legs: String = "Four -> " + super.legs
}

trait SixLegged extends TwoLegged {
  override def legs: String = "Six -> " + super.legs
}

class ClassB extends FourLegged with TwoLegged {
  override def legs = "B -> " + super.legs
}
```

## Linearization
Trait linearization is a process which comes in picture when ever we mix any number of traits and classes in a single trait.

Scala linearization is a process in which all traits are present in linear hierarchy, by this we can solve the diamond problem . Remember that the syntax to mixin traits is as follows: class A extends B with C with D.

The rules for this process are as follows:
1. Start from the very first class or trait which is extended and write the linearized hierarchy.
2. Take the next trait and write this hierarchy down
   * now remove all those classes and traits which we have already used in our previous linearized hierarchy.
   * add the remaining traits to the bottom of the linearized hierarchy to create the new linearized hierarchy.
3. repeat step 2 for every trait.
4. Place the class itself as the last type extending the linearized hierarchy.

## Sealed Trait 

Sealed provides exhaustive checking for our application. Exhaustive checking allows to check that all members of a sealed trait must be declared in the same file as of the source file. That means that all the possible known members of a trait that must be included are known by the compiler in advance. So this gives us advantage to prevent mistakes in our code. 

``` 
sealed trait X
class A extends X
class B extends X
class C extends X
```

Exhaustive checking is mostly used in type / pattern matching in scala. 

``` 
def obj(item: X) = item match {
   case A => //
   case B => //
}
```
We get a warning `Warning: match may not be exhaustive`

#### Benefits
* Sub-types of a trait are known in advance- Not including any of the sub-type of sealed class C in pattern match would give us warning. Such a warning tells you that there’s a risk your code might produce a Match Error exception because some possible patterns are not handled. The warning points to a potential source of run-time faults, so it is usually a welcome help in getting your program right.
* Sealed traits can only extend in the same source file as of sub-types- In above example, we have another class python in another scala file. Importing the trait geeks from language.scala we would get error message as below.
* Sealed class is also mostly used in enums– Preventing illegal inheritance and using all the sub-type so to avoid exhaustive matching warnings.

