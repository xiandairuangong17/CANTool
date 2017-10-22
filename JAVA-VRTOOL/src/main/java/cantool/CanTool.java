package cantool;

import gnu.io.SerialPort;
import serialException.SendDataToSerialPortFailure;
import serialException.SerialPortOutputStreamCloseFailure;
import serialException.TooManyListeners;
import serialPort.SerialTool;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.io.File;
import java.util.Scanner;

/**
 * Created by Admin on 2017/10/17.
 */
public class CanTool {
    private SerialPort port;
    private boolean flag;
    private int speed;

    public CanTool(SerialPort serialport) {
        this.port = serialport;
        flag = false;
        speed = 10;
    }

    public void addListener(SerialListener listener) {
        try {
            SerialTool.addListener(port, listener);
            System.out.println("监听成功");
        } catch (TooManyListeners e) {
            e.printStackTrace();
        }
    }

    public int returnActualLength(byte[] data) {
        int i = 0;
        for (; i < data.length; i++) {
            if (data[i] == '\0')
                break;
        }
        return i;
    }

    public void getCommand(byte[] bytes) throws UnsupportedEncodingException, SendDataToSerialPortFailure, SerialPortOutputStreamCloseFailure {
        byte[] command = bytes;
        int length = returnActualLength(command);
        String temp = new String(command, 0, length, "UTF-8");
        char c = (char) command[0];
        if (c == 'O' && command[1] == '1') {
            if (checklength(c, length)) {        //处理命令长度错误
                open_s();
            }
        } else if (c == 'V') {
            if (checklength(c, length)) {        //处理命令长度错误
//	          System.out.println("SV2.5-HV2.0");
                SerialTool.sendToPort(port, "SV2.5-HV2.0\r".getBytes());
            }
        } else if (c == 'C') {
            if (checklength(c, length)) {        //处理命令长度错误
                close_s();
            }
        } else if (c == 'S') {
            if (checklength(c, length)) {        //处理命令长度错误
                Changespeed((char) command[1]);
            }
        } else if (c == 't') {
            Sendstandardframe(temp);
        } else if (c == 'T') {
            Sendexternalframe(temp);
        } else {
            fail();
        }
    }

    public void open_s() {
        if (!flag) {
            flag = true;
            //返回成功
            success();
            return;
        }
        fail();
    }

    public void close_s() {
        if (flag) {
            flag = false;
            //返回成功
            success();
            return;
        }
        fail();
    }

    public void Changespeed(char NO_speed) {
        //  Serial.println(NO_speed);
        if (flag) {
            int num[] = {10, 20, 50, 100, 125, 250, 500, 800, 1000};
            if (NO_speed < '0' || NO_speed > '8') {//返回错误
                fail();
            } else {
                int pos_speed = (int) (NO_speed - '0');
                speed = num[pos_speed];
                System.out.println("速度改变为：" + speed);
                //返回成功
                success();
            }
        } else {
            fail();
        }
    }

    public void Sendstandardframe(String standardframe) {
        //  Serial.println(standardframe);
        if (Checkframe(standardframe, 1)) {
            System.out.println("向CAN总线发送该标准帧");
            success();
        } else
            fail();


    }

    public void Sendexternalframe(String externalframe) {
        //  Serial.println(externalframe);
        if (Checkframe(externalframe, 0)) {
            System.out.println("向CAN总线发送该扩展帧");
            success();
        } else
            fail();


    }

    public void success() {
        System.out.print("success");
        String temp = "success" + (char) 0x07;
        try {
            SerialTool.sendToPort(port, temp.getBytes());
        } catch (SendDataToSerialPortFailure | SerialPortOutputStreamCloseFailure e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void fail() {
        System.out.print("fail");
        String temp = "fail" + (char) 0x07;
//		try {
//			SerialTool.sendToPort(port, temp.getBytes());
//		} catch (SendDataToSerialPortFailure | SerialPortOutputStreamCloseFailure e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }

    public boolean Checkframe(String frame, int n) {//n=1，标准帧；n=0，扩展帧
        int idlen = 0;
        if (n == 1) {
            idlen = 3;
        } else if (n == 0) {
            idlen = 8;
        }
        if (frame.length() < 6 + idlen)//标准帧和扩展帧至少6+idlen（id，t/T，数据长度，周期）
            return false;
        String id = frame.substring(1, 1 + idlen);
        int length_f = Integer.parseInt(frame.substring(1 + idlen, 2 + idlen));
        if (length_f < 0 || length_f > 8)
            return false;
        if (frame.length() != (idlen + 6 + length_f * 2)) {
            return false;
        }
        String data = frame.substring(2 + idlen, length_f * 2 + idlen + 2);
        String period = frame.substring(length_f * 2 + idlen + 2, length_f * 2 + idlen + 6);

        //标准帧id[0]在0-0x7FF之中,扩展帧在（00000000-1FFFFFFF) 之中
        if (n == 1 && (id.charAt(0) >= '8' || id.charAt(0) < '0')) {
            return false;
        } else if (n == 1 && (id.charAt(0) != '0' && id.charAt(0) != '1')) {
            return false;
        }
        for (int i = 1; i < idlen; i++) {
            if ((id.charAt(i) > 'F' || id.charAt(i) < '0') || (id.charAt(i) < 'A' && id.charAt(i) > '9')) {
                return false;
            }
        }
        //标准帧data和周期在'0'-'F'之间
        for (int i = 2 + idlen; i < length_f * 2 + idlen + 6; i++) {
            if (frame.charAt(i) > 'F' || frame.charAt(i) < '0' || (frame.charAt(i) < 'A' && frame.charAt(i) > '9')) {
                return false;
            }
        }
        return true;
    }

    //检查命令长度
    public boolean checklength(char NO, int len) {
        boolean result = false;
        if ((NO == 'C' || NO == 'V') && len == 1) {
            result = true;
        } else if ((NO == 'O' || NO == 'S') && len == 2) {
            result = true;
        } else {
            fail();
        }
        return result;
    }

    public void sendframetoAPP() {
//将txt中的数据读出来准备发送给APP用于通信
        SerialPort serialPort = SerialTool.openPort("COM12", 115200);
        CanTool tool = new CanTool(serialPort);
        SerialListener listener = new SerialListener(serialPort, tool);
        tool.addListener(listener);
        InputStream in1 = new FileInputStream(new File("test1.txt"));
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(in1);
        Thread thread = new Thread();
        String tempString;
        int time;

        while (scan.hasNext()) {

                for (int i = 0; i < 200; i++) {
                    tempString = scan1.next() + "\r";
                    SerialTool.sendToPort(serialPort, tempString.getBytes());
                    Thread.sleep(20);

            }

        }

      //打开串口用于和APP通信，发送读取出来的txt数据



    }

}





