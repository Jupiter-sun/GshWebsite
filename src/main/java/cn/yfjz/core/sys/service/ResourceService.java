package cn.yfjz.core.sys.service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import cn.yfjz.core.util.MD5;
import cn.yfjz.core.util.UUIDHexGenerator;
import cn.yfjz.core.util.VFSUtil;


@Service
public class ResourceService {
	private static final Log logger = LogFactory.getLog(ResourceService.class);
	
	/**资源存放路径*/
	public final static String RESOURCE_IMG_PATHNAME = "img";
	
	/**
	 * 上传文件并返回上传后的路径
	 * @param file
	 * @return
	 * @throws Exception 
	 */
	public String upload(MultipartFile file){
		String originalFilename = file.getOriginalFilename();
		int index = originalFilename.lastIndexOf(".");
		String suffix = null;
		if(index >= 0) {
			suffix = originalFilename.substring(index+1);
		}
		//计算文件的MD5
		String md5;
		try {
			md5 = generateMD5(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			md5 = UUIDHexGenerator.gen();
		}
		String filepath = getHashFilepath(md5) + "/" + md5 + (StringUtils.isEmpty(suffix)? "" : "." + suffix.toLowerCase());
		VFSUtil.saveFile(file, filepath);
		return filepath;
	}
	
	/**
	 * 构造一个hash文件存储路径，支持最多65536个目录
	 * @param schoolId
	 * @param md5
	 * @return
	 */
	public String getHashFilepath(String md5) {
		if (md5 == null || md5.length() < 4) {
			md5 = MD5.go2(UUIDHexGenerator.get());
		}
		StringBuffer path = new StringBuffer();
		path.append("/").append(RESOURCE_IMG_PATHNAME).append("/").append(StringUtils.substring(md5, 0, 2))
				.append("/").append(StringUtils.substring(md5, md5.length()-2));
		return path.toString();
	}
	
	/**
	 * 计算上传文件的md5，注意：计算完InputStream将会被关闭
	 * @param file
	 * @return
	 */
	public String generateMD5(InputStream InputStream) {
		String md5 = null;
		try {
			md5 =  MD5.getFileMD5Checksum(InputStream);
			return md5;
		}catch (Exception e) {
			logger.error("生成md5失败"+ e.getMessage(), e);
			return md5;
		}finally{
			IOUtils.closeQuietly(InputStream);
		}
	}
}
