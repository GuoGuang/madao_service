package com.madaoo.auth.validate.impl.captcha;

import com.madaoo.auth.validate.impl.ValidateCodeGenerator;
import com.madaoo.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 默认的图片验证码生成器
 */
public class CaptchaValidateCodeGenerator implements ValidateCodeGenerator {

	/**
	 * 系统配置
	 */

	@Autowired
	private SecurityProperties securityProperties;

	Random randColor = new Random();
	Random random = new Random();

	@Override
	public com.madaoo.auth.validate.impl.captcha.Captcha generate(ServletWebRequest request) {
		int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width",
				securityProperties.getCode().getImage().getWidth());
		int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height",
				securityProperties.getCode().getImage().getHeight());
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		StringBuilder sRand = new StringBuilder();
		for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand.append(rand);
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			g.drawString(rand, 30 * i + 6, 23);
		}

		g.dispose();

		return new com.madaoo.auth.validate.impl.captcha.Captcha(image, sRand.toString(), securityProperties.getCode().getImage().getExpireIn());
	}
	
	/**
	 * 生成随机背景条纹
	 * @param fc
	 * @param bc
	 */
	private Color getRandColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + randColor.nextInt(bc - fc);
		int g = fc + randColor.nextInt(bc - fc);
		int b = fc + randColor.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public SecurityProperties getSecurityProperties() {
		return securityProperties;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
	
	

}
