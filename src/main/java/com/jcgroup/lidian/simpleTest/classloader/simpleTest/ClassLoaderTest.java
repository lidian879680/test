package com.jcgroup.lidian.simpleTest.classloader.simpleTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <p>
 * 测试方法，把test.java拷贝到d：
 * </p>
 *
 * @author dians
 * @date created in 2018/5/8 9:15
 * @since
 */
public class ClassLoaderTest {
    public static void main(String args[]){
        System.out.println(System.getProperty("user.dir"));
        System.out.println(System.getProperty("user.home"));
        System.out.println(System.getProperty("------------------"));
        String classPath = System.getProperty("java.class.path");
        String[] classPaths = classPath.split(";");
        for(String c : classPaths){
            System.out.println(c);
        }

        System.out.println(System.getProperty("------------------"));

        ClassLoaderTest classLoaderTest = new ClassLoaderTest();
//        String currentPath = classLoaderTest.getClass().getResource("").getPath();
//        System.out.println(currentPath);
//        ClassLoader c = new DiskClassLoader(currentPath);
        //一个自定义classloader如果不指定parent，则默认appclassloader是父
        ClassLoader c = new DiskClassLoader("d:");

        try {
            //注意，调用loadclass的时候，父classloader需要通过全限定名才能在classpath中找到该class
            //注意，这里必须使用全类名
            Class test = c.loadClass("com.jcgroup.lidian.simpleTest.classloader.simpleTest.Test");
            Method method = test.getDeclaredMethod("sayHelle", null);
            Object obj = test.newInstance();
            method.invoke(obj);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
