package lab8;

/**
 * Created by Виталий on 24.05.2017.
 */
public class Progressbar {

  private long commonCycle;
  private volatile long currentCycle;


  public Progressbar(long commonCycle) {
    this.commonCycle = commonCycle;
    this.currentCycle = commonCycle;
  }

  public void updateProgress(long computed) {
    this.currentCycle -= computed;
    System.out.println("Progress : " + (((commonCycle - currentCycle) * 100) / commonCycle) + " %");
  }
}
