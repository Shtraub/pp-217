package lab8.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Виталий on 29.05.2017.
 */
public class CountDownLatch {

  private AtomicInteger aI;

  public CountDownLatch(Integer countThread) {
    if (countThread <= 0) {
      throw new IllegalArgumentException("count <= 0");
    }
    aI = new AtomicInteger(countThread);
  }

  public synchronized void countDown() {
    aI.decrementAndGet();
    if (aI.get() == 0) {
      notifyAll();
    }
  }

  public synchronized void await() throws InterruptedException {
    if (aI.get() > 0) {
      wait();
    }
  }
}
