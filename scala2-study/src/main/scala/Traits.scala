

object Traits extends App {

  /* Simple Trail */
  trait Example

  /* trait with methods */
  trait Iterator[A] {
    def hasNext: Boolean
    def next(): A
  }

  /* Using Trait */
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
  println("Using Iterator trait ${iterator.next()}")  // returns 0
  println("Using Iterator trait ${iterator.next()}")  // returns 1

  /* Subtyping */

  import scala.collection.mutable.ArrayBuffer

  trait Pet { // trait declaration
    val name: String
  }

  // Using trait in two classes
  // name in constructor overrides name method in Pet
  class Cat(val name: String) extends Pet
  class Dog(val name: String) extends Pet

  val dog = new Dog("Harry")
  val cat = new Cat("Sally")

  val animals = ArrayBuffer.empty[Pet] // creating Array Buffer ot type Pet

  // able to append objects in dog and cat
  animals.append(dog)
  animals.append(cat)

  animals.foreach(pet => println("Subtyping result : ${pet.name}"))

  /* Abstract classes can extend a trait */
  trait Animal {
    def foo() {
      println("This is a type of Animal")
    }
  }

  abstract class Bird extends Animal {}

  /* Mixin */

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


  /* Stackable modification and linearization  */
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

  class ClassB extends FourLegged with TwoLegged { // try experimenting by switching places and adding more traits
    override def legs = "B -> " + super.legs
  }

  val bClass = new ClassB()
  println(bClass.legs) // Guess what can be the answer for this

  /* Sealed Trait Example - Please check SealedTrait.scala to see the signature */
  def checkValue(t:X)={
    t match {
      case a:A => println("Class A")
      case b:B => println("Class B")
      case c:C => println("Class C")
    }
  }

  checkValue(new A())
  //  class D extends X // <-- This will not work.
}
