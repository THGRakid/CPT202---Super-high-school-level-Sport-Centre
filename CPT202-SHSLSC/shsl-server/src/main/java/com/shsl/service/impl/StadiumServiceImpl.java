package com.shsl.service.impl;

import com.shsl.entity.Place;
import com.shsl.entity.Stadium;
import com.shsl.mapper.StadiumMapper;
import com.shsl.service.StadiumService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        System.out.println("Delete stadium " + id + " successfully!");
    }

    @Override
    public List<Stadium> getAllStadiums() {
        return stadiumMapper.getAllStadiums();
    }

    @Override
    public Stadium getStadiumById(Integer id) {
        return stadiumMapper.getStadiumById(id);
    }

    @Override
    public Stadium getStadiumByName(String name) {
        return stadiumMapper.getStadiumByName(name);
    }

    @Override
    public List<Place> getPlaceById(Integer staId) {
        return stadiumMapper.getPlaceByStaId(staId);
    }
}
