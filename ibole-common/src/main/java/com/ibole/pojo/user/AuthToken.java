package com.ibole.pojo.user;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by mrt on 2018/5/21.
 */
@Data
@ToString
@NoArgsConstructor
public class AuthToken {
    @ApiModelProperty("访问token就是短令牌，用户身份令牌")
    String access_token;
    @ApiModelProperty("刷新token")
    String refresh_token;
    @ApiModelProperty("jwt令牌")
    String jwt_token;
}
