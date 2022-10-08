#include "esp_camera.h"
#include <WiFi.h>

//카메라 모델 선택
#define CAMERA_MODEL_AI_THINKER
#include "camera_pins.h"

const char* ssid = "soo";
const char* password = "20001207";

void startCameraServer();          
void setup() {
  Serial.begin(115200);
  Serial.setDebugOutput(true);
  Serial.println();

  camera_config_t config;
  config.ledc_channel = LEDC_CHANNEL_0;
  config.ledc_timer = LEDC_TIMER_0;
  config.pin_d0 = Y2_GPIO_NUM;
  config.pin_d1 = Y3_GPIO_NUM;
  config.pin_d2 = Y4_GPIO_NUM;
  config.pin_d3 = Y5_GPIO_NUM;
  config.pin_d4 = Y6_GPIO_NUM;
  config.pin_d5 = Y7_GPIO_NUM;
  config.pin_d6 = Y8_GPIO_NUM;
  config.pin_d7 = Y9_GPIO_NUM;
  config.pin_xclk = XCLK_GPIO_NUM;
  config.pin_pclk = PCLK_GPIO_NUM;
  config.pin_vsync = VSYNC_GPIO_NUM;
  config.pin_href = HREF_GPIO_NUM;
  config.pin_sscb_sda = SIOD_GPIO_NUM;
  config.pin_sscb_scl = SIOC_GPIO_NUM;
  config.pin_pwdn = PWDN_GPIO_NUM;
  config.pin_reset = RESET_GPIO_NUM;
  config.xclk_freq_hz = 20000000;
  config.pixel_format = PIXFORMAT_JPEG;

 
  
  if(psramFound())    //psram이 있는 카메라를 사용하는 경우
  {
    config.frame_size = FRAMESIZE_UXGA;  //프레임 크기 설정(1600*1200)
    config.jpeg_quality = 10;    //화질 설정(0 to 63) 숫자가 낮을수록 품질이 우수
    config.fb_count = 2;      //프레임 버퍼 수
  } else 
  {
    config.frame_size = FRAMESIZE_SVGA; //프레임 사이즈(800*600)
    config.jpeg_quality = 12;
    config.fb_count = 1;
  }

#if defined(CAMERA_MODEL_ESP_EYE)
  pinMode(13, INPUT_PULLUP);
  pinMode(14, INPUT_PULLUP);
#endif

  // 카메라 초기값
  esp_err_t err = esp_camera_init(&config);
  if (err != ESP_OK)     //카메라 초기값 못 받았다면
  {
    Serial.printf("Camera init failed with error 0x%x", err);  //에러 메시지 출력
    return;
  }

  sensor_t * s = esp_camera_sensor_get();   
  if (s->id.PID == OV3660_PID)      //프로세스 id가 OV3660(이미지센서)이면
{   s->set_vflip(s, 1);             //다시 채운다
    s->set_brightness(s, 1);        //밝기 설정(-2~2)
    s->set_saturation(s, -2);        //포화도를 낮춘다
}
  s->set_framesize(s, FRAMESIZE_QVGA);

#if defined(CAMERA_MODEL_M5STACK_WIDE)
  s->set_vflip(s, 1);    //수평(가로)을 채운다(1:enable)
  s->set_hmirror(s, 1);    //수직(세로)을 채운다(1:enable)
#endif

  WiFi.begin(ssid, password);     //연결될 와이파이ssid와 비밀번호 연결 시작

  while (WiFi.status() != WL_CONNECTED) {     //연결되면
    delay(1000);
    Serial.print(".");          //대기중
  }
  Serial.println("");
  Serial.println("WiFi connected");        //wifi connected라고 출력

  startCameraServer();           //cameraServer작동 시작

  Serial.print("Camera Ready! Use 'http://");        
  Serial.print(WiFi.localIP());         //ip주소 출력
  Serial.println("' to connect");
}

void loop() {
  delay(10000);
}
