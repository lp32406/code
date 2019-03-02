package com.eb.dianlianbao_server.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eb.dianlianbao_server.util.alipay.Md5Util;


/**
 * 请求处理�?
 * 请求处理类继承此类，重写createSign方法即可�?
 *
 */
public class RequestHandler {

    /** 网关url地址 */
    private String gateUrl;

    /** 密钥 */
    private String key;

    /** 请求的参�? */
	@SuppressWarnings("rawtypes")
	private SortedMap parameters;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    /**
     * 构�?�函�?
     * @param request
     * @param response
     */
	@SuppressWarnings("rawtypes")
	public RequestHandler(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.gateUrl = "https://gw.tenpay.com/gateway/pay.htm";
        this.key = "";
        this.parameters = new TreeMap();
    }

    /**
    *初始化函数�??
    */
    public void init() {
        //nothing to do
    }

    /**
    *获取入口地址,不包含参数�??
    */
    public String getGateUrl() {
        return gateUrl;
    }

    /**
    *设置入口地址,不包含参数�??
    */
    public void setGateUrl(String gateUrl) {
        this.gateUrl = gateUrl;
    }

    /**
    *获取密钥
    */
    public String getKey() {
        return key;
    }

    /**
    *设置密钥
    */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * 获取参数�?
     * @param parameter 参数名称
     * @return String
     */
    public String getParameter(String parameter) {
        String s = (String)this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    /**
     * 设置参数�?
     * @param parameter 参数名称
     * @param parameterValue 参数�?
     */
	@SuppressWarnings("unchecked")
	public void setParameter(String parameter, Object parameterValue) {
        String v = "";
        if(null != parameterValue) {
            if(parameterValue instanceof String)
            v = ((String) parameterValue).trim();
        }
        this.parameters.put(parameter, v);
    }

    /**
     * 返回�?有的参数
     * @return SortedMap
     */
	@SuppressWarnings("rawtypes")
	public SortedMap getAllParameters() {
        return this.parameters;
    }

    /**
     * 获取带参数的请求URL
     * @return String
     * @throws UnsupportedEncodingException
     */
	@SuppressWarnings("rawtypes")
	public String getRequestURL() throws UnsupportedEncodingException {

        this.createSign();

        StringBuffer sb = new StringBuffer();
        String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();

            if(!"spbill_create_ip".equals(k)) {
                sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
            } else {
                sb.append(k + "=" + v.replace("\\.", "%2E") + "&");
            }
        }

        //去掉�?后一�?&
        String reqPars = sb.substring(0, sb.lastIndexOf("&"));

        return this.getGateUrl() + "?" + reqPars;

    }

    public void doSend() throws UnsupportedEncodingException, IOException {
        this.response.sendRedirect(this.getRequestURL());
    }

    /**
     * 创建md5摘要,规则�?:按参数名称a-z排序,遇到空�?�的参数不参加签名�??
     */
	@SuppressWarnings("rawtypes")
	protected void createSign() {
        StringBuffer sb = new StringBuffer();
        Set es = this.parameters.entrySet();
        Iterator it = es.iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            String k = (String)entry.getKey();
            String v = (String)entry.getValue();
            if(null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + this.getKey());
        String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        String sign = Md5Util.MD5Encode(sb.toString(), enc).toUpperCase();

        this.setParameter("sign", sign);

    }

    protected HttpServletRequest getHttpServletRequest() {
        return this.request;
    }

    protected HttpServletResponse getHttpServletResponse() {
        return this.response;
    }
}
