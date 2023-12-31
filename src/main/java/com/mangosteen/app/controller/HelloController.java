package com.mangosteen.app.controller;

import com.mangosteen.app.model.dao.UserInfo;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello Controller.
 */
@RestController
public class HelloController {
    /**
     * Say hello API.
     *
     * @param name name
     * @param id   specific id
     * @return hello string
     */
    @GetMapping(path = "v1.0/hello/{name}/{id}")
    public String sayHello(@PathVariable("name") String name,
                           @PathVariable("id") Long id) {
        return String.format("Hello, %s, %d", name, id);
    }

    //http://localhost:8080/v1.0/greeting?name=zhangsan&id=100

    /**
     * Say Greeting API.
     * @param name name
     * @param id id
     * @return hello greet
     */
    @GetMapping("v1.0/greeting")
    public String sayGreeting(@RequestParam("name") String name,
                              @RequestParam("id") Long id) {
        val userInfo = UserInfo.builder()
                               .username("xxxx")
                               .build();
        System.out.println(userInfo.getId());
        return String.format("Say greeting:  %s with %d", name, id);
    }


}
