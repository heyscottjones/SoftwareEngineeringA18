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

    subject.dettach(observer);
    subject.setState(0);

    // observer is detached so changes are not propageted
    assertEquals(1, ((FlyweightObserver) observer).getWhatObserverKnows());
  }
}
