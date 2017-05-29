package lab8.sync;

/**
 * Created by Виталий on 29.05.2017.
 */
public interface Lock {

  void lock() throws InterruptedException;

  void unlock();
}
