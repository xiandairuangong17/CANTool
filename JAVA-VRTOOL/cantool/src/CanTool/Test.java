package CanTool;

/**
 * Created by 鐜嬪笀鍌� on 2017/10/19.
 */
import serialPort.SerialTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import gnu.io.SerialPort;

//public class Test {//浠巘xt鏂囦欢涓鍙栦俊鎭�
// public static Scanner InOut(String filepath) throws FileNotFoundException {
//     InputStream inputStream = new FileInputStream(new File(filepath));
//     Scanner scan = new Scanner(inputStream);
//     return scan;
//}
//    public static void main(String[] args) throws Exception {
//        // TODO Auto-generated method stub
//        SerialPort port = SerialTool.openPort("COM3", 115200);
//        CanTool tool = new CanTool(port);
//        SerialListener listener = new SerialListener(port,tool);
//        tool.addListener(listener);
//        Scanner scan1=InOut("澶х搴廙essage1.txt");
//        Scanner scan2=InOut("澶х搴廙essage2.txt");
//        Scanner scan3=InOut("澶х搴廙essage3.txt");
//        Scanner scan4=InOut("澶х搴廙essage4.txt");
//        Scanner scan5=InOut("灏忕搴廙essage1.txt");
//        Scanner scan6=InOut("娴嬭瘯鐢ㄤ緥.txt");
//        Scanner scan = new Scanner(System.in);
//        Thread thread = new Thread();
//        String tempString;
//        int time;
//        //1鍥哄畾鏃堕棿闂撮殧锛�2闅忔満鏃堕棿闂撮殧锛�3蹇�燂紝4鍗曚俊鎭祴璇曟洸绾�,5澶氭牸寮忔暟鎹祴璇�
//        while(scan.hasNext())
//        {
//            int temp = scan.nextInt();
//            if(temp==1)
//            {
//                for(int i=0;i<1000;i++)
//                {
//                    tempString=scan1.next()+"\r";
//                    SerialTool.sendToPort(port, tempString.getBytes());
//                    Thread.sleep(1000);//浜ゅ嚭绾跨▼鍗犵敤CPU鏃堕棿涓�绉掗挓
//                }
//            }
//            else if(temp==2)
//            {
//                for(int i=0;i<200;i++)
//                {
//                    time=scan2.nextInt();//杈撳叆闅忔満鏁�
//                    tempString=scan2.next()+"\r";
//                    SerialTool.sendToPort(port, tempString.getBytes());
//                    Thread.sleep(time);
//                }
//            }
//            else if(temp==3)
//            {
//                for(int i=0;i<600;i++)
//                {
//                    tempString=scan3.next()+"\r";
//                    SerialTool.sendToPort(port, tempString.getBytes());
//                    Thread.sleep(60);
//                }
//            }
//            else if(temp==4)
//            {
//                for(int i=0;i<1000;i++)
//                {
//                    tempString=scan4.next()+"\r";
//                    SerialTool.sendToPort(port, tempString.getBytes());
//                    Thread.sleep(100);
//                }
//            }
//            else if(temp==5)
//            {
//                for(int i=0;i<1000;i++)
//                {
//                    tempString=scan5.next()+"\r";
//                    SerialTool.sendToPort(port, tempString.getBytes());
//                    Thread.sleep(100);
//                }
//            }
//        }
//
//    }
//
//}
public class Test {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        SerialPort serialPort = SerialTool.openPort("COM1", 115200);
        CanTool tool = new CanTool(serialPort);
        SerialListener listener = new SerialListener(serialPort,tool);
        tool.addListener(listener);
        InputStream in1 = new FileInputStream(new File("message1.txt"));
        InputStream in2 = new FileInputStream(new File("message2.txt"));
        InputStream in3 = new FileInputStream(new File("message3.txt"));
        InputStream in4 = new FileInputStream(new File("message4.txt"));
        InputStream in5 = new FileInputStream(new File("message5.txt"));
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(in1);
        Scanner scan2 = new Scanner(in2);
        Scanner scan3 = new Scanner(in3);
        Scanner scan4 = new Scanner(in4);
        Scanner scan5 = new Scanner(in5);
        Thread thread = new Thread();
        String tempString;
        int time;
        //1鍥哄畾鏃堕棿闂撮殧锛�2闅忔満鏃堕棿闂撮殧锛�3蹇�燂紝4鍗曚俊鎭祴璇曟洸绾�,5澶氭牸寮忔暟鎹祴璇�
        while(scan.hasNext())
        {
            int temp = scan.nextInt();
            if(temp==1)
            {
                for(int i=0;i<3;i++)
                {
                    tempString=scan1.next()+"\r";
                    SerialTool.sendToPort(serialPort, tempString.getBytes());
                    thread.sleep(200);
                }
            }
            else if(temp==2)
            {
                for(int i=0;i<3;i++)
                {
                    time=scan2.nextInt();
                    tempString=scan2.next()+"\r";
                    SerialTool.sendToPort(serialPort, tempString.getBytes());
                    thread.sleep(time);
                }
            }
            else if(temp==3)
            {
                for(int i=0;i<3;i++)
                {
                    tempString=scan3.next()+"\r";
                    SerialTool.sendToPort(serialPort, tempString.getBytes());
                    thread.sleep(5);
                }
            }
            else if(temp==4)
            {
                for(int i=0;i<3;i++)
                {
                    tempString=scan4.next()+"\r";
                    SerialTool.sendToPort(serialPort, tempString.getBytes());
                    thread.sleep(50);
                }
            }
            else if(temp==5)
            {
                for(int i=0;i<3;i++)
                {
                    tempString=scan5.next()+"\r";
                    SerialTool.sendToPort(serialPort, tempString.getBytes());
                    thread.sleep(100);
                }
            }
        }

    }

}