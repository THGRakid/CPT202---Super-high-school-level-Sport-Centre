package com.shsl.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * The data request when paging query the user data
 */
@Data
public class AdminPageQueryDTO implements Serializable {

    // User name
    private String adminName;

    // Page number
    private int page;

    // The number of records displayed per page
    private int pageSize;

}