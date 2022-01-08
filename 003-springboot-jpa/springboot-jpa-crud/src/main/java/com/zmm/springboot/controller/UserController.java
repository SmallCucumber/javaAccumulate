package com.zmm.springboot.controller;

import com.zmm.springboot.entity.User;
import com.zmm.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    UserService userService;


    @RequestMapping("/")
    public String index() {
        return "redirect:/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        List<User> users=userService.getUserList();

        logger.info("UserController>>>list>>{}",users.toString());
        model.addAttribute("users", users);
        return "user/list";
    }

    @RequestMapping("/toAdd")
    public String toAdd() {

        logger.info("UserController>>>toAdd>>");

        return "user/userAdd";
    }

    @RequestMapping("/add")
    public String add(User user) {
        logger.info("UserController>>>add>>{}",user.toString());

        userService.save(user);
        return "redirect:/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(Model model,Long id) {
        User user=userService.findUserById(id);

        logger.info("UserController>>>toEdit>>{}",user.toString());

        model.addAttribute("user", user);
        return "user/userEdit";
    }

    @RequestMapping("/edit")
    public String edit(User user) {

        logger.info("UserController>>>edit>>{}",user.toString());
        userService.edit(user);
        return "redirect:/list";
    }


    @RequestMapping("/delete")
    public String delete(Long id) {
        logger.info("UserController>>>delete>>{}",id);
        userService.delete(id);
        return "redirect:/list";
    }
}
