package com.inv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class JoinDriver extends Configured implements Tool{
	@Override
	public int run(String[] arg0) throws Exception {
		Configuration conf=this.getConf();
		conf.set("mapreduce.output.textoutputformat.separator", "@");
		Job job=Job.getInstance(conf,"Movie lens Map Side Join Driver");
		
		//job.addCacheFile(new URI("/home/hadoop/Music/Classes/MovieLens-Work/ml-1m/users.dat"));
		
		//job.addCacheFile(new URI("/home/hadoop/Music/Classes/MovieLens-Work/ml-1m/movies.dat"));

		/*URI[] files=job.getCacheFiles();
		for (int i = 0; i < files.length; i++) {
			System.out.println("++++++ Files added are::::"+files[i].getPath());
		}*/
			
		job.setJarByClass(JoinDriver.class);
		job.setMapperClass(RatingsMapper.class);
		//job.setReducerClass(JoinReducer.class);
		job.setNumReduceTasks(0);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
				
		FileInputFormat.addInputPath(job, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(job, new Path(arg0[1]));
				
		return job.waitForCompletion(true)?0:1;
	}
	
	public static void main(String[] args) throws Exception {
		
		System.exit(ToolRunner.run(new JoinDriver(), args));	
		
		
	}
}