

object CompanionObjectDemo {
  def main(args: Array[String]): Unit = {
    println(Person1.name)
    println(Person1.printName)
    val obj1=new Person1(100)
    println(obj1.id)
  }
  
  }

class Person1(var id:Int){
  
}
//companion object -> Static members
object Person1{
  println("Inside companion obj")
  val name="Person1"
  def printName={
    println("inside Companion ObjectName Method"+name)
  }
  
}