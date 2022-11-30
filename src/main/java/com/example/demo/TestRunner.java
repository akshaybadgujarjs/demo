package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.net.ssl.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
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

    @Autowired
    private CustomListener customListener;

    public void triggerTestFromRequest() {
        try {
           /* URL jarFile = new File("/Users/akshay.badgujar_js/IdeaProjects/demo/test-0.0.1-SNAPSHOT.jar").toURI().toURL();

            URL[] classLoaderUrls = new URL[]{jarFile};
            URLClassLoader urlClassLoader = new URLClassLoader(classLoaderUrls);

            *//*InputStream in = urlClassLoader.getResourceAsStream("pulsar.xml");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(in);

            //Get al nodes with tag class
            NodeList nodeList = doc.getElementsByTagName("class");
            //Iterate over all nodes with tag class
            List<String> classesList = new ArrayList<>();
            log.info("Extracting classed from xml passed");
            for (int i = 0; i < nodeList.getLength(); i++) {
                //Get the nodes incrementally
                Node node = nodeList.item(i);
                if (node.hasAttributes()) {
                    //Get all attributed for that class tag
                    NamedNodeMap namedNodeMap = node.getAttributes();
                    //Get the attribute with name "name"
                    Node className = namedNodeMap.getNamedItem("name");
                    log.info("className => "+ className.getNodeName());
                    //Get its value which will be our class name
                    classesList.add(className.getTextContent());
                }
            }*//*

            *//*log.info("Classes Extracted " + Arrays.toString(classesList.toArray()));

            Element xmlDoc = doc.getDocumentElement();
            NodeList listenersList =  xmlDoc.getElementsByTagName("listeners");
            if(listenersList!=null){
                if(listenersList.getLength() > 0){
                    for (int i = 0; i < listenersList.getLength(); i++) {
                        if(i== listenersList.getLength()-1){
                            Element listener = doc.createElement("listener");
                            listener.setAttribute("class-name","com.swiggy.testexecutor.common.utils.CustomListener");
                            Node listenerNode = listenersList.item(i);
                            listenerNode.getParentNode().appendChild(listener);
                        }
                    }
                }else {
                    //TODO: Handle listeners tag present but no element inside it
                }
            }else {
                //TODO: Handle listeners tag not present part
            }

            logDocument(doc);*//*
            *//*
            Setting class in suites
             *//*

            List<String> classesList = new ArrayList<>();
            classesList.add("com.example.test.PulsarTestsSample");
            Class[] classes = new Class[classesList.size()];
            for (int i = 0; i < classesList.size(); i++) {
                Class<?> testClassName = urlClassLoader.loadClass(classesList.get(i));
                classes[i] = testClassName;
            }

            log.info("Creating Test ng object and adding classes");*/

            /*TestNG testNG = new TestNG();
            testNG.addListener(customListener);
//            testNG.setXmlPathInJar(request.getXmlFiles(0));
//            testNG.setTestJar(saveDir + File.separator + fileName);
            log.info("addListener");
//            testNG.setTestClasses(classes);
//            testNG.setTestClasses(new Class[] { classes[0].newInstance().getClass()});
            testNG.setTestClasses(new Class[] { classes[0].newInstance().getClass()});
//            testNG.setTestClasses(new Class[] {Class.forName("com.swiggy.test.tests.vendor.PulsarTests").newInstance().getClass()});
            log.info("setTestClasses");
            testNG.run();*/



            /*XmlSuite suite = new XmlSuite();
            suite.setName("PulsarTests");

            XmlTest test = new XmlTest(suite);
            test.setName("PulsarTests");
            List<XmlClass> classes2 = new ArrayList<XmlClass>();
            classes2.add(new XmlClass("com.example.test.PulsarTestsSample"));
            test.setXmlClasses(classes2) ;

            List<XmlSuite> suites = new ArrayList<XmlSuite>();
            suites.add(suite);
            TestNG tng = new TestNG();
            URLClassLoader customClassLoader = URLClassLoader.newInstance(new URL[]{new File("/Users/akshay.badgujar_js/IdeaProjects/demo/test-0.0.1-SNAPSHOT.jar").toURI().toURL()});
            tng.addClassLoader(customClassLoader);
            tng.setXmlSuites(suites);
            tng.addListener(customListener);
            tng.run();*/


            TestNG testNg = new TestNG();
            URLClassLoader customClassLoader = URLClassLoader.newInstance(new URL[]{new File("/Users/akshay.badgujar_js/IdeaProjects/demo/test-0.0.1-SNAPSHOT.jar").toURI().toURL()});
            testNg.addClassLoader(customClassLoader);
//            testNg.addListener(customListener);
            XmlSuite suite = new XmlSuite();
            suite.setName("Pulsar Tests Suite");

            XmlTest test = new XmlTest();
            test.setXmlSuite(suite);
            test.setName("Pulsar Test");
            test.setParallel(XmlSuite.ParallelMode.NONE);
            XmlClass xmlClass = new XmlClass("com.example.test.PulsarTestsSample");
            test.setClasses(Collections.singletonList(xmlClass));
            test.setVerbose(2);
            suite.setTests(Collections.singletonList(test));
            System.err.println(suite.toXml());
            testNg.setXmlSuites(Collections.singletonList(suite));

//            testNg.setTestJar("/Users/akshay.badgujar_js/IdeaProjects/demo/test-0.0.1-SNAPSHOT.jar");
//            testNg.setXmlPathInJar("vendor/Pulsar.xml");

            testNg.setVerbose(2);
            testNg.run();
            int status = testNg.getStatus();
            System.err.println("Status ===> " + status);


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
}

