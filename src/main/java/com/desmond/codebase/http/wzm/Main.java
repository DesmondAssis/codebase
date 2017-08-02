package com.desmond.codebase.http.wzm;

import com.desmond.codebase.log.HelloWorld;
import com.desmond.codebase.string.StringUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Li.Xiaochuan on 17/3/6.
 */
public class Main {
    private static Logger LOGGER = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) {

        String url = "http://127.0.0.1:8080/system/record2";
        String content = "[{\"visit_time\":12345678,\"leave_time\":12345578,\"visit_type\":\"page\",\"visit_page\":\"page1\"},{\"visit_time\":12345678,\"leave_time\":12345578,\"visit_type\":\"page\",\"visit_page\":\"page2\"},{\"visit_time\":12345678,\"leave_time\":12345578,\"visit_type\":\"page\",\"visit_page\":\"page3\"}]";

        sendHttp(url, content);
    }


    public static void sendHttp(String url, String message) {
        HttpClient httpClient = new HttpClient();

        if (StringUtils.isBlank(message)) {
            LOGGER.info("a blank message, return.");
            return;
        }
        PostMethod postMethod = new PostMethod(url);
        postMethod.setContentChunked(true);
        postMethod.setRequestHeader("Content-Encoding", "gzip");
        postMethod.setRequestHeader("Transfer-Encoding", "chunked");

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            GZIPOutputStream gzipOut = new GZIPOutputStream(baos);
            gzipOut.write(message.getBytes(Charset.forName("UTF-8")));

            gzipOut.flush();
            gzipOut.finish();

            postMethod.setRequestEntity(new ByteArrayRequestEntity(baos.toByteArray()
                    , "application/json;charset=utf-8"));

        } catch (IOException e) {
            LOGGER.error("write message fail.", e);
            return;
        }

        int retry = 0;
        do {
            try {
                int status = httpClient.executeMethod(postMethod);
                if (HttpStatus.SC_OK == status) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("send http success, url=" + url
                                + ", content=" + message);
                    }
                    return;
                } else {
                    String rsp = postMethod.getResponseBodyAsString();
                    LOGGER.error("send http fail, status is: " + status
                            + ", response is: " + rsp);
                }
            } catch (HttpException e) {
                LOGGER.info("http exception when send http.", e);
            } catch (IOException e) {
                LOGGER.info("io exception when send http.", e);
            } finally {
                postMethod.releaseConnection();
            }
            LOGGER.info("this is " + retry + " time, try next");
        } while (retry++ < 3);
    }
}
