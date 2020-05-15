package FileIO;

import java.io.FileNotFoundException;

/**
 * Performs a standardized way to get data from a file
 * ReadFile interface provides a way to read a file which guarantees  matching with all code structure already developed
 * @author Group 20
 *
 */
public interface ReadFile {
	
	/**
	 * prints the read data
	 */
	void print();
	/**
	 * Returns the read data into Dataset type
	 * @return Dataset data read from a file
	 */
	Dataset data();
	
	/**
	 * Close the file pointer
	 * @throws FileNotFoundException
	 */
	void close() throws FileNotFoundException;
	
	
}
