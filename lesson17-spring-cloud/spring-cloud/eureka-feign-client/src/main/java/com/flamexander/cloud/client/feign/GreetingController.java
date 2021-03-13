package com.flamexander.cloud.client.feign;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class GreetingController {
    private final GreetingClient greetingClient;

    // @FeignClient("eureka-client")
    // public interface GreetingClient {
    //   @GetMapping("/parametrized/{id}")
    //   String parametrized(@PathVariable(value = "id") String id); =>
    // }
    //
    // public class GreetingClientProxy implements GreetingClient {
    //   @LoadBalanced RestTemplate restTemplate;
    //
    // => public String parametrized(@PathVariable(value = "id") String id) {
    //      return restTemplate.getForObject("http://eureka-client/app/demo/data/{" + id + "}", String.class);
    //    }
    // }

    @RequestMapping("/get-greeting")
    public String greeting() {
        System.out.println(greetingClient.getClass().getName());
        String answer = greetingClient.greeting();
        answer = greetingClient.parametrized("1000");
        return answer;
    }
}
