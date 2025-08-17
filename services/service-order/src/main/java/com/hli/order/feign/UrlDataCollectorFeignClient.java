package com.hli.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * value = "data-collector-client" 指明了服务名。
 * Feign 有了 一个明确服务名，即使你本地测试，也能通过 Ribbon/LoadBalancer 找到对应 URL。
 * url="http://127.0.0.1:8802" 告诉它 直接去这个地址，不去注册中心找。
 */
@FeignClient(value = "data-collector-client", url = "http://127.0.0.1:8801")
public interface UrlDataCollectorFeignClient {

    @GetMapping("/data-collector/base_date/get_trade_date")
    List<String> getTradeDateListByTime(@RequestParam String startTime, @RequestParam String endTime);
}
