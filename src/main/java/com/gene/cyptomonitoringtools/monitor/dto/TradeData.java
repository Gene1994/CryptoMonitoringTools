package com.gene.cyptomonitoringtools.monitor.dto;

import lombok.Data;

@Data
public class TradeData {
    private long id;
    private float amount;
    private double price;
    private String direction;
    private long ts;
}
