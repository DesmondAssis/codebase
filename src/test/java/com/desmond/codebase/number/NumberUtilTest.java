package com.desmond.codebase.number;

import com.desmond.codebase.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Li.Xiaochuan on 15/10/31.
 */
public class NumberUtilTest extends BaseTest{

    @Test
    public void getDoubleByDigits_success_digits_equals_0() {
        double d = 12.2345;
        String value = NumberUtil.getDoubleByDigits(d, 0);
        Assert.assertEquals("12.2345", value);
    }

    @Test
    public void getDoubleByDigits_success_digits_equals_2() {
        double d = 12.2345;
        String value = NumberUtil.getDoubleByDigits(d, 2);
        Assert.assertEquals("12.23", value);
    }

    @Test
    public void getDoubleByDigits_success_digits_equals_3() {
        double d = 12.2345;
        String value = NumberUtil.getDoubleByDigits(d, 3);
        Assert.assertEquals("12.235", value);
    }
}
