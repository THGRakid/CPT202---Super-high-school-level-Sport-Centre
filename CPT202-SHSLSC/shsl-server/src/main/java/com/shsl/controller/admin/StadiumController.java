package com.shsl.controller.admin;

import com.shsl.constant.MessageConstant;
import com.shsl.entity.Stadium;
import com.shsl.result.Result;
import com.shsl.service.StadiumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/stadium")
public class StadiumController {

    private static final Logger log = LoggerFactory.getLogger(StadiumController.class);
    
    private StadiumService stadiumService;

    @Value("${photo.file.dir}")
    private String realpath;

    @Autowired
    public StadiumController(StadiumService stadiumService) {
        this.stadiumService = stadiumService;
    }

    /**
     * List all the stadium in user page
     *
     * @return
     */
    @RequestMapping("/showLists")
    @ResponseBody
    public Result<List<Stadium>> showStadiums() {
        List<Stadium> stadiumList = stadiumService.getAllStadiums();
        if (stadiumList != null && !stadiumList.isEmpty()) {
            return Result.success(stadiumList);
        } else {
            return Result.error(MessageConstant.STADIUM_NOT_FIND);
        }
    }

    @GetMapping("/find/{id}")
    @ResponseBody
    public Stadium getStadiumById(@PathVariable String id) {
        Integer staId = Integer.valueOf(id);
        return stadiumService.getStadiumById(staId);
    }

    @PostMapping("/add")
    @ResponseBody
    public String addStadium(@RequestBody Stadium stadium) {
        stadiumService.addStadium(stadium);
        return "成功添加体育馆：" + stadium.getStaName();
    }
    
    @RequestMapping("update")
    public String updateStadium(Stadium stadium, MultipartFile img) throws IOException {

        //1.判断是否更新头像
        boolean notEmpty = !img.isEmpty();
        log.debug("是否更新头像: {}", notEmpty);
        if (notEmpty) {
            //1.删除老的头像 根据id查询原始头像
            String oldPhoto = stadiumService.getStadiumById(stadium.getStaId()).getStaPicture();
            File file = new File(realpath, oldPhoto);
            if (file.exists()) file.delete();//删除文件
            //2.处理新的头像上传
            String originalFilename = img.getOriginalFilename();
            String newFileName = uploadPhoto(img, originalFilename);
            //3.修改场馆新的头像名称
            stadium.setStaPicture(newFileName);
        }
        //2.没有更新头像直接更新基本信息
        stadiumService.updateStadium(stadium.getStaId(),stadium);
        return "redirect:/stadium/lists";//更新成功,跳转到场馆列表
    }

    //上传头像方法
    private String uploadPhoto(MultipartFile img, String originalFilename) throws IOException {
        String fileNamePrefix = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String fileNameSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = fileNamePrefix + fileNameSuffix;
        img.transferTo(new File(realpath, newFileName));
        return newFileName;
    }

    @RequestMapping("delete")
    public String deleteStadium(Integer id, MultipartFile img){
        log.debug("delete stadium id: {}",id);
        String photo = stadiumService.getStadiumById(id).getStaPicture();
        stadiumService.deleteStadium(id);

        File file = new File(realpath, photo);
        if (file.exists()) file.delete();
        return "redirect:/stadium/lists";
    }

    
    @RequestMapping("detail")
    public String detail(Integer id, Model model) {
        log.debug("当前查询场馆id: {}", id);
        //1.根据id查询一个
        Stadium stadium = stadiumService.getStadiumById(id);
        model.addAttribute("stadium", stadium);
        return "update_stadium";//跳转到更新页面
    }


    @RequestMapping("save")
    public String save(Stadium stadium, MultipartFile img) throws IOException {
        String originalFilename = img.getOriginalFilename();
        log.debug("头像名称: {}", originalFilename);
        log.debug("头像大小: {}", img.getSize());
        log.debug("上传的路径: {}", realpath);

        //1.处理头像的上传&修改文件名称
        String newFileName = uploadPhoto(img, originalFilename);
        //2.保存场馆信息
        stadium.setStaPicture(newFileName);//保存头像名字
        stadiumService.addStadium(stadium);
        return "redirect:/stadium/lists";//保存成功跳转到列表页面
    }
}
