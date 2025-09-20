package com.hli.order.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc_v6x.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hli.vo.ResultVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

/**
 * @author hli
 * @program: cloud2025
 * @Date 2025-09-18 20:36:16
 * @description: 自定义sentinel关于web资源异常处理
 */
@Slf4j
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, String resourceName, BlockException e) throws Exception {
        response.setStatus(429);
        response.setContentType("application/json;charset=utf-8");
        PrintWriter writer = response.getWriter();
        ResultVO<String> resultVO = new ResultVO<>();
        resultVO.setCode(500);
        resultVO.setMessage("被sentinel限流了,原因=" + e.getClass());
        writer.write(mapper.writer().writeValueAsString(resultVO));
        writer.flush();
        writer.close();
    }
}
