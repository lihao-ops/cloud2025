package com.hli.order.feign;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class DataCollectorFeignClientTest {

    @Autowired
    private UrlDataCollectorFeignClient urlDataCollectorFeignClient;
    @Test
    void getBase() {
        List<String> dateListByTime = urlDataCollectorFeignClient.getTradeDateListByTime("20200101", "20201231");
        System.out.println(dateListByTime);
    }
}