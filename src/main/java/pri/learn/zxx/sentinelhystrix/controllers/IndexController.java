package pri.learn.zxx.sentinelhystrix.controllers;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @desc
 * @createTime 2019-02-21-上午 9:46
 */
@RestController
public class IndexController {
    @GetMapping(value = "/index")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(
                    name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "50")}, fallbackMethod = "indexFallBack")
    public String index(
            @RequestParam(name = "costTime", defaultValue = "10", required = false)
                    Long costTime) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(costTime);
        System.out.print(Thread.currentThread().getName() + " -- costTime::" + costTime);
        return "hello,hystrix,-costTime::" + costTime;
    }

    public String indexFallBack(Long costTime) {
        return "index fallBack ::costTime" + costTime;
    }

    @GetMapping(value = "/demo")
    public String demo() {
        System.out.print(Thread.currentThread().getName() + " -- costTime");
        return "index fallBack ::costTime";
    }

    @GetMapping(value = "/sentinel")
    @SentinelResource(value = "sp", entryType = EntryType.IN, fallback = "indexFallBack", blockHandler = "block", blockHandlerClass = ExecptionHandle.class)
    public String sentinel() {
        //System.out.print(Thread.currentThread().getName() + "::sentinel");
        return "index sentinel";
    }


}
