import akka.actor.UntypedActor;
import static akka.actor.Actors.*;

public class CollectionActor extends UntypedActor{
	
	private boolean receiveFileCount;
	private int numberOfFiles;
	public CollectionActor(){
		receiveFileCount = false; 
	}
	
	public void onReceive(Object message) throws Exception {
		if(message instanceof FileCount && !receiveFileCount){
			receiveFileCount = true;
			FileCount tempMessage = (FileCount) message;
			numberOfFiles = tempMessage.getNumberOfFiles();
		}
		else if(message instanceof Found){
			Found tempMessage = (Found) message;
			System.out.println(tempMessage.getFileName());
			for(String s : tempMessage.getMatchedLines()){
				System.out.println(s);
			}
			numberOfFiles--;
			
			if(numberOfFiles < 1){
				//shutdown actor: can not figure out how to call method
				//Actors.registry().shutdownAll();
			}

		}
	}
}
