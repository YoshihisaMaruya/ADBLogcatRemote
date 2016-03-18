

// Simple server
import java.net._
import java.io._
import scala.io._

/**
 * @author rv
 */
object Server {
  def start(port : Int, file : String) {
    println("port is " + port + ", file is " + file)
    val server = new ServerSocket(port)
    val s = server.accept()
    val in = new BufferedReader(new InputStreamReader(s.getInputStream()))
    val writer = new PrintWriter(new File(file))
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

println("Server logcat")
Server.start(args(0).toInt,args(1))
