package com.hli.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "quant-data-collector")
public interface DataCollectorFeignClient {

    @GetMapping("/data-collector/base_date/get_trade_date")
    List<String> getTradeDateListByTime(@RequestParam String startTime, @RequestParam String endTime);
}
