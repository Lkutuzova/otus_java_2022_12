package homework.test;

import homework.After;
import homework.Before;
import homework.Test;
import org.junit.jupiter.api.DisplayName;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {
    private List<Method> testMethodList = new ArrayList<>();
    private List<Method> beforeMethodList = new ArrayList<>();
    private List<Method> afterMethodList = new ArrayList<>();

    public void runTest(String className)throws ClassNotFoundException, NoSuchMethodException {
        Class<?> clazz = Class.forName(className);
        Method[] methods = clazz.getDeclaredMethods();

        sortingMethods(methods);
        executeMethods(clazz);
    }

    private void sortingMethods (Method[] methods) {
        for (Method method: methods) {
             if (method.isAnnotationPresent(Before.class)) {
                 beforeMethodList.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                testMethodList.add(method);
            }
            if (method.isAnnotationPresent(After.class)) {
                afterMethodList.add(method);
            }
        }
    }

    private void executeMethods (Class<?> clazz) {
        Object object = null;
        int successBefore = 0;
        int successAfter = 0;
        int successTest = 0;
        int successMethod = 0;
        int allTestsPerMethod = 1 + beforeMethodList.size() + afterMethodList.size();
        int allSize = testMethodList.size() *( 1 + beforeMethodList.size() + afterMethodList.size());
        int allSuccess = 0;
        String documentation = "";

        for (Method method : testMethodList) {
            try {
                object = clazz.getDeclaredConstructor().newInstance();
                successBefore = runMethodsList(object, beforeMethodList);
                method.invoke(object);
                successTest++;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    successAfter = runMethodsList(object, afterMethodList);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                successMethod = 1 + successBefore + successAfter;

               if(method.isAnnotationPresent(DisplayName.class)) {
                    documentation = method.getDeclaredAnnotation(DisplayName.class).value();
               }else{
                   documentation = "";
               }
                printStatistic( method.getName() + " " + documentation, successMethod, allTestsPerMethod);
                allSuccess = allSuccess + successMethod;
            }
        }
        printStatisticAll( clazz.getName(),allSuccess, allSize);
    }

    private static int runMethodsList(Object object, List<Method> methods) throws InvocationTargetException, IllegalAccessException {
        int cnt = 0;
        for(Method m : methods) {
            m.invoke(object);
            cnt++;
        }
        return cnt;
    }

    private static void printStatistic(String methodMame, int countSuccess, int allSize) {
        System.out.println("Tested method " + methodMame);
        System.out.println("All = " + allSize);
        System.out.println("Success = " + countSuccess);
        System.out.println("Failed = " + (allSize - countSuccess));
    }

    private static void printStatisticAll(String naim, int countSuccess, int allSize) {
        System.out.println("Tested at all -----------------------------------------------------" + naim);
        System.out.println("All = " + allSize);
        System.out.println("Success = " + countSuccess);
        System.out.println("Failed = " + (allSize - countSuccess));
    }
}
