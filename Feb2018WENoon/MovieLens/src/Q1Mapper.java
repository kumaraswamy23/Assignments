import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Q1Mapper  extends Mapper<LongWritable, Text, Text, IntWritable>{
	
	
	//Read data from ratings file and send movie id and 1 as output
	IntWritable one=new IntWritable(1);
	Text movieid=new Text();
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String mvid=value.toString().split("::")[1];
		movieid.set(mvid);
		context.write(movieid, one);
	
	}
}
