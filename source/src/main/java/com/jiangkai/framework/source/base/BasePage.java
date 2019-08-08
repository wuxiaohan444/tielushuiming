package com.jiangkai.framework.source.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class BasePage implements Serializable {
    private Integer page;
    private Integer limit;
    private String order;
    private String sort;
    private Map foo;
}