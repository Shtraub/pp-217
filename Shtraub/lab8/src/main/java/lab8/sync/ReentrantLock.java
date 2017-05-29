package lab8.sync;

/**
 * Created by Виталий on 29.05.2017.
 */
public class ReentrantLock implements Lock {

  private volatile boolean locked = false;

  public ReentrantLock() {
  }

  @Override
  public void lock() throws InterruptedException {
    synchronized (ReentrantLock.this) {
      if (locked) {
        wait();
      }
      locked = true;
    }
  }

  @Override
  public void unlock() {
    synchronized (ReentrantLock.this) {
      locked = false;
      notifyAll();
    }
  }
}
