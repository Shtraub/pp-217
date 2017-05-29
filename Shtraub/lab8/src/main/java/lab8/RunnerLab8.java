package lab8;


/**
 * Created by Виталий on 17.05.2017.
 */
public class RunnerLab8 {

  public static void main(String[] args) {
    Manager manager = new Manager();
    manager.setNumThread(4);
    manager.startComputation();
  }
}
