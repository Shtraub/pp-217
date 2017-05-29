package lab8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import lab8.sync.CountDownLatch;
import lab8.sync.Lock;
import lab8.sync.ReentrantLock;
import lab8.sync.Semaphore;


/**
 * Created by Виталий on 24.05.2017.
 */
public class Manager {


  public void setNumThread(int numThread) {
    this.numThread = numThread;
  }

  private int numThread;
  private ExecutorService executorService;
  private Progressbar progressbar;

  public Manager() {
  }

  public void ZeroingOfProbability() {
    Helper.getInstance().ZeroingOfProbability();
  }

  public double startComputation() {
    this.progressbar = new Progressbar(Helper.experimentCount);
    this.executorService = Executors.newFixedThreadPool(numThread);
    List<Future<Double>> futureList = new ArrayList<>();
    Semaphore semaphore = new Semaphore(Runtime.getRuntime().availableProcessors());
    Lock locker = new ReentrantLock();
    CountDownLatch latch = new CountDownLatch(numThread);

    for (int i = 1; i <= numThread; i++) {
      Future<Double> futureTask = executorService
          .submit(new Lab8(i, (Helper.experimentCount / numThread), progressbar, semaphore, locker,
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
