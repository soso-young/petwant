#include <DS1302.h>
#include <Servo.h>

const int RST = 7; // DS 1302의 핀 설정
const int DATA = 6; 
const int CLOCK = 5;

DS1302 rtc(RST, DATA, CLOCK); // DS1302 모듈을 rtc 오브젝트로 설정                                                                     

Servo servo; // 서보모터 이름 설정

int servoMotorPin = 8; // 서보모터 신호 핀 설정


void setup() {
  // rtc에 쓰기방지 OFF, 정지기능 OFF
  rtc.writeProtect(false);
  rtc.halt(false);

  // 초기 시간을 2022년 6월 3일 오후 6시 14분 금요일로 설정
  Time t(2022, 6, 3, 18, 13, 00, Time :: kFriday);
  
  // 현재 시간을 DS 1302에 입력.
  rtc.time(t);

  // 서보모터 9번 핀 사용
  servo.attach(9);
 
}

void loop() {
  // DS 1302로 부터 현재 시간을 읽어온다. 
  Time t = rtc.time();
  if(t.sec%1==0) //1초마다 반복
  {
   //서보모터 동작
   servo.write(90);   //정지 
   delay(1000);
   servo.write(80);    //역회전
   delay(1000);   
  }

}
