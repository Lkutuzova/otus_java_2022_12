package homework.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.*;

public class Ioc {
    private Ioc() {
    }

    static TestLoggingInterface createTestLoggingProxy() {
        InvocationHandler handler = new LogInvocationHandler(new TestLoggingImpl());
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }
    static class LogInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private final List<Method> annotatedMethodList;

        LogInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            List<Method> annotatedMethodList = new ArrayList<>();
            for (Method method: myClass.getClass().getDeclaredMethods()) {
                for (Annotation annotation: method.getDeclaredAnnotations()) {
                    if (annotation.annotationType() == Log.class) {
                        annotatedMethodList.add(method);
                    }
                }
            }
            this.annotatedMethodList = annotatedMethodList;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            for (Method annotatedMethod: annotatedMethodList) {
                if (annotatedMethod.getName().equals(method.getName()) &&
                        Arrays.toString(annotatedMethod.getParameterTypes()).equals(Arrays.toString(method.getParameterTypes()))) {
                    System.out.println("executed method: " + method.getName() + ", params: " + Arrays.toString(args));
                    break;
                }
            }
            return method.invoke(myClass, args);
        }
    }
}
