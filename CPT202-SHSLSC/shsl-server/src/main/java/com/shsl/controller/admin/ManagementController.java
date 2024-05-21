package com.shsl.controller.admin;

import com.shsl.entity.*;
import com.shsl.service.AdminService;
import com.shsl.service.ReservationService;
import com.shsl.service.StadiumService;
import com.shsl.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/")
public class ManagementController {
    private static final Logger log = LoggerFactory.getLogger(StadiumController.class);

    private StadiumService stadiumService;

    private UserService userService;

    private AdminService adminService;

    private ReservationService reservationService;

    public ManagementController(StadiumService stadiumService, UserService userService, AdminService adminService, ReservationService reservationService) {
        this.stadiumService = stadiumService;
        this.userService = userService;
        this.adminService = adminService;
        this.reservationService = reservationService;
    }

    /**
     * List all the stadiums
     */
    @RequestMapping("/stadium/lists")
    public String getAllStadiums(Model model) {
        log.debug("Find all stadiums information");
        List<Stadium> stadiumList = stadiumService.getAllStadiums();
        model.addAttribute("stadiumList", stadiumList);
        return "stalist";
    }

    /**
     * List all the users
     */
    @RequestMapping("/user/lists")
    public String getAllUser(Model model) {
        log.debug("Find all users information");
        List<User> userList = userService.selectAll();
        model.addAttribute("userList", userList);
        return "userlist";
    }

    /**
     * List all the admins
     */
    @RequestMapping("/admin/lists")
    public String getAllAdmin(Model model) {
        log.debug("Find all admins information");
        List<Admin> adminList = adminService.selectAll();
        model.addAttribute("adminList", adminList);
        return "adminlist";
    }

    /**
     * List all the reservations
     */
    @RequestMapping("/book/lists")
    public String getAllBook(Model model) {
        log.debug("Find all books information");
        List<ReservationRecord> bookList = reservationService.getAll();
        model.addAttribute("bookList", bookList);
        return "booklist";
    }


}
