package com.modelsystem.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 * 文件接受辅助工具
 * @Title: FileClientSocket.java 
 * @Package com.modelsystem.util 
 * @author lxd <836696016@qq.com>
 * @date 2015年12月30日 下午2:30:38 
 * @version V1.0
 */
public class FileClientSocket {
	private String ip;

    private Socket socket = null;

    DataOutputStream out = null;

    DataInputStream getMessageStream = null;

    public FileClientSocket(String ip) {
        this.ip = ip;
    }

    /**
     * 创建socket连接
     * 
     * @throws Exception
     *             exception
     */
    public void CreateConnection() throws Exception {
        try {
            socket = new Socket(ip, Configuration.FILEPORT);
        } catch (Exception e) {
            e.printStackTrace();
            if (socket != null)
                socket.close();
            throw e;
        } finally {
        }
    }

    public void sendMessage(String sendMessage) throws Exception {
        try {
            out = new DataOutputStream(socket.getOutputStream());
            if (sendMessage.equals("Windows")) {
                out.writeByte(0x1);
                out.flush();
                return;
            }
            if (sendMessage.equals("Unix")) {
                out.writeByte(0x2);
                out.flush();
                return;
            }
            if (sendMessage.equals("Linux")) {
                out.writeByte(0x3);
                out.flush();
            } else {
                out.writeUTF(sendMessage);
                out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (out != null)
                out.close();
            throw e;
        } finally {
        }
    }

    public DataInputStream getMessageStream() throws Exception {
        try {
            getMessageStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            return getMessageStream;
        } catch (Exception e) {
            e.printStackTrace();
            if (getMessageStream != null)
                getMessageStream.close();
            throw e;
        } finally {
        }
    }

    public void shutDownConnection() {
        try {
            if (out != null)
                out.close();
            if (getMessageStream != null)
                getMessageStream.close();
            if (socket != null)
                socket.close();
        } catch (Exception e) {

        }
    }
}
