package homework.aop;


public class Main {
    public static void main(String[] args){
        TestLoggingInterface testClass = Ioc.createTestLoggingProxy();
        testClass.calculation(1);
        testClass.calculation(1, 5);
        testClass.calculation(1, 5, 22);
    }
}
