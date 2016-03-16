
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
  val tail_file_path = "/Users/rv/Desktop/log.txt"
  val cmd = "tail -f " + tail_file_path
  
  def start {
    val socket = new Socket(InetAddress.getByName("localhost"), 9999)
    val pio = new ProcessIO(
      in => {},
      out => {
        val reader = new BufferedReader(new InputStreamReader(out))
        val writer = new PrintWriter(socket.getOutputStream(), true)
        def readLine(): Unit = {
          val line = reader.readLine()
          println("a")
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

Client.start