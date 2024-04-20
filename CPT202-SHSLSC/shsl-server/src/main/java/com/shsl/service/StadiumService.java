package com.shsl.service;

import com.shsl.entity.Stadium;

import java.util.Collection;
import java.util.Optional;

public interface StadiumService {

    /**
     * 添加体育馆信息
     * @param stadium 待添加的体育馆信息
     */
    void addStadium(Stadium stadium);

    /**
     * 更新体育馆信息
     * @param id 待更新的体育馆ID
     * @param stadium 更新后的体育馆信息
     */
    void updateStadium(Integer id, Stadium stadium);

    /**
     * 删除体育馆信息
     * @param id 待删除的体育馆ID
     */
    void deleteStadium(Integer id);

    /**
     * 获取所有体育馆的信息
     * @return 所有体育馆的信息
     */
    Collection<Stadium> getAllStadiums();

    /**
     * 根据ID获取体育馆信息
     * @param id 待获取的体育馆ID
     * @return 对应ID的体育馆信息
     */
    Optional<Stadium> getStadiumById(Integer id);

    /**
     * 根据名称获取体育馆信息
     * @param name 待获取的体育馆名称
     * @return 对应名称的体育馆信息
     */
    Optional<Stadium> getStadiumByName(String name);
}
