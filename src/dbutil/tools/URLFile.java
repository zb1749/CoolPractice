package dbutil.tools;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;


public class URLFile extends File {
	private static Logger logger = Logger.getLogger(URLFile.class);

	private static final long serialVersionUID = 6607216819937577215L;
	private File rwFile;
	
	private File zipFile;//所在Zip包的文件
	private String zippedPath;//在Zip包中的路径
	
	private URL url;
	private String type;
	private String path;
	//仅当URL时
	private boolean exist=true;
	private File data;
	
	/**
	 * 将非本地文件复制为一个临时文件
	 */
	private void loadData(){
		if(exist && data!=null)return;
		try{
			InputStream in=url.openStream();
			data=IOUtils.saveAsTempFile(in);	
		}catch(IOException e){
			logger.error("loading "+url.toString()+" error!", e);
			exist=false;
		}catch(Throwable t){
			logger.error("Load file " + url +" error!",t);
			exist=false;
		}
	}
	
	/**
	 * 是否一个本地文件
	 * @return
	 */
	public boolean isLocalFile(){
		return rwFile!=null;
	}
	
	/**
	 * 返回本地文件，只有当{@link #isLocalFile()}=true时才能使用
	 * @return
	 */
	public File getLocalFile(){
		//Assert.notNull(rwFile);
		return rwFile;
	}
	
	public FileInputStream getInputStream() throws IOException{
		if(rwFile!=null){
			return new FileInputStream(rwFile);
		}
		loadData();
		if(data!=null){
			return new FileInputStream(data);
		}
		throw new FileNotFoundException("File " + path +" not exist!" );
	}
	
	public URLFile(URI uri) {
		super("");
		try {
			this.url=uri.toURL();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
		init();
	}
	
	public URLFile(URL url) {
		super("");
		//Assert.notNull(url);
		this.url=url;
		init();
	}

	private void init(){
		this.type=url.getProtocol().toLowerCase();
		try {
			path=URLDecoder.decode(url.getPath(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			//Never Happens
		}
		
		if("file".equals(type)){
			rwFile=new File(path);
		}else if("jar".equals(type)){//位于JAR文件中
			String zipName=StringUtils.substringAfter(StringUtils.substringBefore(path, "!"), "file:");
			while(zipName.startsWith("//")){
				zipName=zipName.substring(1);//丢弃一个/
			}
			this.zipFile=new File(zipName);
			//Assert.fileExist(zipFile);
			this.zippedPath=StringUtils.substringAfter(path, "!");
		}else if("zip".equals(type)){
			String zipName=StringUtils.substringBefore(path, "!");
			this.zipFile=new File(zipName);
			//Assert.fileExist(zipFile);
			this.zippedPath=StringUtils.substringAfter(path, "!");
		}
		if(path.charAt(0)=='/')path=path.substring(1);
	}

	@Override
	public String getName() {
		return StringUtils.substringAfterLastIfExist(path, "/");
	}

	@Override
	public String getParent() {
		if(rwFile!=null){
			return rwFile.getParent();
		}
		String s=url.toString();
		if(s.indexOf('/')>-1){
			return StringUtils.substringBeforeLast(url.toString(), "/");	
		}else{
			return null;
		}
		
	}

	@Override
	public File getParentFile() {
		if(rwFile!=null){
			return rwFile.getParentFile();
		}
		String parent=getParent();
		if(parent==null)return null;
		parent=parent.replace('\\', '/');
		parent=StringUtils.substringBeforeLast(parent, "/");
		if(parent.endsWith("!")){
			parent=parent.substring(4,parent.length()-1);
		}
		try {
			URL url=new URL(parent);
			return new URLFile(url);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException("error create Parent UrlFile:["+parent+"]"+e.getMessage());
		}
	}

	@Override
	public String getPath() {
		if(rwFile!=null){
			return rwFile.getPath();
		}
		return path;
	}

	@Override
	public boolean isAbsolute() {
		if(rwFile!=null){
			return rwFile.isAbsolute();
		}
		return true;
	}

	@Override
	public String getAbsolutePath() {
		if(rwFile!=null){
			return rwFile.getAbsolutePath();
		}
		return path;
	}

	@Override
	public File getAbsoluteFile() {
		if(rwFile!=null){
			return rwFile.getAbsoluteFile();
		}
		return this;
	}

	@Override
	public String getCanonicalPath() throws IOException {
		if(rwFile!=null){
			return rwFile.getCanonicalPath();
		}
		return path;
	}

	@Override
	public File getCanonicalFile() throws IOException {
		if(rwFile!=null){
			return rwFile.getCanonicalFile();
		}
		return this;
	}
	
	@Override
	public URL toURL() throws MalformedURLException {
		return url;
	}

	@Override
	public URI toURI() {
		if(rwFile!=null){
			return rwFile.toURI();
		}
		try {
			return url.toURI();
		} catch (URISyntaxException e) {
			throw new IllegalArgumentException(e);
		}
	}

	@Override
	public boolean canRead() {
		if(rwFile!=null){
			return rwFile.canRead();
		}
		loadData();
		return exist;
	}

	@Override
	public boolean canWrite() {
		if(rwFile!=null){
			return rwFile.canWrite();
		}
		return false;
	}

	@Override
	public boolean exists() {
		if(rwFile!=null){
			return rwFile.exists();
		}
		loadData();
		return exist;
	}

	@Override
	public boolean isDirectory() {
		if(rwFile!=null){
			return rwFile.isDirectory();
		}
		return path.endsWith("/");
	}

	@Override
	public boolean isFile() {
		if(rwFile!=null){
			return rwFile.isFile();
		}
		return !path.endsWith("/");
	}

	@Override
	public boolean isHidden() {
		if(rwFile!=null){
			return rwFile.isHidden();
		}
		return false;
	}

	@Override
	public long lastModified() {
		if(rwFile!=null){
			return rwFile.lastModified();
		}else if(zipFile!=null){
			return zipFile.lastModified();//返回ZIP文件内的日期
		}
		loadData();
		if(exist){
			return data.lastModified();
		}
		return -1;
	}
	
	public boolean isZipFile(){
		return this.zipFile!=null;
	}
	
	public File getZipContainer(){
		//Assert.notNull(zipFile);
		return this.zipFile;
	}
	public String getZipEntryPath(){
		//Assert.notNull(zippedPath);
		return this.zippedPath;
	}
	
	@Override
	public long length() {
		if(rwFile!=null){
			return rwFile.length();
		}
		loadData();
		if(exist){
			return data.length();
		}
		return -1;
	}

	@Override
	public boolean createNewFile() throws IOException {
		if(rwFile!=null){
			return rwFile.createNewFile();
		}
		return false;
	}

	@Override
	public boolean delete() {
		if(rwFile!=null){
			return rwFile.delete();
		}
		if(data!=null){
			data.delete();
			exist=false;
			data=null;
		}
		return false;
	}

	@Override
	public void deleteOnExit() {
		if(rwFile!=null){
			rwFile.deleteOnExit();
		}
		if(data!=null){
			data.deleteOnExit();
			exist=false;
			data=null;
		}
	}

	@Override
	public String[] list() {
		if(rwFile!=null){
			return rwFile.list();
		}
		return ArrayUtils.EMPTY_STRING_ARRAY;
	}

	@Override
	public String[] list(FilenameFilter filter) {
		if(rwFile!=null){
			return rwFile.list(filter);
		}
		return ArrayUtils.EMPTY_STRING_ARRAY;
	}

	@Override
	public File[] listFiles() {
		if(rwFile!=null){
			return rwFile.listFiles();
		}
		return new File[0];
	}

	@Override
	public File[] listFiles(FilenameFilter filter) {
		if(rwFile!=null){
			return rwFile.listFiles(filter);
		}
		return new File[0];
	}

	@Override
	public File[] listFiles(FileFilter filter) {
		if(rwFile!=null){
			return rwFile.listFiles(filter);
		}
		return new File[0];
	}

	@Override
	public boolean mkdir() {
		if(rwFile!=null){
			return rwFile.mkdir();
		}
		return false;
	}

	@Override
	public boolean mkdirs() {
		if(rwFile!=null){
			return rwFile.mkdirs();
		}
		return false;
	}

	@Override
	public boolean renameTo(File dest) {
		if(rwFile!=null){
			return rwFile.renameTo(dest);
		}
		return false;
	}

	@Override
	public boolean setLastModified(long time) {
		if(rwFile!=null){
			return rwFile.setLastModified(time);
		}
		if(data!=null){
			return data.setLastModified(time);
		}
		return false;
	}

	@Override
	public boolean setReadOnly() {
		if(rwFile!=null){
			return rwFile.setReadOnly();
		}
		return true;
	}

	@Override
	public int compareTo(File pathname) {
		if(rwFile!=null){
			return rwFile.compareTo(pathname);
		}
		if(pathname==null)return 1;
		return getPath().compareTo(pathname.getPath());
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(rwFile!=null){
			return rwFile.equals(obj);
		}
		if(obj instanceof File){
			return ((File) obj).getPath().equals(this.url.getFile());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		if(rwFile!=null){
			return rwFile.hashCode();
		}
		return url.toString().hashCode();
	}

	@Override
	public String toString() {
		if(rwFile!=null){
			return rwFile.toString();
		}
		return url.toString();
	}
}
