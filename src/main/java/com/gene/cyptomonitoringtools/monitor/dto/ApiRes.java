package com.gene.cyptomonitoringtools.monitor.dto;

import lombok.Data;

@Data
public class ApiRes {
    private int errno;
    private String errmsg;
    private ApiResResult result;
}
