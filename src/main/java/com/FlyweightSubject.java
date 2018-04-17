package com.hundredwordsgof.flyweightobserver;

// Parent class with default methods for state that is unique to each object.

public class FlyweightSubject extends Subject implements Flyweight {

  public Object state;

  public Object getState() {
    return state;
  }

  public void setState(Object state) {
    this.state = state;
    this.notifyObservers();
  }
}

// For shared objects.  Note that the methods have different names but still 
// implement similar functions. This class exists for future modification to 
// include separation of intrinsic and extrinsic states.

 class SharedFlyweightSubject extends FlyweightSubject {

  private Object intrinsicState;

  // Constructor
  public SharedFlyweightSubject(Object state) {
      this.intrinsicState = state;
  }
 
  public Object getIntrinsicState() {
    return intrinsicState;
  }

}
// For unshared objects with unique state for each.

class UnSharedFlyweightSubject extends FlyweightSubject {

  // Constructor
  public UnSharedFlyweightSubject(Object state) {
      this.state = state; 
  }
  
}