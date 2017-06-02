package com.desmond.codebase.bean;

/**
 * Created by Li.Xiaochuan on 16/3/16.
 */
public class TestBean {

    public static void main(String[] args) {
        SourceBean sourceBean = new SourceBean();
        sourceBean.setVisitPage("page");
        sourceBean.setId(1l);
        sourceBean.setPhoneId(2);
        sourceBean.setIsTrue(true);
        sourceBean.setTestInt(100);
        sourceBean.setType((short) 2);

        TargetBean targetBean = ParseUtil.parse(sourceBean, new TargetBean());
        System.out.println(targetBean);
    }
}
