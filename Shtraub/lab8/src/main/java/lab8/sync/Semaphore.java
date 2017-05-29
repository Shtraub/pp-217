package lab8.sync;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Виталий on 29.05.2017.
 */
public class Semaphore {

  private AtomicInteger aI;

  public Semaphore(Integer countThread) {
    if (countThread <= 0) {
      throw new IllegalArgumentException("count <= 0");
    }
    this.aI = new AtomicInteger(countThread);
  }

  public void acquire() throws InterruptedException {
    synchronized (Semaphore.this) {
      while (aI.get() == 0) {
        wait();
      }
      aI.decrementAndGet();
    }
  }

  public void release() {
    synchronized (Semaphore.this) {
      aI.incrementAndGet();
      notifyAll();
    }
  }
}
