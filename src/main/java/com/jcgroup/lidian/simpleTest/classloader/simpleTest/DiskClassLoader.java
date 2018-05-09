package com.jcgroup.lidian.simpleTest.classloader.simpleTest;

import java.io.*;

/**
 * <p>
 *
 * </p>
 *
 * @author dians
 * @date created in 2018/5/7 19:34
 * @since
 */
public class DiskClassLoader extends ClassLoader {

    private final String basePath;

    public DiskClassLoader(String basePath) {
        this.basePath = basePath;
    }

    /**
     * 注意，双亲委派模式，如果在父classloader的classPath中，可以找到该name的class文件，就不会调用自定义
     * classloader，因为已经委托父classloader加载到了
     * @param name
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        String className = getFilename(name);

        File file = new File(basePath, className);
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();

            int len;
            while((len = fis.read()) != -1){
                bos.write(len);
            }

            byte[] result = bos.toByteArray();

            //调用defineClass可以生成class类,注意，这里的name必须是全限定类名
            return defineClass(name, result, 0 ,result.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(fis != null) fis.close();
                if(bos != null) bos.close();
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        return super.findClass(name);
    }

    private String getFilename(String name){
        int index = name.lastIndexOf('.');
        if(index == -1){
            return name + ".class";
        }else {
            return name.substring(index + 1) + ".class";
        }
    }
}
