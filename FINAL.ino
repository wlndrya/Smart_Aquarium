// Date and time functions using a DS1307 RTC connected via I2C and Wire lib
#include <Wire.h>
#include "RTClib.h"
#include <Servo.h>
Servo myservo;

RTC_DS1307 rtc;

char daysOfTheWeek[7][12] = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
  
#include "FirebaseESP8266.h"  // Install Firebase ESP8266 library
#include <ESP8266WiFi.h>
#include <NTPClient.h>
#include <WiFiUdp.h>

#include <SoftwareSerial.h>
//SoftwareSerial s(D6,D5);
#include <ArduinoJson.h>
#include <ESP8266WiFi.h>

#define FIREBASE_HOST "smartaqua-613d4-default-rtdb.firebaseio.com" //Without http:// or https:// schemes
#define FIREBASE_AUTH "UvFIlCMHEZ2Wf6CZXySNEf03p9ch9Yd0dTmxd7bN"
#define WIFI_SSID "GAK ADA WIFI"                           //NAMA WIFI
#define WIFI_PASSWORD "pr3a9no15"                //PASS WIFI

FirebaseData firebaseData,time1,time2,time3;
FirebaseJson json;
int data;
void setup() {
  Serial.begin(9600);
//  myservo.attach(16); //D1
//  myservo.write(0);
  
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);
  Serial.print("Connecting to Wi-Fi");
  
  while (WiFi.status() != WL_CONNECTED)
  {
    Serial.print(".");
    delay(300);
  }
  Serial.println();
  Serial.print("Connected with IP: ");
  Serial.println(WiFi.localIP());
  Serial.println();

  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);
  Firebase.reconnectWiFi(true);

  //RTC
  
#ifndef ESP8266
  while (!Serial); // wait for serial port to connect. Needed for native USB
#endif

  if (! rtc.begin()) {
    Serial.println("Couldn't find RTC");
    Serial.flush();
    abort();
  }

  if (! rtc.isrunning()) {
    Serial.println("RTC is NOT running, let's set the time!");
    // When time needs to be set on a new device, or after a power loss, the
    // following line sets the RTC to the date & time this sketch was compiled
    rtc.adjust(DateTime(F(__DATE__), F(__TIME__)));
    // This line sets the RTC with an explicit date & time, for example to set
    // January 21, 2014 at 3am you would call:
    // rtc.adjust(DateTime(2014, 1, 21, 3, 0, 0));
  }
}
 
void loop() {
   DateTime now = rtc.now();

                            /////BUTTON PERTAMA/////
Firebase.getInt(time1,"/Timer/1/jam");
 int jam1=time1.intData();
// Serial.println(jam1);

 Firebase.getInt(time1,"/Timer/1/menit");
 int menit1=time1.intData();
// Serial.println(x3);

 String fixtime1 = String(jam1)+":"+String(menit1)+":0";
  Serial.println(fixtime1);
  /////////////////////////////////////////////////////
  
                            /////BUTTON KEDUA/////
Firebase.getInt(time2,"/Timer/2/jam");
 int jam2=time2.intData();
// Serial.println(jam2);

 Firebase.getInt(time2,"/Timer/2/menit");
 int menit2=time2.intData();
// Serial.println(x3);

 String fixtime2 = String(jam2)+":"+String(menit2)+":0";
  Serial.println(fixtime2);
  ///////////////////////////////////////////////////////
  
                          /////BUTTON KETIGA/////
Firebase.getInt(time3,"/Timer/3/jam");
 int jam3=time3.intData();
// Serial.println(jam3);

 Firebase.getInt(time3,"/Timer/3/menit");
 int menit3=time3.intData();
// Serial.println(x3);

 String fixtime3 = String(jam3)+":"+String(menit3)+":0";
  Serial.println(fixtime3);
  ///////////////////////////////////////////////////////

//for RTC KODING 
    String waktu = String(now.hour())+":"+String(now.minute())+":"+String(now.second());
    Serial.println(waktu);
//    Serial.println(now.second());
       
// //////////////////BUTTON PERTAMA///////////////////////  
    if(fixtime1.equals(waktu)){
       myservo.write(90);
       myservo.attach(16); //D1
      delay(2000);
       myservo.write(0);
    }
////////////////////BUTTON KEDUA//////////////////////////
if(fixtime2.equals(waktu)){
       myservo.write(90);
       myservo.attach(16); //D1
      delay(2000);
       myservo.write(0);
    }
////////////////////BUTTON KETIGA/////////////////////////
if(fixtime3.equals(waktu)){
       myservo.write(90);
       myservo.attach(16); //D1
      delay(2000);
       myservo.write(0);
    }
//////////////////////////////////////////////
      
  
//    Serial.print(now.year(), DEC);
//    Serial.print('/');
//    Serial.print(now.month(), DEC);
//    Serial.print('/');
//    Serial.print(now.day(), DEC);
//    Serial.print(" (");
//    Serial.print(daysOfTheWeek[now.dayOfTheWeek()]);
//    Serial.print(") ");
//    Serial.print(now.hour(), DEC);
//    Serial.print(':');
//    Serial.print(now.minute(), DEC);
//    Serial.print(':');
//    Serial.print(now.second(), DEC);
//    Serial.println();
//    delay(1000);

//     if (now.year() == 2021 && now.month() == 07 && now.day() == 11 && now.hour() == 11 && now.minute() == 9 && now.second()== 00){
//      myservo.write(90);
//      delay(5000);
//    }
//    else {
//      myservo.write(0);
//    }

}
