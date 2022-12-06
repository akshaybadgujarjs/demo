package runner;

import lombok.extern.slf4j.Slf4j;
import org.testng.TestNG;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestNgRunner {

    public static void runTestWithPath(String jarPath, String className) throws ClassNotFoundException, MalformedURLException {
        log.info("runTestWithPath");

        URL jarFile = new File(jarPath).toURI().toURL();
        URL[] classLoaderUrls = new URL[]{jarFile};
        URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);
        List<String> classesList = new ArrayList<>();
        classesList.add(className);
        Class[] classes = new Class[classesList.size()];
        for (int i = 0; i < classesList.size(); i++) {
            Class<?> testClassName = urlClassLoader.loadClass(classesList.get(i));
            classes[i] = testClassName;
        }


        log.info("Creating Test ng object and adding classes");
        TestNG testNG = new TestNG();
        log.info("addListener");
        testNG.setTestClasses(classes);
        testNG.addListener(new CustomListener());
        log.info("setTestClasses");
        testNG.run();
        int status = testNG.getStatus();
        System.err.println("Status ===> " + status);
    }
}
