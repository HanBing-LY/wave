package com.liyuan.wave.sms.loadbalancing;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @author  liyuan
 * @date  2020-01-20-0:27
 */
public interface LoadBalancer {

    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
