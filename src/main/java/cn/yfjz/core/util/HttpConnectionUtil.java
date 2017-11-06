package cn.yfjz.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;



/**
 * 进行http请求，获取第三方返回的结果
 * 
 * */
public class HttpConnectionUtil {
	public static String getHttpContent(String url,Map<String,Object> paramsMap) throws Exception{
		HttpURLConnection con=null;
		URL httpUrl=new URL(url);
		con=(HttpURLConnection)httpUrl.openConnection();
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty("Charset", "utf-8");
		con.setRequestMethod("POST");
		con.setDoOutput(true);
		StringBuffer params=new StringBuffer();
		if(paramsMap!=null){
			for(String key:paramsMap.keySet()){
				params.append(key).append("=").append(paramsMap.get(key))
				.append("&");
			}
		}
		byte[] bytes=params.toString().getBytes();
		con.getOutputStream().write(bytes);
		InputStream is=con.getInputStream();
		String json=new String(readInputStream(is));
		return json;
	}
	public static String readInputStream(InputStream inStream) throws Exception{
		 BufferedReader in = new BufferedReader(new InputStreamReader(inStream, "UTF-8"));
		 StringBuffer buffer = new StringBuffer();
		 String line = "";
		 while ((line = in.readLine()) != null){
			 buffer.append(line);
		 }
		 return buffer.toString();
	}

	//模拟浏览器
	public static String sendGet(String url, String param) {
		String result = "";
		String urlName = url + "?" + param;
		try {
			URL realURL = new URL(urlName);
			URLConnection conn = realURL.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
			conn.connect();

			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += "\n" + line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
