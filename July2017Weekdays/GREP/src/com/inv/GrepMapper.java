package com.inv;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GrepMapper extends Mapper<LongWritable, Text, Text, NullWritable>{

	//byte offset , line keyin and value in
	// Word , 1 -> kout, vout
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context)
			throws IOException, InterruptedException {
		//search word is "hadoop"
				if(value.toString().toLowerCase().contains("Hadoop".toLowerCase())){
					context.write(value, NullWritable.get());
				}
	}
}

	
