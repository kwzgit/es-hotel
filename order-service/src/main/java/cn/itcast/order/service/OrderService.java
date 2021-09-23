package cn.itcast.order.service;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserClient userClient;
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        Long userId = order.getUserId();
        // feign远程调用
        User user = userClient.findById(userId);
        order.setUser(user);
        return order;
    }

   /* @Autowired
    private RestTemplate restTemplate;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        Long userId = order.getUserId();
//        String url = "http://localhost:8081/user/"+userId;
        String url = "http://userservice/user/"+userId;
        User user = restTemplate.getForObject(url, User.class);
        order.setUser(user);

        // 4.返回
        return order;
    }*/
}
