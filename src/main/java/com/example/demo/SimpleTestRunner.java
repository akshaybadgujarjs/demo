package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

@Slf4j
@Service
@Component
@Configurable
public class SimpleTestRunner {

    private static final String JARPATH =
            "jars/";

    public void runTestCase(JarRequest jarRequest) throws MalformedURLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        TestNG testNg = new TestNG();

        /*URLClassLoader customClassLoader = URLClassLoader.newInstance(new URL[]{getURL(jarRequest.getJarName())});
        testNg.addClassLoader(customClassLoader);*/
        /*URLClassLoader customClassLoader = URLClassLoader.newInstance(new URL[]{getURL(jarRequest.getJarName())});
        testNg.addClassLoader(getClassLoader(getURL(jarRequest.getJarName()).toString()));*/
        loadJar(getURL(jarRequest.jarName).toString());
        XmlSuite suite = new XmlSuite();
        suite.setName("Pulsar Tests Suite");

        XmlTest test = new XmlTest();
        test.setXmlSuite(suite);
        test.setName("Pulsar Test");
        test.setParallel(XmlSuite.ParallelMode.NONE);
        XmlClass xmlClass = new XmlClass(jarRequest.className);
        test.setClasses(Collections.singletonList(xmlClass));
        test.setVerbose(2);
        suite.setTests(Collections.singletonList(test));
        log.info(suite.toXml());
        testNg.setXmlSuites(Collections.singletonList(suite));

        testNg.addListener(new LocalListener());
        testNg.setVerbose(2);
        testNg.run();
        int status = testNg.getStatus();
        log.info("Status ===> {} ", status);
    }

    private void loadJar(String jarPath) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        jarPath = "/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar";
        URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(classLoader, new File(jarPath).toURI().toURL());
    }


    private static URL getURL(String jarName) {
        try {
            URL url = new File(JARPATH+File.separator+jarName).toURI().toURL();
            log.info("Working with URL {}", url);
            return url;
        } catch (MalformedURLException e) {
            log.error("Could not load URL. Root cause: {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static class LocalListener implements IInvokedMethodListener {

        @Override
        public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
            log.info("About to run {}", method.getTestMethod().getQualifiedName());
        }
    }

    public static ClassLoader getClassLoader(String url) {
        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            URLClassLoader classLoader = new URLClassLoader(new URL[]{}, ClassLoader.getSystemClassLoader());
            method.invoke(classLoader, new URL(url));
            return classLoader;
        } catch (Exception e) {
            log.error("getClassLoader-error", e);
            return null;
        }
    }
}
