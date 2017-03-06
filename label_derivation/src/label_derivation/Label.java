package label_derivation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Label {

	private String owner;
	private Set<String> readers=new HashSet<String>();
	private Set<String> writers=new HashSet<String>();
	
	public Label(String owner, ArrayList<String> readers, ArrayList<String> writers) {
		this.owner=owner;
		this.setReaders(readers);
		this.setWriters(writers);
	}
	
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public Set<String> getReaders() {
		return readers;
	}
	
	public void setReaders(ArrayList<String> readers) {
		this.readers.clear();
		for(String reader:readers)
			this.readers.add(reader);
	}
	
	public Set<String> getWriters() {
		return writers;
	}
	
	public void setWriters(ArrayList<String> writers) {
		for(String writer:writers)
			this.writers.add(writer);
	}
	
	public boolean isEqual(Label label)
	{
		if(this.owner.equals(label.owner))
		{
			if(this.readers.equals(label.readers))
			{
				if(this.writers.equals(label.writers))
					return true;
			}
		}
		
		return false;
	}
	
}
