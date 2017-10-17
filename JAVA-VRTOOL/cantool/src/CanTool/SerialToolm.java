package CanTool;

import java.io.IOException;
import java.io.InputStream;
import gnu.io.SerialPort;
import serialException.*;

public class SerialToolm {
	
	   /**
     * �Ӵ��ڶ�ȡ����
     * @param serialPort ��ǰ�ѽ������ӵ�SerialPort����
     * @return ��ȡ��������
     * @throws ReadDataFromSerialPortFailure �Ӵ��ڶ�ȡ����ʱ����
     * @throws SerialPortInputStreamCloseFailure �رմ��ڶ�������������
     */
    public static byte[] readFromPort(SerialPort serialPort) throws ReadDataFromSerialPortFailure, SerialPortInputStreamCloseFailure {

    	InputStream in = null;
        byte[] bytes = new byte[1024];
        
        try {
        	int temp;
        	in = serialPort.getInputStream();
        	int bufflenth = in.available();		//��ȡbuffer������ݳ���
        	temp=in.read();
        	int num=0;//bytes�������ֽڵ�λ��
        	
        	while (bufflenth != 0 && temp!=0x0D) {                             
                bytes[num]=(byte) temp;
                bufflenth = in.available();		//��ȡbuffer������ݳ���
                temp=in.read();
                num++;
        	} 

        } catch (IOException e) {
        	throw new ReadDataFromSerialPortFailure();
        } finally {
        	try {
            	if (in != null) {
            		in.close();
            		in = null;
            	}
        	} catch(IOException e) {
        		throw new SerialPortInputStreamCloseFailure();
        	}
        }
        return bytes;
    }

}