package com.sonyan.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author sonyan
 * @create 2022/4/15 10:15
 */
@Service
public class PaymentService {

    public String paymentInfo_OK(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_OK,id: " + id;
    }


    /**
     * <p>服务降级</p>
     * 注解 @HystrixCommand 处理
     * 一旦调用服务方法失败并抛出了错误信息后，会自动调用  @HystrixCommand 标注好的fallbackMethod 调用类中的指定方法
     */
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_Timeout(Integer id) {
        int timeoutSecond = 5;
        //int timeoutSecond = 5 / 0;
        try {
            TimeUnit.SECONDS.sleep(timeoutSecond);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + " paymentInfo_Timeout,id: " + id + "\t 耗时：" + timeoutSecond + "秒钟。";
    }

    public String paymentInfo_TimeoutHandler(Integer id) {
        return "[8001]" + "线程池： " + Thread.currentThread().getName() + " >> 服务超时/运行出错，请稍后再试,id: " + id;
    }


    //============服务熔断============
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不可为 负数");
        }
        String serialNumber = UUID.randomUUID().toString().replaceAll("-", "");

        return Thread.currentThread().getName() + "\t 调用成功，流水号：" + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(Integer id) {
        return "id 不能为负数，请稍后重试，🆔：" + id;
    }


    //========================All
    // @HystrixCommand(fallbackMethod = "str_fallbackMethod",
    // groupKey = "strGroupCommand",
    // commandKey = "strCommand",
    // threadPoolKey = "strThreadPool",
    // commandProperties = {
    // 设置隔离策略，THREAD 表示线程池 SEMAPHORE：信号池隔离
    // @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
    // 当隔离策略选择信号池隔离的时候，用来设置信号池的大小（最大并发数）
    // @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
    //  配置命令执行的超时时间
    //  @HystrixProperty(name = "execution.isolation.thread.timeoutinMilliseconds", value = "10"),
    // 是否启用超时时间
    // @HystrixProperty(name = "execution.timeout.enabled", value = "true"),
    // 执行超时的时候是否中断
    // @HystrixProperty(name = "execution.isolation.thread.interruptOnTimeout", value = "true"),
    // 执行被取消的时候是否中断
    // @HystrixProperty(name = "execution.isolation.thread.interruptOnCancel", value = "true"),
    // 允许回调方法执行的最大并发数
    // @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "10"),
    // 服务降级是否启用，是否执行回调函数
    // @HystrixProperty(name = "fallback.enabled", value = "true"),
    // 是否启用断路器
    // @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
    // 该属性用来设置在滚动时间窗中，断路器熔断的最小请求数。例如，默认该值为 20 的时候，
    // 如果滚动时间窗（默认10秒）内仅收到了19个请求， 即使这19个请求都失败了，断路器也不会打开。
    // @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),
    // 该属性用来设置在滚动时间窗中，表示在滚动时间窗中，在请求数量超过
    // circuitBreaker.requestVolumeThreshold 的情况下，如果错误请求数的百分比超过50,
    // 就把断路器设置为 "打开" 状态，否则就设置为 "关闭" 状态。
    // @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
    // 该属性用来设置当断路器打开之后的休眠时间窗。 休眠时间窗结束之后，
    // 会将断路器置为 "半开" 状态，尝试熔断的请求命令，如果依然失败就将断路器继续设置为 "打开" 状态，
    // 如果成功就设置为 "关闭" 状态。
    // @HystrixProperty(name = "circuitBreaker.sleepWindowinMilliseconds", value = "5000"),
    // 断路器强制打开
    // @HystrixProperty(name = "circuitBreaker.forceOpen", value = "false"),
    // 断路器强制关闭
    // @HystrixProperty(name = "circuitBreaker.forceClosed", value = "false"),
    // 滚动时间窗设置，该时间用于断路器判断健康度时需要收集信息的持续时间
    // @HystrixProperty(name = "metrics.rollingStats.timeinMilliseconds", value = "10000"),
    // 该属性用来设置滚动时间窗统计指标信息时划分"桶"的数量，断路器在收集指标信息的时候会根据
    // 设置的时间窗长度拆分成多个 "桶" 来累计各度量值，每个"桶"记录了一段时间内的采集指标。
    // 比如 10 秒内拆分成 10 个"桶"收集这样，所以 timeinMilliseconds 必须能被 numBuckets 整除。否则会抛异常
    // @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "10"),
    // 该属性用来设置对命令执行的延迟是否使用百分位数来跟踪和计算。如果设置为 false, 那么所有的概要统计都将返回 -1。
    // @HystrixProperty(name = "metrics.rollingPercentile.enabled", value = "false"),
    // 该属性用来设置百分位统计的滚动窗口的持续时间，单位为毫秒。
    // @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "60000"),
    // 该属性用来设置百分位统计滚动窗口中使用 “ 桶 ”的数量。
    // @HystrixProperty(name = "metrics.rollingPercentile.numBuckets", value = "60000"),
    // 该属性用来设置在执行过程中每个 “桶” 中保留的最大执行次数。如果在滚动时间窗内发生超过该设定值的执行次数，
    // 就从最初的位置开始重写。例如，将该值设置为100, 滚动窗口为10秒，若在10秒内一个 “桶 ”中发生了500次执行，
    // 那么该 “桶” 中只保留 最后的100次执行的统计。另外，增加该值的大小将会增加内存量的消耗，并增加排序百分位数所需的计算时间。
    // @HystrixProperty(name = "metrics.rollingPercentile.bucketSize", value = "100"),
    // 该属性用来设置采集影响断路器状态的健康快照（请求的成功、 错误百分比）的间隔等待时间。
    // @HystrixProperty(name = "metrics.healthSnapshot.intervalInMilliseconds", value = "500"),
    // 是否开启请求缓存
    // @HystrixProperty(name = "requestCache.enabled", value = "true"),
    // HystrixCommand的执行和事件是否打印日志到 HystrixRequestLog 中
    // @HystrixProperty(name = "requestLog.enabled", value = "true"),
    // },
    // threadPoolProperties = {
    // 该参数用来设置执行命令线程池的核心线程数，该值也就是命令执行的最大并发量
    // @HystrixProperty(name = "coreSize", value = "10"),
    // 该参数用来设置线程池的最大队列大小。当设置为 -1 时，线程池将使用 SynchronousQueue 实现的队列，
    // 否则将使用 LinkedBlockingQueue 实现的队列。
    // @HystrixProperty(name = "maxQueueSize", value = "-1"),
    // 该参数用来为队列设置拒绝阈值。 通过该参数， 即使队列没有达到最大值也能拒绝请求。
    // 该参数主要是对 LinkedBlockingQueue 队列的补充,因为 LinkedBlockingQueue
    // 队列不能动态修改它的对象大小，而通过该属性就可以调整拒绝请求的队列大小了。
    // @HystrixProperty(name = "queueSizeRejectionThreshold", value = "5"),
    // })
    // public String strConsumer() {
    //      return "hello 2020";
    //
    // }
    // public String str_fallbackMethod(){
    // return "*****fall back str_fallbackMethod";
    // }
}
