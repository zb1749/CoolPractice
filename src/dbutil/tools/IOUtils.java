package dbutil.tools;


import org.apache.log4j.Logger;

import java.io.*;
import java.net.URL;

public class IOUtils {

    private static Logger logger = Logger.getLogger(IOUtils.class);

    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final File[] EMPTY = new File[0];

    /**
     * 将URL转化为文件
     *
     * @param url 要转换的URL，必须是file://协议，否则抛出异常。
     */
    public static File urlToFile(URL url) {
        if (url == null) return null;
        try {
            URLFile file = new URLFile(url);
            if (file.isLocalFile()) return file.getLocalFile();
            return file;
        } catch (RuntimeException e) {
            logger.error(url.toString() + " is not a valid file:" + e.getMessage());
            return null;
        }
    }

    /**
     * 将指定的流保存为临时文件
     *
     * @param is
     * @return
     * @throws IOException
     */
    public static File saveAsTempFile(InputStream is) throws IOException {
        File f = File.createTempFile("~tmp", ".io");
        saveAsFile(f, is);
        return f;
    }

    /**
     * 将输入流保存为文件
     */
    public static void saveAsFile(File file, InputStream... iss) throws IOException {
        ensureParentFolder(file);
        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file));
        try {
            for (InputStream is : iss) {
                copy(is, os, false);
            }
        } finally {
            if (os != null) {
                os.flush();
                os.close();
            }
        }
    }

    /**
     * 检查/创建文件在所的文件夹
     *
     * @param file
     */
    public static void ensureParentFolder(File file) {
        File f = file.getParentFile();
        if (f != null && !f.exists()) {
            f.mkdirs();
        } else if (f != null && f.isFile()) {
            throw new RuntimeException(f.getAbsolutePath() + " is a exist file, can't create directory.");
        }
    }

    /**
     * 流之间拷贝
     *
     * @param pInputStream
     * @param pOutputStream
     * @param pClose        关闭输出流? (输入流默认关闭)
     * @return
     * @throws IOException
     */
    public static long copy(InputStream pInputStream, OutputStream pOutputStream, boolean pClose) throws IOException {
        return copy(pInputStream, pOutputStream, true, pClose, new byte[DEFAULT_BUFFER_SIZE]);
    }

    /*
     * Copies the contents of the given {@link InputStream} to the given {@link
	 * OutputStream}.
	 *
	 * @param pIn The input stream, which is being read. It is guaranteed, that
	 * {@link InputStream#close()} is called on the stream.
	 * 关于InputStram在何时关闭的问题，我一直认为应当是成对操作的（即在哪个方法中生成Stream，就要在使用完后关闭），
	 * 因此不打算在这里使用close方法。 但是后来我又考虑到，InputStream在使用完后，其内部标记已经发生了变化，无法再次使用。
	 * (reset方法的效果和实现有关，并不能保证回复到Stream使用前的状态。)
	 * 因此考虑这里统一关闭以防止疏漏，外面再关一次也不会有问题(作为好习惯，还是应该成对打开和关闭)。
	 *
	 * @param pOut 输出流，可以为null,此时输入流中的相应数据将丢弃
	 *
	 * @param pClose True guarantees, that {@link OutputStream#close()} is
	 * called on the stream. False indicates, that only {@link
	 * OutputStream#flush()} should be called finally.
	 *
	 * @param pBuffer Temporary buffer, which is to be used for copying data.
	 *
	 * @return Number of bytes, which have been copied.
	 *
	 * @throws IOException An I/O error occurred.
	 */
    private static long copy(InputStream in, OutputStream out, boolean inClose, boolean outClose, byte[] pBuffer) throws IOException {
        if (in == null)
            throw new NullPointerException();
        long total = 0;
        try {
            int res;
            while ((res = in.read(pBuffer)) != -1) {
                if (out != null) {
                    out.write(pBuffer, 0, res);
                }
                total += res;
            }
            if (out != null)
                out.flush();
        } finally {
            if (outClose && out != null)
                out.close();
            if (inClose)
                in.close();
        }
        return total;
    }

    /**
     * 列出指定目录下的文件(不含文件夹)
     *
     * @param file
     * @param pattern 文件名，可以用*,?,+表示匹配任意字符
     * @return
     */
    public static File[] listFilesLike(File file, final String pattern) {
        File[] r = file.listFiles(new FileFilter() {
            public boolean accept(File f) {
                if (f.isFile() && StringUtils.matches(f.getName(), pattern, true)) {
                    return true;
                }
                return false;
            }
        });
        return r == null ? EMPTY : r;
    }

    /**
     * 关闭指定的对象，不会抛出异常
     *
     * @param c
     */
    public static void close(Closeable c) {
        if (c == null)
            return;
        try {
            c.close();
        } catch (IOException e) {
            //Logger.exeption(e);
            e.printStackTrace();
        }
    }

    /**
     * 获得二进制文件写入句柄
     *
     * @Title: getInputStream
     */
    public static BufferedInputStream getInputStream(File file) throws IOException {
        return new BufferedInputStream((file instanceof URLFile) ? ((URLFile) file).getInputStream() : new FileInputStream(file));
    }
}