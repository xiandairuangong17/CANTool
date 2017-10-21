package cantool;

/**
 * Created by 王师傅 on 2017/10/19.
 */
import serialPort.SerialTool;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import gnu.io.SerialPort;

public class Test {//从txt文件中读取信息
 public static Scanner InOut(String filepath) throws FileNotFoundException {
     InputStream inputStream = new FileInputStream(new File(filepath));
     Scanner scan = new Scanner(inputStream);
     return scan;
}
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        SerialPort port = SerialTool.openPort("COM12", 115200);
        CanTool tool = new CanTool(port);
        SerialListener listener = new SerialListener(port,tool);
        tool.addListener(listener);
        Scanner scan1=InOut("data1.txt");
        Scanner scan2=InOut("data2.txt");
        Scanner scan3=InOut("data3.txt");
        Scanner scan4=InOut("data4.txt");
        Scanner scan5=InOut("data5.txt");
        Scanner scan = new Scanner(System.in);
        Thread thread = new Thread();
        String tempString;
        int time;
        //1固定时间间隔，2随机时间间隔，3快速，4单信息测试曲线,5多格式数据测试
        while(scan.hasNext())
        {
            int temp = scan.nextInt();
            if(temp==1)
            {
                for(int i=0;i<1000;i++)
                {
                    tempString=scan1.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(1000);//交出线程占用CPU时间一秒钟
                }
            }
            else if(temp==2)
            {
                for(int i=0;i<200;i++)
                {
                    time=scan2.nextInt();//输入随机数
                    tempString=scan2.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(time);
                }
            }
            else if(temp==3)
            {
                for(int i=0;i<600;i++)
                {
                    tempString=scan3.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(60);
                }
            }
            else if(temp==4)
            {
                for(int i=0;i<1000;i++)
                {
                    tempString=scan4.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(100);
                }
            }
            else if(temp==5)
            {
                for(int i=0;i<1000;i++)
                {
                    tempString=scan5.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(100);
                }
            }
        }

    }

}
