package com.hundredwordsgof.flyweightobserver;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * FlyweightFactory, creates and manages the flyweight objects.
 *
 */
public class FlyweightSubjectFactory implements FlyweightFactory{

  private static Map<String, Flyweight> flyweights = new HashMap<String, Flyweight>();

  
 // Returns shared or unshared FlyweightSubject object. If the requested object's key begins with "unshared"
 // then a unique/fresh object is created.  Otherwise, it checks to see if an object already exists that shares 
 // the key.   If it doesn, it returns the already created object.


   /* The following violates the Open Close principle because two string constants are passed to 
    * the separate calls to the FlyweightSubject constructors.
    */

  public static Flyweight getSubject(String key, String value) {

    if (key.startsWith("unshared")) {
      flyweights.put(key, new UnSharedFlyweightSubject(value));
    } else {
      if (!flyweights.containsKey(key)) {
        flyweights.put(key, new SharedFlyweightSubject(value));
      }
    }

    return (Flyweight) flyweights.get(key);
  }
}
