package com.eb.dianlianbao_server.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class HttpClientUtil {
	/**
	 * http客户端工具类
	 *
	 */
	public static final String SunX509 = "SunX509";
	public static final String JKS = "JKS";
	public static final String PKCS12 = "PKCS12";
	public static final String TLS = "TLS";

	/**
	 * get HttpURLConnection
	 * @param strUrl url地址
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public static HttpURLConnection getHttpURLConnection(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
		return httpURLConnection;
	}

	/**
	 * get HttpsURLConnection
	 * @param strUrl url地址ַ
	 * @return HttpsURLConnection
	 * @throws IOException
	 */
	public static HttpsURLConnection getHttpsURLConnection(String strUrl) throws IOException {
		URL url = new URL(strUrl);
		HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
		return httpsURLConnection;
	}

	/**
	 * 获取不带查询串的url
	 * @param strUrl
	 * @return String
	 */
	public static String getURL(String strUrl) {

		if (null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if (-1 != indexOf) {
				return strUrl.substring(0, indexOf);
			}

			return strUrl;
		}

		return strUrl;

	}

	/**
	 * 获取查询�?
	 * @param strUrl
	 * @return String
	 */
	public static String getQueryString(String strUrl) {

		if (null != strUrl) {
			int indexOf = strUrl.indexOf("?");
			if (-1 != indexOf) {
				return strUrl.substring(indexOf + 1, strUrl.length());
			}

			return "";
		}

		return strUrl;
	}

	/**
	 * 查询字符串转化为map
	 * name1=key1&name2=key2&...
	 * @param queryString
	 * @return
	 */
	public static Map queryString2Map(String queryString) {
		if (null == queryString || "".equals(queryString)) {
			return null;
		}

		Map m = new HashMap();
		String[] strArray = queryString.split("&");
		for (int index = 0; index < strArray.length; index++) {
			String pair = strArray[index];
			HttpClientUtil.putMapByPair(pair, m);
		}

		return m;

	}

	/**
	 * 把键值添加到map
	 * pair:name=value
	 * @param pair name=value
	 * @param m
	 */
	public static void putMapByPair(String pair, Map m) {

		if (null == pair || "".equals(pair)) {
			return;
		}

		int indexOf = pair.indexOf("=");
		if (-1 != indexOf) {
			String k = pair.substring(0, indexOf);
			String v = pair.substring(indexOf + 1, pair.length());
			if (null != k && !"".equals(k)) {
				m.put(k, v);
			}
		} else {
			m.put(pair, "");
		}
	}

	/**
	 * BufferedReader转换成String<br/>
	 * 注意:流关闭需要自行处�?
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static String bufferedReader2String(BufferedReader reader) throws IOException {
		StringBuffer buf = new StringBuffer();
		String line = null;
		while ((line = reader.readLine()) != null) {
			buf.append(line);
			buf.append("\r\n");
		}

		return buf.toString();
	}

	/**
	 * 处理输出<br/>
	 * 注意:流关闭需要自行处�?
	 * @param out
	 * @param data
	 * @param len
	 * @throws IOException
	 */
	public static void doOutput(OutputStream out, byte[] data, int len) throws IOException {
		int dataLen = data.length;
		int off = 0;
		while (off < data.length) {
			if (len >= dataLen) {
				out.write(data, off, dataLen);
				off += dataLen;
			} else {
				out.write(data, off, len);
				off += len;
				dataLen -= len;
			}

			// ˢ�»�����
			out.flush();
		}

	}

	/**
	 * 获取SSLContext
	 * @param trustFile
	 * @param trustPasswd
	 * @param keyFile
	 * @param keyPasswd
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyStoreException
	 * @throws IOException
	 * @throws CertificateException
	 * @throws UnrecoverableKeyException
	 * @throws KeyManagementException
	 */
	public static SSLContext getSSLContext(FileInputStream trustFileInputStream, String trustPasswd, FileInputStream keyFileInputStream, String keyPasswd)
			throws NoSuchAlgorithmException, KeyStoreException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {

		// ca
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore trustKeyStore = KeyStore.getInstance(HttpClientUtil.JKS);
		trustKeyStore.load(trustFileInputStream, HttpClientUtil.str2CharArray(trustPasswd));
		tmf.init(trustKeyStore);

		final char[] kp = HttpClientUtil.str2CharArray(keyPasswd);
		KeyManagerFactory kmf = KeyManagerFactory.getInstance(HttpClientUtil.SunX509);
		KeyStore ks = KeyStore.getInstance(HttpClientUtil.PKCS12);
		ks.load(keyFileInputStream, kp);
		kmf.init(ks, kp);

		SecureRandom rand = new SecureRandom();
		SSLContext ctx = SSLContext.getInstance(HttpClientUtil.TLS);
		ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), rand);

		return ctx;
	}

	/**
	 * 字符串转换成char数组
	 * @param str
	 * @return char[]
	 */
	public static char[] str2CharArray(String str) {
		if (null == str)
			return null;

		return str.toCharArray();
	}

	public static InputStream String2Inputstream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}

	/**
	 * InputStream转换成Byte
	 * 注意:流关闭需要自行处�?
	 * @param in
	 * @return byte
	 * @throws Exception
	 */
	public static byte[] InputStreamTOByte(InputStream in) throws IOException {

		int BUFFER_SIZE = 4096;
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;

		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);

		data = null;
		byte[] outByte = outStream.toByteArray();
		outStream.close();

		return outByte;
	}

	/**
	 * InputStream转换成String
	 * 注意:流关闭需要自行处�?
	 * @param in
	 * @param encoding 编码
	 * @return String
	 * @throws Exception
	 */
	public static String InputStreamTOString(InputStream in, String encoding) throws IOException {

		return new String(InputStreamTOByte(in), encoding);

	}

	/**
	 * 向指定 URL 发送POST方法的请求
	 * @param url  发送请求的 URL
	 * @param param  请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			LogUtils.logError("发送post失败", e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	  /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
}