import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StartTest {
    public static void start(Class c) {
        List<Method> methods = new ArrayList<>();
        Method[] classMethods = c.getDeclaredMethods();

        for (Method m: classMethods) {
            if(m.isAnnotationPresent(Test.class)) {
                methods.add(m);
            }
        }
        methods.sort(Comparator.comparing((Method i) -> i.getAnnotation(Test.class).priority()).reversed());

        for (Method m: classMethods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (methods.size() > 0 && methods.get(0).isAnnotationPresent(BeforeSuite.class)) {
                    throw new RuntimeException("BeforeSuite Annotation method > 1");
                }
                methods.add(0, m);
            }
        }

        for (Method m: classMethods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (methods.size() > 0 && methods.get(0).isAnnotationPresent(AfterSuite.class)) {
                    throw new RuntimeException("AfterSuite Annotation method > 1");
                }
                methods.add(m);
            }
        }

        for (Method m: methods) {
            try{
                m.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
