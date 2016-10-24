package io.ftp.ftpclient_apache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;

public class FtpUtils {
	private static Logger logger = Logger.getLogger(FtpUtils.class);
	
	private FTPClient ftpClient;

	private static FtpUtils intence = new FtpUtils();

	public FtpUtils() {
	}

	public static FtpUtils getIntence() {
		return intence;
	}

	/**
	 * 利用FtpConfig进行服务器连接
	 * 
	 * @param ftpConfig
	 *            参数配置Bean类
	 * @return 
	 * @throws SocketException
	 * @throws IOException
	 */
	public static FtpUtils connectServer(FtpConfig ftpConfig) throws SocketException,
			IOException {
		String server = ftpConfig.getServer();
		int port = ftpConfig.getPort();
		String user = ftpConfig.getUsername();
		String password = ftpConfig.getPassword();
		String location = ftpConfig.getLocation();
		return connectServer(server, port, user, password, location, ftpConfig.getEncoding());
	}

	/**
	 * 使用详细信息进行服务器连接
	 * 
	 * @param server
	 *            ：服务器地址名称
	 * @param port
	 *            ：端口号
	 * @param user
	 *            ：用户名
	 * @param password
	 *            ：用户密码
	 * @param path
	 *            ：转移到FTP服务器目录
	 * @return 
	 * @throws SocketException
	 * @throws IOException
	 */
	public static FtpUtils connectServer(String server, int port, String user,
			String password, String path, String encoding) throws SocketException, IOException {
		FtpUtils ftpUtils = new FtpUtils();
		ftpUtils.ftpClient = new FTPClient();
		ftpUtils.ftpClient.connect(server, port);
		logger.debug("ftp连接服务器" + server);
		// 连接成功后的回应码
		logger.debug("连接返回码：" + ftpUtils.ftpClient.getReplyCode());
		ftpUtils.ftpClient.login(user, password);
		if (path != null && path.length() != 0) {
			if (!ftpUtils.ftpClient.changeWorkingDirectory(path)) {
				logger.error("ftp切换路径失败 " + path);
			}
		}
		ftpUtils.ftpClient.setBufferSize(1024);//设置上传缓存大小  
		ftpUtils.ftpClient.setControlEncoding(encoding);//设置编码  
		ftpUtils.ftpClient.setFileType(FTP.BINARY_FILE_TYPE);//设置文件类型
		return ftpUtils;
	}

	/**
	 * 设置传输文件类型:FTP.BINARY_FILE_TYPE | FTP.ASCII_FILE_TYPE 二进制文件或文本文件
	 * 
	 * @param fileType
	 * @throws IOException
	 */
	public void setFileType(int fileType) throws IOException {
		ftpClient.setFileType(fileType);
	}
	
	/**
	 * 设置连接方式 pasv 或 port, 默认被动
	 */
	public void setConnectType(String connectType) throws IOException{
		if("pasv".equalsIgnoreCase(connectType)){//被动
			ftpClient.enterLocalPassiveMode();
		}else if("port".equalsIgnoreCase(connectType)||"active".equals(connectType)){//主动
			ftpClient.enterLocalActiveMode();
		}else{
			ftpClient.enterLocalPassiveMode();//被动
		}
	}

	/**
	 * 关闭连接
	 * 
	 * @throws IOException
	 */
	public void closeServer() throws IOException {
		if(ftpClient == null || !ftpClient.isConnected()){
			logger.warn("ftp已关闭的链接");
			return;
		}
		ftpClient.logout();//退出FTP服务器   
		ftpClient.disconnect();//关闭FTP连接   
	}
	
	
	/**
	 * 安静关闭连接
	 */
	public void closeServerSilently(){
		try {
			closeServer();
		} catch (IOException e) {
			logger.debug("关闭连接异常");
		}
	}

	/**
	 * 转移到FTP服务器工作目录
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean changeDirectory(String path) throws IOException {
		return ftpClient.changeWorkingDirectory(path);
	}

	/**
	 * 在服务器上创建目录
	 * 
	 * @param pathName
	 * @return
	 * @throws IOException
	 */
	public boolean createDirectory(String pathName) throws IOException {
		return ftpClient.makeDirectory(pathName);
	}

	/**
	 * 在服务器上删除目录
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean removeDirectory(String path) throws IOException {
		return ftpClient.removeDirectory(path);
	}

	/**
	 * 删除所有文件和目录
	 * 
	 * @param path
	 * @param isAll
	 *            true:删除所有文件和目录
	 * @return
	 * @throws IOException
	 */
	public boolean removeDirectory(String path, boolean isAll)
			throws IOException {

		if (!isAll) {
			return removeDirectory(path);
		}

		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		if (ftpFileArr == null || ftpFileArr.length == 0) {
			return removeDirectory(path);
		}
		for (FTPFile ftpFile : ftpFileArr) {
			String name = ftpFile.getName();
			if (ftpFile.isDirectory()) {
				logger.info("* [sD]删除FTP目录 [" + path + "/" + name + "]");
				removeDirectory(path + "/" + name, true);
			} else if (ftpFile.isFile()) {
				logger.info("* [sF]删除FTP文件 [" + path + "/" + name + "]");
				deleteFile(path + "/" + name);
			} else if (ftpFile.isSymbolicLink()) {

			} else if (ftpFile.isUnknown()) {

			}
		}
		return ftpClient.removeDirectory(path);
	}

	/**
	 * 检查目录在服务器上是否存在 true：存在 false：不存在
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public boolean existDirectory(String path) throws IOException {
		boolean flag = false;
		FTPFile[] ftpFileArr = ftpClient.listFiles(path);
		for (FTPFile ftpFile : ftpFileArr) {
			if (ftpFile.isDirectory()
					&& ftpFile.getName().equalsIgnoreCase(path)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 得到文件列表,listFiles返回包含目录和文件，它返回的是一个FTPFile数组 listNames()：只包含目录的字符串数组
	 * String[] fileNameArr = ftpClient.listNames(path);
	 * 
	 * @param path
	 *            :服务器上的文件目录:/DF4
	 */
	public List<String> getFileList(String path) throws IOException {
		FTPFile[] ftpFiles = ftpClient.listFiles(path);
		// 通过FTPFileFilter遍历只获得文件
		List<String> retList = new ArrayList<String>();
		if (ftpFiles == null || ftpFiles.length == 0) {
			return retList;
		}
		for (FTPFile ftpFile : ftpFiles) {
			if (ftpFile.isFile()) {
				retList.add(ftpFile.getName());
			}
		}
		return retList;
	}

	/**
	 * 删除服务器上的文件
	 * 
	 * @param pathName
	 * @return
	 * @throws IOException
	 */
	public boolean deleteFile(String pathName) throws IOException {
		return ftpClient.deleteFile(pathName);
	}

	/**
	 * 上传文件到ftp服务器 在进行上传和下载文件的时候，设置文件的类型最好是：
	 * ftpUtil.setFileType(FtpUtil.BINARY_FILE_TYPE) localFilePath:本地文件路径和名称
	 * remoteFileName:服务器文件名称
	 */
	public boolean uploadFile(String localFilePath, String remoteFileName)
			throws IOException {
		boolean flag = false;
		InputStream iStream = null;
		try {
			iStream = new FileInputStream(localFilePath);
			flag = ftpClient.storeFile(remoteFileName, iStream);
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (iStream != null) {
				iStream.close();
			}
		}
		return flag;
	}

	/**
	 * 上传文件到ftp服务器，上传新的文件名称和原名称一样
	 * 
	 * @param fileName
	 *            ：文件名称
	 * @return
	 * @throws IOException
	 */
	public boolean uploadFile(String fileName) throws IOException {
		return uploadFile(fileName, fileName);
	}

	/**
	 * 上传文件到ftp服务器
	 * 
	 * @param iStream
	 *            输入流
	 * @param newName
	 *            新文件名称
	 * @return
	 * @throws IOException
	 */
	public boolean uploadFile(InputStream iStream, String newName)
			throws IOException {
		boolean flag = false;
		try {
			flag = ftpClient.storeFile(newName, iStream);
		} catch (IOException e) {
			flag = false;
			return flag;
		} finally {
			if (iStream != null) {
				iStream.close();
			}
		}
		return flag;
	}

	/**
	 * 从ftp服务器上下载文件到本地
	 * 
	 * @param remoteFileName
	 *            ：ftp服务器上文件名称
	 * @param localFileName
	 *            ：本地文件名称
	 * @return
	 * @throws IOException
	 */
	public boolean download(String remoteFileName, String localFileName)
			throws IOException {
		boolean flag = false;
		File outfile = new File(localFileName);
		OutputStream oStream = null;
		try {
			oStream = new FileOutputStream(outfile);
			flag = ftpClient.retrieveFile(remoteFileName, oStream);
		} catch (IOException e) {
			logger.error("下载文件[" + remoteFileName + "]失败! " + e);
			flag = false;
			return flag;
		} finally {
			if (oStream != null) {
				oStream.flush();
				oStream.close();
			}
		}
		return flag;
	}

	/**
	 * 从ftp服务器上下载文件到本地
	 * 
	 * @param sourceFileName
	 *            ：服务器资源文件名称
	 * @return InputStream 输入流
	 * @throws IOException
	 */
	public InputStream downFile(String sourceFileName) throws IOException {
		return ftpClient.retrieveFileStream(sourceFileName);
	}

	/**
	 * 获得ftpClient
	 */
	public FTPClient getFtpClient() {
		return ftpClient;
	}

	/**
	 * 下载文件 - 连接和传输都包括
	 * 
	 * @param ftpConfig
	 * @param remoteFileName
	 * @param remotePath
	 * @param localFileName
	 * @param localPath
	 * @param isDelete
	 * @return
	 */
	public boolean download(FtpConfig ftpConfig, String remoteFileName,
			String remotePath, String localFileName, String localPath,
			boolean isDelete) {
		// 连接
		FtpUtils ftp = null;
		try {
			ftp = FtpUtils.connectServer(ftpConfig.getServer(),
					ftpConfig.getPort(), ftpConfig.getUsername(),
					ftpConfig.getPassword(), ftpConfig.getLocation(),
					ftpConfig.getEncoding());
			String mode = "pasv";
			ftp.setConnectType(mode);
		} catch (Exception e) {
			logger.error("FTP无法连接", e);
			throw new RuntimeException("FTP无法连接");
		}
		// 校验/创建目录
		File localDirCheck = new File(localPath);
		if (!localDirCheck.exists()) {
			localDirCheck.mkdirs();
		}
		// 切换路径
		if (remotePath != null && remotePath.length() != 0) {
			try {
				if (!ftp.ftpClient.changeWorkingDirectory(remotePath)) {
					logger.error("ftp切换路径失败 " + remotePath);
				}
			} catch (IOException e) {
				logger.error("ftp切换路径失败 " + remotePath + " " + e);
			}
		}
		// 下载
		boolean isDownload = false;
		try {
			isDownload = ftp
					.download(remoteFileName, localPath + localFileName);
		} catch (IOException e) {
			logger.error("下载文件失败 " + e);
			return false;
		}
		if (!isDownload) {
			logger.error("下载文件失败 : " + remoteFileName);
			return false;
		}
		// 删除文件
		if (isDelete) {
			try {
				ftp.deleteFile(remoteFileName);
			} catch (IOException e) {
				logger.error("删除文件失败! " + e);
				return false;
			}
		}
		logger.info("下载FTP文件" + remotePath + remoteFileName + ",到" + localPath
				+ localFileName);
		// 关闭
		ftp.closeServerSilently();
		return isDownload;
	}

	/**
	 * 上传文件到ftp (包括建立连接)
	 * 
	 * @param ftpConfig
	 *            - 需要在location里面指定需要上传的目标ftp文件路径
	 * @param remoteFileName
	 *            上传到ftp的文件名-不包含路径
	 * @param localPath
	 *            本地文件路径
	 * @param localFileName
	 *            需要上传的文件名-不包含路径
	 * @return
	 */
	public boolean uploadFile(FtpConfig ftpConfig, String remoteFileName,
			String localPath, String localFileName) {
		FtpUtils ftp = null;
		try {
			ftp = FtpUtils.connectServer(ftpConfig.getServer(),
					ftpConfig.getPort(), ftpConfig.getUsername(),
					ftpConfig.getPassword(), ftpConfig.getLocation(),
					ftpConfig.getEncoding());
			String mode = "pasv";
			ftp.setConnectType(mode);
		} catch (Exception e) {
			logger.error("FTP无法连接", e);
			throw new RuntimeException("FTP无法连接");
		}
		boolean flag = false;
		try {
			flag = ftp.uploadFile(localPath + localFileName, remoteFileName);
		} catch (IOException e) {
			logger.error("BDC_FTP 上传文件失败", e);
		} finally {
			ftp.closeServerSilently();
		}
		logger.info("上传FTP文件" + localPath + localFileName + ",到" + ftpConfig.getLocation()
				+ remoteFileName);
		return flag;
	}

}
