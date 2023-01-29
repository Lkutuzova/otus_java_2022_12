package homework;

import homework.test.TestExample;
import homework.test.TestRunner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        TestRunner testRunner = new TestRunner();
        testRunner.runTest(TestExample.class.getName());
    }
}
