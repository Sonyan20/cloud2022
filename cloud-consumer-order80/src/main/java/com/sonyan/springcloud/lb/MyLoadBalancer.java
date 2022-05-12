package com.sonyan.springcloud.lb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sonyan
 * @create 2022/4/14 14:05
 */
@Component
public class MyLoadBalancer implements LoadBalancer{

    private static final Logger LOGGER = LoggerFactory.getLogger(MyLoadBalancer.class);

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.atomicInteger.compareAndSet(current,next));
        LOGGER.info("****访问次数 next: " + next);
        return next;
    }

    /**
     * 负载均衡算法：rest接口第几次请求数 % 服务器集群总数量 = 实际调用服务器位置下标 ， 每次服务器重启后 rest接口重新从1开始计数
     * @param serviceInstances 服务实例集合
     */
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = this.getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
