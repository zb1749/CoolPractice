package servlet;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.log4j.Logger;
//import org.springframework.web.context.ContextLoader;
import servlet.pojo.ContentOfflineInfoPojo;
import servlet.pojo.ContentOfflinePojo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * NSS统一接受外部请求同步管控中心
 */
public class SyncShelfContentOfflineServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger logger = Logger.getLogger(SyncShelfContentOfflineServlet.class);

//	private BookSoldOutService syncOfflineService = (BookSoldOutService) ContextLoader.getCurrentWebApplicationContext()
//			.getBean("bookSoldOutService");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        logger.info("收到内容下架请求.");
        // 产生应答信息
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-control", "no-cache");
        response.getWriter().print(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Response>\n<IsAccepted>1</IsAccepted>\n</Response>");
        response.getWriter().flush();
        // 解析请求
        String requestXml = getRequestXml(request, "utf-8");
        ContentOfflinePojo requestPojo = parseContentOfflineRequest(requestXml);

        logger.info("解析请求成功.");
        // 添加下架任务
        if (requestPojo != null) {
            List<ContentOfflineInfoPojo> contentOfflineList = requestPojo.getContentOfflineList();
            if (contentOfflineList != null && !contentOfflineList.isEmpty()) {
                for (ContentOfflineInfoPojo pojo : contentOfflineList) {
                    //syncOfflineService.addBookOffShelveTask(pojo.getContentId(), pojo.getChapterId());
                    System.out.println("bookId: " + pojo.getContentId() + ", chapterId: " + pojo.getChapterId());
                }
            }
        }

        logger.info("添加下架任务成功.");

    }

    /**
     * 获取请求xml信息
     *
     * @param request
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String getRequestXml(HttpServletRequest request, String encoding) throws IOException {
        StringBuffer sb = new StringBuffer(request.getContentLength());
        String line = null;
        request.setCharacterEncoding(encoding);
        while (null != (line = request.getReader().readLine())) {
            sb.append(line);
        }
        return sb.toString();

    }

    /**
     * 解析请求信息
     *
     * @param xml
     * @return
     */
    private static ContentOfflinePojo parseContentOfflineRequest(String xml) {
        XStream xstream = new XStream(new DomDriver());
        // 类名称转换
        xstream.alias("Request", ContentOfflinePojo.class);
        xstream.alias("contentOfflineList", List.class);
        xstream.alias("contentOfflineInfo", ContentOfflineInfoPojo.class);
        return (ContentOfflinePojo) xstream.fromXML(xml);
    }

}
