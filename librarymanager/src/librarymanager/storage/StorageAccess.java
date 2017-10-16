package librarymanager.storage;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.HashMap;

import librarymanager.models.*;

public class StorageAccess {
	/**
	 * for my notes:
	 * 
	 * after we get a serializable object then we can write it
	 * as an ObjectOutPutStream with writeObjects() from FileOutPutStream 
	 * @throws FileNotFoundException 
	 */
	
	static String separator = System.getProperty("file.separator");
	private static final String DATA_DIR = System.getProperty("user.dir")+
			separator+"src"+separator+"librarymanager"+separator+"storage"+separator+"data"+separator;
	
	public StorageAccess() {}
	
	
	public static void save(Model object) {
		if( fileExists(object.getSaveType())) {
			@SuppressWarnings("unchecked")
			HashMap<String, Model> map = (HashMap<String, Model>) read(object.getSaveType());
			map.put(object.getKey(), object);
			save(object.getSaveType(), map);
		}
		else {
			HashMap<String, Model> newmap = new HashMap<String, Model>();
			newmap.put(object.getKey(), object);
			save(object.getSaveType(), newmap);
		}
	}
	
	public static void save(String type, HashMap<String, ? extends Model> newmap) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(DATA_DIR, type);
			out = new ObjectOutputStream(Files.newOutputStream(path 
					// StandardOpenOption.CREATE, StandardOpenOption.APPEND
					));
			out.writeObject(newmap);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	/**
	 * if a file exists, it means the map is already created,
	 * so we don't create a new one
	 * @param path
	 */
	public static boolean fileExists(String type) {
		Path path = FileSystems.getDefault().getPath(DATA_DIR, type);
		if( Files.exists(path, LinkOption.NOFOLLOW_LINKS)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public static Object read(String type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(DATA_DIR, type);
			if( fileExists(type )) {
				in = new ObjectInputStream(Files.newInputStream(path));
				retVal = in.readObject();
			}
		} catch( Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
}
