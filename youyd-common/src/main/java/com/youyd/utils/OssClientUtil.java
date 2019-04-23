package com.youyd.utils;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;


/** 阿里云OSS对象存储
 * @author LGG
 * @create 2019年4月21日18:15:16
 **/
@Service
public class OssClientUtil {


	private static final String BUCKET_NAME = "vue-admin-guoguang";

	private final OSSClient ossClient; // 阿里云OSS对象存储

	@Autowired
	public OssClientUtil(OSSClient ossClient) {
		this.ossClient = ossClient;
	}

	/**
	 * 上传文件
	 * @param file 文件
	 */
	public String uploadFile(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		ossClient.putObject(BUCKET_NAME, fileName,file.getInputStream());
		String fileUrl = ossClient.generatePresignedUrl(BUCKET_NAME, fileName, new Date(System.currentTimeMillis() + (1000 * 30)), HttpMethod.GET).toString();
		return fileUrl;
	}
}
