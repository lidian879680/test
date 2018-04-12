package com.jcgroup.lidian.simpleTest.resourceTest;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: lidian
 * Date: 2018-04-08
 * Time: 14:46
 */
public class TestResource {

    public static void main(String args[]){
        TestResource t = new TestResource();
        try {
//            t.getResource1();
            t.getResource1();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getResource1() throws IOException {
        //从类的根路径查找，也就是com下开始
        Resource resource = new ClassPathResource("jdbc.properties");
        this.listResource(resource);
    }

    public void getResource2() throws IOException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        //这种方式，会按照class的路径查找jdbc1.properties
        Resource resource = new ClassPathResource("jdbc1.properties", this.getClass());
        this.listResource(resource);
    }

    public void getResource3() throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        //这个是一个统一的资源加载器，通过前缀classpath:, file:,等等各种资源
//        Resource resource = loader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "jdbc.properties");
        Resource resource = loader.getResource("file:jdbc.properties");
        this.listResource(resource);
    }

    public void getResource4() throws IOException {
        System.out.println(System.getProperty("java.class.path"));
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //由于这个文件在很多jar包中都有，包括jdk的jar包，而且找到第一个满足的，就会返回，所以返回的都是jar中的文件
//        Resource[] resources = resolver.getResources("classpath:META-INF/INDEX.LIST");
        //遍历所有META-INF路径，系统jar，外部jar，程序自身的包，返回所有的匹配文件
        Resource[] resources = resolver.getResources("classpath*:META-INF/INDEX.LIST");
        for(Resource resource : resources){
            this.listResource(resource);
        }
    }

    public void getResource5() throws IOException {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        //注意，如果使用通配符比如classpath*:asd-*.conf,将不会搜索jar中，除非包含根目录
        Resource[] resources = resolver.getResources("classpath*:SqlSessionUtils.class");
        for(Resource resource : resources){
            this.listResource(resource);
        }
    }

    private void listResource(Resource resource) throws IOException {
        InputStream reader = resource.getInputStream();
        byte[] cont = new byte[1024];
        int len = reader.read(cont);
        System.out.println("resource:" + new String(cont, 0, len));
        System.out.println("resource abs path:" + resource.getFile().getAbsolutePath());
        System.out.println("resource path:" + resource.getFile().getCanonicalPath());
        System.out.println("**********************");
    }


}
