import static akka.actor.Actors.*;
import akka.actor.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ScanActor extends UntypedActor{

	ArrayList<String> matchLines = new ArrayList<String>();
	
	@Override
	public void onReceive(Object message) throws Exception
	{
		String fileName;
		if (message instanceof Configure)
		{
			Configure thisMessage = (Configure)message; 
			fileName = thisMessage.getFile();
			ActorRef collector = thisMessage.getCollectionActor();
			File file = new File(fileName);
			parseFile(file);
			Found found = new Found(fileName, matchLines);
			collector.tell(found);
		}
		else
		{
			throw new IllegalArgumentException("Wrong type of message");
		}
		
		
		
	}
	
	public void parseFile(File checkFile) throws FileNotFoundException
	{
		Scanner scanner = new Scanner(checkFile);
		String line;
		while (scanner.hasNext())
		{
			line = scanner.nextLine();
			checkLine(line);
		}
	
	}
	
	public void checkLine(String checkLine)
	{
		int patternCount = 0;
		Pattern pattern = Pattern.compile("ed");
		Matcher matcher = pattern.matcher("ed");
		
		if (matcher.find())
		{
			matchLines.add(checkLine);
		}
	}
	
}
