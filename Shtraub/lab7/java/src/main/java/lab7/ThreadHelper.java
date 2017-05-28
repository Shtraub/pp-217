package lab7;

/**
 * Created by Виталий on 17.05.2017.
 */
public class ThreadHelper {

  private double sum = 0.0;
  private double result = 0.0;
  public static int experimentCount;


  public ThreadHelper(int experimentCount) {

    this.experimentCount = experimentCount;
  }


  public synchronized void addToSum(Double data) {
    this.sum += data;
  }

  public void printProbability() {
    this.result = sum / experimentCount;
    System.out.println("Probability = " + result);
  }

  public double getResult() {
    return result;
  }
}
