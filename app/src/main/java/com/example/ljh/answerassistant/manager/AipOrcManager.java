package com.example.ljh.answerassistant.manager;

import com.baidu.aip.ocr.AipOcr;

/**
 * Created by ljh on 2018/2/24.
 */

public class AipOrcManager {
    public static final String APP_ID = "10849505";
    public static final String API_KEY = "myXjDOh0UAFPvEFWAXISYkZf";
    public static final String SECRET_KEY = "lppZWGGt9WykstQledjm45GB2mQIGGGO";

    public static AipOcr getAipOcr(){
        return new AipOcr(APP_ID,API_KEY,SECRET_KEY);
    }
}
