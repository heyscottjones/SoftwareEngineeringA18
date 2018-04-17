package com.hundredwordsgof.flyweightobserver;


 // FlyweightObserver  maintains a reference to a single (one) FlyweightSubject object.  
 // There is currently an Unshared and Shared FlyweightObserver subclasses without significant difference
 // in function.  Both attempt to keept track of the subject's state. 

 
public class FlyweightObserver implements Observer, Flyweight {

  protected Object whatObserverKnows;
  protected FlyweightSubject subject;

  public void update() {
    whatObserverKnows = subject.getState();
  }

  public Object getWhatObserverKnows() {
    return whatObserverKnows;
  }
}

class SharedFlyweightObserver extends FlyweightObserver {

  // Constructor
  public SharedFlyweightObserver(FlyweightSubject subject) {
    this.subject = subject;
  }

  @Override
  public void update() {
    whatObserverKnows = ((SharedFlyweightSubject) subject).getIntrinsicState();
  }

}

class UnSharedFlyweightObserver extends FlyweightObserver{

  // Constructor
  public UnSharedFlyweightObserver(FlyweightSubject subject) {
    this.subject = subject;
  }

}