package com.shsl.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeChatLoginVO implements Serializable {

    private Integer userid;
    private String openid;
    private String token;

}

