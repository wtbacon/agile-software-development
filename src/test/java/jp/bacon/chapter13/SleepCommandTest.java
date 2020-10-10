package jp.bacon.chapter13;

import junit.framework.*;
import junit.swingui.TestRunner;

public class SleepCommandTest extends TestCase {

  public static void main(String[] args) {
    TestRunner.main(new String[]{"jp.bacon.chapter13.SleepCommandTest"});
  }

  public SleepCommandTest(String name) {
    super(name);
  }

  private boolean commandExecuted = false;

  public void testSleep() throws Exception {
    Command wakeup = new Command() {
      public void execute() {
        commandExecuted = true;
      }
    };
    ActiveObjectEngine e = new ActiveObjectEngine();
    SleepCommand c = new SleepCommand(1001, e, wakeup);
    e.addCommand(c);
    long start = System.currentTimeMillis();
    e.run();
    long stop = System.currentTimeMillis();
    long sleepTime = (stop - start);
    assertTrue("SleepTime " + sleepTime + " expected > 1000", sleepTime > 1000);
    assertTrue("SleepTime " + sleepTime + " expected < 1100", sleepTime < 1100);
    assertTrue("Command Executed", commandExecuted);
  }
}
