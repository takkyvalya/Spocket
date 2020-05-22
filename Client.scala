package sockets

import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner


object Client extends App {
  val srv: Socket = new Socket("localhost", 1337)
  val toServer = new PrintWriter(srv.getOutputStream, true)
  val fromServer = new Scanner(srv.getInputStream)

  println(fromServer.nextLine())
  //greet
  val name = scala.io.StdIn.readLine() //
  toServer.println(name) //send name

  var motion = ""
  var a=0

  while (a!=5){
    println(fromServer.nextLine())
    motion = scala.io.StdIn.readLine()
    toServer.println(motion)
    println(fromServer.nextLine())
    var b=fromServer.nextLine()
    println(b)
    println(fromServer.nextLine())
    if(b != "Dead heat") {a +=1}
  }
  println(fromServer.nextLine())

}
