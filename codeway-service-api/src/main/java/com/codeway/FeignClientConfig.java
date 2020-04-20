package com.codeway;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Feign调用配置
 **/
@Configuration
public class FeignClientConfig  { // implements feign.codec.ErrorDecoder

	/**
	 * Feign 请求日志打印级别
	 */
	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	/**
	 * 全局处理Feign调用异常
	 * Error Decoders are invoked only when a response is received and the response code is not 2xx.
	 */

//	@Override
//	public Exception decode(String methodKey, Response response) {
//		Exception exception = null;
//		try {
//			// 获取原始的返回内容
//			String json = Util.toString(response.body().asReader());
//			exception = new RuntimeException(json);
//			// 将返回内容反序列化为Result，这里应根据自身项目作修改
//			JsonData result = JsonUtil.jsonToPojo(json, JsonData.class);
//			// 业务异常抛出简单的 RuntimeException，保留原来错误信息
//			if (!result.isStatus()) {
//				exception = new RuntimeException(result.getMessage());
//			}
//		} catch (IOException ex) {
//			LogBack.error(ex.getMessage(), ex);
//		}
//		return exception;
//	}
}