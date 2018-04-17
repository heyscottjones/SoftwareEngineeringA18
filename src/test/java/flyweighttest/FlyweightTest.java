package com.hundredwordsgof.flyweightobserver;


import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * Test Flyweight pattern.
 */
public class FlyweightTest {

  @Test
  public void testFlyweight() {

    // Create 3 FlyweightSubject instances.
    SharedFlyweightSubject sfS1 = (SharedFlyweightSubject) FlyweightSubjectFactory.getSubject("key1", "value1");
    SharedFlyweightSubject sfS2 = (SharedFlyweightSubject) FlyweightSubjectFactory.getSubject("key2", "value2");
    SharedFlyweightSubject sfS3 = (SharedFlyweightSubject) FlyweightSubjectFactory.getSubject("key1", "value3");

    // Create 3 UnSharedFlyweight instances. 
    UnSharedFlyweightSubject usfS1 = (UnSharedFlyweightSubject) FlyweightSubjectFactory.getSubject("unsharedKey1","value1");
    UnSharedFlyweightSubject usfS2 = (UnSharedFlyweightSubject) FlyweightSubjectFactory.getSubject("unsharedKey2","value2");
    UnSharedFlyweightSubject usfS3 = (UnSharedFlyweightSubject) FlyweightSubjectFactory.getSubject("unsharedKey1","value3");

    // 3 shared flyweight object equivalency tests
    assertNotEquals(sfS1, sfS2);
    assertEquals(sfS1, sfS3);
    assertNotEquals(sfS2, sfS3);

    // 3 unshared flyweight object non-equivalency tests
    assertNotEquals(usfS1, usfS2);
    assertNotEquals(usfS1, usfS3);
    assertNotEquals(usfS2, usfS3);

   // 3 shared flyweight tests assessing the values for the shared (instrinsic) state.

    if (sfS1 instanceof com.hundredwordsgof.flyweightobserver.FlyweightSubject) {
      assertEquals("value1", sfS1.getIntrinsicState());
    }

    if (sfS2 instanceof com.hundredwordsgof.flyweightobserver.FlyweightSubject) {
      assertEquals("value2",sfS2.getIntrinsicState());
    }

    if (sfS3 instanceof com.hundredwordsgof.flyweightobserver.FlyweightSubject) {
      assertEquals("value1", sfS3.getIntrinsicState());
    }

    // 3 unshared flyweight tests assessing the values of their states.

    if (usfS1 instanceof com.hundredwordsgof.flyweightobserver.UnSharedFlyweightSubject) {
      assertEquals("value1", usfS1.getState());
    }

    if (usfS2 instanceof com.hundredwordsgof.flyweightobserver.UnSharedFlyweightSubject) {
      assertEquals("value2", usfS2.getState());
    }

    if (usfS3 instanceof com.hundredwordsgof.flyweightobserver.UnSharedFlyweightSubject) {
      assertEquals("value3", usfS3.getState());
    }
  }
}
