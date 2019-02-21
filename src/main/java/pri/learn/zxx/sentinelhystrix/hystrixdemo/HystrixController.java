package pri.learn.zxx.sentinelhystrix.hystrixdemo;

import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

/**
 * @author Administrator
 * @desc
 * @createTime 2019-02-21-下午 1:26
 */
@RestController
public class HystrixController {
    @GetMapping(value = "/hy")
    public String hystrix(@RequestParam(name = "time", defaultValue = "10") long time) throws InterruptedException {
        HystrixCommand hc = new HystrixCommand(HystrixCommandGroupKey.Factory.asKey("hello"), "key", () -> "hello");
        Thread.sleep(time);
        System.out.println("time::" + time);
        return hc.execute();
    }


    class HystrixCommand extends com.netflix.hystrix.HystrixCommand<String> {
        private String name;

        private Callable<String> callable;

        protected HystrixCommand(HystrixCommandGroupKey group, String name, Callable callable) {
            super(group, 20);
            this.name = name;
            this.callable = callable;
        }

        @Override
        protected String run() throws Exception {
            return callable.call() + name;
        }
    }
}
