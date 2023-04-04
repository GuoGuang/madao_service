package com.madao.event;

import com.madao.model.dto.user.AuthToken;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LoginSuccessEvent extends ApplicationEvent {
	private final AuthToken extendsParams;

	public LoginSuccessEvent(Object source, AuthToken params) {
		super(source);
		this.extendsParams = params;
	}
}