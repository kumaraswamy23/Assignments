import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class Q1Reducer extends Reducer<IntWritable, IntWritable, Text, Text>{
	
	int mintemp=-999;
	String minyears="";
	int maxtemp=999;
	String maxyears="";
	int firstflag=-1;
	@Override
	protected void reduce(IntWritable key, Iterable<IntWritable> values,
			Reducer<IntWritable, IntWritable, Text, Text>.Context context) throws IOException, InterruptedException {
		if(firstflag==-1){
			mintemp=key.get();
			firstflag=0;
			for (IntWritable val:values) {
				minyears=minyears+","+val;
			}
			
			
		}
		
		maxtemp=key.get();
		String maxyears1="";
		for (IntWritable val:values) {
			maxyears1=maxyears1+","+val;
		}
		
		maxyears=maxyears1;
		
	}
	@Override
	protected void cleanup(Reducer<IntWritable, IntWritable, Text, Text>.Context context)
			throws IOException, InterruptedException {
		context.write(new Text("min consumption"), new Text(mintemp+"--"+minyears));
		context.write(new Text("max consumption"), new Text(maxtemp+"--"+maxyears));

	}
	
	
		

}
