package com.youyd.pojo.user;

import com.youyd.pojo.BasePojo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Resource extends BasePojo {
    private String code = "12";
    private String name;
    private String type;
    private String url;
    private String method;
    private String description;
}
