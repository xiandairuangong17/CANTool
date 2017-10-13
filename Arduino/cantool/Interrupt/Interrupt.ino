#include<Math.h>

boolean flag=false; //tool开启或关闭状态标志位
int S_speed=10;
boolean busy=true;//CAN总线是否忙
long numloop=0;//周期发送一个帧，这是计数loop（）次数的标志
boolean dir=true;//发送帧的方向

void setup() {
  // put your setup code here, to run once:
    Serial.begin(115200);
    while(Serial.read()>=0){
      
    }
}
void loop() {
  // put your main code here, to run repeatedly:
  //计入装置开启状态下的loop次数，生成折线数据
  if(flag){
   if(numloop%10000==0 ){
      Serial.println(numloop/10000);
    }
    if(dir ){
      numloop++;
    }else if(!dir){
      numloop--;
    }
    if(numloop>=100000){
      dir=false;
    }
    if(numloop<=0){
      dir=true;
    }
  }
}
void serialEvent()
{
     while(Serial.available() ){
      String command=Serial.readStringUntil('\r');//按照\r截取字符串
      Serial.print(command);//方便串口调试打印命令
      char c=command[0];
      if(c=='O'&& command[1]=='1'){
        if(checklength(c,command.length())){        //处理命令长度错误 
          open_s();
        }
      }else if(c=='V'){
        if(checklength(c,command.length())){        //处理命令长度错误 
          Serial.println("SV2.5-HV2.0");
        }
      }else if(c=='C'){
        if(checklength(c,command.length())){        //处理命令长度错误 
          close_s();
        }
      }else if(c=='S'){ 
        if(checklength(c,command.length())){        //处理命令长度错误 
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
  if(!flag){
    flag=true;
    //返回成功
    success();
    return;
  }
  fail();
}

void close_s(){
  if(flag){
    flag=false;
    //返回成功
    success();
    return;
  }
  fail();
}

void Changespeed(char NO_speed){
//  Serial.println(NO_speed);
   if(flag){
    int num[]={10,20,50,100,125,250,500,800,1000};
    if(NO_speed<'0' or NO_speed>'8'){//返回错误
      fail();
    }else{
      int pos_speed=int(NO_speed-'0');
      S_speed=num[pos_speed];
      //返回成功
      success();
    }
  }else{
    fail();
  }
}

void Sendstandardframe(String standardframe){
  Serial.println(standardframe);
  if(Checkframe(standardframe,1)){
    Serial.println("向CAN总线发送该标准帧"); 
    success();
  }else
    fail();
}
void Sendexternalframe(String externalframe){
  Serial.println(externalframe);
  if(Checkframe(externalframe,0)){
    Serial.println("向CAN总线发送该扩展帧"); 
    success();
  }else
    fail();
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
  int idlen;
  if(n==1){
    idlen=3;
  }else if(n==0){
    idlen=8;
  }
    Serial.print("standard:");
    String id=frame.substring(1,1+idlen);
    Serial.print(id);
    Serial.print(" ");
    int length_f=frame.substring(1+idlen,2+idlen).toInt();
    if(length_f<0 || length_f>8)
      return false;
    Serial.print(length_f);
    Serial.print(" ");
    String data=frame.substring(2+idlen,length_f*2+idlen+2);
    Serial.print(data);
    Serial.print(" ");
    String period=frame.substring(length_f*2+idlen+2,length_f*2+idlen+6);
    Serial.println(period);
    float timep=0;    char temp='0';
    //标准帧id[0]在0-0x7FF之中,扩展帧在（00000000-1FFFFFFF) 之中
    if(n==1 && (id[0]>='8' || id[0]<'0')){
        return false;
    }else if(n==1 && (id[0]!='0' && id[0]!='1')){
        return false;
    }
    for(int i=1;i<idlen;i++){
      if((id[i]>'F' || id[i]<'0') || (id[i]<'A' && id[i]>'9')){
        return false;
      }
    }
    //标准帧data和周期在'0'-'F'之间
    for(int i=2+idlen;i<length_f*2+idlen+6;i++){
      if(frame[i]>'F' || frame[i]<'0' || (frame[i]<'A' && frame[i]>'9')){
        return false;        
      }
    }
    if(frame.length()!=(idlen+6+length_f*2)){
      return false;
    }else{
          //计算该帧的发送周期
      for(int i=0;i<4;i++){
        float temp16=pow(16,(3-i));
        if(period[i]>='0' && period[i]<='9'){
          temp='0';
          int tempchar=period[i]-temp;
          timep=timep+temp16*tempchar;
          }
        else{
          temp='A'; 
          int tempchar=period[i]-temp+10;
          timep=timep+temp16*tempchar;
          }
      }
      Serial.println(timep); 
      return true;
    }
  }

  boolean checklength(char NO,int len){
    boolean result=false;
    if(( NO=='C' || NO=='V') && len==1){
      result=true;
    }else if((NO=='O' || NO=='S') && len==2){
      result=true;
    }else{
      fail();
    }
    return result;
  }
/**
 * 按周期生成标准帧和扩展帧发送给App
 */
  void C_sframe(){
    
  }
  void C_eframe(){
    
  }

