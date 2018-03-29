import org.apache.spark.SparkConf
import org.apache.spark.streaming.StreamingContext
import org.apache.spark.streaming.Seconds
import org.apache.log4j.Logger
import org.apache.log4j.Level
import org.apache.spark.sql.SparkSession


object Spark2x_streaming {
  def main(args: Array[String]): Unit = {
    
        Logger.getLogger("org").setLevel(Level.ERROR)
    //val conf = new SparkConf().setMaster("local[*]")
    //.setAppName("ReadNetwork")
    
    var spark=SparkSession.
 builder.
 appName("Movies").
 master("local[*]").
 getOrCreate()
 
 
 val streamingDF=spark.readStream.format("socket").
  option("host","localhost").option("port","9999").load()
     
  streamingDF.createOrReplaceTempView("sample")
   val wc=spark.sql("""select * from sample
    """) 
  /*  val op=wc.writeStream.outputMode("complete")
    .queryName("aggregates")
    .format("memory").start()*/
    
   wc.writeStream
  .format("parquet")
  .option("checkpointLocation", "/home/hadoop/INPUT/chk")
  .option("path", "/home/hadoop/INPUT/strOut")
  .start().awaitTermination()
    
    //spark.sql("select * from aggregates").show()
    
  //wc.awaitTermination()
     
    //nc -lk -p 9999 -> give the input
    //nc localhost 9999
  
  }
}