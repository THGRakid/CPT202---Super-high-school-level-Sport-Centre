package com.shsl.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@Api(tags = "Page Start Interface")
public class MyController {

    private static final Logger log = LoggerFactory.getLogger(MyController.class);
    @RequestMapping("/")
    @ApiOperation(value = "Initial Interface")
    public String homepageBlank() {
        log.debug("start ok");
        return "init";
    }
    @GetMapping("/user/loginView")
    @ApiOperation(value = "User Login Page")
    public ModelAndView userLogin(Model model) {
        ModelAndView userLogin = new ModelAndView("user_login");
        return userLogin;
    }

    @GetMapping("/user/RegisterView")
    @ApiOperation(value = "User Register Page")
    public ModelAndView userResister(Model model) {
        ModelAndView userRegister = new ModelAndView("user_register");
        return userRegister;
    }

    @GetMapping("/admin/loginView")
    @ApiOperation(value = "Admin Login Page")
    public ModelAndView adminLogin(Model model) {
        ModelAndView adminLogin = new ModelAndView("admin_login");
        return adminLogin;
    }
    @GetMapping("/admin/RegisterView")
    @ApiOperation(value = "Admin Register Page")
    public ModelAndView adminResister(Model model) {
        ModelAndView adminRegister = new ModelAndView("admin_register");
        return adminRegister;
    }


    @GetMapping("/admin/homepage")
    @ApiOperation(value = "Admin Homepage")
    public ModelAndView enterAdminHomepage(Integer id) {
        ModelAndView adminHomepage = new ModelAndView("admin_homepage");
        return adminHomepage;
    }
    @GetMapping("/user/personalHomepage")
    @ApiOperation(value = "Personal Homepage")
    public ModelAndView enterPersonalHomepage(Integer id) {
        ModelAndView personalHomepage = new ModelAndView("personal_homepage");
        return personalHomepage;
    }

    @GetMapping("/user/book")
    @ApiOperation(value = "Book Page")
    public ModelAndView enterBookPage(Integer id) {
        ModelAndView bookPage = new ModelAndView("book");
        return bookPage;
    }
    @GetMapping("/user/order")
    @ApiOperation(value = "Order Page")
    public ModelAndView order(Integer id) {
        ModelAndView order = new ModelAndView("order");
        return order;
    }


}