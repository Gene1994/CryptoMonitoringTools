package com.gene.cyptomonitoringtools.monitor.scheduler;

import com.alibaba.fastjson.JSON;
import com.gene.cyptomonitoringtools.monitor.dto.ApiRes;
import com.gene.cyptomonitoringtools.monitor.dto.TradeData;
import com.gene.cyptomonitoringtools.utils.HttpUtil;
import com.gene.cyptomonitoringtools.utils.HttpsUtil;
import com.gene.cyptomonitoringtools.utils.OkHttpUtil;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class PriceMonitoringScheduleThread implements Runnable {
    private static final String BASE_URL = "https://openapi.aofex.com";

    private static final String BTC_TRADE = "/openApi/market/trade?symbol=BTC-USDT";

    @Override
    public void run() {
        ApiRes apiRes = null;
        byte[] bytes = new byte[0];
        try {
            //bytes = HttpsUtil.doGet("https://www.baidu.com");
            bytes = HttpsUtil.doGet("https://openapi.aofex.com/openApi/market/trade?symbol=BTC-USDT");
            apiRes = JSON.parseObject(new String(bytes, "utf-8"), ApiRes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiRes == null) {
            log.warn("can not get apiRes");
            return;
        }
        if (apiRes.getErrno() != 0) {
            log.warn("get price failed, errorNo:{}", apiRes.getErrno());
            return;
        }
        List<TradeData> tradeData = apiRes.getResult().getData();
        if (tradeData.isEmpty()) {
            log.warn("tradeData is empty");
            return;
        }
        tradeData.get(0).getPrice();
    }

    public static void main(String[] args) {
        ApiRes apiRes = null;
        byte[] bytes = new byte[0];
        try {
            //bytes = HttpsUtil.doGet("https://www.baidu.com");
            bytes = HttpsUtil.doGet("https://openapi.aofex.com/openApi/market/trade?symbol=BTC-USDT");
            apiRes = JSON.parseObject(new String(bytes, "utf-8"), ApiRes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (apiRes == null) {
            log.warn("can not get apiRes");
            return;
        }
        if (apiRes.getErrno() != 0) {
            log.warn("get price failed, errorNo:{}", apiRes.getErrno());
            return;
        }
        List<TradeData> tradeData = apiRes.getResult().getData();
        if (tradeData.isEmpty()) {
            log.warn("tradeData is empty");
            return;
        }
        long ts = tradeData.get(0).getTs();
        System.out.println(ts);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = df.format(ts*1000);
            System.out.println(date);

        System.out.println(System.currentTimeMillis());

    }
}


