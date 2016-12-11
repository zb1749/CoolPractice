package struts2.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.*;


public class UploadAction extends ActionSupport {
    // 封装文件标题请求参数的属性
    private String title;
    // 封装上传文件域的属性
    private File upload;
    // 封装上传文件类型的属性
    private String uploadContentType;
    // 封装上传文件名的属性
    private String uploadFileName;
    // 直接在struts.xml文件中配置的属性
    private String savePath;

    // 接受struts.xml文件配置值的方法
    public void setSavePath(String value) {
        this.savePath = value;
    }

    // 获取上传文件的保存位置
    private String getSavePath() throws Exception {
        return ServletActionContext.getServletContext()
                .getRealPath(savePath);
    }

    // title的setter和getter方法
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return (this.title);
    }

    // upload的setter和getter方法
    public void setUpload(File upload) {
        this.upload = upload;
    }

    public File getUpload() {
        return (this.upload);
    }

    // uploadContentType的setter和getter方法
    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadContentType() {
        return (this.uploadContentType);
    }

    // uploadFileName的setter和getter方法
    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public String getUploadFileName() {
        return (this.uploadFileName);
    }

    @Override
    public String execute() throws Exception {
        //好弱鸡啊，都不创建文件和路径的喽
        // 以服务器的文件保存地址和原文件名建立上传文件输出流
        FileOutputStream fos = new FileOutputStream(getSavePath()
                + "\\" + getUploadFileName());
        FileInputStream fis = new FileInputStream(getUpload());
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = fis.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }
        fos.close();
        return SUCCESS;
    }
}