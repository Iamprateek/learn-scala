object Expression extends App{

  /* Simple Expressions */
  println("Hello") // "Hello" is an expression
  println("H" + "ell" + "o") // "H" + "ell" + "o" is an expression

  /* Block Expression */
  val profit = {
    val cost_price = 100
    val selling_price = 120

    selling_price - cost_price // This is the last expression so it will be returned from the block
  }

  println(s"Profit : $profit")

  /* Side Effects */
  // Also statements
  val res = {
    val x = 1 // This returns Unit
  }
  println(println(res)) // println is also gives a side effect

  /*
   Control Structures
   * if
   * match
   * for
   * while
   * do... while
   */

  /* If Expression */
  val x = if(134%2 == 0 ) "Is an Even Number" // returns String value
  /*
  val z:String = x // <- this will not work, x is og type Any. Uncomment to try it.
  */

  /* if... else Expression */

  val a = 8; val b = 10 ;
  val max_value = if( a > b ) a else b // both if and else returns value of type Int so max_value is of Int.

  /* Match Expression */
  val max_value_match =  a > b  match {
    // alternate way of if else to find max value.
    case true => a
    case false => b
  }

  /* While Loop */
  var sid = 5
  while(sid < 15 ) {
    println("(while) Student Id is:" +sid)
    sid = sid + 1
  }

  /* do while */
  sid = 4
  do {
    println("(do while) Student Id is:"+sid);
    sid = sid + 1
  }while(sid < 10)

  /* for loop */
  val ints = List(1, 2, 3, 4, 5)
  for (i <- ints) { println(s"Plain for loop : ${i}")} // side effects

  // Multiple ranges
  var id = 0;
  var  marks = 60;

  for( id <- 1 to 2; marks <- 70 to 72){ // side effects
    println( "(for with multiple range) Student Id is : " + id );
    println( "(for with multiple range) Marks is : " + marks );
  }

  // for loop with filters
  id = 0;
  val listId = List(4,5,6,7,8,9,10,11,12);
  for(id <- listId if id > 6; if id !=11 ) { // side effects
    println("(for with filters) Id:"+id);
  }

  // for .. yield
  var result =
    for{
      id <- listId
      if  id <9; if id !=7
    }yield id // returns a iterable collection of result

  for(id <- result) {
    println("(for .. yield ) Student Id:"+id);
  }
}
