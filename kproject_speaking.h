class kproject_speaking
{
  private:
  public:
    kproject_speaking(Stream &stream);
    Stream *_stream;
    void begin();
    void speak_naver(char *string);
    void speak_naver(char *string, int8_t language, int8_t speed, int8_t speaker);
    void speak_baidu(char *string, int8_t language, int8_t speed);
    void speak_baidu(char *string);
    void speak_kakao(char *string, int8_t language);
    void speak_kakao(char *string);
    void set_wifi(char *ssid, char *password);
    void set_volume(int volume);
    bool is_ready();
    bool wifi_connected();
};

bool kproject_speaking::wifi_connected()
{
  while( _stream->available() ) _stream->read();
  _stream->write(20);
  _stream->write(11);
  _stream->write(21);
  unsigned long start_millis = millis();
  while( ( millis() - start_millis ) < 200 )
  {
    if ( _stream->available() >= 2 )
    {
      if ( _stream->read() == 20 )
      {
        if ( _stream->read() == 20 )
        {
          return true;
        }
        else
        {
          return false;
        }
      }
    }
  }
  return false;
}


bool kproject_speaking::is_ready()
{
  while( _stream->available() ) _stream->read();
  unsigned long start_millis = millis();
  while( ( millis() - start_millis ) < 200 )
  {
    if ( _stream->available() >= 3 )
    {
      if ( _stream->read() == 20 )
      {
        if ( _stream->read() == 1 )
        {
          if ( _stream->read() == 21 )
          {
            return true;
          }
        }
      }
    }
  }
  return false;
}

kproject_speaking::kproject_speaking(Stream &stream)
{
  _stream = &stream;
}

void kproject_speaking::begin()
{
}

// string : 음성 출력할 문자열
// language : 0 : 한글 , 1 : 영어 , 2 : 일어, 3 : 중국어
// speed : -5~5 값이 클수록 느림
// speaker : 0 : 여성 , 1 : 남성
// streaming : 0 : streaming mode , 1 : download mode
void kproject_speaking::speak_naver(char *string, int8_t language, int8_t speed, int8_t speaker)
{
  int len = strlen(string);
  // 명령 인식 코드
  _stream->write(20);
  // COMMAND
  _stream->write(1);
  _stream->write(language);
  _stream->write(speed);
  _stream->write(speaker);
  _stream->write(1);
  _stream->write(len);
  _stream->write(len >> 8);
  delay(10);
  for ( int i = 0; i < len; i++)
  {
    _stream->write(string[i]);
  }
  _stream->write(21);
  delay(10);
}

void kproject_speaking::speak_naver(char *string)
{
  speak_naver(string, 0, 0, 0);
}

// string : 음성 출력할 문자열
// baidu_lan = 0; // 0 : kor , 1 : en , 2 : jp , 3 : zh , 4 : de
// baidu_speed = 4; // 1~7

void kproject_speaking::speak_baidu(char *string, int8_t language, int8_t speed)
{
  int len = strlen(string);
  // 명령 인식 코드
  _stream->write(20);
  // COMMAND
  _stream->write(2);
  _stream->write(language);
  _stream->write(speed);
  _stream->write(1);
  _stream->write(len);
  _stream->write(len >> 8);
  delay(10);
  for ( int i = 0; i < len; i++)
  {
    _stream->write(string[i]);
  }
  _stream->write(21);
  delay(10);
}

void kproject_speaking::speak_baidu(char *string)
{
  speak_baidu(string, 0, 4);
}

void kproject_speaking::speak_kakao(char *string, int8_t language)
{
  int len = strlen(string);
  // 명령 인식 코드
  _stream->write(20);
  // COMMAND
  _stream->write(3);
  _stream->write(language);
  _stream->write(1);
  _stream->write(len);
  _stream->write(len >> 8);
  delay(10);
  for ( int i = 0; i < len; i++)
  {
    _stream->write(string[i]);
  }
  _stream->write(21);
  delay(10);
}

void kproject_speaking::speak_kakao(char *string)
{
  speak_kakao(string, 0);
}

void kproject_speaking::set_wifi(char *ssid, char *password)
{
  char temp[20];
  char temp2[20];
  memset(temp, 0, 20);
  memset(temp2, 0, 20);
  // 명령 인식 코드
  _stream->write(20);
  // COMMAND
  _stream->write(4);
  strcpy(temp, ssid);
  strcpy(temp2, password);
  for ( int i = 0; i < 20; i++)
  {
    _stream->write(temp[i]);
  }
  for ( int i = 0; i < 20; i++)
  {
    _stream->write(temp2[i]);
  }
  _stream->write(21);
  delay(1000); // WIFI에 재연결한 경우 모듈이 리셋이 되므로 일정시간 대기해야함
}

void kproject_speaking::set_volume(int volume)
{
  _stream->write(20);
  // COMMAND
  _stream->write(10);
  _stream->write(volume);
  _stream->write(21);
  delay(10);
}
