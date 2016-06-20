package org.fast.core.kit;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * 文件和目录相关
 * 
 * @author 董华健 2012-9-7 下午2:06:13
 */
public class DirFileKit {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(DirFileKit.class);
	
	/**
	 * 获取当前代码所在行
	 * @return
	 */
	public static String getLineNumber(){
		StackTraceElement ste = new Throwable().getStackTrace()[1];
		return ste.getFileName() + ": Line " + ste.getLineNumber();
	}
	
	/**
	 * 获取目录下的文件名称，不包含子目录名称
	 * 
	 * @author 董华健 2012-9-6 下午8:17:51
	 * @param dirPath
	 * @return
	 */
	public static List<String> getDirFileNames(String dirPath) {
		List<String> nameList = new ArrayList<String>();
		File file = new File(dirPath);
		File[] files = file.listFiles();
		for (File fileTemp : files) {
			if (!fileTemp.isDirectory()) {
				nameList.add(fileTemp.getName());
			}
		}
		return nameList;
	}
	
	/**
	 * 复制文件夹或文件
	 * 
	 * @author 董华健 2012-9-3 下午7:29:28
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void copyDir(String source, String target) throws IOException {
		(new File(source)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(source)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 复制文件
				copyFile(file[i], new File(target + file[i].getName()));
			}
			if (file[i].isDirectory()) {
				// 复制目录
				String sourceDir = source + File.separator + file[i].getName();
				String targetDir = target + File.separator + file[i].getName();
				copyDirectiory(sourceDir, targetDir);
			}
		}
	}

	/**
	 * copy文件或目录
	 * 
	 * @author 董华健 2012-9-3 下午7:31:57
	 * @param source
	 * @param target
	 */
	public static void lovecopy(String source, String target) {
		// (new File(url2)).mkdirs();
		File f = new File(target);
		if (!f.exists()) {
			f.mkdirs();
		}

		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(source)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 复制文件
				try {
					copyFile(file[i], new File(target + file[i].getName()));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (file[i].isDirectory()) {
				// 复制目录
				String sourceDir = source + File.separator + file[i].getName();
				String targetDir = target + File.separator + file[i].getName();
				try {
					copyDirectiory(sourceDir, targetDir);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @author 董华健 2012-9-3 下午7:32:26
	 * @param sourceFile
	 * @param targetFile
	 * @throws IOException
	 */
	public static void copyFile(File sourceFile, File targetFile) throws IOException {
		// 新建文件输入流并对它进行缓冲
		FileInputStream input = new FileInputStream(sourceFile);
		BufferedInputStream inBuff = new BufferedInputStream(input);

		// 新建文件输出流并对它进行缓冲
		FileOutputStream output = new FileOutputStream(targetFile);
		BufferedOutputStream outBuff = new BufferedOutputStream(output);

		// 缓冲数组
		byte[] b = new byte[1024 * 5];
		int len;
		while ((len = inBuff.read(b)) != -1) {
			outBuff.write(b, 0, len);
		}
		// 刷新此缓冲的输出流
		outBuff.flush();

		// 关闭流
		inBuff.close();
		outBuff.close();
		output.close();
		input.close();
	}

	/**
	 * 复制文件夹
	 * 
	 * @author 董华健 2012-9-3 下午7:32:33
	 * @param sourceDir
	 * @param targetDir
	 * @throws IOException
	 */
	public static void copyDirectiory(String sourceDir, String targetDir) throws IOException {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile = file[i];
				// 目标文件
				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
				copyFile(sourceFile, targetFile);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1 = sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2 = targetDir + "/" + file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}
	
	/**
	 * 检查目录是否存在，如果不存在就创建目录
	 * 
	 * @author 董华健    2012-9-10 下午5:17:58
	 * @param dirPath
	 */
	public static void createDirectory(String dirPath){
		File file = new File(dirPath);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	public static boolean isExitFile(String filePath){
		File file = new File(filePath);
		if(file.exists())
			return true;
		return false;
	}
	
	public static boolean isExitFiles(String dirPath){
		File file = new File(dirPath);
		if(file.exists() && file.isDirectory()){
			File files[] = file.listFiles();
			if(files!=null&&files.length>0)
				return true;
		}
		return false;
	}
	
	/**
	 * 删除文件或者目录
	 * @param file
	 */
	public static void delete(File file) {
		if (file != null && file.exists()) {
			if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i=0, length = files.length; i<length; i++) {
					delete(files[i]);
				}
			}else{
				file.delete();
			}
		}
	}
	
	/**
	 * 文件下载
	 * @param response
	 * @param fileName
	 * @param filePath
	 * @throws IOException
	 */
	public static void download(HttpServletResponse response, String fileName, String filePath) throws IOException{
		FileInputStream fis = null;
        BufferedInputStream buff = null;
		try {
    		File file = new File(filePath);
    		response.setContentType("application/x-msdownload");//设置response的编码方式
            response.setContentLength((int)file.length());//写明要下载的文件的大小
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "iso-8859-1")); //解决中文乱码
	        
	        //读出文件到i/o流
	        fis = new FileInputStream(file);
	        buff = new BufferedInputStream(fis);
	        byte[] bytes = new byte[1024];//相当于我们的缓存
	        long k = 0;//该值用于计算当前实际下载了多少字节
	        OutputStream os = response.getOutputStream();//从response对象中得到输出流,准备下载
	        
	        //开始循环下载
	        while(k < file.length()){
	            int j = buff.read(bytes, 0, 1024);
	            k += j;
	            os.write(bytes, 0, j);//将b中的数据写到客户端的内存
	        }
	        
	        os.flush();//将写入到客户端的内存的数据,刷新到磁盘
	        
	        buff.close();
	        buff = null;
	        
	        fis.close();
	        fis = null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(buff != null){
				buff.close();
		        buff = null;
			}
			if(fis != null){
				fis.close();
				fis = null;
			}
		}
	}
	
	/**
	 * 创建文件
	 * @param savePath 保存路径
	 * @param content 文件内容
	 */
	public static void createFile(String savePath, String content){
		try {
			File file = new File(savePath);
			if(!file.exists()){
				file.createNewFile();
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(file));   
			output.write(content);   
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException{
		copyDir("D:\\aa\\新建文本文档 (2).txt", "D:\\bb\\新建文本文档 (2).txt");
	}

}
