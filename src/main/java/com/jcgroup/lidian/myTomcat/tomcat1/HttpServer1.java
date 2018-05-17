package com.jcgroup.lidian.myTomcat.tomcat1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * <p>
 * 这里写有象视频代码描述
 * </p>
 *
 * @author dians
 * @date created in 2018/5/15 15:58
 * @since
 */
public class HttpServer1 {

    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";

    private boolean shutdown = false;

    public static void main(String args[]) {
        HttpServer1 server1 = new HttpServer1();
        server1.await();
    }

    public void await() {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while(!shutdown){
            Socket socket = null;
            InputStream input = null;
            OutputStream output = null;
            try {
                socket = serverSocket.accept();
                input = socket.getInputStream();
                output = socket.getOutputStream();

                // create Request object and parse
                Request request = new Request(input);
                request.parse();

                // create Response object
                Response response = new Response(output);
                response.setRequest(request);
                if(request.getUri().startsWith("/servlet/")){
                    ServletProcessor processor = new ServletProcessor();
                    processor.processor(request, response);
                }else {
                    StaticResourceProcessor processor = new StaticResourceProcessor();
                    processor.process(request, response);
                }

                // Close the socket
                socket.close();

                //check if the previous URI is a shutdown command
                shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
            }
            catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }
}
