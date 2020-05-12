package FileIO;

import java.io.FileNotFoundException;

public interface ReadFile {
	
	void print();
	Dataset data();
	void close() throws FileNotFoundException;
	
	
	
	
}
