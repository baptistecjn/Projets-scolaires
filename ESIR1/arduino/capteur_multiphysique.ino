
const int ledPIN = 13;
const int VER = A0;
const int HOR = A1;
const int SEL = A2;
const int trigger = 8;
const int echo = 7;
const int temperaturePIN = A3;
const int lumPIN = A4;



void setup() {
  pinMode(VER,INPUT);
  pinMode(HOR,INPUT);
  pinMode(SEL,INPUT);
  pinMode(trigger,OUTPUT);
  pinMode(echo,INPUT);
  pinMode(ledPIN,OUTPUT);
  pinMode(lumPIN,INPUT);
  pinMode(temperaturePIN,INPUT);

  Serial.begin(9600);



}

void loop() {
  float joyVer = analogRead(VER);

  float joyHor = analogRead(HOR);

  int joySel = digitalRead(SEL);

  if(joyHor>550){
    Serial.println(" ");
    Serial.println("-------------------jostick vers la gauche--------------------------");
    Serial.println(" ");
    
    digitalWrite(trigger,HIGH);
    delay(100);
    digitalWrite(trigger,LOW);
  
    int pulse = pulseIn(echo,HIGH);
  
    float dist = 0.5 * 340.0 * pulse / 10000.0;
    Serial.print("la distance entre le capteur et l'obstacle est de ");
    Serial.print(dist);
    Serial.println(" cm");  
  }
  if(joyHor<450){
    Serial.println(" ");
    Serial.println("-------------------jostick vers la droite--------------------------");
    Serial.println(" ");

    float lumValue = analogRead(lumPIN);
    Serial.print("valeur lumière : ");
    Serial.println(lumValue);
  }
  if(joyVer>550){
    Serial.println(" ");
    Serial.println("-------------------jostick vers le haut----------------------------");
    Serial.println(" ");

    float temperatureValue = analogRead(temperaturePIN);
    temperatureValue = ((((temperatureValue/1023)*5.)*100.)+2);

    Serial.print("La température est : ");
    Serial.print(temperatureValue);
    Serial.println("°");  
  }
  if(joyVer<450){
    Serial.println(" ");
    Serial.println("-------------------jostick vers le bas-----------------------------");
    Serial.println(" ");

    float temperatureValue = analogRead(temperaturePIN);
    temperatureValue = ((((temperatureValue/1023)*5)*100)+2);

    Serial.print("La température est : ");
    Serial.print(temperatureValue);
    Serial.println("°C");

    
    
    digitalWrite(trigger,HIGH);
    delay(100);
    digitalWrite(trigger,LOW);
  
    int pulse = pulseIn(echo,HIGH);
  
    float dist = 0.5 * 340.0 * pulse / 10000.0;
    Serial.print("la distance entre le capteur et l'obstacle est de ");
    Serial.print(dist);
    Serial.println(" cm"); 
    
  }
  

  if(joySel == 0){
    Serial.println(" ");
    Serial.println("-------------------pression sur le joystick------------------------");
    Serial.println(" ");


    float lumValue = analogRead(lumPIN);
    Serial.print("valeur lumière : ");
    Serial.println(lumValue);

    
    float temperatureValue = analogRead(temperaturePIN);
    temperatureValue = ((((temperatureValue/1023)*5)*100)+2);

    Serial.print("La température est : ");
    Serial.print(temperatureValue);
    Serial.println("°C");

    
    
    digitalWrite(trigger,HIGH);
    delay(100);
    digitalWrite(trigger,LOW);
  
    int pulse = pulseIn(echo,HIGH);
  
    float dist = 0.5 * 340.0 * pulse / 10000.0;
    Serial.print("la distance entre le capteur et l'obstacle est de ");
    Serial.print(dist);
    Serial.println(" cm"); 
    
    
  }

  delay(500);
}
