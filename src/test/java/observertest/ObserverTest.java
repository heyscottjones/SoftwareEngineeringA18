package com.hundredwordsgof.flyweightobserver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
/**
 * Test Observer pattern.
 */
public class ObserverTest {

  @Test
  public void testObserver() throws CloneNotSupportedException {

    FlyweightSubjectFactory fwSFactory = new FlyweightSubjectFactory();

    FlyweightSubject subject = (FlyweightSubject) fwSFactory.getSubject("ObserverTest", "testSubject");

    UnSharedFlyweightObserver observer = new UnSharedFlyweightObserver(subject);
    subject.attach(observer);
    subject.setState(1);

    // changes via subject.setState is propagated towards observer
    assertEquals(1, ((FlyweightObserver) observer).getWhatObserverKnows());
  }
  
  @Test
  public void testDettach() throws CloneNotSupportedException {
    FlyweightSubjectFactory fwSFactory = new FlyweightSubjectFactory();

    FlyweightSubject subject = (FlyweightSubject) fwSFactory.getSubject("ObserverTest", "testSubject");

    UnSharedFlyweightObserver observer = new UnSharedFlyweightObserver(subject);
    subject.attach(observer);
    subject.setState(1);

    // changes via subject.setState is propagated towards observer
    assertEquals(1, ((FlyweightObserver) observer).getWhatObserverKnows());

    subject.dettach(observer);
    subject.setState(0);

    // observer is detached so changes are not propageted
    assertEquals(1, ((FlyweightObserver) observer).getWhatObserverKnows());
  }
  
  
  // Testing that multiple observers are updated when the setState method (which contains notifyObservers)
  @Test
  public void newTests1() {
	FlyweightSubjectFactory fwSFactory = new FlyweightSubjectFactory();
	
	FlyweightSubject subject = (FlyweightSubject) fwSFactory.getSubject("unsharedObserverTest", "testSubject");
	
	UnSharedFlyweightObserver observer1 = new UnSharedFlyweightObserver(subject);
	UnSharedFlyweightObserver observer2 = new UnSharedFlyweightObserver(subject);
	UnSharedFlyweightObserver observer3 = new UnSharedFlyweightObserver(subject);
	subject.attach(observer1);
	subject.setState(1);
	subject.attach(observer2);
	subject.setState(3);
	subject.dettach(observer2);
	subject.attach(observer3);
	subject.setState(2);
	
	assertEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer3).getWhatObserverKnows());
	assertNotEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer2).getWhatObserverKnows());
	assertNotEquals(((FlyweightObserver) observer3).getWhatObserverKnows(), ((FlyweightObserver) observer2).getWhatObserverKnows());
  }
  
  // Testing if the observers are notified if we call notifyObservers after we have set the state
  @Test
  public void newTests2() {
	FlyweightSubjectFactory fwSFactory = new FlyweightSubjectFactory();
	
	FlyweightSubject subject = (FlyweightSubject) fwSFactory.getSubject("unsharedObserverTest", "testSubject");
	
	UnSharedFlyweightObserver observer1 = new UnSharedFlyweightObserver(subject);
	UnSharedFlyweightObserver observer2 = new UnSharedFlyweightObserver(subject);
	UnSharedFlyweightObserver observer3 = new UnSharedFlyweightObserver(subject);
	subject.attach(observer1);
	subject.setState(1);
	subject.attach(observer2);
	subject.attach(observer3);
	subject.notifyObservers();
	
	assertEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer3).getWhatObserverKnows());
	assertEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer2).getWhatObserverKnows());
	assertEquals(((FlyweightObserver) observer3).getWhatObserverKnows(), ((FlyweightObserver) observer2).getWhatObserverKnows());
  }
  
  // Testing that the update method does update the individual observers
  @Test
  public void newTests3() {
	FlyweightSubjectFactory fwSFactory = new FlyweightSubjectFactory();
		
	FlyweightSubject subject = (FlyweightSubject) fwSFactory.getSubject("unsharedObserverTest", "testSubject");
	
	UnSharedFlyweightObserver observer1 = new UnSharedFlyweightObserver(subject);
	UnSharedFlyweightObserver observer2 = new UnSharedFlyweightObserver(subject);
	UnSharedFlyweightObserver observer3 = new UnSharedFlyweightObserver(subject);
	subject.setState(1);
	subject.attach(observer1);
	subject.attach(observer2);
	subject.attach(observer3);
	observer1.update();
	observer2.update();
	assertNotEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer3).getWhatObserverKnows());
	observer3.update();
	
	assertEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer3).getWhatObserverKnows());
	assertEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer3).getWhatObserverKnows());
	assertEquals(((FlyweightObserver) observer1).getWhatObserverKnows(), ((FlyweightObserver) observer3).getWhatObserverKnows());
  }
  
  // Testing that update perpetuates through all shared flyweight subjects with the same key
  @Test
  public void newTests4() {
	FlyweightSubjectFactory fwSFactory = new FlyweightSubjectFactory();
	
	FlyweightSubject subject1 = (FlyweightSubject) fwSFactory.getSubject("sharedObserverTest", "testSubject");
	
	SharedFlyweightObserver observer1 = new SharedFlyweightObserver(subject1);
	
	subject1.attach(observer1);
	subject1.setState(1);
	
	FlyweightSubject subject2 = (FlyweightSubject) fwSFactory.getSubject("sharedObserverTest", "testSubject");
	
	assertEquals(subject1.getState(), subject2.getState());
  }
}
