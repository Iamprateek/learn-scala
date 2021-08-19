# Expression

`Expression` can be said as  a unit of code that returns a value after it has been executed.

`"Hello"` is a simple expression

`"H" + "ell" + "o"` is an expression that returns string after concatenating 

`1 :: Nil`  returns a list, List(1)


### Expression Block
One or more lines of code can be considered a an expression if they are put inside curly braces ({ and }). This is known as an expression block.

```
val profit = {
    val cost_price = 100
    val selling_price = 120
    
    selling_price - cost_price
}
```
In a block there can be multiple number of expressions. 
The last return value of expression in the block will be returned from the block. 


### Statement 
`Statement` is just an expression that does not return any meaningful value. Statements have return type of **Unit** `()`.

```
val x = 1

println(x)
```
value definition is a statement because it doesn’t return anything

println returns `Unit`. 

commonly used to modify existing data or make changes outside the scope of the application (e.g., writing to the console, updating a database, connecting to an external server.

They are also called `side effects` in terms of functional programming. 


### Control Structures

### IF Expression 

`if (<Boolean expression>) <expression>`

`Boolean expression` is an expression that will return `Boolean` value i.e. either `true` or `false`.

Some examples :

```
val x = if(134%2 == 0 ) "Is an Even Number"
```
Here, both x  recevies a value from `if` expression. 
For `x` , the value should be a string and we can print the value using `println(x)`.

But if we assign `x` to another String val like `val z:String = x`, it gives error.
```
type mismatch;
 found   : Any
 required: String
```
Also, `y` when printed `println(y)` returns `Unit`. 

The type of the result of `x` value in this example is unspecified so the compiler used type inference to determine the most appropriate type. Either a String or Unit could have been returned, so the compiler chose the root class Any. This is the one class common to both String (which extends AnyRef) and to Unit (which extends AnyVal)

### IF..Else Expression

```
if (<Boolean expression>) <expression>
else <expression>
```

Here too, Whatever type `if` and `else` returns , type inference will be done to identify the appropriate type. 

```
val a = 8; val b = 10 ;
val max_value = if( a > b ) a else b
```

`max_value` here will be of type `Int` as both `if` and `else` expression will always return `Int`.

### Match Expressions

`match` is similar to `switch` statements that we see in other languages, where a single input item is evaluated and the first pattern that is “matched” is executed and its value returned. But it it quite powerful in scala.

Simple match expression will be like : 
```
<expression> match {
  case <pattern> => <expression>
  [case ...]
}
```

The features it provides makes it preferable over `if..else` expresison.

```
val a = 8; val b = 10 ;
val max_value_match =  a > b  match {
  case true => a
  case false => b
}
```


***Pattern Alternatives*** prevents code duplication by reusing the same case block for multiple patterns.
It looks like this 
```
case <pattern 1> | <pattern 2> .. => <one or more expressions>
```

One example cane be finding whether the day is weekday or weekend. 

```
val day:String = 'MON'

val kindOfDay = day match {
  case "MON" | "TUE" | "WED" | "THU" | "FRI" => "weekday"
  case "SAT" | "SUN" => "weekend"
} // returns weekday
```

Its necessary that what you are matching shall be present as a pattern in `case`, otherwise `scala.MatchError` will be raised. 

This will not work.
```
"Hello" match {case "Bye" => "GoodBye!" }
```

To prevent errors from disrupting your match expression, use a wildcard match-all pattern or else add enough patterns to cover all possible inputs.

2 kinds of wildcard patterns you can use in a match expression:
* value binding 
* Wildcard (aka “underscore”) operators.

**Value binding**
The input to a match expression is bound to a local value, which can then be used in the body of the case block. 
```
case <identifier> => <one or more expressions>
```

```
val anotherDay:String = 'REST'

val anotherKindOfDay = day match {
  case "MON" | "TUE" | "WED" | "THU" | "FRI" => "weekday"
  case "SAT" | "SUN" => "weekend"
  case unknown => s" ${unknown}day."
} 
```
Here, `unknown` is an identifier in case that picks any leftover value not picked up by previous case statements. 

**Wildcard Operator Pattern**

```
case _ => <one or more expressions>
```

The wildcard cannot be accessed on the right side of the arrow, unlike with value binding.

```
val againAnotherDay:String = 'HOLI'

val againAnotherKindOfDay = day match {
  case "MON" | "TUE" | "WED" | "THU" | "FRI" => "weekday"
  case "SAT" | "SUN" => "weekend"
  case _ =>  "unknown day."
} 
```

There is more to it and will be discussed later.


### While and Do..While Loops

A `while` loop statement is executed repeatedly until the condition is false.

``` 
var sid = 5 ;
while(sid < 15 ) {
 println("Student Id is:" +sid)
 sid = sid + 1
}
 ```

The `do while` loop statements executes at least once and then the while loop is executed till the condition is true.

``` 
var sid = 4
do {
   println("Student Id is:"+sid)
   sid = sid + 1
}while(sid < 10)
```

### For Loop

For loop is a repetitive structure which allows us to execute a block of code multiple times. 

``` 
val ints = List(1, 2, 3, 4, 5)

for (i <- ints) { println(s"Plain for loop : ${i}")}
```
The code i <- ints is referred to as a generator, and the code in curly braces is the body of the loop. 

Multiple ranges can be specified using semicolon (;) as the separator and the loop iterates for all possible combinations.
``` 
var id = 0;
var  marks = 60;
     
for( id <- 1 to 2; marks <- 70 to 72){
    println( "Student Id is : " + id );
    println( "Marks is : " + marks );
}
```


### for loop with Filters

Some elements can be filtered in the for loop using if statements.

```
var id = 0;
val listId = List(4,5,6,7,8,9,10,11,12);
for(id <- listId if id > 6; if id !=11 ) {
println("Id:"+id);
}
```


### for loop with yield:

The return values can be stored in a variable or can be returned through a function. To do this use the keyword yield as

``` 
var id =0;
val Listid = List(4,5,6,7,8,10);
var result = for{ id <- Listid
           if  id <9; if id !=7
}yield id

for(id <- result) {
 println("Student Id:"+id);
}
```

## Expression Grammar 
``` 
Expr         ::=  (Bindings | id | `_') `=>' Expr
               |  Expr1
Expr1        ::=  `if' `(' Expr `)' {nl} Expr [[semi] `else' Expr]
               |  `while' `(' Expr `)' {nl} Expr
               |  `try' (`{' Block `}' | Expr) [`catch' `{' CaseClauses `}'] [`finally' Expr]
               |  `do' Expr [semi] `while' `(' Expr ')'
               |  `for' (`(' Enumerators `)' | `{' Enumerators `}') {nl} [`yield'] Expr
               |  `throw' Expr
               |  `return' [Expr]
               |  [SimpleExpr `.'] id `=' Expr
               |  SimpleExpr1 ArgumentExprs `=' Expr
               |  PostfixExpr
               |  PostfixExpr Ascription
               |  PostfixExpr `match' `{' CaseClauses `}'
PostfixExpr  ::=  InfixExpr [id [nl]]
InfixExpr    ::=  PrefixExpr
               |  InfixExpr id [nl] InfixExpr
PrefixExpr   ::=  [`-' | `+' | `~' | `!'] SimpleExpr
SimpleExpr   ::=  `new' (ClassTemplate | TemplateBody)
               |  BlockExpr
               |  SimpleExpr1 [`_']
SimpleExpr1  ::=  Literal
               |  Path
               |  `_'
               |  `(' [Exprs] `)'
               |  SimpleExpr `.' id s
               |  SimpleExpr TypeArgs
               |  SimpleExpr1 ArgumentExprs
               |  XmlExpr
Exprs        ::=  Expr {`,' Expr}
BlockExpr    ::=  ‘{’ CaseClauses ‘}’
               |  ‘{’ Block ‘}’
Block        ::=  BlockStat {semi BlockStat} [ResultExpr]
ResultExpr   ::=  Expr1
               |  (Bindings | ([`implicit'] id | `_') `:' CompoundType) `=>' Block
Ascription   ::=  `:' InfixType
               |  `:' Annotation {Annotation}
               |  `:' `_' `*'
```

### Exercises

* Write simple expression to add 2 Ints
* Write statement expression 
* Write expression block to square a number
* Write if-else expression find a number is odd or even
* Write match expression find a number is odd or even
* Write for and while loop statements to print numbers from 1 to 5
* Write expression using loop which returns a list from 1 to 5 
