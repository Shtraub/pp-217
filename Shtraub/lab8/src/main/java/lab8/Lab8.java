package lab8;

import java.util.Random;
import java.util.concurrent.Callable;
import lab8.sync.CountDownLatch;
import lab8.sync.Lock;
import lab8.sync.Semaphore;


/**
 * Created by Виталий on 24.05.2017.
 */
public class Lab8 implements Callable<Double> {


  final static int FACETS = 10;
  final static int NUMBER_OF_DICE = 10;
  final static int PROBABILITY = 80;

  private double favorableEvent = 0;
  private int numPiece;
  private long interval;
  private Progressbar progressbar;
  private Semaphore semaphore;
  private Lock locker;
  private CountDownLatch latch;

  public Lab8(int numPiece, long interval, Progressbar progressbar, Semaphore semaphore,
      Lock locker, CountDownLatch latch) {
    this.progressbar = progressbar;
    this.numPiece = numPiece;
    this.interval = interval;
    this.semaphore = semaphore;
    this.locker = locker;
    this.latch = latch;

  }

  public static int ThrowTheDice() {
    Random r = new Random();
    int dice = r.nextInt(FACETS) + 1;
    if (dice == FACETS) {
      dice += ThrowTheDice();
    }
    return dice;
  }

  public static int Task() {
    int dice = 0;
    for (int i = 0; i < NUMBER_OF_DICE; i++) {
      dice += ThrowTheDice();
    }
    return dice;
  }

  @Override
  public Double call() throws Exception {
    System.out.println(Thread.currentThread().getName() + " waiting permission");
    semaphore.acquire();
    try {
      int startIndex = (int) ((numPiece * interval) - interval);
      int threadsCycles = (int) ((numPiece * interval) - startIndex);
      long counter = 0;

      for (int i = startIndex; i <= numPiece * interval; i++) {
        if (Task() > PROBABILITY) {
          favorableEvent++;
        }
        counter++;
        if (counter == threadsCycles / 10) {
          locker.lock();
          try {
            progressbar.updateProgress(counter);
            counter = 0;
          } catch (Exception e) {
            throw e;
          } finally {
            locker.unlock();
          }
        }
      }
    } finally {
      semaphore.release();
      System.out.println(Thread.currentThread().getName() + " released permission");
    }
    long currentTime = System.nanoTime();
    latch.countDown();
    try {
      latch.await();
    } catch (InterruptedException e) {
      throw e;
    }

    System.out.println(Thread.currentThread().getName() + " Delay time: " + (
        (System.nanoTime() - currentTime) / 1E9) + " s");

    return favorableEvent;
  }
}
