package homework.aop;


public class TestLoggingImpl implements TestLoggingInterface {

    @Log
    public void calculation(int param1){
        System.out.printf("print extra message, Param: %d%n", param1);
    }
    @Log
    public void calculation(int param1, int param2){
        System.out.printf("print extra message, Params: %d %d%n", param1 , param2);
    }
    //проверка отсутствия логгирования --@Log
    public void calculation(int param1, int param2, int param3){
        System.out.printf("print extra message, Params: %d %d %d%n", param1 , param2, param3);
    }

}
