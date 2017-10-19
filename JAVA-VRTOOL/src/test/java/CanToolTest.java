import cantool.CanTool;
import gnu.io.SerialPort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import serialPort.SerialTool;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Created by Admin on 2017/10/17.
 */
public class CanToolTest {
	private CanTool tool,spy;
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
	//测试返回版本信息
	@Test
	public void getCommandTest1() {

		doNothing().when(spy).success();
		spy.getCommand("");
		verify(spy,times(1)).success();

	}

	//测试正常开机
	@Test
	public void getCommandTest2() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		verify(spy,times(1)).open_s();
		verify(spy,times(1)).success();

	}

	//测试非正常开机
	@Test
	public void getCommandTest3() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		spy.getCommand("O1\r");
		verify(spy,times(2)).open_s();
		verify(spy,times(1)).success();
		verify(spy,times(1)).success();

	}

	//测试正常关机
	@Test
	public void getCommandTest4() {

		doNothing().when(spy).success();
		spy.getCommand("O1r");
		spy.getCommand("C\r");
		verify(spy,times(1)).open_s();
		verify(spy,times(1)).close_s();
		verify(spy,times(2)).success();

	}

	//测试非正常关机
	@Test
	public void getCommandTest5() {

		doNothing().when(spy).success();
		spy.getCommand("C\r");
		verify(spy,times(1)).close_s();
		verify(spy,times(1)).success();

	}

	//测试关机状态下调节速度
	@Test
	public void getCommandTest6() {

		doNothing().when(spy).success();
		spy.getCommand("S1\r");
		verify(spy,times(1)).Changespeed('1');
		verify(spy,times(1)).success();

	}

	//测试关机状态下调节速度
	@Test
	public void getCommandTest7() {

		doNothing().when(spy).success();
		spy.getCommand("O1r");
		spy.getCommand("S1\r");
		verify(spy,times(1)).Changespeed('1');
		verify(spy,times(1)).success();
		verify(spy,times(1)).success();

	}

	//测试连续调节速度
	@Test
	public void getCommandTest8() {

		doNothing().when(spy).success();
		spy.getCommand("S1\r");
		spy.getCommand("S2\r");
		spy.getCommand("S3\r");
		verify(spy,times(1)).Changespeed('1');
		verify(spy,times(1)).Changespeed('2');
		verify(spy,times(1)).Changespeed('3');
		verify(spy,times(3)).success();
	}

	//测试发送1次标准帧
	@Test
	public void getCommandTest9() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		spy.getCommand("t36380000000300000D500000\r");
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

	//测试发送多次标准帧
	@Test
	public void getCommandTest10() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		spy.getCommand("t36380000000300000D500010\r");
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

	//测试发送标准帧错误
	@Test
	public void getCommandTest11() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		spy.getCommand("t3F380000W000300000D500010\r");
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

	//测试未开机发送标准帧
	@Test
	public void getCommandTest12() {

		doNothing().when(spy).success();
		//spy.readCommand("O1\r");
		spy.getCommand("t359800301513034014880010\r");
		//verify(spy,times(1)).returnTheInfo(1,"");
		try {
			verify(spy,times(1)).Sendstandardframe("t359800301513034014880010");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		verify(spy,times(1)).success();

	}

	//测试发送1次扩展帧
	@Test
	public void getCommandTest13() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		spy.getCommand("T0000036380000000300000D500000\r");
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

	//测试发送多次扩展帧
	@Test
	public void getCommandTest14() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		spy.getCommand("T0000036380000000300000D500010\r");
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

	//测试发送扩展帧错误
	@Test
	public void getCommandTest15() {

		doNothing().when(spy).success();
		spy.getCommand("O1\r");
		spy.getCommand("T000003F380000000300000D500010\r");
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

	//测试未开机发送扩展帧错误
	@Test
	public void getCommandTest16() {

		doNothing().when(spy).success();
		//spy.readCommand("O1\r");
		spy.getCommand("T00000359800301513034014880010\r");
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