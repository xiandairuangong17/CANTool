package cantool;

import gnu.io.SerialPort;
import serialPort.SerialTool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by 王师傅 on 2017/10/26.
 */
public class SendMessage {
    public static Scanner InOut(String filepath) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File(filepath));
        Scanner scan = new Scanner(inputStream);
        return scan;
    }
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        SerialPort port = SerialTool.openPort("COM3", 115200);
        CanTool tool = new CanTool(port);
        SerialListener listener = new SerialListener(port,tool);
        tool.addListener(listener);
        Scanner scan1=InOut("id800.txt");
        Scanner scan2=InOut("id837.txt");
        Scanner scan3=InOut("id856.txt");
        Scanner scan4=InOut("id864.txt");
        Scanner scan5=InOut("id915.txt");
        Scanner scan6=InOut("id100.txt");
        Scanner scan7=InOut("id273.txt");
        Scanner scan8=InOut("id1020.txt");
        Scanner scan9=InOut("id800.txt");
        Scanner scan0=InOut("id837.txt");
        Scanner scan = new Scanner(System.in);
        Thread thread = new Thread();
        String tempString;
        while(scan.hasNext())
        {
            int temp = scan.nextInt();
            if(temp==1)//周期发送id800的信息
            {
                for(int i=0;i<120;i++)
                {
                    tempString=scan1.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==2)//周期发送id837的信息
            {
                for(int i=0;i<133;i++)
                {
                    tempString=scan2.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==3)//周期发送id856的信息
            {
                for(int i=0;i<135;i++)
                {
                    tempString=scan3.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==4)//周期发送id864的信息
            {
                for(int i=0;i<153;i++)
                {
                    tempString=scan4.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==5)//周期发送id915的信息
            {
                for(int i=0;i<187;i++)
                {
                    tempString=scan5.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==6)//周期发送id100的信息
            {
                for(int i=0;i<171;i++)
                {
                    tempString=scan6.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==7)//周期发送id273的信息
            {
                for(int i=0;i<88;i++)
                {
                    tempString=scan7.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==8)//周期发送id1020的信息
            {
                for(int i=0;i<56;i++)
                {
                    tempString=scan8.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(500);//交出线程占用CPU时间500毫秒
                }
            }
            else if(temp==9)//随机时间间隔发送数据
            {
                for(int i=0;i<120;i++)
                {
                    int time=(int)(Math.random()*500);//输入随机数
                    tempString=scan9.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(time);
                }
            }
            else if(temp==0)//快速发送数据
            {
                for(int i=0;i<133;i++)
                {
                    tempString=scan0.next()+"\r";
                    SerialTool.sendToPort(port, tempString.getBytes());
                    thread.sleep(60);
                }
            }
        }

    }
}
