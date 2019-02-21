package pri.learn.zxx.sentinelhystrix.hystrixdemo;

import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 * @author Administrator
 * @desc
 * @createTime 2019-02-21-下午 1:05
 */
public class HystrixCommand extends com.netflix.hystrix.HystrixCommand<String> {
    private String name;

    protected HystrixCommand(HystrixCommandGroupKey group, String name) {
        super(group);
        this.name = name;
    }

    @Override
    protected String run() throws Exception {
        return "hello::" + name;
    }
}
