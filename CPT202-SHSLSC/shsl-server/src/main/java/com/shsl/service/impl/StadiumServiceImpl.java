package com.shsl.service.impl;

import com.shsl.entity.Stadium;
import com.shsl.mapper.StadiumMapper;
import com.shsl.service.StadiumService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Data
@Service
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumMapper stadiumMapper;

    @Override
    public void addStadium(Stadium stadium) {
        stadiumMapper.addStadium(stadium);
        System.out.println("成功添加体育馆：" + stadium.getStaName());
    }

    @Override
    public void updateStadium(Integer id, Stadium stadium) {
        stadiumMapper.updateStadium(id, stadium);
        System.out.println("成功更新体育馆：" + stadium.getStaName());
    }

    @Override
    public void deleteStadium(Integer id) {
        stadiumMapper.deleteStadium(id);
        System.out.println("成功删除体育馆，ID：" + id);
    }

    @Override
    public Collection<Stadium> getAllStadiums() {
        return stadiumMapper.getAllStadiums();
    }

    @Override
    public Optional<Stadium> getStadiumById(Integer id) {
        return stadiumMapper.getStadiumById(id);
    }

    @Override
    public Optional<Stadium> getStadiumByName(String name) {
        return stadiumMapper.getStadiumByName(name);
    }
}
