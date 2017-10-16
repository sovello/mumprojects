package librarymanager.models;

import java.io.Serializable;

public interface Model extends Serializable{
	public String getSaveType();
	
	public String getKey();
}
