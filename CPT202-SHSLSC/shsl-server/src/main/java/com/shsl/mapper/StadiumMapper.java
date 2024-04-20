package com.shsl.mapper;

import com.shsl.entity.Stadium;
import org.apache.ibatis.annotations.*;

import java.util.Collection;
import java.util.Optional;

@Mapper
public interface StadiumMapper {

    @Insert("INSERT INTO stadium (sta_name, place_number, open_time, close_time, sta_info, sta_picture, 'limit', price, location) " +
            "VALUES (#{stadium.staName}, #{stadium.placeNumber}, #{stadium.openTime}, #{stadium.closeTime}, #{stadium.staInfo}, #{stadium.staPicture}, #{stadium.limit}, #{stadium.price}, #{stadium.location})")
    void addStadium(@Param("stadium") Stadium stadium);

    @Update("UPDATE stadium SET sta_name = #{stadium.staName}, place_number = #{stadium.placeNumber}, open_time = #{stadium.openTime}, " +
            "close_time = #{stadium.closeTime}, sta_info = #{stadium.staInfo}, sta_picture = #{stadium.staPicture}, `limit` = #{stadium.limit}, " +
            "price = #{stadium.price}, location = #{stadium.location} WHERE sta_id = #{stadium.staId}")
    void updateStadium(Integer id, @Param("stadium") Stadium stadium);

    @Delete("DELETE FROM stadium WHERE sta_id = #{id}")
    void deleteStadium(@Param("id") Integer id);

    @Select("SELECT * FROM stadium")
    Collection<Stadium> getAllStadiums();

    @Select("SELECT * FROM stadium WHERE sta_id = #{id}")
    Optional<Stadium> getStadiumById(@Param("id") Integer id);

    @Select("SELECT * FROM stadium WHERE sta_name = #{name}")
    Optional<Stadium> getStadiumByName(@Param("name") String name);
}
