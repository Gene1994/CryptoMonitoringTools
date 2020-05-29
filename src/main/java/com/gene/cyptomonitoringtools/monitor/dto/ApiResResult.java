package com.gene.cyptomonitoringtools.monitor.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiResResult {
    private String symbol;
    private long ts;
    private List<TradeData> data;
}
