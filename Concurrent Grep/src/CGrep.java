import static akka.actor.Actors.*;
import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.actor.UntypedActorFactory;

public class CGrep
{

	/**
	 * @param args	args[0]: regex pattern, mandatory
	 * 				args[1...n]: files, optional
	 */
	public static void main(String[] args) {
		
		try {
		
			ActorRef collector = makeCollectionActor();
			collector.tell(new FileCount(args.length - 1));
		
			if (args.length <= 0) {
				throw new Exception("Error: no arguments specified");
			} else if (args.length == 1) {
				ActorRef stdInputScanActor = makeScanActor();
				stdInputScanActor.tell(new Configure(null, collector));
			} else {
				ActorRef [] scanActor = new ActorRef[args.length];
				for (int i = 1; i <= args.length; i++) {
					scanActor[i] = makeScanActor();
					scanActor[i].tell(new Configure(args[i], collector));
				}
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	private static ActorRef makeCollectionActor() {
		ActorRef collectionActor = actorOf(
                new UntypedActorFactory() {
                    public UntypedActor create() {
                        return new CollectionActor() ;
                    }
                }) ;
		collectionActor.start();
		return collectionActor;
	}
	
	
	private static ActorRef makeScanActor() {
		ActorRef scanActor = actorOf(
                new UntypedActorFactory() {
                    public UntypedActor create() {
                        return new ScanActor() ;
                    }
                }) ;
		scanActor.start();
		return scanActor;
	}

}
