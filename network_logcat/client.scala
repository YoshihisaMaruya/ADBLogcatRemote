
// Simple client
import java.net._
import java.io._
import scala.io._
import sys.process._
import java.io.{ BufferedReader, InputStreamReader }

/**
 * @author rv
 *
 */
object Client {
  def start(adblogfile : String, address : String, port : Int) {
  	println("address is " + address + ", port is " + port + ", adblogfle is " + adblogfile)
  	val cmd = "tail -f " + adblogfile
    val socket = new Socket(InetAddress.getByName(address), port)
    val pio = new ProcessIO(
      in => {},
      out => {
        val reader = new BufferedReader(new InputStreamReader(out))
        val writer = new PrintWriter(socket.getOutputStream(), true)
        def readLine(): Unit = {
          val line = reader.readLine()
          println(line) // ここに1行ずつで結果が来るから適当に処理する
          writer.println(line)
          if (line != null) readLine()
        }
        readLine()
      },
      err => {
      })
    // runする時に引数に渡す
    val process = cmd.run(pio)
    process.exitValue
  }
}

Client.start(args(0),args(1),args(2).toInt)