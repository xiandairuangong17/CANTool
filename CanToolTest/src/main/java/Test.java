import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Admin on 2017/10/13.
 */
public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SerialPort serialPort = SerialTool.openPort("COM12", 115200);
		CANTool tool = new CANTool(serialPort);
		SerialListener listener = new SerialListener(serialPort,tool);
		tool.addListener(listener);
		InputStream in1 = new FileInputStream(new File("test1.txt"));
		InputStream in2 = new FileInputStream(new File("test2.txt"));
		InputStream in3 = new FileInputStream(new File("test3.txt"));
		InputStream in4 = new FileInputStream(new File("test4.txt"));
		InputStream in5 = new FileInputStream(new File("test5.txt"));
		Scanner scan = new Scanner(System.in);
		Scanner scan1 = new Scanner(in1);
		Scanner scan2 = new Scanner(in2);
		Scanner scan3 = new Scanner(in3);
		Scanner scan4 = new Scanner(in4);
		Scanner scan5 = new Scanner(in5);
		Thread thread = new Thread();


}
