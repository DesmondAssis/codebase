package com.desmond.codebase.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Li.Xiaochuan on 15/10/15.
 */
public class HelloWorld {
    public static void main(String[] args) {
        Logger _log = LoggerFactory.getLogger(HelloWorld.class);
        _log.debug("Hello, World");
    }
}
