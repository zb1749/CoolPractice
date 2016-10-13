package io.ftp;

import com.enterprisedt.net.ftp.FTPTransferType;

public class FtpOperationModel {
	private String ftpIp = " ";

	private int ftpPort = 21;

	/** 需要上传的文件* */
	private String fromFTPFile = " ";

	/** 上传到的目录* */
	private String toFTPDir = "";
	/** 上传后的文件名 **/
	private String toFTPFile = "";

	private String ftpUsername = " ";

	private String ftpPassword = " ";

	/** ftp文件传送方式* */
	private FTPTransferType ftpTransferType;

	// 要传输的文件
	private String remoteFile = "";
	// ftp的目录
	private String ftpDir = "";
	// 本地的目录
	private String localPath = "";

	// 本地的文件名称
	private String localFile = "";

	/** 链接方式 **/
	private String connectMode = "";
	
	//是否需要设置字符格式
	private Boolean encodingSet = false;

	public Boolean getEncodingSet() {
		return encodingSet;
	}

	public void setEncodingSet(Boolean encodingSet) {
		this.encodingSet = encodingSet;
	}

	// 是否删除ftp上的文件 0:保留 1:删除
	private Integer isDeleteFile = 0;

	public String getConnectMode() {
		return connectMode;
	}

	public void setConnectMode(String connectMode) {
		this.connectMode = connectMode;
	}

	public String getRemoteFile() {
		return remoteFile;
	}

	public void setRemoteFile(String remoteFile) {
		this.remoteFile = remoteFile;
	}

	public String getFtpDir() {
		return ftpDir;
	}

	public void setFtpDir(String ftpDir) {
		this.ftpDir = ftpDir;
	}

	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public FtpOperationModel() {
	}

	public FtpOperationModel(String ftpIp, int ftpPort, String fromFTPFile,
			String toFTPDir, String toFTPFile, String ftpUserName,
			String ftpPassword, FTPTransferType type) {
		this.ftpIp = ftpIp;
		this.ftpPort = ftpPort;
		this.fromFTPFile = fromFTPFile;
		this.toFTPDir = toFTPDir;
		this.toFTPFile = toFTPFile;
		this.ftpUsername = ftpUserName;
		this.ftpPassword = ftpPassword;
		this.ftpTransferType = type;
	}

	public String getFtpIp() {
		return ftpIp;
	}

	public void setFtpIp(String ftpIp) {
		this.ftpIp = ftpIp;
	}

	public String getFtpPassword() {
		return ftpPassword;
	}

	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}

	public int getFtpPort() {
		return ftpPort;
	}

	public void setFtpPort(int ftpPort) {
		this.ftpPort = ftpPort;
	}

	public String getFtpUsername() {
		return ftpUsername;
	}

	public void setFtpUsername(String ftpUsername) {
		this.ftpUsername = ftpUsername;
	}

	public FTPTransferType getFtpTransferType() {
		return ftpTransferType;
	}

	public void setFtpTransferType(FTPTransferType ftpTransferType) {
		this.ftpTransferType = ftpTransferType;
	}

	public String getFromFTPFile() {
		return fromFTPFile;
	}

	public void setFromFTPFile(String fromFTPFile) {
		this.fromFTPFile = fromFTPFile;
	}

	public String getToFTPDir() {
		return toFTPDir;
	}

	public void setToFTPDir(String toFTPDir) {
		this.toFTPDir = toFTPDir;
	}

	public String getToFTPFile() {
		return toFTPFile;
	}

	public void setToFTPFile(String toFTPFile) {
		this.toFTPFile = toFTPFile;
	}

	public String getLocalFile() {
		return localFile;
	}

	public void setLocalFile(String localFile) {
		this.localFile = localFile;
	}

	public Integer getIsDeleteFile() {
		return isDeleteFile;
	}

	public void setIsDeleteFile(Integer isDeleteFile) {
		this.isDeleteFile = isDeleteFile;
	}
}
