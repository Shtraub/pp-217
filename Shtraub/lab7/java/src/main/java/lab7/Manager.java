package lab7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Виталий on 24.05.2017.
 */
public class Manager {


  private int numThread;
  private ExecutorService executorService;
  private Progressbar progressbar;

  public Manager(int numThread) {
    this.numThread = numThread;
    this.progressbar = new Progressbar(Helper.experimentCount);
    this.executorService = Executors.newFixedThreadPool(numThread);
  }

  public void ZeroingOfProbability() {
    Helper.getInstance().ZeroingOfProbability();
  }

  public double startComputation() {
    List<Future<Double>> futureList = new ArrayList<>();
    Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
    Lock locker = new ReentrantLock();
    CountDownLatch latch = new CountDownLatch(numThread);

    for (int i = 1; i <= numThread; i++) {
      Future<Double> futureTask = executorService
          .submit(new Lab7(i, (Helper.experimentCount / numThread), progressbar, semaphore, locker,
              latch));
      futureList.add(futureTask);
    }
    executorService.shutdown();
    return waitingResult(futureList);
  }


  private double waitingResult(List<Future<Double>> futureList) {
    for (Future<Double> future : futureList) {
      try {
        Helper.getInstance().addToSum(future.get());
      } catch (InterruptedException | ExecutionException e) {
        System.out.println("Thread pool is interrupted  \n " + e.toString());
        executorService.shutdownNow();
      }
    }
    Helper.getInstance().printProbability();
    return Helper.getInstance().getResult();
  }
}
