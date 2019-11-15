package com.ibole.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/** 阿里云OSS对象存储
 * @author LGG
 * @create 2019年4月21日18:15:16
 **/
@Component
public class OssClientUtil {


	private static final String BUCKET_NAME = "vue-admin-guoguang"; // Bucket名称
	@Autowired(required = false)
	private OSSClient ossClient; // 阿里云OSS对象存储




	/**
	 * 上传文件
	 * @param file 文件
	 */
	public String uploadFile(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		ossClient.putObject(BUCKET_NAME, fileName,file.getInputStream());
		return ossClient.generatePresignedUrl(
								BUCKET_NAME,
								fileName,
								DateUtil.convertLdtToDate(DateUtil.getPlusMonths(99999)),
								HttpMethod.GET).toString();
	}
}
