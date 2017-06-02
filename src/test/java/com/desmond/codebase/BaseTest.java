package com.desmond.codebase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Best testing class.
 * Created by Li.Xiaochuan on 15/9/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/resources/applicationContext.xml")
public class BaseTest {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void simple() throws Exception {


    }
}
