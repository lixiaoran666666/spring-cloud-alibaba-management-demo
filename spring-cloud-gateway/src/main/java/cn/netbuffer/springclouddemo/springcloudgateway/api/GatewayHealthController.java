package cn.netbuffer.springclouddemo.springcloudgateway.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class GatewayHealthController {

    @Value("${spring.application.name:spring-cloud-gateway}")
    private String applicationName;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping("/gateway/health")
    public Map<String, Object> health() {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("service", applicationName);
        result.put("status", "UP");
        result.put("checkedAt", LocalDateTime.now().toString());
        result.put("registeredServices", discoveryClient.getServices());
        return result;
    }
}
