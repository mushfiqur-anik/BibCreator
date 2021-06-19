package bibCreator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BibCreator {
	// Attributes
	private final static int numOfFiles = 10;
	private static Scanner[] input = new Scanner[numOfFiles];
	private static String[] latexFileNames = new String[numOfFiles];
	private static String[] IEEEOutputName = new String[numOfFiles];
	private static String[] ACMOutputName = new String[numOfFiles];
	private static String[] NJOutputName = new String[numOfFiles];
	private static PrintWriter[] IEEEOutput = new PrintWriter[numOfFiles];
	private static PrintWriter[] ACMOutput = new PrintWriter[numOfFiles];
	private static PrintWriter[] NJOutput = new PrintWriter[numOfFiles];
	private static boolean isInvalidInput = false;
	private static boolean isInvalidOutput = false;
	private static int invalidInputIndex = 0;
	
	// Method to initialize file names
	public static void initializeFileName() {
		int index;
		for(int i = 0; i < numOfFiles; i++) { 
			index = (i+1);
			latexFileNames[i] = "Latex" + index + ".bib";
			IEEEOutputName[i] = "IEEE" + index + ".json";
			ACMOutputName[i] = "ACM" + index + ".json";
			NJOutputName[i] = "NJ" + index + ".json";
		}
	}
	
	// Method to open files 
	public static void openFilesForReading(){ 
		try { 
			for(int i = 0; i < numOfFiles; i++) { 
				input[i] = new Scanner(new FileReader(latexFileNames[i]));
				System.out.println("Successfully opened Latex" + (i+1) + ".bib");
				invalidInputIndex++;
			}
		}
		
		catch(FileNotFoundException e) { 
			isInvalidInput = true;
			System.out.println("Could not open input file latex" + (invalidInputIndex+1) + ".bib for reading. Please check if file exists! Program will terminate after closing any opened files.");
			
			// Close opened files
			for(int i = 0; i < invalidInputIndex; i++) { 
				System.out.println("Closing files Latex" + (i+1) + ".bib");
				input[i].close();
			}
		}
	}
	
	// Create all 30 Output files 
	public static void createOutputFiles() { 
		// Index of invalid file
		int IEEEInvalidFile = 0;
		int ACMInvalidFile = 0;
		int NJInvalidFile = 0;
		
		try {
			// Attempt to create 30 files
			for(int i = 0; i < numOfFiles; i++) { 
				System.out.println("Successfully created IEEE" + (i+1));
				IEEEOutput[i] = new PrintWriter(IEEEOutputName[i]);
				IEEEInvalidFile++;
				
				System.out.println("Successfully created ACM" + (i+1));
				ACMOutput[i] = new PrintWriter(ACMOutputName[i]);
				ACMInvalidFile++;
				
				System.out.println("Successfully created NJ" + (i+1));
				NJOutput[i] = new PrintWriter(NJOutputName[i]);
				NJInvalidFile++;
			}
		} 
		
		// Throw exception if files can't be created
		catch(FileNotFoundException e) { 
			for(int i = 0; i < IEEEInvalidFile; i++) { // Close IEEE
				IEEEOutput[i].close();
			}
			for(int i = 0; i < IEEEInvalidFile; i++) { // Close ACM
				ACMOutput[i].close();
			}
			for(int i = 0; i < IEEEInvalidFile; i++) { // Close NJ
				NJOutput[i].close();
			}
			deleteAllFiles(); // Delete all files
		}
	}
	
	// Method to delete created files if one of output files is invalid
	public static void deleteAllFiles() { 
		File file; 
		
		for(int i = 0; i < numOfFiles; i++) {
			// IEEE
			file = new File(IEEEOutputName[i]);
			if(file.exists()) { 
				file.delete();
			}
			// ACM
			file = new File(ACMOutputName[i]);
			if(file.exists()) { 
				file.delete();
			}
			// NJ
			file = new File(NJOutputName[i]);
			if(file.exists()) { 
				file.delete();
			}	
		} 
	} 
	
	// Process Files for validation
	public static void processFilesForValidation() { 
		// Check if All three files are valid 
		
		// Delete IEEE, ACM, & NJ of the corre
	}
	
	public static void main(String[] args) {
		initializeFileName(); // Initialize files
		
		openFilesForReading(); // Open files for reading
		
		if(!isInvalidInput) {
			System.out.println("Will create 30 files");
			createOutputFiles();
		}

	}
}
