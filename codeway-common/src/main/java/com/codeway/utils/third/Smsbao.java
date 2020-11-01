package com.codeway.utils.third;

import com.codeway.utils.LogBack;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * http://www.smsbao.com/openapi/52.html
 * Smsbao api
 */
@Component
@ConditionalOnProperty(name = "sms.account")
public class Smsbao {

	@Value("${sms.account}")
	String account;
	@Value("${sms.password}")
	String password;
	String httpUrl = "http://api.smsbao.com/sms";

	public String sendSms(String phone,String code) {
		String content = "【codeway】您的验证码为" + code + "，在10分钟内有效。";
		StringBuffer httpArg = new StringBuffer();
		httpArg.append("u=").append(account).append("&");
		httpArg.append("p=").append(password).append("&");
		httpArg.append("m=").append(phone).append("&");
		httpArg.append("c=").append(encodeUrlString(content, StandardCharsets.UTF_8.toString()));

		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
			String strRead = reader.readLine();
			if (strRead != null) {
				sbf.append(strRead);
				while ((strRead = reader.readLine()) != null) {
					sbf.append("\n");
					sbf.append(strRead);
				}
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			LogBack.error(e.getMessage(), e);
		}
		return result;
	}

	public static String md5(String plainText) {
		StringBuffer buf = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
		} catch (NoSuchAlgorithmException e) {
			LogBack.error(e.getMessage(), e);
		}
		return buf.toString();
	}

	public static String encodeUrlString(String str, String charset) {
		String strret = null;
		if (str == null) {
			return str;
		}
		try {
			strret = java.net.URLEncoder.encode(str, charset);
		} catch (Exception e) {
			LogBack.error(e.getMessage(), e);
			return null;
		}
		return strret;
	}
}
