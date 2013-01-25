import java.awt.List;
import java.util.*;
/**
 * @author cory
 * Message class. Will contain the name of the file it's referring to, and 
 * the lines in that file that matched the user provided pattern
 */
public class Found {

	private final String fileName;
	private final ArrayList<String> matchedLines;
	
	public Found(String fileName, ArrayList<String> matchedLines){
		this.fileName = fileName;
		this.matchedLines = new ArrayList<String>(matchedLines);
	}
	
	public ArrayList<String> getMatchedLines(){
		return matchedLines;
	}
	
	public String getFileName(){
		return fileName;
	}
}
