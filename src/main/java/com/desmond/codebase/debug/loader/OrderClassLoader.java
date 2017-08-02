package com.desmond.codebase.debug.loader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Created by presleyli on 2017/7/5.
 */
public class OrderClassLoader extends ClassLoader {
    private String filePath;

    public OrderClassLoader(String path) {
        this.filePath = path;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class clazz = findClass(name);

        if(null == clazz) {
            System.out.println("无法载入:" + name + ",需要父加载器");
            return super.loadClass(name);
        }

        return clazz;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        Class clazz = this.findLoadedClass(className);

        if(null == clazz) {
            try {
                String classFile = getClassFile(className);

                FileInputStream fis = new FileInputStream(classFile);
                byte[] bytes = new byte[fis.available()];
                fis.read(bytes);

                fis.close();

                clazz = defineClass(className, bytes, 0, bytes.length);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return clazz;
    }

    private String getClassFile(String className) {
        return filePath + className.replace(".", "/") + ".class";
    }
}
