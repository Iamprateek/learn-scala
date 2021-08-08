object Fingers extends Enumeration {
  type Finger = Value

  val Thumb, Index, Middle, Ring, Little = Value
  /*
  To override Name and Id for the values, comment out the above and uncomment below vals
   */
//  val Thumb = Value(1, "Thumb Finger")
//  val Index = Value(2, "Pointing Finger")
//  val Middle = Value(3, "The Middle Finger")
//  val Ring = Value(4, "Finger With The Ring")
//  val Little = Value(5, "Shorty Finger")
}

object Enumerations extends App{
  import Fingers._

  def isShortest(finger: Finger) = finger == Little

  println(isShortest(Middle))
  def twoLongest() =
    Fingers.values.toList.filter(finger => finger == Middle || finger == Index)
  println(twoLongest())

  println(s"for the Little finger, Identifier: ${Little.id} and name : ${Little.toString}")


}
