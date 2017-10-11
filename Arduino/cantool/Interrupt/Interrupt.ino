boolean flag=false; //tool开启或关闭状态标志位
int S_speed=10;
boolean busy=true;//CAN总线是否忙

void setup() {
  // put your setup code here, to run once:
    Serial.begin(115200);
}
void loop() {
  // put your main code here, to run repeatedly:
}
void serialEvent()
{
     while(Serial.available() ){
      String command=Serial.readStringUntil('\r');//按照\r截取字符串
      Serial.print(command);//方便串口调试打印命令
      char c=command[0];
      if(c=='O'){
        if(checklength(c,command.length())){        //处理命令错误 
          open_s();
        }
      }else if(c=='V'){
        if(checklength(c,command.length())){        //处理命令错误 
          Serial.println("SV2.5-HV2.0");
        }
      }else if(c=='C'){
        if(checklength(c,command.length())){        //处理命令错误 
          close_s();
        }
      }else if(c=='S'){ 
        if(checklength(c,command.length())){        //处理命令错误 
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
  //返回成功
  success();
}

void close_s(){
  flag=false;
  //返回成功
  success();
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
    Serial.print(length_f);
    Serial.print(" ");
    String data=frame.substring(2+idlen,length_f*2+idlen+2);
    Serial.print(data);
    Serial.print(" ");
    String period=frame.substring(length_f*2+idlen+2,length_f*2+idlen+6);
    Serial.println(period);
//    Serial.print(" ");
    if(frame.length()!=(idlen+6+length_f*2)){
      fail();
      return false;
    }else{
      success();
      return true;
    }
  }

  boolean checklength(char NO,int len){
    boolean result=false;
    if((NO=='O' || NO=='C' || NO=='V') && len==1){
      result=true;
    }else if(NO=='S' && len==2){
      result=true;
    }else{
      fail();
    }
    return result;
  }

