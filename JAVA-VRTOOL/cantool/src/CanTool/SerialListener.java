package CanTool;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialListener implements SerialPortEventListener{
	private SerialPort port;
	private String buff;
	private CanTool cantool;
	
	public SerialListener(SerialPort port,CanTool cantool) {
		this.port=port;
		this.cantool=cantool;
	}
	
    /**
     * �رմ���
     * @param serialport ���رյĴ��ڶ���
     */
    public static void closePort(SerialPort serialPort) {
    	if (serialPort != null) {
    		serialPort.close();
    		serialPort = null;
    	}
    }
	
	 public void serialEvent(SerialPortEvent serialPortEvent) {
	        
	        switch (serialPortEvent.getEventType()) {

	            case SerialPortEvent.BI: // 10 ͨѶ�ж�
	            	System.out.println("�봮���豸ͨѶ�ж�");
	                break;

	            case SerialPortEvent.OE: // 7 ��λ�����������

	            case SerialPortEvent.FE: // 9 ֡����

	            case SerialPortEvent.PE: // 8 ��żУ�����

	            case SerialPortEvent.CD: // 6 �ز����

	            case SerialPortEvent.CTS: // 3 �������������

	            case SerialPortEvent.DSR: // 4 ����������׼������

	            case SerialPortEvent.RI: // 5 ����ָʾ

	            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 ��������������
	                break;
	            
	            case SerialPortEvent.DATA_AVAILABLE: // 1 ���ڴ��ڿ�������
	                
	                byte[] bytes = null;
	                try {
	                	if (port == null) {
	                    	System.out.println("�����ڸô��ڣ�����ʧ��");
	                    }else{
	                    	bytes=SerialToolm.readFromPort(port);
	                    	cantool.getCommand(bytes);
	                    }                   
	                } catch (Exception e) {
	                    System.exit(0);
	                }    
	                break;
	        }

	    }


}