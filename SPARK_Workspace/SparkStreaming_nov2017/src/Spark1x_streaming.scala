import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.log4j.Logger
import org.apache.log4j.Level


object Spark1x_streaming {
  def main(args: Array[String]): Unit = {
    
        Logger.getLogger("org").setLevel(Level.ERROR)
    val conf = new SparkConf().setMaster("local[*]")
    .setAppName("ReadNetwork")
    
    val ssc = new StreamingContext(conf, Seconds(5))
    val lines = ssc.socketTextStream("localhost", 9999) //Dstreams -> RDD
    
    val word_count= lines.flatMap(x => x.split(" "))
    .map(word => (word, 1))
    .reduceByKey((x,y) => x+y)
    
     word_count.print()
       
    ssc.start()
    ssc.awaitTermination()
    
    //nc -lk -p 9999 -> give the input
    //nc localhost 9999
  
  }
}