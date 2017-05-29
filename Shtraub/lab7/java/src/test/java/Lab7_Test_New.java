import static org.junit.Assert.assertEquals;

import Lab7.Manager;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 * Created by Виталий on 24.05.2017.
 */
@RunWith(Parameterized.class)
public class Lab7_Test {

  private static double EXPECTED_RESULT = 0.088595;
  private double DELTA_PRECISION = 0.001;

  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {4}, {6}, {8}
    });
  }

  @Parameter
  public int fInput;

  @Test
  public void startThreadsFactory() throws Exception {
    Manager manager = new Manager(fInput);
    long startTime = System.nanoTime();
    assertEquals(EXPECTED_RESULT, manager.startComputation(), DELTA_PRECISION);
    manager.ZeroingOfProbability();
    System.out.println("Time : " + (System.nanoTime() - startTime) / 1E9 + " s");
  }
}

