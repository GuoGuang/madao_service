package com.codeway.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;


/** 阿里云OSS对象存储
 **/
@Component
public class OssClientUtil {

	/**
	 * Bucket名称
	 */
	private static final String BUCKET_NAME = "vue-admin-guoguang";

	@Autowired(required = false)
	private OSSClient ossClient;

	/**
	 * 上传文件
	 * @param file 文件
	 */
	public String uploadFile(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		ossClient.putObject(BUCKET_NAME, fileName,file.getInputStream());
		URL url = ossClient.generatePresignedUrl(
				BUCKET_NAME,
				fileName,
				DateUtil.convertLdtToDate(DateUtil.getPlusMonths(99999)),
				HttpMethod.GET);
		return "https://"+url.getHost()+url.getPath();
	}
}
