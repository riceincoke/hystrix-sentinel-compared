package pri.learn.zxx.sentinelhystrix;

import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCircuitBreaker//激活服务熔断
@EnableDiscoveryClient//激活服务注册
public class SentinelHystrixApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelHystrixApplication.class, args);
    }

    @Bean
    public FlowRule flowRule() {
        FlowRule fr = new FlowRule();
        fr.setResource("sentinel");
        fr.setCount(10);
        fr.setGrade(1);
        fr.setStrategy(0);
        return fr;
    }

}
