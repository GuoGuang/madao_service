package com.madao.model.dto.user;

import com.google.common.base.Objects;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthToken {
	@Schema(title = "访问token就是短令牌，用户身份令牌")
	String access_token;
	@Schema(title = "刷新token")
	String refresh_token;
	@Schema(title = "jwt令牌")
	String jwt_token;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AuthToken authToken)) return false;
		return Objects.equal(access_token, authToken.access_token) && Objects.equal(refresh_token, authToken.refresh_token) && Objects.equal(jwt_token, authToken.jwt_token);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(access_token, refresh_token, jwt_token);
	}
}
