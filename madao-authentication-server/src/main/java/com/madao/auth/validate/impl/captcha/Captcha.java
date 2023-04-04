package com.madao.auth.validate.impl.captcha;

import com.google.common.base.Objects;
import com.madao.auth.validate.impl.ValidateCode;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图片验证码
 *
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
public class Captcha extends ValidateCode implements Serializable {

	private static final long serialVersionUID = -6020470039852318468L;

	private BufferedImage image;

	public Captcha(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}

	public Captcha(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Captcha captcha)) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}
		return Objects.equal(image, captcha.image);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(super.hashCode(), image);
	}

	public BufferedImage getImage() {
		return image;
	}

	public Captcha setImage(BufferedImage image) {
		this.image = image;
		return this;
	}
}
