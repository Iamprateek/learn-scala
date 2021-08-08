
object CaseClass extends App{

  case class Person(name:String, age:Int)

  /* Creating Object */
  val person = Person("Fred", 40) // or new Person("Mukesh", 40)

  /* Calling Getter */
  println(person.name)

  /* Inbuilt toString */
  println(person) // or person.toString also works

  /* Equals and Hashcode */
  val person1 = Person("Lilly", 21)
  val person2 = Person("Lilly", 21)

  println("person1 and person2 equal ? Ans : ${ person1 == person2}")
  println(s"Hashcodes of person1 : $person1.# and person2 : $person2")

  /* Copy */

  val person3 = Person("Dolores", 21)
  val person4 = person3.copy(age = 31)
  val person5 = person3.copy()

  println(s"person3 = ${person3}")
  println(s"person4 = ${person4}")
  println(s"person5 = ${person5}")

  /* apply and unapply */
  // apply
  val person6 = Person.apply("Leona", 15) // val person9 = Person("Leona", 15)

  // unapply
  println( Person.unapply(person6))

  // extraction of values
  val Person(name, age) = person6
  println(s"Extracted ${name} and ${age} from ${person6}")

}