package com.hundredwordsgof.flyweightobserver;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * FlyweightFactory, creates and manages the flyweight objects.
 *
 */
public class FlyweightObserverFactory implements FlyweightFactory{

  private static Map<String, Flyweight> flyweights = new HashMap<String, Flyweight>();

  /**
   * Returns Flyweight object. Just for sake of example following logic is
   * applied, if key starts with phrase "unshared," then only extrinsic state is set and a fresh object is created.  
   * Otherwise, it checks to see if an object already exists that shares the "key"
   * 
   */

   /* The following violates the Open Close principle because two string constants are passed to 
    * the separate calls to the FlyweightSubject constructors.
    */

  public static Flyweight getObserver(String key, FlyweightSubject value) {

    if (key.startsWith("unshared")) {
      flyweights.put(key, new UnSharedFlyweightObserver(value));
    } else {
      if (!flyweights.containsKey(key)) {
        flyweights.put(key, new SharedFlyweightObserver(value));
      }
    }

    return (Flyweight) flyweights.get(key);
  }
}
