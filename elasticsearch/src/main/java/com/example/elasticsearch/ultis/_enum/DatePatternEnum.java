package com.example.elasticsearch.ultis._enum;

public enum DatePatternEnum implements DatePattern {
    MM_YYYY("MMYYYY"),
    DD_MM_YYYY_HHMMSS("DDMMYYYYHHMMSS"),
    DD_MM_YYYY("DDMMYYYY")
    ;
    DatePatternEnum(String code){
        this.code = code;
    }
    private String code;
    @Override
    public String getCode() {
        return this.code;
    }
}
