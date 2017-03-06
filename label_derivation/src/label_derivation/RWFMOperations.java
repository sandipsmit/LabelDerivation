package label_derivation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RWFMOperations {
	
	public Label createNewLabel()
	{
		ArrayList<String> readers=new ArrayList<String>();
		ArrayList<String> writers =new ArrayList<String>();
		readers.add("*");
		writers.add("");
		Label new_label =new Label("PC",readers,writers);
		return new_label;
	}
	
	public Label createLabelFromInput(String label)
	{
		String owner;
		ArrayList<String> readers=new ArrayList<String>();
		ArrayList<String> writers=new ArrayList<String>();
		
		String parts[]=label.split("[{}()]");
		owner=parts[1].substring(0, parts[1].indexOf(','));
		
		char reader_set[]=parts[2].toCharArray();
		String reader="";
		for(char ch: reader_set)
		{
			int flag=0;
			if(ch!=',')
			{
				reader=reader+ch;
			}
			else
				flag=1;
			
			if(flag==1)
			{
				readers.add(reader);
				reader="";
			}
		}
		readers.add(reader);
		
		char writer_set[]=parts[4].toCharArray();
		String writer="";
		for(char ch: writer_set)
		{
			int flag=0;
			if(ch!=',')
			{
				writer=writer+ch;
			}
			else
				flag=1;
			
			if(flag==1)
			{
				writers.add(writer);
				writer="";
			}
		}
		writers.add(writer);
		
		Label new_label=new Label(owner,readers,writers);
		return new_label;
		
	}
	
	public void displayLabels(String variable, Map<String,Label> map)
	{
		for(Map.Entry<String, Label> entry: map.entrySet())
		{
			if(entry.getKey().equals(variable))
			{
				System.out.println(entry.getKey()+"=("+entry.getValue().getOwner()+","+entry.getValue().getReaders()+","+entry.getValue().getWriters()+")");
			}
		}
	}
	
	
	public Label operationRead(Label source, Label target)
	{	
		Set<String> all =new HashSet<String>();
		all.add("*");
		if(target.getReaders().equals(all))
		{
			ArrayList<String> readers =new ArrayList<String>();
			for(String source_reader:source.getReaders())
				readers.add(source_reader);
			target.setReaders(readers);
		}
		else
		{	
			target.getReaders().retainAll(source.getReaders());
			if(target.getReaders().equals(""))
			{
				System.out.println("Program is not flow safe");
				System.exit(0);
			}
		}
		
		target.getWriters().addAll(source.getWriters());
		if(target.getWriters().contains(""))
		{
			target.getWriters().remove("");
		}
		
		return target;
	}
}
