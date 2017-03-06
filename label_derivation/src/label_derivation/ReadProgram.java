package label_derivation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReadProgram {

	
	public static void main(String[] args) {
		
		
		Map<String,Label> static_labels = new HashMap<String,Label>();
		Map<String,Label> dynamic_labels = new HashMap<String,Label>();
		
		try{
			FileInputStream fstream=new FileInputStream(args[0]);
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			while((line=br.readLine())!=null)
			{
				String tokens[]=line.split("\\=");
				static_labels.put(tokens[0], new RWFMOperations().createLabelFromInput(tokens[1]));
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	
		boolean isLabelChanged=false;
		
		do{
			try{
				FileInputStream fstream=new FileInputStream(args[1]);
				BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
				String line;
				isLabelChanged=false;
				
				while((line=br.readLine())!=null)
				{
					if(line.contains("if") && line.contains("="))
					{
						
					}
					else if(line.contains("="))
					{
						isLabelChanged=new LabelGenerator().labelForAssignment(line, static_labels, dynamic_labels);
					}
					
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}while(isLabelChanged==true);
	
		
		for(String variable: dynamic_labels.keySet())
		{
			new RWFMOperations().displayLabels(variable, dynamic_labels);
		}
		
	}
		
}
