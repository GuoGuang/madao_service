/*
package com.ibole.utils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.captcha.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

*/
/**
 * 图片验证码生成
 * @author LGG
 * @create 2019-06-21
 **//*

public class CaptchaCode {
	*/
/**
	 * 验证码长度
	 *//*

	int length = 4;

	*/
/**
	 * 验证码字体大小
	 *//*

	int fontSize = 30;

	*/
/**
	 * 边框补
	 *//*

	int padding = 0;

	*/
/**
	 * 是否输出燥点（默认输出）
	 *//*

	boolean chaos = true;

	*/
/**
	 * 输出燥点的颜色（默认灰色）
	 *//*

	Color chaosColor = Color.lightGray;

	*/
/**
	 * 自定义背景色（默认白色）
	 *//*

	Color backgroundColor = Color.white;

	*/
/**
	 * 自定义字体数组
	 *//*

	String[] fonts = { "Arial", "Georgia","Times New Roman","Blue","Yellow" };

	*/
/**
	 * 自定义随机码字符串序列（使用逗号分隔）
	 *//*

	String codeSerial = "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";

	*/
/**
	 * 产生波形滤镜效果
	 *//*

	private final double PI = 3.1415926535897932384626433832799;//此值越大，扭曲程度越大
	*/
/**
	 * 字体长度的一对方法
	 * @return
	 *//*

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	*/
/**
	 * 字体长度的一对方法
	 * @return
	 *//*

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}
	*/
/**
	 * 边框的一对方法
	 * @return
	 *//*

	public int getPadding() {
		return padding;
	}

	public void setPadding(int padding) {
		this.padding = padding;
	}
	*/
/**
	 * 是否输出躁点的一对方法
	 * @return
	 *//*

	public boolean isChaos() {
		return chaos;
	}

	public void setChaos(boolean chaos) {
		this.chaos = chaos;
	}
	*/
/**
	 * 躁点的颜色
	 * @return
	 *//*

	public Color getChaosColor() {
		return chaosColor;
	}

	public void setChaosColor(Color chaosColor) {
		this.chaosColor = chaosColor;
	}
	*/
/**
	 * 背景颜色
	 * @return
	 *//*

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	*/
/**
	 * 自定义字体样式的数组
	 * @return
	 *//*

	public String[] getFonts() {
		return fonts;
	}

	public void setFonts(String[] fonts) {
		this.fonts = fonts;
	}
	*/
/**
	 * 自定义随机码字符串序列，表示可输出的数字的范围
	 * @return
	 *//*

	public String getCodeSerial() {
		return codeSerial;
	}

	public void setCodeSerial(String codeSerial) {
		this.codeSerial = codeSerial;
	}

	*/
/**
	 * 给定范围获得随机颜色
	 *
	 * @param fc
	 * @param bc
	 * @return
	 *//*

	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	*/
/**
	 * 生成校验码图片
	 *
	 * @param code
	 * @return
	 *//*

	private BufferedImage createImageCode(String code) {
		int fWidth = this.fontSize + this.padding;

		//图象的宽度
		int imageWidth = (int) (code.length() * fWidth) + 4 + this.padding * 2;
		//图象的高度
		int imageHeight = this.fontSize ;

		// 在内存中创建图象
		BufferedImage bi = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		//创建画笔
		Graphics graphics = bi.getGraphics();

		// 将位图填充背景色
		graphics.setColor(this.backgroundColor);
		graphics.fillRect(0, 0, bi.getWidth(), bi.getHeight());

		// 生成随机类
		Random random = new Random();

		// 给背景添加随机生成的燥点,使图象中的认证码不易被其它程序探测到
		if (this.chaos) {
			int c = this.length * 10;
			for (int i = 0; i < c; i++) {
				int x = random.nextInt(bi.getWidth());
				int y = random.nextInt(bi.getHeight());
				graphics.setColor(this.chaosColor);// 设置燥点的颜色
				graphics.drawRect(x, y, 1, 1);// 生成燥点
				//graphics.drawLine(x, y, y, x);//生成干扰线
			}
		}
		// 随机字体和颜色的验证码字符
		for (int i = 0; i < code.length(); i++) {

			//设置字体属性
			int findex = random.nextInt(this.fonts.length);
			Font font = new Font(fonts[findex], Font.BOLD, this.fontSize);
			graphics.setFont(font);

			int top = (int) ((imageHeight + code.length()*2) / 1.5);
			if (i % 2 != 1) {
				top = top - code.length();
			}
			int left = i * fWidth+code.length();
			graphics.setColor(this.getRandColor(1 + i, 250 - i));

			try {
				graphics.drawString(code.substring(i, i + 1), left, top);
			} catch (StringIndexOutOfBoundsException e) {
				System.out.print(e.toString());
			}
		}

		// 画一个边框，边框颜色为Color.gray
		graphics.setColor(Color.gray);
		graphics.drawRect(0, 0, bi.getWidth() - 1, bi.getHeight() - 1);

		// 图象生效
		graphics.dispose();

		// 产生波形
		//bi = TwistImage(bi, true, 8, 4);

		return bi;
	}

	*/
/**
	 * 将创建好的图片输出到页面
	 *//*

	public String createImageOnPage(ByteArrayOutputStream outputStream) {
		String code = CreateVerifyCode(4);
		BufferedImage bi = createImageCode(code);
		ServletOutputStream os = null;
		try {
			ImageIO.write(bi,"JPEG", outputStream);
			return code;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(os != null)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return null;
	}

	*/
/**
	 * 生成随机字符码
	 *
	 * @param codeLen
	 *            生成字符码的个数,0则默认的个数
	 * @return
	 *//*

	public String CreateVerifyCode(int codeLen) {
		if (codeLen == 0) {
			codeLen = this.length;
		}
		String[] arr = this.codeSerial.split(",");
		String code = "";
		int randValue = -1;
		Random random = new Random();
		for (int i = 0; i < codeLen; i++) {
			randValue = random.nextInt(arr.length - 1);
			code += arr[randValue];
		}
		return code;
	}

}*/
