package pri.learn.zxx.sentinelhystrix.controllers;

import org.springframework.cloud.alibaba.sentinel.rest.SentinelClientHttpResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * @desc
 * @createTime 2019-02-21-上午 11:45
 */
public class ExecptionHandle {
    public static SentinelClientHttpResponse block(HttpServletRequest request, Exception e) {
        System.out.println(request.getRequestURI() + "::blocked");
        return new SentinelClientHttpResponse(request.getRequestURI() + "::blocked");
    }
}
