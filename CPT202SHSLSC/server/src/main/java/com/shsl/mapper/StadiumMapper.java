package com.shsl.mapper;

import com.shsl.pojo.Stadium;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StadiumMapper {

    private static Map<Integer, Stadium> stadiums = new HashMap<>();
    private static final String ADMIN_ROLE = "admin";

    // 添加体育馆信息（管理员）
    public void addStadium(Stadium stadium, String role) {
        try {
            if (isAdmin(role)) {
                stadiums.put(stadium.getId(), stadium);
                System.out.println("成功添加体育馆：" + stadium.getName());
            } else {
                System.out.println("无权限添加体育馆");
            }
        } catch (NullPointerException e) {
            System.out.println("体育馆信息添加失败：空指针异常");
        }
    }

    // 更新体育馆信息（管理员）
    public void updateStadium(Integer id, Stadium stadium, String role) {
        try {
            if (isAdmin(role)) {
                stadiums.put(id, stadium);
                System.out.println("成功更新体育馆：" + stadium.getName());
            } else {
                System.out.println("无权限更新体育馆");
            }
        } catch (NullPointerException e) {
            System.out.println("体育馆信息更新失败：空指针异常");
        }
    }

    // 删除体育馆信息（管理员）
    public void deleteStadium(Integer id, String role) {
        try {
            if (isAdmin(role)) {
                stadiums.remove(id);
                System.out.println("成功删除体育馆，ID：" + id);
            } else {
                System.out.println("无权限删除体育馆");
            }
        } catch (NullPointerException e) {
            System.out.println("体育馆信息删除失败：空指针异常");
        }
    }

    // 获取所有体育馆的信息（无需权限验证）
    public Collection<Stadium> getAllStadiums() {
        return stadiums.values();
    }

    // 根据ID获取体育馆信息（无需权限验证）
    public Optional<Stadium> getStadiumById(Integer id) {
        return Optional.ofNullable(stadiums.get(id));
    }

    // 获取指定名称的体育馆（无需权限验证）
    public Optional<Stadium> getStadiumByName(String name) {
        return stadiums.values().stream()
                .filter(stadium -> stadium.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    // 权限验证
    private boolean isAdmin(String role) {
        return ADMIN_ROLE.equals(role);
    }
}
