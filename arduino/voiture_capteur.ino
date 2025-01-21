#define NOTE_B0  31
#define NOTE_C1  33
#define NOTE_CS1 35
#define NOTE_D1  37
#define NOTE_DS1 39
#define NOTE_E1  41
#define NOTE_F1  44
#define NOTE_FS1 46
#define NOTE_G1  49
#define NOTE_GS1 52
#define NOTE_A1  55
#define NOTE_AS1 58
#define NOTE_B1  62
#define NOTE_C2  65
#define NOTE_CS2 69
#define NOTE_D2  73
#define NOTE_DS2 78
#define NOTE_E2  82
#define NOTE_F2  87
#define NOTE_FS2 93
#define NOTE_G2  98
#define NOTE_GS2 104
#define NOTE_A2  110
#define NOTE_AS2 117
#define NOTE_B2  123
#define NOTE_C3  131
#define NOTE_CS3 139
#define NOTE_D3  147
#define NOTE_DS3 156
#define NOTE_E3  165
#define NOTE_F3  175
#define NOTE_FS3 185
#define NOTE_G3  196
#define NOTE_GS3 208
#define NOTE_A3  220
#define NOTE_AS3 233
#define NOTE_B3  247
#define NOTE_C4  262
#define NOTE_CS4 277
#define NOTE_D4  294
#define NOTE_DS4 311
#define NOTE_E4  330
#define NOTE_F4  349
#define NOTE_FS4 370
#define NOTE_G4  392
#define NOTE_GS4 415
#define NOTE_A4  440
#define NOTE_AS4 466
#define NOTE_B4  494
#define NOTE_C5  523
#define NOTE_CS5 554
#define NOTE_D5  587
#define NOTE_DS5 622
#define NOTE_E5  659
#define NOTE_F5  698
#define NOTE_FS5 740
#define NOTE_G5  784
#define NOTE_GS5 831
#define NOTE_A5  880
#define NOTE_AS5 932
#define NOTE_B5  988
#define NOTE_C6  1047
#define NOTE_CS6 1109
#define NOTE_D6  1175
#define NOTE_DS6 1245
#define NOTE_E6  1319
#define NOTE_F6  1397
#define NOTE_FS6 1480
#define NOTE_G6  1568
#define NOTE_GS6 1661
#define NOTE_A6  1760
#define NOTE_AS6 1865
#define NOTE_B6  1976
#define NOTE_C7  2093
#define NOTE_CS7 2217
#define NOTE_D7  2349
#define NOTE_DS7 2489
#define NOTE_E7  2637
#define NOTE_F7  2794
#define NOTE_FS7 2960
#define NOTE_G7  3136
#define NOTE_GS7 3322
#define NOTE_A7  3520
#define NOTE_AS7 3729
#define NOTE_B7  3951
#define NOTE_C8  4186
#define NOTE_CS8 4435
#define NOTE_D8  4699
#define NOTE_DS8 4978

#include <Wire.h>
#include "rgb_lcd.h"

const int VER = A1;
const int HOR = A0;
const int SEL = A3;
const int trigger = 4;
const int echo = 2; 

// Broches pour le moteur B
const int motorB_IN3 = 10; // Direction 1
const int motorB_IN4 = 11; // Direction 2
const int motorB_ENB = 9;  // Contrôle vitesse (PWM)

rgb_lcd lcd;

// Vitesse du son en m/s
const float SOUND_SPEED = 340.0; 

void setup() {
  pinMode(VER, INPUT);
  pinMode(HOR, INPUT);
  pinMode(SEL, INPUT);
  pinMode(trigger, OUTPUT);  //lancer le capteur
  pinMode(echo, INPUT);      //temps de distance

  pinMode(motorB_IN3, OUTPUT);
  pinMode(motorB_IN4, OUTPUT);
  pinMode(motorB_ENB, OUTPUT);

  Serial.begin(9600);
  lcd.begin(16, 2);
  lcd.setRGB(100, 100, 100);
}

void loop() {  
  float joyVer = analogRead(VER);
  float joyHor = analogRead(HOR);
  int joySel = digitalRead(SEL);

  // Mesure de la distance
  digitalWrite(trigger, HIGH);
  delay(100);
  digitalWrite(trigger, LOW);
  int pulse = pulseIn(echo, HIGH);
  float dist = 0.5 * SOUND_SPEED * pulse / 10000.0;

  // Si obstacle trop proche
  if (dist < 5) {
    lcd.clear();
    lcd.setRGB(255, 0, 0);
    lcd.setCursor(0, 0);
    lcd.print("Danger trop pres");
    lcd.setCursor(5, 1);
    lcd.print(dist);
    lcd.print(" cm");
    int noteDuration = 1000 / 8;
    tone(8, NOTE_D7, noteDuration);
    delay(noteDuration);
    noTone(8);
  }
  // Joystick à droite
  else if (joyHor > 750) {
    lcd.clear();
    lcd.setRGB(255, 80, 0);
    lcd.setCursor(0, 0);
    lcd.print("           ---->");
    lcd.setCursor(0, 1);
    lcd.print("           ---->");
    int noteDuration = 1000 / 6;
    tone(8, NOTE_C3, noteDuration);
    delay(noteDuration + 200);
    noTone(8);
    tone(8, NOTE_C4, noteDuration);
    delay(noteDuration);
    noTone(8);
  }
  // Joystick à gauche
  else if (joyHor < 250) {
    lcd.clear();
    lcd.setRGB(255, 80, 0);
    lcd.setCursor(0, 0);
    lcd.print("<----");
    lcd.setCursor(0, 1);
    lcd.print("<----");
    int noteDuration = 1000 / 6;
    tone(8, NOTE_C3, noteDuration);
    delay(noteDuration + 200);
    noTone(8);
    tone(8, NOTE_C4, noteDuration);
    delay(noteDuration);
    noTone(8);
  }
  // Joystick en haut
  else if (joyVer < 400) {
    lcd.clear();
    lcd.print("^^^^^^^^^^^^^^^^");
    lcd.setCursor(0, 1);
    lcd.print("||||||||||||||||");
    digitalWrite(motorB_IN3, LOW);
    digitalWrite(motorB_IN4, HIGH);
    analogWrite(motorB_ENB, 150);
    while (analogRead(VER) < 400) {
      // Attente
    }
    digitalWrite(motorB_IN3, LOW);
    digitalWrite(motorB_IN4, LOW);
    analogWrite(motorB_ENB, 0);
  }
  // Joystick en bas
  else if (joyVer > 750) {
    lcd.clear();
    lcd.setRGB(255, 40, 0);
    lcd.setCursor(0, 0);
    lcd.print("||||||||||||||||");
    lcd.setCursor(0, 1);
    lcd.print("vvvvvvvvvvvvvvvv");
    int noteDuration = 1000 / 2;
    tone(8, NOTE_D7, noteDuration);
    delay(noteDuration + 300);
    noTone(8);
  }
  // Si le bouton est pressé
  if (joySel == 0) {
    lcd.clear();
    lcd.setCursor(2, 0);
    lcd.print("TUT TUUUUUTT");        
    lcd.setCursor(2, 1);
    lcd.print("BIP BIIIIIPP");
    int noteDuration = 1000 / 6;
    tone(8, NOTE_C6, noteDuration);
    delay(noteDuration + 200);
    noTone(8);
  }

  delay(1);
}
