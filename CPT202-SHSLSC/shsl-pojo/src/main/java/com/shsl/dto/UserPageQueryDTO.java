package com.shsl.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The data request when paging query the admin data
 */
@Data
public class UserPageQueryDTO implements Serializable {

    // user name
    private String userName;

    // Page number
    private int page;

    // The number of records displayed per page
    private int pageSize;

}