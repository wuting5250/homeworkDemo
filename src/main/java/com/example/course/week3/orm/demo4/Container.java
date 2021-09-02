package com.example.course.week3.orm.demo4;

import com.example.course.week3.orm.demo4.ioc.Autowired;
import com.example.course.week3.orm.demo4.ioc.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Container {
    /**
     * Dependency injection => IOC (IOC container)
     * why IOC ?
     *
     * @Component -> mark bean -> container
     * @Autowired -> inject bean
     */
    private final Map<String, Object> objectFactory = new HashMap<>();

    public static void start() throws Exception {
        Container c = new Container();
        List<Class<?>> classes = c.scan();
        c.register(classes);
        c.injectObjects(classes);
    }

    private List<Class<?>> scan() {
        return Arrays.asList(StuMapperImpl.class, Starter.class);
    }

    private boolean register(List<Class<?>> classes) throws Exception {
        for (Class<?> clazz : classes) {
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation a : annotations) {
                if (a.annotationType() == Component.class) {
                    objectFactory.put(clazz.getSimpleName(), clazz.getDeclaredConstructor(null).newInstance());
                }
            }
        }
        return true;
    }

    private boolean injectObjects(List<Class<?>> classes) throws Exception {
        for (Class<?> clazz : classes) {
            Field[] fields = clazz.getDeclaredFields();
            Object curInstance = objectFactory.get(clazz.getSimpleName());
            for (Field f : fields) {
                Annotation[] annotations = f.getAnnotations();
                for (Annotation a : annotations) {
                    if (a.annotationType() == Autowired.class) {
                        Class<?> type = f.getType();
                        Object injectInstance = objectFactory.get(type.getSimpleName());
                        f.setAccessible(true);
                        f.set(curInstance, injectInstance);
                    }
                }
            }
        }
        return true;
    }
}
