//import CanTool.CanTool;
//import gnu.io.SerialPort;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import serialException.SendDataToSerialPortFailure;
//import serialException.SerialPortOutputStreamCloseFailure;
//import serialPort.SerialTool;
//
//import java.io.UnsupportedEncodingException;
//
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//
///**
// * Created by Admin on 2017/10/17.
// */
//public class CanToolTest {
//	private CanTool tool;
//	private CanTool spy;
//	private SerialPort port;
//	private SerialTool sendToPort;
//
//	@Before
//	public void setUp() throws Exception {
//		port = mock(SerialPort.class);
//		tool = new CanTool(port);
//		spy = spy(tool);
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//
//	//娴嬭瘯杩斿洖鐗堟湰淇℃伅
//	@Test
//	public void getCommandTest1() throws UnsupportedEncodingException, SerialPortOutputStreamCloseFailure, SendDataToSerialPortFailure {
//		String str = "t";
//		byte[] byteArray = str.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray);
//		verify(spy, times(1)).success();
//
//	}
//
////娴嬭瘯寮�鏈�
//	@Test
//	public void getCommandTest2() throws  UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str = "O1";
//		byte[] byteArray = str.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray);
//		verify(spy, times(1)).open_s();
//		verify(spy, times(1)).success();
//
//	}
//
////娴嬭瘯寮�鏈�
//	@Test
//	public void getCommandTest3() throws UnsupportedEncodingException, SerialPortOutputStreamCloseFailure, SendDataToSerialPortFailure {
//		String str1 = "01";
//		byte[] byteArray = str1.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray);
//		spy.getCommand(byteArray);
//		verify(spy, times(2)).open_s();
//		verify(spy, times(1)).success();
////		verify(spy, times(1)).success();
//
//	}
//
////娴嬭瘯寮�鍏虫満
//	@Test
//	public void getCommandTest4() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure  {
//		String str1 = "O1";
//		String str2 = "C";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).open_s();
//		verify(spy, times(1)).close_s();
//		verify(spy, times(2)).success();
//
//	}
//	@Test
//	public void getCommandTest5() throws  UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure  {
//        String str = "C";
//		byte[] byteArray = str.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray);
//		verify(spy, times(1)).close_s();
//		verify(spy, times(1)).success();
//
//	}
//	//锟斤拷锟皆关伙拷状态锟铰碉拷锟斤拷锟劫讹拷
//	@Test
//	public void getCommandTest6() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 ="O1";
//		String str2 = "S1";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).Changespeed('1');
//		verify(spy, times(1)).success();
//		verify(spy, times(1)).success();
//
//	}
//
////鍏虫満鏀瑰彉閫熷害
//	@Test
//	public void getCommandTest7() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str2 ="S1";
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).Changespeed('1');
//		verify(spy, times(1)).success();
////		verify(spy, times(1)).success();
//
//	}
//
////鏀瑰彉閫熷害
//	@Test
//	public void getCommandTest8() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//        String str = "O1";
//		String str1 = "S1";
//		String str2 = "S2";
//		String str3	= "S3";
//		byte[] bytesArray = str.getBytes();
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		byte[] byteArray3 = str3.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(bytesArray);
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		spy.getCommand(byteArray3);
//		verify(spy, times(1)).Changespeed('1');
//		verify(spy, times(1)).Changespeed('2');
//		verify(spy, times(1)).Changespeed('3');
//		verify(spy, times(3)).success();
//
//	}
//
//	//鍙戦�佹爣鍑嗗抚
//	@Test
//	public void getCommandTest9() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 ="O1";
//		String str2 ="t36380000000300000D500000";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).open_s();
////		spy.readCommand("O1\r");
////		spy.readCommand("t36380000000300000D500000\r");
//		verify(spy,times(1)).open_s();
//		try {
//			verify(spy, times(1)).Sendstandardframe("t36380000000300000D500000");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(spy, times(2)).success();
//		//verify(spy,times(1)).returnTheInfo(0,"");
//
//	}
//
//	@Test
//	public void getCommandTest10() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 ="O1";//鏀逛负O
////		String str2 ="t36380000000300000D500010";
//		String str2 ="t12341122331234";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).open_s();
////		spy.readCommand("O1\r");
////		spy.readCommand("t36380000000300000D500010\r");
//		verify(spy,times(1)).open_s();
//		try {
//			verify(spy, times(1)).Sendstandardframe("t36380000000300000D500010");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(spy, times(2)).success();
//		//verify(spy,times(1)).returnTheInfo(0,"");
//
//	}
//
//	@Test
//	public void getCommandTest11() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 ="O1";
//		String str2 ="t3F380000000300000D500010";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).open_s();
////		spy.readCommand("O1\r");
////		spy.readCommand("t3F380000000300000D500010\r");
//		verify(spy,times(1)).open_s();
//		try {
//			verify(spy, times(1)).Sendstandardframe("t3F380000000300000D500010");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(spy, times(1)).success();
//		verify(spy, times(1)).success();
//
//	}
//
//	//锟斤拷锟斤拷未锟斤拷锟斤拷锟斤拷锟酵憋拷准帧
//	@Test
//	public void getCommandTest12() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "t359800301513034014880010";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		//spy.readCommand("O1\r");
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
////		spy.readCommand("t359800301513034014880010\r");
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		try {
//			verify(spy, times(1)).Sendstandardframe("t359800301513034014880010");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(spy, times(1)).success();
//		verify(spy,times(1)).success();
//
//	}
//
//	//锟斤拷锟皆凤拷锟斤拷1锟斤拷锟斤拷展帧
//	@Test
//	public void getCommandTest13() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 ="O1";
//		String str2 ="T0000036380000000300000D500000";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).open_s();
////		spy.readCommand("O1\r");
////		spy.readCommand("T0000036380000000300000D500000\r");
//		verify(spy,times(1)).open_s();
//		try {
//			verify(spy, times(1)).Sendexternalframe("T0000036380000000300000D500000");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(spy, times(2)).success();
//		//verify(spy,times(1)).returnTheInfo(0,"");
//
//	}
//
//	//锟斤拷锟皆凤拷锟酵讹拷锟斤拷锟秸怪�
//	@Test
//	public void getCommandTest14() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 ="O1";
//		String str2 ="T0000036380000000300000D500010";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).open_s();
////		spy.readCommand("O1\r");
////		spy.readCommand("T0000036380000000300000D500010\r");
//		verify(spy,times(1)).open_s();
//		try {
//			verify(spy, times(1)).Sendexternalframe("T0000036380000000300000D500010");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(spy, times(2)).success();
//		//verify(spy,times(1)).returnTheInfo(0,"");
//
//	}
//
//	//锟斤拷锟皆凤拷锟斤拷锟斤拷展帧锟斤拷锟斤拷
//	public void getCommandTest15() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//        String str1 ="O1";
//        String str2 ="T000003F380000000300000D500010";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		verify(spy, times(1)).open_s();
//		try {
//			verify(spy, times(1)).Sendexternalframe("T000003F380000000300000D500010");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(spy, times(1)).success();
//		verify(spy, times(1)).success();
//
//	}
//
//	//锟斤拷锟斤拷未锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷展帧锟斤拷锟斤拷
//	@Test
//	public void getCommandTest16() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str ="T00000359800301513034014880010";
//		byte[] byteArray = str.getBytes();
//		doNothing().when(spy).success();
//		//spy.readCommand("O1\r");
//		spy.getCommand(byteArray);
//		try {
//			verify(spy, times(1)).Sendexternalframe("T00000359800301513034014880010");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//
//	}
//	@Test
//	public void getCommandTest17() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "t12341122331234";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
////		spy.readCommand("O1\r");
////		spy.readCommand("T000003F380000000300000D500010\r");
//
//		verify(spy,times(1)).open_s();
//		try {
//			verify(spy, times(1)).Sendexternalframe("t12341122331234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//
//	}
//	//锟斤拷锟斤拷未锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷展帧锟斤拷锟斤拷
//	@Test
//	public void getCommandTest18() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "t12H31122331234";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		try {
//			verify(spy, times(1)).Sendexternalframe("t12341122331234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//
//	}
//
//	@Test
//	public void getCommmandTest19() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "t12391122331234";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		//spy.readCommand("O1\r");
////		spy.readCommand("T00000359800301513034014880010\r");
//		try {
//			verify(spy, times(1)).Sendexternalframe("t12391122331234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//
//	}
//
//	@Test
//	public void getCommmandTest20() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "t12331122OO1234";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		try {
//			verify(spy, times(1)).Sendexternalframe("t12331122OO1234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//	}
//
//	@Test
//	public void getCommmandTest21() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "t12331122OO12M4";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		try {
//			verify(spy, times(1)).Sendexternalframe("t12331122OO12M4");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//	}
//
//	@Test
//	public void getCommandTest22() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "t8F3511223344551234";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		try {
//			verify(spy, times(1)).Sendexternalframe("t8F3511223344551234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//	}
//
//	@Test
//	public void getCommandTest23() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str1 = "O1";
//		String str2 = "TH23456784112233441234";
//		byte[] byteArray1 = str1.getBytes();
//		byte[] byteArray2 = str2.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray1);
//		spy.getCommand(byteArray2);
//		try {
//			verify(spy, times(1)).Sendexternalframe("TH23456784112233441234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//	}
//	@Test
//	public void getCommandTest24() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str ="TH23456784112233441234";
//		byte[] byteArray = str.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray);
//		try {
//			verify(spy, times(1)).Sendexternalframe("TH23456784112233441234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//	}
//	public void getCommandTest25() throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
//		String str = "t8F3511223344551234";
//		byte[] byteArray = str.getBytes();
//		doNothing().when(spy).success();
//		spy.getCommand(byteArray);
//		try {
//			verify(spy, times(1)).Sendexternalframe("TH23456784112233441234");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//verify(spy,times(1)).returnTheInfo(1,"");
//		verify(spy, times(1)).success();
//	}
//}
