package bibCreator;

public class FileInvalidException extends Exception {
	// Default Constructor 
	public FileInvalidException() { 
		super("Error: Input file cannot be parsed due to missing information (i.e. month={}, title={}, etc.)");
	}
	
	// Parameter Constructor
	public FileInvalidException(String msg) {
		super(msg);
	}
}