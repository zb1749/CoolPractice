package http;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.commons.httpclient.ConnectTimeoutException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;



/**
 * 接口调用实现方法
 */
public class HttpUtil {
    /**
     * 日志对象
     */
    private static Logger log = Logger.getLogger(HttpUtil.class);

    public final static String LINE_SEP = System.getProperty("line.separator");

    public final static String HTTP_VER = "HTTP/1.0";

    public final static int OK = 200;// 成功响应码

    public final static int BAD_REQ = 400;// 失败响应码

    public final static int INTERAL_ERROR = 500;// 服务器错误响应码

    public final static String CHARSET_UTF8 = "UTF-8";// 系统使用的字符集

    public final static String RST_CODE = "Result-Code";// 响应结果码键值

    public final static String RST_MSG = "result_msg";// 响应结果

    public final static int OTHER_ERR_CODE = 999;// 代表http请求異常

    public final static int MAX_BUFFER_SIZE = 1024;// 读取输入流中的数据到缓冲区,该缓冲区的最大长度

    public static final String CONT_TYPE = "content-type";// http的header中的content-type属性的名字

    public static final String CONT_LEN = "content-length";// http的header中的content-length属性的名字

    public static final String CONT_TYPE_VAL_XML_UTF8 = "text/xml; charset=UTF-8";// http的header中的content-type属性的内容

    /**
     * http的header中的content-type属性的传输类型
     */
    public static final String CONT_TYPE_TEXT_XML = "text/xml";

    /**
     * 链接的超时数,默认为10秒,此处要做成可配置
     */
    private static final int CONNECTION_TIMEOUT = 10000;

    /**
     * 读取数据的超时数,默认为10秒,此处要做成可配置
     */
    private static final int SOCKET_TIMEOUT = 10000;

    /**
     * 客户端总并行链接最大数，默认为50
     */
    private static final int MAX_TOTAL_CONNECTIONS = 50;

    /**
     * 每个主机的最大并行链接数，默认为10
     */
    private static final int MAX_CONNECTIONS_PER_HOST = 10;

    private static final int MAX_RETRY_COUNT = 1;// 失败重试次数

    private static MultiThreadedHttpConnectionManager connectionManager;

    private static int maxTotalConnectNum = MAX_TOTAL_CONNECTIONS;// 池中最大的连接数

    private static int maxConnectPerHost = MAX_CONNECTIONS_PER_HOST;// 池中最大的连接数

    private static int connTimeout = CONNECTION_TIMEOUT;// 连接超时时间

    private static int socketTimeout = SOCKET_TIMEOUT;// 读取数据超时

    public static int retryCount = MAX_RETRY_COUNT;// 重试次数

    public final static String HTTP_OK = "200";

    public final static String NSS = "nss";

    public static final String SEND_FAILURE = "sendFailure";// 发送失败

    public static final String INNER_FAILURE = "innerFailure";// 内部发送失败

    /**
     * HttpUtil初始化块
     */
    static {
        connectionManager = new MultiThreadedHttpConnectionManager();
        maxTotalConnectNum = MAX_TOTAL_CONNECTIONS;
        maxConnectPerHost = MAX_CONNECTIONS_PER_HOST;
        connTimeout = CONNECTION_TIMEOUT;
        socketTimeout = SOCKET_TIMEOUT;
        retryCount = MAX_RETRY_COUNT;
        // 设定参数：客户端的总连接数
        connectionManager.getParams()
                .setMaxTotalConnections(maxTotalConnectNum);
        // 设定参数：与每个主机的最大连接数
        connectionManager.getParams().setDefaultMaxConnectionsPerHost(
                maxConnectPerHost);
        // 设置超时时间
        connectionManager.getParams().setConnectionTimeout(connTimeout);
        connectionManager.getParams().setSoTimeout(socketTimeout);
    }

    /**
     * 发送http请求，并返回xml报文 客户端发送xml报文请求，并接受服务端返回的xml报文
     *
     * @param url 请求的服务端地址
     * @param xml 请求的xml报文
     * @return String 返回的xml报文
     */
    public static String sendHttpRequest(String url, String xml) {
        if (log.isDebugEnabled()) {
            String[][] appInfo = {{"url", url}, {"\n xml", xml}};
            log.debug("Enter sendHttpRequest()!"+ appInfo.toString());
        }

        EntityEnclosingMethod httpMethod = new PostMethod(url);
        int resultCode = 0;
        try {
            // 设置header信息，传输XML格式的
            httpMethod.setRequestHeader(CONT_TYPE, CONT_TYPE_VAL_XML_UTF8);

            // 发送含xml消息体的对象
            RequestEntity entity = new StringRequestEntity(xml,
                    CONT_TYPE_TEXT_XML, CHARSET_UTF8);

            httpMethod.setRequestEntity(entity);

            // 处理响应结果码
            resultCode = getHttpClient().executeMethod(httpMethod);
            if (HttpStatus.SC_OK != resultCode) {
                if (log.isDebugEnabled()) {
                    String[][] appInfo = {
                            {"resuleCode", String.valueOf(resultCode)},
                            {"url", url}};
                    log.error("URL = [" + url + "] | xml = [" + xml + "] 响应错误 ResponseCode = [" + resultCode + "]");
                }
                return SEND_FAILURE;
            }

            String responseXml = null;
            byte[] resBody = httpMethod.getResponseBody();
            if (null == resBody || 0 == resBody.length) {
                responseXml = httpMethod.getResponseBodyAsString();
            } else {
                responseXml = new String(resBody, CHARSET_UTF8);
            }
            if (log.isDebugEnabled()) {
                String[][] appInfo = {{"url", url},
                        {"response xml", responseXml}};
                log.debug("Exit sendHttpRequest()!"+ appInfo.toString());
            }
            return responseXml;
        } catch (Exception ex) {
            if (log.isDebugEnabled()) {
                log.error("send http request error!", ex);
            }
            return INNER_FAILURE;
        } finally {
            if (null != httpMethod) {
                httpMethod.releaseConnection();
            }
        }
    }

    /**
     * 发送post请求
     *
     * @param strHttpUrl  请求URL
     * @param strBody     请求消息体
     * @param iRetryCount 请求重试次数
     * @return HttpResp [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static HttpResp sendPostReqXml(String strHttpUrl, String strBody,
                                          int iRetryCount) {
        int iCnt = 0;
        HttpResp resp = null;
        do {
            try {
                resp = HttpUtil.sendPostReqXml(strHttpUrl, strBody);
            } catch (Exception ex) {
                log.error("Send HTTP request error", ex);
            }

            if ((null != resp) && (HttpUtil.OK == resp.getRstCode()))// 如果发送成功则直接跳出循环
            {
                if (log.isInfoEnabled()) {
                    log.info("HttpMessage send successfully strHttpUrl: "
                            + strHttpUrl);
                }
                break;
            }

            iCnt++;// 执行一次
        } while (iCnt < iRetryCount);// 迭代次数小于配置的次数

        return resp;
    }

    /**
     * 向接口发送请求调用接口
     *
     * @return String 相应消息体
     */
    public static String sendHttpRequestWithResponsBody(String strHttpUrl,
                                                        String strBody) {
        return sendHttpRequest(strHttpUrl, strBody);
    }

    /**
     * <一句话功能简述> <功能详细描述>
     *
     * @param strHttpUrl
     * @param strBody
     * @return HttpResp [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static HttpResp sendPostReqXml(String strHttpUrl, String strBody) {
        return sendPostReqXml(strHttpUrl, null, strBody);
    }

    /**
     * 发送post请求
     *
     * @param strHttpUrl 请求地址
     * @param headMap    请求头参数
     * @param strBody    请求消息体
     * @return HttpResp [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static HttpResp sendPostReqXml(String strHttpUrl,
                                          Map<String, String> headMap, String strBody) {
        return sendPostReq(strHttpUrl, headMap, CONT_TYPE_TEXT_XML, strBody);
    }

    /**
     * 发送POST请求并解析http响应
     *
     * @return
     */
    public static HttpResp sendPostReq(String strHttpUrl,
                                       Map<String, String> headMap, String strContType, String strBody) {
        if (isEmpty(strHttpUrl)) {
            log.error("Request target is null");
            return null;
        }

        StringBuilder sbTmp = new StringBuilder(200);
        strBody = trim(strBody);

        // 失败信息
        String errorInfo = null;

        if (log.isDebugEnabled()) {
            sbTmp.append("Enter sendPostReq():");
            sbTmp.append(LINE_SEP).append("POST ");
            sbTmp.append(strHttpUrl).append(" ").append(HTTP_VER);
            if ((null != headMap) && (!headMap.isEmpty())) {
                for (Map.Entry<String, String> tmpEntry : headMap.entrySet()) {
                    sbTmp.append(LINE_SEP).append(tmpEntry.getKey())
                            .append(":").append(tmpEntry.getValue());
                }
            }

            sbTmp.append(LINE_SEP).append(LINE_SEP).append(strBody);
            log.debug(sbTmp.toString());
        }

        HttpResp resp = new HttpResp();
        PostMethod postMethod = new PostMethod(strHttpUrl);
        // Request content will be getted from byte array
        if (null != strContType) {
            RequestEntity entity = null;
            try {
                entity = new StringRequestEntity(strBody, strContType,
                        CHARSET_UTF8);
                postMethod.setRequestEntity(entity);
            } catch (UnsupportedEncodingException ex) {
                log.error("Charset error:" + CHARSET_UTF8, ex);
            }
        }

        // 设置请求消息头
        if ((null != headMap) && (!headMap.isEmpty())) {
            for (Map.Entry<String, String> tmpEntry : headMap.entrySet()) {
                postMethod.setRequestHeader(tmpEntry.getKey(),
                        tmpEntry.getValue());
            }
        }

        // 设置Content-Type头参数
        // postMethod.setRequestHeader(CONT_TYPE, strContType);
        postMethod.setRequestHeader(CONT_TYPE, CONT_TYPE_VAL_XML_UTF8);

        // 设置Content-Length头参数
        int iLen = 0;
        try {
            iLen = strBody.getBytes(CHARSET_UTF8).length;
        } catch (UnsupportedEncodingException e1) {
            iLen = strBody.getBytes().length;
        }
        postMethod.setRequestHeader(CONT_LEN, String.valueOf(iLen));

        String strRsp = null;
        try {
            // 处理响应结果码
            int iHttpRst = getHttpClient().executeMethod(postMethod);
            if (HttpStatus.SC_OK != iHttpRst) {
                log.error("send http GET request error,ResponseCode="
                        + iHttpRst);
            }
            resp.setRstCode(iHttpRst);
            // 读取响应消息头
            Map<String, String> rspHeadMap = new HashMap<String, String>();
            Header[] headArr = postMethod.getResponseHeaders();
            int iHeadLen = 0;// 头的长度
            if ((null != headArr) && (headArr.length > 0)) {
                iHeadLen = headArr.length;
                for (int i = 0; i < iHeadLen; i++) {
                    rspHeadMap.put(headArr[i].getName(), headArr[i].getValue());
                }
            }
            resp.setHeadMap(rspHeadMap);

            // 读取响应消息体
            byte[] tmpBuf = new byte[MAX_BUFFER_SIZE];
            long lHttpLen = postMethod.getResponseContentLength();
            long i = -1l;
            if (i == lHttpLen) {
                InputStream in = postMethod.getResponseBodyAsStream();
                ByteArrayOutputStream baout = new ByteArrayOutputStream(
                        MAX_BUFFER_SIZE);

                int iCnt = 0;
                while ((iCnt = in.read(tmpBuf)) != -1) {
                    baout.write(tmpBuf, 0, iCnt);
                }
                tmpBuf = baout.toByteArray();
                lHttpLen = tmpBuf.length;
            } else {
                tmpBuf = postMethod.getResponseBody();
                lHttpLen = postMethod.getResponseContentLength();
            }

            if (null == tmpBuf)
            // if (null == strRsp)
            {
                log.info("----------------getResponse null--------------");
                tmpBuf = new byte[0];
            }

            strRsp = new String(tmpBuf, CHARSET_UTF8);
            resp.setResp(strRsp);
            resp.setContLen(lHttpLen);
        } catch (ConnectTimeoutException e) {
            errorInfo = "连接超时！";
            log.error(
                    "Conss 发送nss请求超时！ 请求url：" + strHttpUrl + "消息体：" + strBody,
                    e);
            resp.setRstCode(BAD_REQ);
        } catch (SocketException e) {
            errorInfo = "网络异常！";
            log.error("Conss 发送nss发生网络异常！ 请求url：" + strHttpUrl + "消息体："
                    + strBody, e);
            resp.setRstCode(BAD_REQ);
        } catch (Exception e) {
            errorInfo = "服务器异常！";
            log.error("", e);
            resp.setRstCode(INTERAL_ERROR);
        } finally {
            if (null != postMethod) {
                postMethod.releaseConnection();
            }

            // TODO [原华为系统][注释] 后续添加nss任务失败处理，例如持久化记录等

        }

        if (log.isDebugEnabled()) {
            sbTmp.delete(0, sbTmp.length());
            sbTmp.append("Exit sendPostReq()");
            sbTmp.append(LINE_SEP).append(HTTP_VER);
            sbTmp.append(" ").append(resp.getRstCode())
                    .append(trim(resp.getRstDesc()));
            if ((null != resp.getHeadMap()) && (!resp.getHeadMap().isEmpty())) {
                for (Map.Entry<String, String> tmpEntry : resp.getHeadMap()
                        .entrySet()) {
                    sbTmp.append(LINE_SEP).append(tmpEntry.getKey())
                            .append(":").append(tmpEntry.getValue());
                }
            }
            sbTmp.append(LINE_SEP).append(LINE_SEP).append(resp.getResp());
            log.debug(sbTmp.toString());
            sbTmp.delete(0, sbTmp.length());
        }

        return resp;
    }

    /**
     * 发生Get请求
     *
     * @param url 请求url
     * @return String [返回类型说明]
     * @throws throws          [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String sendHttpRequestByGet(String url) {
        if (log.isDebugEnabled()) {
            log.debug("Enter sendHttpRequest()!"+ "url"+ url);
        }

        GetMethod getMethod = new GetMethod(url);
        String responseXml = null;
        try {
            int resultCode = getHttpClient().executeMethod(getMethod);
            if (HttpStatus.SC_OK != resultCode) {
                if (log.isDebugEnabled()) {
                    String[][] appInfo = {{"resuleCode", String.valueOf(resultCode)}, {"url", url}};
                    log.error("send http Get request error!"+ appInfo.toString());
                }

            }

            // 响应消息

            byte[] resBody = getMethod.getResponseBody();
            if (null == resBody || 0 == resBody.length) {
                responseXml = getMethod.getResponseBodyAsString();
            } else {
                responseXml = new String(resBody, "utf-8");
            }

            if (log.isDebugEnabled()) {
                String[][] appInfo = {{"url", url}, {"response xml", responseXml}};
                log.debug("Exit sendHttpRequest()!"+ appInfo.toString());
            }

        } catch (Exception ex) {

        } finally {
            if (null != getMethod) {
                getMethod.releaseConnection();
            }
        }
        return responseXml;
    }

    /**
     * 构造Http客户端对象 <功能详细描述>
     *
     * @return HttpClient [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    private static HttpClient getHttpClient() {
        // 使用连接池技术创建HttpClient对象
        HttpClient httpClient = new HttpClient(connectionManager);
        return httpClient;
    }

    /**
     * 给字符串去掉空格 <功能详细描述>
     *
     * @param arg
     * @return String [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static String trim(String arg) {
        if (null == arg) {
            return "";
        } else {
            return arg.trim();
        }
    }

    /**
     * 检查字符串是否为空，字符串为null，或者长度为0都认为为空 <功能详细描述>
     *
     * @param str
     * @return boolean [返回类型说明]
     * @throws throws [违例类型] [违例说明]
     * @see [类、类#方法、类#成员]
     */
    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        }

        if (0 == str.trim().length()) {
            return true;
        }

        return false;
    }

    public static class HttpResp {
        private int rstCode;// http请求响应码

        private String rstDesc;// http响应描述

        private long contLen;// http响应的长度

        private Map<String, String> headMap;// Http响应头

        private String resp;// http响应数据

        /**
         * @return 返回 rstCode
         */
        public int getRstCode() {
            return rstCode;
        }

        /**
         * 对rstCode进行赋值
         */
        public void setRstCode(int rstCode) {
            this.rstCode = rstCode;
        }

        /**
         * @return 返回 contLen
         */
        public long getContLen() {
            return contLen;
        }

        /**
         * 对contLen进行赋值
         */
        public void setContLen(long contLen) {
            this.contLen = contLen;
        }

        /**
         * @return 返回 headMap
         */
        public Map<String, String> getHeadMap() {
            return headMap;
        }

        /**
         * 对headMap进行赋值
         */
        public void setHeadMap(Map<String, String> headMap) {
            this.headMap = headMap;
        }

        /**
         * @return 返回 resp
         */
        public String getResp() {
            return resp;
        }

        /**
         * 对resp进行赋值
         */
        public void setResp(String resp) {
            this.resp = resp;
        }

        /**
         * @return 返回 rstDesc
         */
        public String getRstDesc() {
            return rstDesc;
        }

        /**
         * 对rstDesc进行赋值
         */
        public void setRstDesc(String rstDesc) {
            this.rstDesc = rstDesc;
        }

        public String toString() {
            StringBuffer sbTmp = new StringBuffer(512);
            sbTmp.append("code:").append(this.rstCode).append(",desc:")
                    .append(this.rstDesc);
            sbTmp.append(",len:").append(this.contLen).append(LINE_SEP);
            sbTmp.append("headers:").append(headMap).append(LINE_SEP);
            sbTmp.append("resp:").append(LINE_SEP);
            sbTmp.append(resp);
            return sbTmp.toString();
        }
    }

    /**
     * 发送http请求，并返回xml报文 客户端发送xml报文请求，并接受服务端返回的xml报文
     *
     * @param url 请求的服务端地址
     * @param xml 请求的xml报文
     * @return String 返回的xml报文
     * <p>
     * 注：此方法为简单方法，基本用于向nss同步
     */
    public static String sendHttpRequestOfGBK(String url, String xml) {

        if (log.isDebugEnabled()) {
            log.debug("Enter sendHttpRequest()!=>" + "url=" + url);
        }
        log.info("发送报文：" + xml);
        EntityEnclosingMethod httpMethod = new PostMethod(url);
        int resultCode = 0;
        String responseXML = null;

        try {
            // 设置header信息，传输XML格式的
            httpMethod.setRequestHeader(CONT_TYPE, "text/xml; charset=GBK");
            // 发送含xml消息体的对象
            RequestEntity entity = new StringRequestEntity(xml,
                    CONT_TYPE_TEXT_XML, "GBK");
            // 绑定实体关系
            httpMethod.setRequestEntity(entity);
            // 处理响应结果码
            resultCode = getHttpClient().executeMethod(httpMethod);

            if (resultCode != HttpStatus.SC_OK) {
                log.error("send http request error,ResponseCode=" + resultCode + ",url=" + url);
            }
            byte[] resBody = httpMethod.getResponseBody();
            if (null == resBody || resBody.length == 0) {
                responseXML = httpMethod.getResponseBodyAsString();
            } else {
                responseXML = new String(resBody, "GBK");
            }
            if (log.isInfoEnabled()) {
                log.info("Exit sendHttpRequest()!=>" + "response xml="
                        + responseXML);
            }
        } catch (Exception e) {
            if (url.contains("nss")) {
                // TODO后续添加nss任务失败处理
            }

            log.error("send http request error!", e);

        } finally {
            if (httpMethod != null) {
                httpMethod.releaseConnection();
            }
        }

        return responseXML;

    }
}