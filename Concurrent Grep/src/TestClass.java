import java.util.*;
import static akka.actor.Actors.*;
import akka.actor.*;

public class TestClass {

	static private ActorRef[] producer = new ActorRef[2];
	static private ActorRef consumer;
	
	static class Producer extends UntypedActor{

		@Override
		public void onReceive(Object arg0) throws Exception {
			// TODO Auto-generated method stub
			
		}
		
	};
}
