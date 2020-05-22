package sockets

import java.io.PrintWriter
import java.net.{ServerSocket, Socket}
import java.util.Scanner

//import sockets.Server.{p1motion, p2motion}

object Server extends App{
  val srv:ServerSocket = new ServerSocket(1337)
  println("server started")

  val player1: Socket  = srv.accept();//wait for connection ...
  val toP1 = new PrintWriter(player1.getOutputStream, true)
  val fromP1 = new Scanner(player1.getInputStream)
  toP1.println("Player 1 welcome to game Spock, enter your name:")//greet

  val player2: Socket  = srv.accept();//wait for connection ...
  val toP2 = new PrintWriter(player2.getOutputStream, true)
  val fromP2 = new Scanner(player2.getInputStream)
  toP2.println("Player 2 welcome to game Spock, enter your name:")//greet

  val p1Name = fromP1.nextLine()//receive name
  val p2Name = fromP2.nextLine()//receive name

  println(s"player 1 name: $p1Name")
  println(s"player 2 name: $p2Name")

  def round(pym:String, phm:String ): String = {
    var a = "arg"
    if(pym == "rock") { if(phm == "scissors" || phm=="lizard"){ a = "You win"
    }else { if(phm == "paper" || phm == "spock"){ a = "You LOSE"
        } else {a = "Dead heat"
          }
        }
      } else { if(pym == "paper"){ if(phm == "rock" || phm == "spock"){ a = "You win"
        } else { if(phm == "scissors" || phm =="lizard"){ a = "You LOSE"
          } else { a = "Dead heat"
            }
          }
        } else {if(pym == "scissors"){ if(phm=="lizard" || phm=="paper"){ a = "You win"
          } else {if(phm == "rock" || phm == "spock"){ a = "You LOSE"
            } else { a = "Dead heat"
              }
            }
          } else {if(pym=="lizard"){ if (phm=="paper" || phm=="spock"){ a = "You win"
            } else {if(phm=="rock" || phm=="scissors"){ a = "You LOSE"
              } else { a = "Dead heat"
                }
              }
            } else { if(pym=="spock"){ if (phm=="rock" || phm=="scissors") { a = "You win"
              } else { {if(phm=="lizard" || phm=="paper"){ a = "You LOSE"
                } else  {a = "Dead heat"
                  }
                }
              }
    }}}}}
    return a
  }
  var p1motion = "i"
  var p2motion = "j"
  var scoreP1 = 0
  var scoreP2 = 0
  while (scoreP1 < 5 && scoreP2 <5 ) {
    toP1.println(s"$p1Name enter your choice: rock, paper, scissors, lizard or spock?") //motion
    toP2.println(s"$p2Name enter your choice: rock, paper, scissors, lizard or spock?") //motion

    p1motion = fromP1.nextLine()
    p2motion = fromP2.nextLine()

    toP1.println(s"$p2Name choose $p2motion " )
    toP2.println(s"$p1Name choose $p1motion " )

    toP1.print(round(p1motion, p2motion) + " ")
    toP2.print(round(p2motion, p1motion) + " ")

    if (round(p1motion, p2motion) == "You win"){ scoreP1 += 1} else { scoreP2 +=1}

    toP1.println(scoreP1 + ":" + scoreP2)
    toP2.println(scoreP2 + ":" + scoreP1)
  }
  if (scoreP1 == 5){
    toP1.println(s"$p1Name is WINNER")
    toP2.println(s"$p1Name is WINNER")
  } else {
    toP1.println(s"$p2Name is WINNER")
    toP2.println(s"$p2Name is WINNER")
  }
}