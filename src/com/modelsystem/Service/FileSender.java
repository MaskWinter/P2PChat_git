package com.modelsystem.Service;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

import com.modelsystem.util.Configuration;

/**
 * 发送文件类
 * @Title: FileSender.java 
 * @Package com.modelsystem.Service 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:31:36 
 * @version V1.0
 */
public class FileSender implements Runnable{

	@Override
	public void run() {
		Socket s = null;
		try{
			ServerSocket ss = new ServerSocket(Configuration.FILEPORT);
			// 选择进行传输的文件
        	JFileChooser jfc = new JFileChooser(".");
            File fi = null;
            int returnVal = jfc.showOpenDialog(new javax.swing.JFrame());
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                fi = jfc.getSelectedFile();

            }else{
            	ss.close();
            	return;
            }
            System.out.println("文件长度:" + (int) fi.length());
            // public Socket accept() throws
            // IOException侦听并接受到此套接字的连接。此方法在进行连接之前一直阻塞。

            s = ss.accept();
            System.out.println("建立socket链接");
            DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            dis.readByte();

            DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(fi.getPath())));
            DataOutputStream ps = new DataOutputStream(s.getOutputStream());
            //将文件名及长度传给客户端。这里要真正适用所有平台，例如中文名的处理，还需要加工，具体可以参见Think In Java 4th里有现成的代码。
            ps.writeUTF(fi.getName());
            ps.flush();
            ps.writeLong((long) fi.length());
            ps.flush();

            int bufferSize = 8192;
            byte[] buf = new byte[bufferSize];

            while (true) {
                int read = 0;
                if (fis != null) {
                    read = fis.read(buf);
                }

                if (read == -1) {
                    break;
                }
                ps.write(buf, 0, read);
            }
            ps.flush();
            // 注意关闭socket链接哦，不然客户端会等待server的数据过来，
            // 直到socket超时，导致数据不完整。                
            fis.close();
            s.close();  
            ss.close();
            System.out.println("文件传输完成");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
