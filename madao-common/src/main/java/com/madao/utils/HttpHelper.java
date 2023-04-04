package com.madao.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2019-09-29 7:37
 */
@Slf4j
public class HttpHelper {

	public static String getBodyString(ServletRequest request) {
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try {
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					log.error(e.getMessage(), e);
				}
			}
		}
		return sb.toString();
	}

	public static Map<String, String> httpPost(List<NameValuePair> params) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token");
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			String responseBody = httpClient.execute(httpPost, httpResponse -> {
				int status = httpResponse.getStatusLine().getStatusCode();
				if (status < 200 || status >= 300) {
					throw new HttpClientErrorException(BAD_REQUEST);
				}
				HttpEntity entity = httpResponse.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			});
			return getUrlParams(responseBody);
		} catch (IOException e) {
			throw new HttpClientErrorException(BAD_REQUEST, e.getMessage());
		}
	}

	public static Map<String, Object> httpOauthGet(String accessToken) {
		try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
			HttpGet httpGet = new HttpGet("https://api.github.com/user");
			httpGet.setHeader("Authorization", "token " + accessToken);
			String responseBody = httpClient.execute(httpGet, httpResponse -> {
				int status = httpResponse.getStatusLine().getStatusCode();
				if (status < 200 || status >= 300) {
					throw new HttpClientErrorException(BAD_REQUEST);
				}
				HttpEntity entity = httpResponse.getEntity();
				return entity != null ? EntityUtils.toString(entity) : null;
			});
			return JsonUtil.jsonToMap(responseBody);
		} catch (IOException e) {
			throw new HttpClientErrorException(BAD_REQUEST);
		}
	}


	/**
	 * URL参数转map
	 *
	 * @param param
	 * @return
	 */
	public static Map<String, String> getUrlParams(String param) {
		Map<String, String> map = new HashMap<>(0);
		if (StringUtils.isBlank(param)) {
			return map;
		}
		String[] params = param.split("&");
		for (int i = 0; i < params.length; i++) {
			String[] p = params[i].split("=");
			if (p.length == 2) {
				map.put(p[0], p[1]);
			}
		}
		return map;
	}

}



