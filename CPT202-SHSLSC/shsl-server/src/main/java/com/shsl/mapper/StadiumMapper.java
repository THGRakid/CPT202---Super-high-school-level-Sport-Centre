package com.shsl.mapper;

import com.shsl.entity.Stadium;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface StadiumMapper {

    @Insert("INSERT INTO stadium (staName, placeNumber, openTime, closeTime, staInfo, staPicture, `limit`, price, location) " +
            "VALUES (#{stadium.staName}, #{stadium.placeNumber}, #{stadium.openTime}, #{stadium.closeTime}, #{stadium.staInfo}, #{stadium.staPicture}, #{stadium.limit}, #{stadium.price}, #{stadium.location})")
    void addStadium(@Param("stadium") Stadium stadium);

    @Update("UPDATE stadium SET staName = #{stadium.staName}, placeNumber = #{stadium.placeNumber}, openTime = #{stadium.openTime}, " +
            "closeTime = #{stadium.closeTime}, staInfo = #{stadium.staInfo}, staPicture = #{stadium.staPicture}, `limit` = #{stadium.limit}, " +
            "price = #{stadium.price}, location = #{stadium.location} WHERE staId = #{stadium.staId}")
    void updateStadium(Integer id, @Param("stadium") Stadium stadium);

    @Delete("DELETE FROM stadium WHERE staId = #{id}")
    void deleteStadium(@Param("id") Integer id);

    @Select("SELECT * FROM stadium")
    Collection<Stadium> getAllStadiums();

    @Select("SELECT * FROM stadium WHERE staId = #{id}")
    Optional<Stadium> getStadiumById(@Param("id") Integer id);

    @Select("SELECT * FROM stadium WHERE staName = #{name}")
    Optional<Stadium> getStadiumByName(@Param("name") String name);
}
