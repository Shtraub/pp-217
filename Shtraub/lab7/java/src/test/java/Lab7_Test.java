import static org.junit.Assert.assertEquals;

import lab7.Manager;
import org.junit.Test;

/**
 * Created by Виталий on 24.05.2017.
 */
public class Lab7_Test {


  private double EXPECTED_RESULT = 0.088595;
  private double DELTA_PRECISION = 0.001;
  private int numThread = 4;

  @Test
  public void startThreadsFactory() throws Exception {
    Manager manager = new Manager(numThread);
    long startTime = System.nanoTime();
    assertEquals(EXPECTED_RESULT, manager.startComputation(), DELTA_PRECISION);
    System.out.println("Time : " + (System.nanoTime() - startTime) / 1E9 + " s");
  }
}
