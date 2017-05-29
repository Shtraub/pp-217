package lab8;


/**
 * Created by Виталий on 24.05.2017.
 */
public class Helper {

  private static volatile ThreadHelper instance;
  public static int experimentCount = 5000000;

  public static ThreadHelper getInstance() {
    if (instance == null) {
      synchronized (ThreadHelper.class) {
        if (instance == null) {
          instance = new ThreadHelper(experimentCount);
        }
      }
    }
    return instance;
  }
}
