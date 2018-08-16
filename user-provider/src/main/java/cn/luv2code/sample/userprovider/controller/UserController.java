package cn.luv2code.sample.userprovider.controller;

import cn.luv2code.sample.userprovider.dao.UserRepository;
import cn.luv2code.sample.userprovider.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserRepository userRepository;
    @Cacheable(cacheNames = "server.user.findById")
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return this.userRepository.findById(id).get();
    }
    @GetMapping("/all")
    public List<User> findAll() {
        return this.userRepository.findAll();
    }
}
