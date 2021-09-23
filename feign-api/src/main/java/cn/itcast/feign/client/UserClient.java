package cn.itcast.feign.client;


import cn.itcast.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @className: cn.itcast.order.client-> UserClient
 * @description: 调用userservice
 * @author: kongwz
 * @createDate: 2021-09-15 10:32
 * @version: 1.0
 * @todo:
 */
@FeignClient(value = "userservice")
public interface UserClient {
    @GetMapping("/user/{id}")
    User findById(@PathVariable("id") Long userId);

}
