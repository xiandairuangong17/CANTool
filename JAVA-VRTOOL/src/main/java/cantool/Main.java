package cantool;

import gnu.io.SerialPort;
import serialPort.SerialTool;

/**
 * Created by Admin on 2017/10/17.
 */
public class Main {

	public static void main(String[] args)throws Exception{
		System.out.println("start");
		SerialPort port= SerialTool.openPort("COM12", 115200);
		CanTool tool=new CanTool(port);
		SerialListener listener=new SerialListener(port,tool);
		tool.addListener(listener);
	}
}
