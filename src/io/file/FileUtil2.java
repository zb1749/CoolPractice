package io.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

public class FileUtil2 {
	private static final int DEFAULT_BUFFER_SIZE = 4096;
	private static Logger logger = Logger.getLogger(FileUtil2.class);

	public static File createFile(String file) {
		if (!assertParam(file)) {
			return null;
		}

		File tmpFile = new File(file);

		if (!tmpFile.exists()) {
			if (!tmpFile.getParentFile().exists()) {
				tmpFile.getParentFile().mkdirs();
			}

			try {
				tmpFile.createNewFile();
				return tmpFile;
			} catch (IOException e) {
				if (logger.isDebugEnabled()) {
					String message = "Cannot create " + tmpFile;
					logger.debug(message, e);
				}
				throw new RuntimeException("405477002", e);
			}

		}

		return null;
	}

	public static File createDirectory(String dir) {
		if (!assertParam(dir)) {
			return null;
		}

		File f = new File(dir);
		try {
			forceMkdir(f);
			return f;
		} catch (Exception e) {
			logger.error("异常！", e);
			e.printStackTrace();
		}
		return null;
	}

	public static boolean delDirectory(String dir) {
		if (!assertParam(dir)) {
			return false;
		}

		File f = new File(dir);
		try {
			delDirectory(f);
			return true;
		} catch (Exception e) {
			logger.error("异常！", e);
			e.printStackTrace();
		}

		return false;
	}

	public static File[] getFileList(String path) {
		if (!assertParam(path)) {
			return new File[0];
		}

		File file = new File(path);

		if ((file.exists()) && (file.isDirectory())) {
			return file.listFiles();
		}

		return new File[0];
	}

	private static int copy(InputStream input, OutputStream output) {
		byte[] buffer = new byte[4096];

		int count = 0;

		int n = 0;
		try {
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);

				count += n;
			}
		} catch (IOException e) {
			logger.debug("Error occured when copy from " + input.toString()
					+ " to " + output.toString(), e);

			throw new RuntimeException("405477007", e);
		}
		return count;
	}

	public static void closeQuietly(InputStream input) {
		try {
			if (null != input) {
				input.close();
			}
		} catch (IOException e) {
			logger.debug("Error occured when close " + input.toString(), e);
			throw new RuntimeException("405477007", e);
		}
	}

	public static void closeQuietly(OutputStream output) {
		try {
			if (assertParam(output)) {
				output.close();
			}
		} catch (IOException e) {
			logger.debug("Error occured when close " + output.toString(), e);
			throw new RuntimeException("405477007", e);
		}
	}

	public static void forceDelete(File file) {
		if (!assertParam(file)) {
			String message = "Parameter is null: ";
			NullPointerException e = new NullPointerException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		if (file.isDirectory()) {
			delDirectory(file);
		} else {
			if (!file.exists()) {
				String message = "File does not exist: " + file;
				FileNotFoundException e = new FileNotFoundException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}
				throw new RuntimeException("405477005", e);
			}

			if (!file.delete()) {
				String message = "Unable to delete file: " + file;
				IOException e = new IOException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}
				throw new RuntimeException("405477004", e);
			}
		}
	}

	private static boolean forceDeleteOnExit(File file) {
		if (!assertParam(file)) {
			return false;
		}

		if (file.isDirectory()) {
			delDirectoryOnExit(file);

			return true;
		}

		file.deleteOnExit();

		return true;
	}

	public static boolean delDirectory(File directory) {
		if (!directory.exists()) {
			return false;
		}

		cleanDirectory(directory);

		if (!directory.delete()) {
			String message = "Unable to delete directory " + directory + ".";
			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477004", e);
		}

		return true;
	}

	public static void cleanDirectory(File directory) {
		if (!directory.exists()) {
			String message = directory + " does not exist";
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477001", e);
		}

		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		File[] files = directory.listFiles();

		if (files == null) {
			String message = "Failed to list contents of " + directory;
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		int length = files.length;

		for (int i = 0; i < length; i++) {
			File file = files[i];

			forceDelete(file);
		}
	}

	private static boolean delDirectoryOnExit(File directory) {
		if (!directory.exists()) {
			return false;
		}

		cleanDirectoryOnExit(directory);

		directory.deleteOnExit();

		return true;
	}

	private static void cleanDirectoryOnExit(File directory) {
		if (!directory.exists()) {
			String message = directory + " does not exist";
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477001", e);
		}

		if (!directory.isDirectory()) {
			String message = directory + " is not a directory";
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		File[] files = directory.listFiles();

		if (files == null) {
			String message = "Failed to list contents of " + directory;
			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477007", e);
		}

		int length = files.length;

		for (int i = 0; i < length; i++) {
			File file = files[i];

			forceDeleteOnExit(file);
		}
	}

	public static void forceMkdir(File directory) {
		if (directory.exists()) {
			if (directory.isFile()) {
				String message = "File " + directory + " exists and is "
						+ "not a directory. Unable to create directory.";

				IOException e = new IOException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}
				throw new RuntimeException("405477002", e);
			}

		} else if (!directory.mkdirs()) {
			String message = "Unable to create directory " + directory;
			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477002", e);
		}
	}

	public static void copyFileToDirectory(File srcFile, File destDir) {
		if (destDir == null) {
			String message = "Destination must not be null";
			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		if ((destDir.exists()) && (!destDir.isDirectory())) {
			String message = "Destination '" + destDir + "' is not a directory";
			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477001", e);
		}

		copyFile(srcFile, new File(destDir, srcFile.getName()), true);
	}

	public static void copyFile(File srcFile, File destFile) {
		copyFile(srcFile, destFile, true);
	}

	public static void copyFile(File srcFile, File destFile,
			boolean preserveFileDate) {
		if (srcFile == null) {
			String message = "Source must not be null";
			NullPointerException e = new NullPointerException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		if (destFile == null) {
			String message = "Destination must not be null";
			NullPointerException e = new NullPointerException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		if (!srcFile.exists()) {
			String message = "Source '" + srcFile + "' does not exist";
			FileNotFoundException e = new FileNotFoundException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477005", e);
		}

		if (srcFile.isDirectory()) {
			String message = "Source '" + srcFile
					+ "' exists but is a directory";

			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		try {
			if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
				String message = "Source '" + srcFile + "' and destination '"
						+ destFile + "' are the same";

				IOException e = new IOException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}
				throw new RuntimeException("405477006", e);
			}

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				String message = "Error occured when get " + srcFile + " or "
						+ destFile + "'s CanonicalPath";

				logger.debug(message, e);
			}
			throw new RuntimeException("405477007", e);
		}

		if ((destFile.getParentFile() != null)
				&& (!destFile.getParentFile().exists())) {
			if (!destFile.getParentFile().mkdirs()) {
				String message = "Destination '" + destFile
						+ "' directory cannot be created";

				IOException e = new IOException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}
				throw new RuntimeException("405477002", e);
			}

		}

		if ((destFile.exists()) && (!destFile.canWrite())) {
			String message = "Destination '" + destFile
					+ "' exists but is read-only";

			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477007", e);
		}

		doCopyFile(srcFile, destFile, preserveFileDate);
	}

	private static void doCopyFile(File srcFile, File destFile,
			boolean preserveFileDate) {
		if ((destFile.exists()) && (destFile.isDirectory())) {
			String message = "Destination '" + destFile
					+ "' exists but is a directory";

			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477006", e);
		}

		FileInputStream input;
		try {
			input = new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			if (logger.isDebugEnabled()) {
				String message = srcFile + "is not founded";
				logger.debug(message, e);
			}
			throw new RuntimeException("405477005", e);
		}

		try {
			FileOutputStream output = new FileOutputStream(destFile);
			try {
				copy(input, output);
			} finally {
			}

		} catch (FileNotFoundException e) {
			if (logger.isDebugEnabled()) {
				String message = destFile + "is not founded";
				logger.debug(message, e);
			}
			throw new RuntimeException("405477005", e);
		} finally {
			closeQuietly(input);
		}

		if (srcFile.length() != destFile.length()) {
			String message = "Failed to copy full contents from '" + srcFile
					+ "' to '" + destFile + "'";

			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477007", e);
		}

		if (preserveFileDate) {
			destFile.setLastModified(srcFile.lastModified());
		}
	}

	public static boolean copyDirectoryToDirectory(File srcDir, File destDir) {
		if (null == srcDir) {
			String message = "Source must not be null";
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		if ((srcDir.exists()) && (!srcDir.isDirectory())) {
			String message = "Source '" + destDir + "' is not a directory";
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		if (null == destDir) {
			String message = "Destination must not be null";
			NullPointerException e = new NullPointerException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		if ((destDir.exists()) && (!destDir.isDirectory())) {
			String message = "Destination '" + destDir + "' is not a directory";
			IllegalArgumentException e = new IllegalArgumentException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		return copyDirectory(srcDir, new File(destDir, srcDir.getName()), true);
	}

	public static boolean copyDirectory(File srcDir, File destDir) {
		return copyDirectory(srcDir, destDir, true);
	}

	public static boolean copyDirectory(File srcDir, File destDir,
			boolean preserveFileDate) {
		if (srcDir == null) {
			String message = "Source must not be null";
			NullPointerException e = new NullPointerException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		if (destDir == null) {
			String message = "Destination must not be null";
			NullPointerException e = new NullPointerException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		if (!srcDir.exists()) {
			String message = "Source '" + srcDir + "' does not exist";
			FileNotFoundException e = new FileNotFoundException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		if (!srcDir.isDirectory()) {
			String message = "Source '" + srcDir
					+ "' exists but is not a directory";

			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			return false;
		}

		try {
			if (srcDir.getCanonicalPath().equals(destDir.getCanonicalPath())) {
				String message = "Source '" + srcDir + "' and destination '"
						+ destDir + "' are the same";

				IOException e = new IOException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}

				return false;
			}

		} catch (IOException e) {
			if (logger.isDebugEnabled()) {
				String message = "Error occured when get " + srcDir + " or "
						+ destDir + "'s CanonicalPath";

				logger.debug(message, e);
			}
			return false;
		}

		return doCopyDirectory(srcDir, destDir, preserveFileDate);
	}

	private static boolean doCopyDirectory(File srcDir, File destDir,
			boolean preserveFileDate) {
		if (destDir.exists()) {
			if (!destDir.isDirectory()) {
				String message = "Destination '" + destDir
						+ "' exists but is not a directory";

				IOException e = new IOException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}
				return false;
			}

		} else {
			if (!destDir.mkdirs()) {
				String message = "Destination '" + destDir
						+ "' directory cannot be created";

				IOException e = new IOException(message);
				if (logger.isDebugEnabled()) {
					logger.debug(message, e);
				}
				return false;
			}

			if (preserveFileDate) {
				destDir.setLastModified(srcDir.lastModified());
			}

		}

		if (false == destDir.canWrite()) {
			String message = "Destination '" + destDir
					+ "' cannot be written to";

			IOException e = new IOException(message);
			if (logger.isDebugEnabled()) {
				logger.debug(message, e);
			}
			throw new RuntimeException("405477007", e);
		}

		File[] files = srcDir.listFiles();
		if (!assertParam(files)) {
			String message = "Failed to list contents of " + srcDir;
			IOException e = new IOException(message);
			throw new RuntimeException("405477007", e);
		}

		int length = files.length;
		for (int i = 0; i < length; i++) {
			File copiedFile = new File(destDir, files[i].getName());
			if (files[i].isDirectory()) {
				doCopyDirectory(files[i], copiedFile, preserveFileDate);
			} else {
				doCopyFile(files[i], copiedFile, preserveFileDate);
			}
		}
		return true;
	}

	private static boolean assertParam(String para) {
		if (null == para) {
			return false;
		}
		return true;
	}

	private static boolean assertParam(Object obj) {
		if (null == obj) {
			return false;
		}
		return true;
	}
}