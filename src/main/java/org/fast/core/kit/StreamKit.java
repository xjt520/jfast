package org.fast.core.kit;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public class StreamKit {
	
	 private static final int DEFAULT_BUFFER_SIZE = 8192;
	
	/** 从输入流读取内容，写入到输出流中。 */
    public static void io(InputStream in, OutputStream out, boolean closeIn, boolean closeOut) throws IOException {
        int bufferSize = DEFAULT_BUFFER_SIZE;
        byte[] buffer = new byte[bufferSize];
        int amount;
        try {
            while ((amount = in.read(buffer)) >= 0) {
                out.write(buffer, 0, amount);
            }
            out.flush();
        } finally {
            if (closeIn) {
                try {
                    in.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
            if (closeOut) {
                try {
                    out.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }
    }
    
    /** 从输入流读取内容，写入到输出流中。 */
    public static void io(Reader in, Writer out, boolean closeIn, boolean closeOut) throws IOException {
        int bufferSize = DEFAULT_BUFFER_SIZE >> 1;
        char[] buffer = new char[bufferSize];
        int amount;

        try {
            while ((amount = in.read(buffer)) >= 0) {
                out.write(buffer, 0, amount);
            }

            out.flush();
        } finally {
            if (closeIn) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }

            if (closeOut) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }
    
    /** 将byte数组写入到指定<code>OutputStream</code>中。 */
    public static void io(byte[] bytes, OutputStream out, boolean closeOut) throws IOException {
    	io(new ByteArray(bytes), out, closeOut);
    }
    
    /** 将byte数组写入到指定<code>OutputStream</code>中。 */
    public static void io(ByteArray bytes, OutputStream out, boolean closeOut) throws IOException {
        try {
            out.write(bytes.getRawBytes(), bytes.getOffset(), bytes.getLength());
            out.flush();
        } finally {
            if (closeOut) {
                try {
                    out.close();
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        }
    }
    
    /** 下载文件到指定路径 */
    public static void down(InputStream in, boolean closeIn, boolean closeOut, String filepath){
    	FileOutputStream out = null;
		try {
			out = new FileOutputStream(filepath);
			io(in, out, closeIn, closeOut);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static String readFromInputStream(InputStream inputStream,String encode) throws Exception {
		byte[] buf = new byte[inputStream.available()];
		inputStream.read(buf);
		return new String(buf, encode);
	}

	public static void writeToOutputStream(OutputStream outputStream,String content, String encode) throws Exception {
		outputStream.write(content.getBytes(encode));
	}

    public static byte[] streamToByte(InputStream inputStream) throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = inputStream.read(buffer)) !=-1){
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }
	
}

