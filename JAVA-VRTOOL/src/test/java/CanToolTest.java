import cantool.CanTool;
import gnu.io.SerialPort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import serialException.SendDataToSerialPortFailure;
import serialException.SerialPortOutputStreamCloseFailure;
import serialPort.SerialTool;

import java.io.UnsupportedEncodingException;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Created by Admin on 2017/10/17.
 */
public class CanToolTest {
	private CanTool tool;
	private CanTool spy;
	private SerialPort port;
	private SerialTool sendToPort;
	@Before
	public void setUp() throws Exception {
		port = mock(SerialPort.class);
		tool = new CanTool (port);
		spy = spy(tool);
	}
	@After
	public void tearDown() throws Exception {
	}

	//错误，string转byte有问题
	public static byte[] strToByteArray(String str) {
		if (str == null) {
			return null;
		}
		byte[] byteArray = str.getBytes();
		return byteArray;
	}
	//测试返回版本信息
	@Test
	public void getCommandTest1() {

		doNothing().when(spy).success();
//		spy.readCommand("V\r");
		try {
			spy.getCommand(strToByteArray("V\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).success();

	}

	//������������
	@Test
	public void getCommandTest2() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
		try {
			spy.getCommand(strToByteArray("O1\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		verify(spy,times(1)).success();

	}

	//���Է���������
	@Test
	public void getCommandTest3() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
//		spy.readCommand("O1\r");
		try {
			spy.getCommand(strToByteArray("O1\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("O1\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(2)).open_s();
		verify(spy,times(1)).success();
		verify(spy,times(1)).success();

	}

	//���������ػ�
	@Test
	public void getCommandTest4() {

		doNothing().when(spy).success();
//		spy.readCommand("O1r");
//		spy.readCommand("C\r");
		try {
			spy.getCommand(strToByteArray("O1r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("C\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		verify(spy,times(1)).close_s();
		verify(spy,times(2)).success();

	}

	//���Է������ػ�
	@Test
	public void getCommandTest5() {

		doNothing().when(spy).success();
//		spy.readCommand("C\r");
		try {
			spy.getCommand(strToByteArray("C\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).close_s();
		verify(spy,times(1)).success();

	}

	//���Թػ�״̬�µ����ٶ�
	@Test
	public void getCommandTest6() {

		doNothing().when(spy).success();
//		spy.readCommand("S1\r");
		try {
			spy.getCommand(strToByteArray("S1\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).Changespeed('1');
		verify(spy,times(1)).success();

	}

	//���Թػ�״̬�µ����ٶ�
	@Test
	public void getCommandTest7() {

		doNothing().when(spy).success();
//		spy.readCommand("O1r");
//		spy.readCommand("S1\r");
		try {
			spy.getCommand(strToByteArray("O1r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("S1\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).Changespeed('1');
		verify(spy,times(1)).success();
		verify(spy,times(1)).success();

	}

	//�������������ٶ�
	@Test
	public void getCommandTest8() {

		doNothing().when(spy).success();
//		spy.readCommand("S1\r");
//		spy.readCommand("S2\r");
//		spy.readCommand("S3\r");
		try {
			spy.getCommand(strToByteArray("S1\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("S2\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("S3\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).Changespeed('1');
		verify(spy,times(1)).Changespeed('2');
		verify(spy,times(1)).Changespeed('3');
		verify(spy,times(3)).success();

	}

	//���Է���1�α�׼֡
	@Test
	public void getCommandTest9() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
//		spy.readCommand("t36380000000300000D500000\r");
		try {
			spy.getCommand(strToByteArray("O1\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("t36380000000300000D500000\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		try {
			verify(spy,times(1)).Sendstandardframe("t36380000000300000D500000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(2)).success();
		//verify(spy,times(1)).returnTheInfo(0,"");

	}

	//���Է��Ͷ�α�׼֡
	@Test
	public void getCommandTest10() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
//		spy.readCommand("t36380000000300000D500010\r");
		try {
			spy.getCommand(strToByteArray("01\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("t36380000000300000D500010\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		try {
			verify(spy,times(1)).Sendstandardframe("t36380000000300000D500010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(2)).success();
		//verify(spy,times(1)).returnTheInfo(0,"");

	}

	//���Է��ͱ�׼֡����
	@Test
	public void getCommandTest11() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
//		spy.readCommand("t3F380000000300000D500010\r");
		try {
			spy.getCommand(strToByteArray("01\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("t3F380000000300000D500010\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		try {
			verify(spy,times(1)).Sendstandardframe("t3F380000000300000D500010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(1)).success();
		verify(spy,times(1)).success();

	}

	//����δ�������ͱ�׼֡
	@Test
	public void getCommandTest12() {

		doNothing().when(spy).success();
		//spy.readCommand("O1\r");
//		spy.readCommand("t359800301513034014880010\r");
		try {
			spy.getCommand(strToByteArray("O1\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("t359800301513034014880010\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		//verify(spy,times(1)).returnTheInfo(1,"");
		try {
			verify(spy,times(1)).Sendstandardframe("t359800301513034014880010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(1)).success();

	}

	//���Է���1����չ֡
	@Test
	public void getCommandTest13() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
//		spy.readCommand("T0000036380000000300000D500000\r");
		try {
			spy.getCommand(strToByteArray("01\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("T0000036380000000300000D500000\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		try {
			verify(spy,times(1)).Sendexternalframe("T0000036380000000300000D500000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(2)).success();
		//verify(spy,times(1)).returnTheInfo(0,"");

	}

	//���Է��Ͷ����չ֡
	@Test
	public void getCommandTest14() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
//		spy.readCommand("T0000036380000000300000D500010\r");
		try {
			spy.getCommand(strToByteArray("O1\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("T0000036380000000300000D500010\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		try {
			verify(spy,times(1)).Sendexternalframe("T0000036380000000300000D500010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(2)).success();
		//verify(spy,times(1)).returnTheInfo(0,"");

	}

	//���Է�����չ֡����
	@Test
	public void getCommandTest15() {

		doNothing().when(spy).success();
//		spy.readCommand("O1\r");
//		spy.readCommand("T000003F380000000300000D500010\r");
		try {
			spy.getCommand(strToByteArray("O1\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			spy.getCommand(strToByteArray("T000003F380000000300000D500010\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		verify(spy,times(1)).open_s();
		try {
			verify(spy,times(1)).Sendexternalframe("T000003F380000000300000D500010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(1)).success();
		verify(spy,times(1)).success();

	}

	//����δ����������չ֡����
	@Test
	public void getCommandTest16() {

		doNothing().when(spy).success();
		//spy.readCommand("O1\r");
//		spy.readCommand("T00000359800301513034014880010\r");
		try {
			spy.getCommand(strToByteArray("T00000359800301513034014880010\\r"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SendDataToSerialPortFailure sendDataToSerialPortFailure) {
			sendDataToSerialPortFailure.printStackTrace();
		} catch (SerialPortOutputStreamCloseFailure serialPortOutputStreamCloseFailure) {
			serialPortOutputStreamCloseFailure.printStackTrace();
		}
		try {
			verify(spy,times(1)).Sendexternalframe("T00000359800301513034014880010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//verify(spy,times(1)).returnTheInfo(1,"");
		verify(spy,times(1)).success();

	}



}