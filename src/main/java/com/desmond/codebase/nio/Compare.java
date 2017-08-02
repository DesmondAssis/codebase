package com.desmond.codebase.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by presleyli on 2017/7/6.
 */
public class Compare {

    static String fileName = "/Users/presleyli/java/desmond/codebase/src/main/java/com/desmond/codebase/nio/package-info.java";

    public static void main(String[] args) {
//        testOldIo();
        testOldIo();
    }

    private static void testOldIo() {
        InputStream in = null;

        try {
            in = new BufferedInputStream(new FileInputStream(fileName));
            byte[] buf = new byte[1024];

            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for(int i =0 ;i<bytesRead;i++) {
                    System.out.println((char)buf[i]);
                }
                bytesRead = in.read(buf);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testNewIo() {
        RandomAccessFile accessFile = null;

        try {
            accessFile = new RandomAccessFile(fileName, "rw");
            FileChannel fileChannel = accessFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);

            int byteRead = fileChannel.read(buf);
            while (byteRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.println((char)buf.get());
                }
                buf.compact();
                byteRead = fileChannel.read(buf);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(accessFile != null) {
                try {
                    accessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
