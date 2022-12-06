package demo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import runner.TestNgRunner;

import javax.net.ssl.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.springframework.util.FileCopyUtils.BUFFER_SIZE;

@Slf4j
@Service
@Component
@Configurable
public class TestRunner {

    public void triggerTestFromRequestForFirstRepo() {
        try {
            URL jarFile = new File("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar").toURI().toURL();
//            addPath("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar");

            loadClassesFromJar("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar");

            URL[] classLoaderUrls = new URL[]{jarFile};
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
            List<String> classesList = new ArrayList<>();
            classesList.add("com.example.test.PulsarTestsSample");
            Class[] classes = new Class[classesList.size()];
            for (int i = 0; i < classesList.size(); i++) {
                Class<?> testClassName = urlClassLoader.loadClass(classesList.get(i));
                classes[i] = testClassName;
            }

            Class classToLoad = Class.forName("com.example.test.PulsarTestsSample", true, urlClassLoader);
            Method sampleTest = classToLoad.getDeclaredMethod("sampleTest");
            Method runTest = classToLoad.getDeclaredMethod("runTest");
            Class[] cArg = new Class[1];
            cArg[0] = String.class;
            Method runTestWithPath = classToLoad.getDeclaredMethod("runTestWithPath", cArg);
            log.info("pulsar test sample instance created");
            Object instance = classToLoad.newInstance();
            log.info("calling sample test method");
            sampleTest.invoke(instance);
            log.info("calling run test method");
            runTest.invoke(instance);
            log.info("calling runTestWithPath method");
            runTestWithPath.invoke(instance,"/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar");


            /*log.info("Creating Test ng object and adding classes");
            TestNG testNG = new TestNG();
            testNG.addListener(customListener);
            log.info("addListener");
            testNG.setTestClasses(classes);
            log.info("setTestClasses");
            testNG.run();
            int status = testNG.getStatus();
            System.err.println("Status ===> " + status);*/

            /*URLClassLoader customClassLoader = URLClassLoader.newInstance(new URL[]{new File("/Users/akshay.badgujar_js/IdeaProjects/demo/test-0.0.1-SNAPSHOT.jar").toURI().toURL()});
            Optional<Class<?>> oTestClass = findClass("SomeCriteria", customClassLoader);
            Class<?> testClass = oTestClass.orElseThrow(IOException::new);

            XmlSuite suite = new XmlSuite();
            suite.setName("Invoked Run");

            XmlClass xmlClass = new XmlClass(testClass);

            XmlTest test = new XmlTest(suite);
            test.setName("Invoked Run");
            test.setParallel(XmlSuite.ParallelMode.NONE);
            test.setXmlClasses(Collections.singletonList(xmlClass));

            TestNG testNg = new TestNG();
            testNg.setXmlSuites(Collections.singletonList(suite));

            testNg.run();
            int status = testNg.getStatus();
            System.err.println("Status ===> " + status);*/
        }
        catch (Exception e) {
            log.info("Caught an exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void triggerTestFromRequestForSecondRepo() {
        try {
            URL jarFile = new File("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/secondTest-0.0.1-SNAPSHOT-plain.jar").toURI().toURL();
//            addPath("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar");

            loadClassesFromJar("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/secondTest-0.0.1-SNAPSHOT-plain.jar");

            URL[] classLoaderUrls = new URL[]{jarFile};
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
            List<String> classesList = new ArrayList<>();
            classesList.add("com.example.secondTest.SecondTestsSample");
            Class[] classes = new Class[classesList.size()];
            for (int i = 0; i < classesList.size(); i++) {
                Class<?> testClassName = urlClassLoader.loadClass(classesList.get(i));
                classes[i] = testClassName;
            }

            Class classToLoad = Class.forName("com.example.secondTest.SecondTestsSample", true, urlClassLoader);
            Method sampleTest = classToLoad.getDeclaredMethod("sampleTest");
            Method runTest = classToLoad.getDeclaredMethod("runTest");
            Class[] cArg = new Class[1];
            cArg[0] = String.class;
            Method runTestWithPath = classToLoad.getDeclaredMethod("runTestWithPath", cArg);
            log.info("pulsar test sample instance created");
            Object instance = classToLoad.newInstance();
            log.info("calling sample test method");
            sampleTest.invoke(instance);
            log.info("calling run test method");
            runTest.invoke(instance);
            log.info("calling runTestWithPath method");
            runTestWithPath.invoke(instance,"/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar");


            /*log.info("Creating Test ng object and adding classes");
            TestNG testNG = new TestNG();
            testNG.addListener(customListener);
            log.info("addListener");
            testNG.setTestClasses(classes);
            log.info("setTestClasses");
            testNG.run();
            int status = testNG.getStatus();
            System.err.println("Status ===> " + status);*/

            /*URLClassLoader customClassLoader = URLClassLoader.newInstance(new URL[]{new File("/Users/akshay.badgujar_js/IdeaProjects/demo/test-0.0.1-SNAPSHOT.jar").toURI().toURL()});
            Optional<Class<?>> oTestClass = findClass("SomeCriteria", customClassLoader);
            Class<?> testClass = oTestClass.orElseThrow(IOException::new);

            XmlSuite suite = new XmlSuite();
            suite.setName("Invoked Run");

            XmlClass xmlClass = new XmlClass(testClass);

            XmlTest test = new XmlTest(suite);
            test.setName("Invoked Run");
            test.setParallel(XmlSuite.ParallelMode.NONE);
            test.setXmlClasses(Collections.singletonList(xmlClass));

            TestNG testNg = new TestNG();
            testNg.setXmlSuites(Collections.singletonList(suite));

            testNg.run();
            int status = testNg.getStatus();
            System.err.println("Status ===> " + status);*/
        }
        catch (Exception e) {
            log.info("Caught an exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void triggerTestFromRequestForThirdRepo() {
        try {
//            URL jarFile = new File("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/runner-0.0.1-SNAPSHOT-plain.jar").toURI().toURL();
//            addPath("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar");

//            loadClassesFromJar("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/runner-0.0.1-SNAPSHOT-plain.jar");


            loadClassesFromJar("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar");

            /*URL[] classLoaderUrls = new URL[]{jarFile};
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

            Class classToLoad = Class.forName("com.example.runner.TestNgRunner", true, urlClassLoader);
            Class[] cArg = new Class[2];
            cArg[0] = String.class;
            cArg[1] = String.class;
            Method runTestWithPath = classToLoad.getDeclaredMethod("runTestWithPath", cArg);
            log.info("runner test sample instance created");
            Object instance = classToLoad.newInstance();
            log.info("calling runTestWithPath method");
            runTestWithPath.invoke(instance,"/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar","com.example.test.PulsarTestsSample");*/

            TestNgRunner.runTestWithPath("/Users/akshay.badgujar_js/IdeaProjects/demo/jars/test-0.0.1-SNAPSHOT.jar","com.example.test.PulsarTestsSample");

            /*log.info("Creating Test ng object and adding classes");
            TestNG testNG = new TestNG();
            testNG.addListener(customListener);
            log.info("addListener");
            testNG.setTestClasses(classes);
            log.info("setTestClasses");
            testNG.run();
            int status = testNG.getStatus();
            System.err.println("Status ===> " + status);*/

            /*URLClassLoader customClassLoader = URLClassLoader.newInstance(new URL[]{new File("/Users/akshay.badgujar_js/IdeaProjects/demo/test-0.0.1-SNAPSHOT.jar").toURI().toURL()});
            Optional<Class<?>> oTestClass = findClass("SomeCriteria", customClassLoader);
            Class<?> testClass = oTestClass.orElseThrow(IOException::new);

            XmlSuite suite = new XmlSuite();
            suite.setName("Invoked Run");

            XmlClass xmlClass = new XmlClass(testClass);

            XmlTest test = new XmlTest(suite);
            test.setName("Invoked Run");
            test.setParallel(XmlSuite.ParallelMode.NONE);
            test.setXmlClasses(Collections.singletonList(xmlClass));

            TestNG testNg = new TestNG();
            testNg.setXmlSuites(Collections.singletonList(suite));

            testNg.run();
            int status = testNg.getStatus();
            System.err.println("Status ===> " + status);*/
        }
        catch (Exception e) {
            log.info("Caught an exception " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void logDocument(Document doc) {
        NodeList listeners = doc.getElementsByTagName("listener");
        log.info("Logging document ::");
        for (int i = 0; i < listeners.getLength(); i++) {
            Node listener = listeners.item(i);
            NamedNodeMap attributes = listener.getAttributes();
            log.info("<listener> => "+attributes.getNamedItem("class-name").getNodeValue());
        }
    }

    public static void addPath(String s) throws Exception {

        log.info("Adding " + s + " to classpath");
        File f = new File(s);
        URL u = f.toURI().toURL();

        URLClassLoader urlClassLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Class urlClass = URLClassLoader.class;
        Method method = urlClass.getDeclaredMethod("addURL", new Class[]{URL.class});
        method.setAccessible(true);
        method.invoke(urlClassLoader, new Object[]{u});
    }

    public static void loadClassesFromJar(String jarPath) throws MalformedURLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        File file = new File(jarPath);
        URL url = file.toURI().toURL();

        URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        method.invoke(classLoader, url);
    }
}

