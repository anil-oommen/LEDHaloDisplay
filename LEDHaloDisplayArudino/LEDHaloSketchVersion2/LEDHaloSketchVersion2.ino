
int cathode_1 = 7;  // GPIO-25 , LED Halo
int cathode_2 = 5;  // GPIO-22 , LED Halo
int cathode_3 = 4;  // GPIO-23 , LED Halo
int cathode_4 = 6;  // GPIO-24 , LED Halo

int anode_1 = 11;   // GPIO-29 , LED Halo
int anode_2 = 10;   // GPIO-28 , LED Halo
int anode_3 = 9;    // GPIO-27 , LED Halo
int anode_4 = 8;    // GPIO-26 , LED Halo

int _4anode_4cathode[] = {
  anode_1,    anode_2,   anode_3,   anode_4,
  cathode_1,  cathode_2, cathode_3, cathode_4
};

unsigned long timeDataSetLastMove = 0;
unsigned long DATASET_MOVE_DELAY = 50;   // Data Set move millisec


int currentDataSet = 0;  //current Row in ledArray
int DATASET_SIZE = 16;      //no of rows in ledArray, ensure it is same as number of lines in ledArray.
int ledAnimeDataSet[][16] = {
  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
  {1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
  {1,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
  {1,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
  {1,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
  {1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
  {1,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
  {1,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
  {1,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1},
  {1,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
  {1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1},
  {1,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
  {1,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
  {1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
  {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
};

int nextAddSet =0;
void resetLEDAnime(int setSize){
  DATASET_SIZE =setSize;
  nextAddSet = 0;
}


//
boolean RESET_DEVICE = false;
void loop() { if(RESET_DEVICE){    //do nothing
}else{ ledLoop();}}

// the setup routine runs once when you press reset:
void setup() {                
  int i=0;
  for(i = 0; i < 8; i = i + 1)
  {
    pinMode(_4anode_4cathode[i], OUTPUT);
    if(i<4){//reset anode and cathode differently
      digitalWrite(_4anode_4cathode[i], LOW);
    }else {
      digitalWrite(_4anode_4cathode[i], HIGH);
    }
  }
  Serial.begin(9600);
  Serial.println("Sketch Initilized");
  timeDataSetLastMove = millis();
}



/***************************************
 * Data Step for Animation 
 */
void dataSetStep(){
  /* DATA Set Move  >> >>  in INTERVAL */
  int timeSinceLastMove = millis() - timeDataSetLastMove;
  if(timeSinceLastMove > DATASET_MOVE_DELAY) {
    //DataSet Move
    timeDataSetLastMove = millis();
    currentDataSet = currentDataSet+1;
    if(currentDataSet>DATASET_SIZE-1){
      currentDataSet = 0 ; //reset to cycle
    }
    Serial.print("DataSet Move to : " );
    Serial.print(currentDataSet);
  }
}


boolean printOnceDoneFlag = false; 
void ledLoop(){
  
  //iterate over current Sprite 16 LED Data Set 
  int _ledIndex = 0; 
  
  for(_ledIndex=0; _ledIndex <= 16 - 1; _ledIndex=_ledIndex+1)
  {
    //Compute Anode Index
    int  anodeIdx = (((_ledIndex+1-1) % 4 )+1);
    //Compute Cathod Index ( though i had to use floor() but worked without it , 
    int cathodeIdx = ((_ledIndex+1+4-1)/4)+4;
    if(!printOnceDoneFlag){
       Serial.print(_ledIndex+1);
       Serial.print(" = anode[");
       Serial.print(anodeIdx);
       Serial.print("]    cathode[");
       Serial.print(cathodeIdx);
       Serial.print("]   LED_STATE = ");
       Serial.print(ledAnimeDataSet[currentDataSet][_ledIndex]); 
       Serial.println(" .");
       /*
       Setch Initilized : Expected OutPut with which i can compute which anode and which cathode to use to light up an LED
       1 = anode[1]    cathode[5]
       2 = anode[2]    cathode[5]
       3 = anode[3]    cathode[5]
       4 = anode[4]    cathode[5]
       5 = anode[1]    cathode[6]
       6 = anode[2]    cathode[6]
       7 = anode[3]    cathode[6]
       8 = anode[4]    cathode[6]
       9 = anode[1]    cathode[7]
       10 = anode[2]    cathode[7]
       11 = anode[3]    cathode[7]
       12 = anode[4]    cathode[7]
       13 = anode[1]    cathode[8]
       14 = anode[2]    cathode[8]
       15 = anode[3]    cathode[8]
       16 = anode[4]    cathode[8]
      */
    }
    
    if( ledAnimeDataSet[currentDataSet][_ledIndex] > 0 ){
      //lite em up
      
      digitalWrite(_4anode_4cathode[anodeIdx-1], HIGH);
      digitalWrite(_4anode_4cathode[cathodeIdx-1], LOW);
      //delay(50);
      //reset 
      digitalWrite(_4anode_4cathode[anodeIdx-1], LOW);
      digitalWrite(_4anode_4cathode[cathodeIdx-1], HIGH);
    } 
    
  }
  printOnceDoneFlag = true; 
  
  dataSetStep();
}








