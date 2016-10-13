package io.ftp;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

//import io.ftp.FtpOperationModel;
//import io.ftp.StringUtil;
import com.enterprisedt.net.ftp.FTPClient;
import com.enterprisedt.net.ftp.FTPConnectMode;
import com.enterprisedt.net.ftp.FTPException;
import com.enterprisedt.net.ftp.FTPTransferType;

public class FtpOperationUtil {
	/** ftp登陆超时时间，超过这个时间点，就报错！* */
	private static final int TIMEOUT = 60000;

	/** ftp 上传下载时用到的字符编码方式* */
	private static final String ENCODING = "UTF-8";

	/** 下载后删除 */
	public static final int DELETE_FILE = 1;

	/** 日志对象 */
	private static final Logger logger = Logger
			.getLogger(FtpOperationUtil.class);

	private static FtpOperationUtil intence = new FtpOperationUtil();

	public FtpOperationUtil() {
	}

	public static FtpOperationUtil getIntence() {
		return intence;
	}

	private FTPClient openFtp1(FtpOperationModel model) throws RuntimeException {
		String msg = "ftp 打开连接错误！";
		FTPClient ftp = null;
		try {
			ftp = new FTPClient(model.getFtpIp(), model.getFtpPort(), TIMEOUT,
					ENCODING);
			ftp.login(model.getFtpUsername(), model.getFtpPassword());
			/** ftp文件传送方式，ASII方式只可以传送tex文本文件，BINARY可以传送一些zip,jpg格式文件* */
			ftp.setType(FTPTransferType.BINARY);

			if (logger.isDebugEnabled()) {
				logger.debug("ip:" + model.getFtpIp());
				logger.debug("port:" + model.getFtpPort());
				logger.debug("passwd:" + model.getFtpPassword());
			}
		} catch (IOException e) {
			logger.error("ftp 打开连接错误！", e);
			throw new RuntimeException("ftp 打开连接错误！");
		} catch (FTPException e) {
			logger.error("ftp 打开连接错误！", e);
			throw new RuntimeException("ftp 打开连接错误！");
		}

		return ftp;
	}

	private void closeFtp(FTPClient ftp) throws RuntimeException {
		try {
			if (null != ftp)
				ftp.quit();
		} catch (IOException e) {
			logger.error("ftp 断开连接错误！", e);
			throw new RuntimeException("ftp 断开连接错误！");
		} catch (FTPException e) {
			logger.error("ftp 断开连接错误！", e);
			throw new RuntimeException("ftp 断开连接错误！");
		}
	}

	/**
	 * 检查在服务器上，此文件目录是否存在！
	 */
	public boolean checkFtpDirExsist(FTPClient ftp, String path) {
		try {
			ftp.chdir(path);
		} catch (IOException e) {
			logger.error("ftp 文件目录不存在。", e);
			return false;
		} catch (FTPException e) {
			logger.error("ftp 文件目录不存在。", e);
			return false;
		}
		return true;
	}

	public void createFtpDir(FTPClient ftp, String path) throws RuntimeException {
		try {
			ftp.mkdir(path);
		} catch (IOException e) {
			logger.error("ftp 上传路径创建错误", e);
			throw new RuntimeException("ftp 上传路径创建错误！");
		} catch (FTPException e) {
			logger.error("ftp 上传路径创建错误", e);
			throw new RuntimeException("ftp 上传路径创建错误");
		}
	};

	/**
	 * 文件上传
	 */
	public boolean uploadFile(FtpOperationModel model) throws RuntimeException {
		FTPClient ftp = null;
		boolean uploadRet = false;
		String msg = "同步MM平台 " + model.getToFTPFile() + "  失败!";
		try {
			ftp = this.openFtp(model);
			String dir = ftp.pwd();
			boolean exist = this.checkFtpDirExsist(ftp, model.getToFTPDir());
			if (!exist) {
				ftp.chdir(dir);
				this.createFtpDir(ftp, model.getToFTPDir());
				ftp.chdir(model.getToFTPDir());
			}
			ftp.put(model.getFromFTPFile(), model.getToFTPFile());
			uploadRet = true;
		} catch (IOException e) {
			logger.error("上传失败！the ftp_ip is "+model.getFtpIp()+" and the user is "+model.getFtpUsername()+" and the password is "+ model.getFtpPassword(), e);
			throw new RuntimeException(e.getMessage());
		} catch (FTPException e) {
			logger.error("上传失败！", e);
			throw new RuntimeException(e.getMessage());
		} catch (RuntimeException e) {
			logger.error("上传失败！", e);
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				if (null != ftp)
					closeFtp(ftp);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return uploadRet;
	}

	public static void main(String[] args) {
		FtpOperationModel model = new FtpOperationModel();
		model.setFtpIp("10.211.93.206");
		model.setFtpPort(21);
		model.setFtpTransferType(FTPTransferType.ASCII);
		model.setFtpUsername("manager3");
		model.setFtpPassword("manager3)O9i");
		model.setToFTPDir("./");
		model.setToFTPFile("author_003.txt");
		model.setFromFTPFile("D:\\test.txt");

		try {
			FtpOperationUtil.getIntence().uploadFile(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对获得的ftp路径进行路径转换，统一转换成/xxxx
	 */
	private String formatFtpDir(String ftpDir) {
		if (null == ftpDir) {
			return "";
		} else {
			if (!ftpDir.startsWith("/")) {
				return "/" + ftpDir;
			}
			if (ftpDir.startsWith("./")) {
				return ftpDir.substring(1);
			}
			return ftpDir;
		}

	}

	public String[] getFtpFileName(FtpOperationModel model) {
		String[] fileNames = null;
		FtpOperationUtil ftp = new FtpOperationUtil();
		FTPClient ftpClient = null;
		try {
			ftpClient = ftp.openFtp(model);
			String dir = ".";
			logger.debug("当前FTP目录" + dir);
			model.setToFTPDir(formatFtpDir(model.getToFTPDir()));
			if (null != ftpClient) {
				ftpClient.chdir(dir + model.getToFTPDir());
				fileNames = ftpClient.dir("./");
			}
		} catch (Exception e) {
			logger.error("getFtpFileName fail", e);
		} finally {
			if (null != ftpClient) {
				try {
					closeFtp(ftpClient);
				} catch (RuntimeException e) {
					logger.error("FTP关闭异常" + e);
				}
			}
		}

		return fileNames;
	}

	/**
	 * 文件下载
	 */
	public boolean downloadFile(FtpOperationModel model)

	{
		logger.debug("进入downloadFile");
		boolean bRet = false;
		String localpath = "";
		FTPClient ftp = null;
		try {
			ftp = this.openFtp(model);
			// String dir = ftp.pwd();
			String dir = ".";
			logger.debug("当前FTP目录" + dir);
			model.setFtpDir(formatFtpDir(model.getFtpDir()));
			ftp.chdir(dir + model.getFtpDir());
			logger.debug("更换FTP目录到 " + dir + model.getFtpDir());
			// 本地目录，要下载文件名
			localpath = model.getLocalPath();
			checkLocalPath(localpath);
			if (StringUtil.isNotNull(model.getRemoteFile())) {
				ftp.get(localpath + model.getRemoteFile(),
						model.getRemoteFile());

				if (DELETE_FILE == model.getIsDeleteFile()) {
					ftp.delete(model.getRemoteFile());
					logger.debug("已把FTP上的文件" + model.getRemoteFile() + "删除");
				}

				bRet = true;
				logger.debug("已把FTP上的文件" + model.getRemoteFile()
						+ "下载到本地,保存到本地的路径为" + localpath);
			}
		} catch (IOException e) {
			logger.error("下载失败！", e);
		} catch (FTPException e) {
			logger.error("下载失败！", e);
		} catch (RuntimeException e) {
			logger.error("下载失败！", e);
		} finally {

			if (null != ftp) {
				try {
					closeFtp(ftp);
				} catch (RuntimeException e) {
					logger.error("关闭FTP异常" + e);
				}
			}

		}
		return bRet;
	}

	/**
	 * 检查在服务器上，此文件目录是否存在！
	 */
	public boolean checkLocalPath(String path) {
		logger.debug("检查本地文件目录" + path + "是否存在");

		boolean bExists = true;

		// 生成文件对象，如果文件不存在，要创建新文件。
		if (!path.startsWith(".")) {
			path = "." + path;
		}

		File file = new File(path);

		if (!file.exists()) {
			File parent = file.getParentFile();

			if (parent != null && !parent.exists()) {
				if (!file.mkdirs()) {
					logger.error("create Directory fail" + path);
				}
			}

			if (file.mkdir()) {
				bExists = true;
			} else {
				bExists = false;
			}
		}

		logger.debug("本地文件目录存在");

		return bExists;
	}

	/**
	 * 连接FTP
	 */
	private FTPClient openFtp(FtpOperationModel model) {
		FTPClient ftp = null;
		try {
			logger.debug("打开FTP...");
			ftp = new FTPClient(model.getFtpIp(), model.getFtpPort(), TIMEOUT,
					ENCODING);
			logger.debug("登入FTP...用户名=" + model.getFtpUsername() + ",密码="
					+ model.getFtpPassword());
			ftp.login(model.getFtpUsername(), model.getFtpPassword());
			/** ftp文件传送方式，ASII方式只可以传送tex文本文件，BINARY可以传送一些zip,jpg格式文件* */
			logger.debug("设置ftp文件传送方式" + model.getFtpTransferType());
			ftp.setType(model.getFtpTransferType());

			// 从配置文件获得ftp连接模式
			String connModel = model.getConnectMode();
			if ("active".equalsIgnoreCase(connModel)) {
				ftp.setConnectMode(FTPConnectMode.ACTIVE);
				logger.debug("设置ftp模式为主动方式");
			} else {
				ftp.setConnectMode(FTPConnectMode.PASV);
				logger.debug("设置ftp模式为被动方式");
			}

		} catch (IOException e) {
			logger.error("ftp 打开连接错误！", e);
		} catch (FTPException e) {
			logger.error("ftp 打开连接错误！", e);
		}

		return ftp;
	}

}
