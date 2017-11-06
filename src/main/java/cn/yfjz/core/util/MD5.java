package cn.yfjz.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class MD5 {
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(MD5.getMD5Str("1234").toUpperCase());
	}
	
	public static String go2(String str){
    	MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}

		return md5StrBuff.toString();
    }
	
	/**
     * 计算文件的md5
     * @param file
     * @return
     * @throws Exception
     */
    public static byte[] createChecksum(File file) throws Exception {
        int BUFFER_SIZE = 1024 * 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        int count = 0;
        RandomAccessFile randomAccessFile = null;
        Long fileSize = file.length();
        long position = 0;

        if (fileSize >= 1024 * 1024 * 32) {//32M
            MessageDigest complete = MessageDigest.getInstance("MD5");
            
            do {//取文件前6M
                randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(position);
                    count = randomAccessFile.read(buffer);

                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                        randomAccessFile = null;
                    }
                    if (count > 0) {
                        complete.update(buffer, 0, count);
                    }
                position += count;
            } while (position <= 5242880); // 1024*1024*5M
            
            position=fileSize-BUFFER_SIZE;
            do {//取文件后6M
                randomAccessFile = new RandomAccessFile(file, "rw");

                randomAccessFile.seek(position);
                count = randomAccessFile.read(buffer);
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                    randomAccessFile = null;
                }
                if (count > 0) {
                    complete.update(buffer, 0, count);
                }
                position -= count;
            } while (position >= fileSize-5242880); // 1024*1024*5M
            
            complete.update(fileSize.byteValue());
            return complete.digest();
        } else {
            InputStream fis = new FileInputStream(file);
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;
            
            do {
                numRead = fis.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while (numRead != -1);
            
            fis.close();
            return complete.digest();
        }

    }
    public static byte[] createChecksum(InputStream  is)  throws Exception{
    	int BUFFER_SIZE = 1024 * 1024;
        byte[] buffer = new byte[BUFFER_SIZE];
        int count = 0;
        Long fileSize = (long) is.available();
        long position = 0;
        if(fileSize >=  32*1024*1024) {
        	MessageDigest complete = MessageDigest.getInstance("MD5");
	        do {//取文件前6M
	                count = is.read(buffer);
	                if (count > 0) {
	                    complete.update(buffer, 0, count);
	                } else {
	                	break;
	                }
	            position += count;
	        } while (position <= 5242880); // 1024*1024*5M
	        long total = (is.available() - 12582912)/1048576;
	        long first = total /2;
	        long last = total - first;
	        is.skip(first * 1048576 );
	        position = 0;
	        do { //中间6M
	            count = is.read(buffer);
	            if (count > 0) {
	                complete.update(buffer, 0, count);
	            }else {
	            	break;
	            }
	            if (count > 0) {
	                complete.update(buffer, 0, count);
	            }
	            position += count;
	        } while (position <= 5242880);
	        is.skip(last * 1048576);
	        position = 0;
	        do {//取文件后6M以后数据
	            count = is.read(buffer);
	            if (count > 0) {
	                complete.update(buffer, 0, count);
	            }else {
	            	break;
	            }
	            position =+ count;
	        } while (position <= 5242880); // 1024*1024*5M
	        complete.update(fileSize.byteValue());
	        return complete.digest();
        } else {
            MessageDigest complete = MessageDigest.getInstance("MD5");
            int numRead;
            do {
                numRead = is.read(buffer);
                if (numRead > 0) {
                    complete.update(buffer, 0, numRead);
                }
            } while(numRead != -1);
            return complete.digest();
        }
    }
    public static String getFileMD5Checksum(File file) throws Exception {
        byte[] b = createChecksum(file);
        return md5(b);
    }
    
    public static String getFileMD5Checksum(InputStream  inputStream) throws Exception {
    	byte[] b = createChecksum(inputStream);
    	return md5(b);
    }
    
    private static String md5(byte []b) {
    	 return new String(Hex.encodeHexString(b));
    }
}
