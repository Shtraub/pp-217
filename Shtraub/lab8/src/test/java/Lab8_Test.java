import static org.junit.Assert.assertEquals;

import lab8.Manager;
import org.junit.Test;


/**
 * Created by Виталий on 24.05.2017.
 */

public class Lab8_Test {

  private double EXPECTED_RESULT = 0.088595;
  private double DELTA_PRECISION = 0.001;

  @Test
  public void startThreadsFactory() throws Exception {
    Manager manager = new Manager();
    for (int i = 2; i < 10; i++) {
      manager.setNumThread(i);
      long startTime = System.nanoTime();
      assertEquals(EXPECTED_RESULT, manager.startComputation(), DELTA_PRECISION);
      manager.ZeroingOfProbability();

      System.out.println("Time : " + (System.nanoTime() - startTime) / 1E9 + " s\n");
    }
  }
}

