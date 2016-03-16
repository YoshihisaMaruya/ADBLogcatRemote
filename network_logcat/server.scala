

// Simple server
import java.net._
import java.io._
import scala.io._

/**
 * @author rvja
 */
object Server {
  def start {
    val server = new ServerSocket(9999)
    val s = server.accept()
    val in = new BufferedReader(new InputStreamReader(s.getInputStream()))
    val writer = new PrintWriter(new File("log.txt" ))
    while (true) {
      val line = in.readLine()
      writer.println(line)
      writer.flush()
    }
    println("close")
    s.close()
    writer.close()
  }
}

Server.start