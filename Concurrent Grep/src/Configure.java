import akka.actor.*;

public class Configure {

	private final String fileToScan;
	private final ActorRef collectionActor;
	
	public Configure(String fileToScan, ActorRef collectionActor){
		this.fileToScan = fileToScan;
		this.collectionActor = collectionActor;
	}
	
	public String getFile(){
		return fileToScan;
	}
	
	public ActorRef getCollectionActor(){
		return collectionActor;
	}
}
