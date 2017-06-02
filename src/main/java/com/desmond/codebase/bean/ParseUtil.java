package com.desmond.codebase.bean;

import org.springframework.beans.BeanUtils;

public class ParseUtil {

    public static <S,T> T parse(S source, T target){
        if(source != null){
            BeanUtils.copyProperties(source, target);
            return target;
        }else{
            return null;
        }
    }
}