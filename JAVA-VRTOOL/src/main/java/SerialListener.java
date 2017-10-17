import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * Created by Admin on 2017/10/17.
 */
public class SerialListener implements SerialPortEventListener {
	private SerialPort port;
	private String buff;
	private CanTool cantool;

	public SerialListener(SerialPort port,CanTool cantool) {
		this.port=port;
		this.cantool=cantool;
	}

	/**
	 * 关闭串口
	 * @param serialport 待关闭的串口对象
	 */
	public static void closePort(SerialPort serialPort) {
		if (serialPort != null) {
			serialPort.close();
			serialPort = null;
		}
	}

	public void serialEvent(SerialPortEvent serialPortEvent) {

		switch (serialPortEvent.getEventType()) {

			case SerialPortEvent.BI: // 10 通讯中断
				System.out.println("与串口设备通讯中断");
				break;

			case SerialPortEvent.OE: // 7 溢位（溢出）错误

			case SerialPortEvent.FE: // 9 帧错误

			case SerialPortEvent.PE: // 8 奇偶校验错误

			case SerialPortEvent.CD: // 6 载波检测

			case SerialPortEvent.CTS: // 3 清除待发送数据

			case SerialPortEvent.DSR: // 4 待发送数据准备好了

			case SerialPortEvent.RI: // 5 振铃指示

			case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
				break;

			case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据

				byte[] bytes = null;
				try {
					if (port == null) {
						System.out.println("不存在该串口，监听失败");
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