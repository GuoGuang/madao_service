package com.madao.model.dto.user;

import com.google.common.base.Objects;
import io.swagger.annotations.ApiModelProperty;

public class AuthToken {
    @ApiModelProperty("访问token就是短令牌，用户身份令牌")
    String access_token;
    @ApiModelProperty("刷新token")
    String refresh_token;
    @ApiModelProperty("jwt令牌")
    String jwt_token;

	public String getAccess_token() {
		return access_token;
	}

	public AuthToken setAccess_token(String access_token) {
		this.access_token = access_token;
		return this;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public AuthToken setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
		return this;
	}

	public String getJwt_token() {
		return jwt_token;
	}

	public AuthToken setJwt_token(String jwt_token) {
		this.jwt_token = jwt_token;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AuthToken)) return false;
		AuthToken authToken = (AuthToken) o;
		return Objects.equal(access_token, authToken.access_token) && Objects.equal(refresh_token, authToken.refresh_token) && Objects.equal(jwt_token, authToken.jwt_token);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(access_token, refresh_token, jwt_token);
	}
}
