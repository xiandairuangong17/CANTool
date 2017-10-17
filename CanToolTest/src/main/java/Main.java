import gnu.io.SerialPort;
import serialPort.SerialTool;

/**
 * Created by Admin on 2017/10/16.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SerialPort serialPort = SerialTool.openPort("COM12", 115200);
		CanTool tool = new CanTool(serialPort);
		SerialListener listener = new SerialListener(serialPort,tool);
		tool.addListener(listener);
	}

}

