package com.geek.java.study.homeWork.week_01;

import lombok.SneakyThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader{


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = new MyClassLoader().findClass("Hello");

        Object o = aClass.newInstance();
        Method hello = aClass.getMethod("hello");
        hello.invoke(o);
    }

    @SneakyThrows
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String path="F:/"+name+".xlass";
        InputStream inputStream = new FileInputStream(path);
        byte[] bytes = toByteArray(inputStream);
        byte[] convert = new byte[1024];
        for (int i = 0;i<bytes.length;i++){
            convert [i] = (byte)(255-bytes[i]);
        }
        return defineClass(name, convert, 0, bytes.length);
    }

    private byte[] toByteArray(InputStream in) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] arr = new byte[1024];
        int start ;
        while ((start = in.read(arr)) != -1) {
                out.write(arr,0,start);
            }
        return out.toByteArray();
    }
}
