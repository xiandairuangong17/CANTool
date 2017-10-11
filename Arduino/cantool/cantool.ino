//#include<Math.h>

boolean flag=false; //设置tool开启或关闭状态标志位
int S_speed=10;
boolean busy=true;//CAN总线是否忙

void blink_p(String period){
    digitalWrite(LED_BUILTIN, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(period.toInt()/2);                       // wait for a second
    digitalWrite(LED_BUILTIN, LOW);    // turn the LED off by making the voltage LOW
//    delay(period.toInt()/2);                       // wait for a second
}

void setup() {
  // put your setup code here, to run once:
  pinMode(LED_BUILTIN, OUTPUT);
  Serial.begin(115200);
  Serial.println("success");
}
/**
 * 有一个问题仍然是\r，电脑端传送过来的数据是将\r作为回车符
 */
void loop() {
  // put your main code here, to run repeatedly:
    if(Serial.available() ){
      String command=Serial.readStringUntil('\r');//按照\r截取字符串
      Serial.print(command);//方便串口调试打印命令
      char c=command[0];
      if(c=='O'){
        //处理命令错误 
        if(command.length()!=1){
          fail();
        }else{
          open_s();
        }
      }else if(c=='V'){
        //处理命令错误 
        if(command.length()!=1){
          fail();
        }else{
          Serial.println("SV2.5-HV2.0");
        }
      }else if(c=='C'){
        //处理命令错误 
        if(command.length()!=1){
          fail();
        }else{
          close_s();
        }
      }else if(c=='S'){
        //处理命令错误 
        if(command.length()!=2){
          fail();
        }else{
          Changespeed(command[1]);
        }
      }else if(c=='t' && busy){
        Sendstandardframe(command);
      }else if(c=='T' && busy){
        Sendexternalframe(command);
      }else{
        fail();
      }
  }
}

void open_s(){
  flag=true;
  //返回成功信息
  success();
}

void close_s(){
  flag=false;
  //返回成功信息
  success();
}

void Changespeed(char NO_speed){
//  Serial.println(NO_speed);
   if(flag){
    int num[]={10,20,50,100,125,250,500,800,1000};
    if(NO_speed<'0' or NO_speed>'8'){//返回错误信息
      fail();
    }else{
      int pos_speed=int(NO_speed-'0');
      S_speed=num[pos_speed];
      //返回成功信息
      success();
    }
  }else{
    fail();
  }
}
/**
 * 未完成
 */
void Sendstandardframe(String standardframe){
  Serial.println(standardframe);
  if(Checkframe(standardframe,1)){
    Serial.println("发送该标准帧"); 
  }
}
void Sendexternalframe(String externalframe){
  Serial.println(externalframe);
  if(Checkframe(externalframe,0)){
    Serial.println("发送该扩展帧"); 
  }
//  Serial.println(standardframe);//打印标准帧
}

void success(){
  Serial.print("success");
  Serial.print("\r");
}

void fail(){
  Serial.print("fail");
  Serial.print(char(0x07));
}

boolean Checkframe(String frame,int n){//n=1，标准帧；n=0，扩展帧
  if(n==1){
    Serial.print("standard:");
    String id=frame.substring(1,4);
    Serial.print(id);
    Serial.print(" ");
    int length_f=frame.substring(4,5).toInt();
    Serial.print(length_f);
    Serial.print(" ");
    String data=frame.substring(5,length_f*2+5);
    Serial.print(data);
    Serial.print(" ");
    String period=frame.substring(length_f*2+5,length_f*2+9);
    Serial.println(period);
//    Serial.print(" ");
    if(frame.length()!=(9+length_f*2)){
      return false;
    }else{
      return true;
    }
  }else if(n==0){
    Serial.print("external:");
    String id=frame.substring(1,9);
    Serial.print(id);
    Serial.print(" ");
    int length_f=frame.substring(9,10).toInt();
    Serial.print(length_f);
    Serial.print(" ");
    String data=frame.substring(10,length_f*2+10);
    Serial.print(data);
    Serial.print(" ");
    String period=frame.substring(length_f*2+10,length_f*2+14);
    Serial.println(period);
//    Serial.print(" ");
    if(frame.length()!=(14+length_f*2)){
      return false;
    }else{
      return true;
    }
  }
}



