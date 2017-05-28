package lab7;

/**
 * Created by Виталий on 24.05.2017.
 */
public class Progressbar {

  private long commonCycle;
  private volatile long currentCycle;
  private int numThread;

  public Progressbar(long commonCycle, int numThread) {
    this.commonCycle = commonCycle;
    this.currentCycle = commonCycle;
    this.numThread = numThread;
  }

  public void updateProgress(long computed) {
    this.currentCycle -= computed;
    System.out.println("Progress : " + (((commonCycle - currentCycle) * 100) / commonCycle) + " %");
  }
}
