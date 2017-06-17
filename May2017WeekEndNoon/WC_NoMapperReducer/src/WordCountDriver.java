import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountDriver {

	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		
		Configuration conf=new Configuration();
		Job job=Job.getInstance(conf, "WordCount");
		
		job.setJarByClass(WordCountDriver.class);
		//job.setMapperClass(WordCountMapper.class);
		//job.setReducerClass(WordCountReducer.class);
		
		job.setNumReduceTasks(0);
		
		//job.setOutputKeyClass(Text.class);
	//	job.setOutputValueClass(IntWritable.class);
		
		
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		boolean result=job.waitForCompletion(true);
		
		System.exit(result?0:1);
		
	}
	
}
