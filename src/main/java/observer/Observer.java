package com.hundredwordsgof.flyweightobserver;

/**
 * Observer defines an updating interface for objects that should be notified of
 * changes in a subject.
 *
 */
public interface Observer {

  Object getWhatObserverKnows();
  void update();
}
