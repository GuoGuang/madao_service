package com.madao.auth.validate.impl.captcha;

import com.google.common.base.Objects;
import com.madao.auth.validate.impl.ValidateCode;
import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 图片验证码
 */
@Getter
@Setter
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
        if (!(o instanceof Captcha)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Captcha captcha = (Captcha) o;
        return Objects.equal(image, captcha.image);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), image);
    }
}
