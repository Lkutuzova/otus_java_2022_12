package homework.test;

import homework.After;
import homework.Before;
import homework.Test;
import org.junit.jupiter.api.DisplayName;

public class TestExample {
    @Before
    public void before1(){
        System.out.println("------------------------------------");
        System.out.println("before1");
    }

    @Before
    public void before2(){
        System.out.println("before2");
    }

    @Test
    @DisplayName("наименование теста TEST1")
    public void test1(){
        System.out.println("test1");
    }

    @Test
    @DisplayName("наименование теста TEST2")
    public void test2(){
        System.out.println("test2");
    }

    @After
    public void after(){
        System.out.println("after");
    }
}
