#include "kproject_speaking.h"
#include <SoftwareSerial.h>

SoftwareSerial soft(3, 2);    //2번과 3번핀 사용
kproject_speaking speaking(soft);

void setup()
{
  Serial.begin(9600);
  
  soft.begin(9600);
  speaking.begin();
  speaking.set_wifi("고유빈", "20195141"); //이름과 비번 입력 변경
}

void loop()
{
  if ( Serial.available() )
  {
    String value = Serial.readStringUntil("\n");
    Serial.println(value);
    speaking.speak_kakao(value.c_str()/*문자열*/, 0/*언어*/);
   }
}
