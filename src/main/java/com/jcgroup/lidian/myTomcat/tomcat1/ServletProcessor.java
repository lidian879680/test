package com.jcgroup.lidian.myTomcat.tomcat1;

import org.springframework.beans.factory.annotation.Configurable;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;
import java.net.*;

/**
 * <p>
 * 这里写有象视频代码描述
 * </p>
 *
 * @author dians
 * @date created in 2018/5/16 11:11
 * @since
 */
public class ServletProcessor {

    public void processor(Request request, Response response){
        String url = request.getUri();
        String servletName = url.substring(url.lastIndexOf('/') + 1);
        URL[] uris = new URL[1];
        URLStreamHandler streamHandler = null;
        File classPath = new File(Constants.WEB_ROOT);
        try {
            String repository = (new URL("file", null, classPath.getCanonicalPath() + File.separator)).toString();
            uris[0] = new URL(null, repository, streamHandler);
            URLClassLoader classLoader = new URLClassLoader(uris);
            Class aClass = classLoader.loadClass(servletName);
            Servlet servlet = (Servlet) aClass.newInstance();
            servlet.service(request, response);
        }  catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }


    }
}
