package com.shsl.controller.user;

import com.shsl.entity.Stadium;
import com.shsl.service.StadiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/stadium")
public class StadiumController {

    @Autowired
    private StadiumService stadiumService;

    @GetMapping("/all")
    @ResponseBody
    public Collection<Stadium> getAllStadiums() {
        return stadiumService.getAllStadiums();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Stadium getStadiumById(@PathVariable Integer id) {
        return stadiumService.getStadiumById(id).orElse(null);
    }

    @PostMapping("/add")
    @ResponseBody
    public String addStadium(@RequestBody Stadium stadium) {
        stadiumService.addStadium(stadium);
        return "成功添加体育馆：" + stadium.getStaName();
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public String updateStadium(@PathVariable Integer id, @RequestBody Stadium stadium) {
        stadiumService.updateStadium(id, stadium);
        return "成功更新体育馆：" + stadium.getStaName();
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteStadium(@PathVariable Integer id) {
        stadiumService.deleteStadium(id);
        return "成功删除体育馆，ID：" + id;
    }
}
