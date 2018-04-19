package com.inv;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{

	IntWritable one=new IntWritable(1);
	Text key_out=new Text();
	
	@Override
	protected void map(LongWritable key, Text value, 
			Context context)
			throws IOException, InterruptedException {
		String line=value.toString(); // convert text into string
		StringTokenizer words=new StringTokenizer(line, " "); // divide line to words
		
		// take one word and send word, 1 as key and value
		while(words.hasMoreTokens()){
			String word=words.nextToken();
			key_out.set(word);
			context.write(key_out, one);
		}
	
	
	}

}
