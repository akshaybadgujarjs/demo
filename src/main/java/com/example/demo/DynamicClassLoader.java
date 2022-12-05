package com.example.demo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;

public class DynamicClassLoader extends URLClassLoader{

    private static final ClassLoader INSTANCE;

    static {
        try {
            INSTANCE = new DynamicClassLoader();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return instance
     */
    public static ClassLoader getInstance() {

        return INSTANCE;
    }

    private DynamicClassLoader() throws Exception {

        super(getAppClassLoaderUrls(), null);
    }

    private static URL[] getAppClassLoaderUrls() throws Exception {

        return getURLs(DynamicClassLoader.class.getClassLoader());
    }

    private static URL[] getURLs(ClassLoader classLoader) throws Exception {

        Class<?> clazz = classLoader.getClass();

        try {
            Field field = null;
            field = clazz.getDeclaredField("ucp");
            field.setAccessible(true);

            Object urlClassPath = field.get(classLoader);

            Method method = urlClassPath.getClass().getDeclaredMethod("getURLs", new Class[] {});
            method.setAccessible(true);
            URL[] urls = (URL[]) method.invoke(urlClassPath, new Object[] {});

            return urls;

        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
