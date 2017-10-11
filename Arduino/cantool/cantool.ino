//#include<Math.h>

boolean flag=false; //设置tool开启或关闭状态标志位
int S_speed=10;
String buff=""; //设置缓冲区

void setup() {
  // put your setup code here, to run once:
  Serial.begin(115200);
  Serial.println("success");
}

void loop() {
  // put your main code here, to run repeatedly:
    if(Serial.available() ){
      buff=Serial.readString();
      Serial.print(buff);
  
      String command=buff;
      char c=command[0];
      if(c=='O'){
        //处理命令错误 
        if(command.length()!=3){
          fail();
        }else{
          open_s();
        }
      }else if(c=='V'){
        //处理命令错误 
        if(command.length()!=3){
          fail();
        }else{
          Serial.println("SV2.5-HV2.0");
        }
      }else if(c=='C'){
        //处理命令错误 
        if(command.length()!=3){
          fail();
        }else{
          close_s();
        }
      }else if(c=='S'){
        //处理命令错误 
        if(command.length()!=4){
          fail();
        }else{
          Changespeed(command[1]);
        }
      }else if(c=='t'){
        Sendstandardframe(command);
      }else if(c=='T'){
        Sendexternalframe(command);
      }else{
        fail();
      }
      buff="";//处理完该命令，清空缓存区数据
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
  Serial.println(standardframe);//打印标准帧
}
void Sendexternalframe(String externalframe){
  Serial.println(externalframe);//打印额外帧
}

void success(){
  Serial.print("\r");
}

void fail(){
  Serial.print(char(0x07));
}

