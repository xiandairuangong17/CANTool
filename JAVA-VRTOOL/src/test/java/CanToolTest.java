import gnu.io.SerialPort;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import serialPort.SerialTool;

import javax.tools.Tool;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Created by Admin on 2017/10/17.
 */
public class CanToolTest {
	private CanTool tool,spy;
	private SerialPort port;
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
	public void getCommand1() throws Exception {
		doNothing().when(spy).success();
		spy.getCommand("V\r");
//		verify(spy,times(1)).returnTheInfo(1,"SV2.5-HV2.0");

	}

}