package com.hundredwordsgof.flyweightobserver;

import org.joda.time.LocalTime;

public class MainProgram {
  public static void main(String[] args)
  {

    LocalTime currentTime = new LocalTime();
    System.out.println("The local time is: " + currentTime);

    // Creating the FlyweightSubject and FlyweightObserver factories.
    
    FlyweightSubjectFactory fwSFactory = new FlyweightSubjectFactory();
    FlyweightObserverFactory fwOFactory = new FlyweightObserverFactory();

    // Create 4 Shared Flyweight Subjects. 
    
    SharedFlyweightSubject sfS1 = (SharedFlyweightSubject) fwSFactory.getSubject("shared_SKey1", "S1");
    SharedFlyweightSubject sfS2 = (SharedFlyweightSubject) fwSFactory.getSubject("shared_SKey2", "S2");
    SharedFlyweightSubject sfS3 = (SharedFlyweightSubject) fwSFactory.getSubject("shared_SKey1", "S3");
    SharedFlyweightSubject sfS4 = (SharedFlyweightSubject) fwSFactory.getSubject("shared_SKey4", "S4");

    // Show us their states.  S1 and S3 should be the same, ie:  "S1"

    System.out.println(sfS1.getIntrinsicState());
    System.out.println(sfS2.getIntrinsicState());
    System.out.println(sfS3.getIntrinsicState());
    System.out.println(sfS4.getIntrinsicState());


    // Create an unshared flyweight subject and show its state.   Just a placeholder for further code extension if wanted.

    UnSharedFlyweightSubject unsf1 = (UnSharedFlyweightSubject) fwSFactory.getSubject("unshared_SKey1", "uS1");
    System.out.println(unsf1.getState());
   
    // Create two unshared flyweight observers and set their Subjects to be sfS4 and sfS1 respectively.   
    UnSharedFlyweightObserver usfO1 = (UnSharedFlyweightObserver) fwOFactory.getObserver("unshared_OKey1",sfS4);
    UnSharedFlyweightObserver usfO2 = (UnSharedFlyweightObserver) fwOFactory.getObserver("unshared_OKey1",sfS1);

    // The Sharded Flyweight Subject (sfS4) attaches observer 1, and then sets its intrinsic/shared state to "1."
    sfS4.attach(usfO1);
    sfS4.setState(1);

    // Print out what the code thinks is the status.   
    System.out.println("Subject sets its state to be 1.  Observer thinks the subject's state is: " + (usfO1.getWhatObserverKnows()));

    // Then have the Shared Flyweight Subject (sfS4) dettach.   And then again set its intrinsic/shared state to something else ("0")
    sfS4.dettach(usfO1);
    sfS4.setState(0);

    // Print out what the code thinks the status is.
    System.out.println("Subject detaches and sets state to 0.  Observer thinks the subject's state is: " + (usfO1.getWhatObserverKnows()));

    // The following lines shows that although the Unshared Flyweight Observer, usfO2 assigns itself to Shared Flyweight Subject sfS1, because 
    // sfS3 and sfS1 are the same object, operations by either of them are picked up by usf02
   
    sfS1.attach(usfO2);
    sfS1.setState(1);

    System.out.println("Subject sfS1 sets its state to be 1.  Observer thinks sfS1's state is: " + (usfO2.getWhatObserverKnows()));
    System.out.println("The Subject sfS3 has the state of: " + (sfS3.getState()));
    sfS3.setState(2);
    System.out.println("The Subject sfS3 sets its state to be 2.  Observer thinks the sfS1's state is: " + (usfO2.getWhatObserverKnows()));

    sfS1.dettach(usfO2);
    sfS1.setState(0);

    System.out.println("Subject sfS1 detaches and sets state to 0.  Observer thinks sfS1's state is: " + (usfO2.getWhatObserverKnows()));

  }
}
