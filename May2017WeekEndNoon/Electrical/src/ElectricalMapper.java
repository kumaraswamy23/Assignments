import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class ElectricalMapper extends Mapper<LongWritable, Text, Text,Text>{

	@Override
	protected void setup(Context context)
			throws IOException, InterruptedException {

		context.write(new Text("YEAR"), new Text("MIN TEMP \t MAX TEMP"));
	
	}
	
	int min=9999;
	int max=-9999;
	
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {

		// 0    1979,23,23,2,43,24,25,26,26,26,26,25,26,25
		String line=value.toString().trim();
		String[] consumption=line.split(",");
		String Year=consumption[0];
		
		
		
		for (int i = 1; i < consumption.length-1; i++) {
			
			int val=Integer.parseInt(consumption[i]);
			
			if( min > val){
				min=val;
			}
			if( max < val){
				max=val;
			}
			
		}
		context.write(new Text(Year), new Text(min+"\t"+max));

	  
	
	
	}
	
	
}
