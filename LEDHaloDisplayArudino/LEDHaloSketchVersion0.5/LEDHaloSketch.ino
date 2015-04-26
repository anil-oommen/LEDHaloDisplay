
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
unsigned long DATASET_MOVE_DELAY = 1000;   // Data Set move millisec


int currentDataSet = 0;  //current Row in ledArray
int DATASET_SIZE = 1;      //no of rows in ledArray, ensure it is same as number of lines in ledArray.
int ledArray[][8] = {

  // {1,1,1,0,1,1,0,1},

  {1,1,0,0,0,0,1,1      }
  /*,
  {0,1,0,0,1,0,1,1      },
  {0,0,1,0,1,0,1,1      },
  {0,0,0,1,1,0,1,1      },
  {1,0,0,0,1,1,1,0      },
  {0,1,0,0,1,1,1,0      },
  {0,0,1,0,1,1,1,0      },
  {0,0,0,1,1,1,1,0      },
  {1,0,0,0,1,1,0,1      },
  {0,1,0,0,1,1,0,1      },
  {0,0,1,0,1,1,0,1      },
  {0,0,0,1,1,1,0,1      },
  {1,0,0,0,0,1,1,1      },
  {0,1,0,0,0,1,1,1      },
  {0,0,1,0,0,1,1,1      },
  {0,0,0,1,0,1,1,1      }
  */
};


int nextAddSet =0;
void resetLED(int setSize){
  DATASET_SIZE =setSize;
  nextAddSet = 0;
}

void addDataSet( 
int p1, int p2, int p3 , int p4 , int p5, int p6, int p7, int p8,
int p9, int p10, int p11 , int p12 , int p13, int p14, int p15, int p16
){
  ledArray[nextAddSet][0] = p1 + p5 + p9 + p13;
  ledArray[nextAddSet][1] = p2 + p6 + p10 + p14;
  ledArray[nextAddSet][2] = p3 + p7 + p11 + p15;
  ledArray[nextAddSet][3] = p4 + p8 + p12 + p16;
  ledArray[nextAddSet][4] = 1 - ( p9 + p10 + p11 + p12 ) ;
  ledArray[nextAddSet][5] = 1 - ( p13 + p14 + p15 + p16 ) ;
  ledArray[nextAddSet][6] = 1 - ( p5 + p6 + p7 + p8 ) ;
  ledArray[nextAddSet][7] = 1 - ( p1 + p2 + p3 + p4 ) ;
  nextAddSet = nextAddSet +1;
  if(nextAddSet > DATASET_SIZE -1 ){
    //resetting so no overrun if caller adds!
    nextAddSet = 0;
  }
}


boolean resetDevice = false;
void loop() {
  if(resetDevice){    //do nothing
  }
  else{ 
    ledLoop();
  }
}

// the setup routine runs once when you press reset:
void setup() {                
  int i=0;
  for(i = 0; i < 8; i = i + 1)
  {
    pinMode(_4anode_4cathode[i], OUTPUT);
    digitalWrite(_4anode_4cathode[i], LOW);
  }
  Serial.begin(9600);
  
  //resetLED(1);
  //addDataSet(1,0,1,0,1,1,0,0,0,0,0,0,0,0,0,0);
  //addDataSet(0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
  
  /*
  addDataSet(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
   addDataSet(1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
   addDataSet(1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
   addDataSet(1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0);
   addDataSet(1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0);
   addDataSet(1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0);
   addDataSet(1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0);
   addDataSet(1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0);
   addDataSet(1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0);
   addDataSet(1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0);
   addDataSet(1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0);
   addDataSet(1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0);
   addDataSet(1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0);
   addDataSet(1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0);
   addDataSet(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0);
   addDataSet(1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
   */
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


    //Debug Layout 
    int pin = 0;
    for(pin=0; pin <= 8 - 1; 
       pin=pin+1)
    {  
      Serial.print(ledArray[currentDataSet][pin] );
    }
    Serial.println(" ");

  }
}



void ledLoop(){
  
  int _ianode=0;
  for(_ianode=0; _ianode <= 4 - 1; 
       _ianode=_ianode+1)
  {

   /* int i4 = 0;
    for(i4 =0;i4 <= 4 -1 ;i4 = i4 +1 ){
      if(i4==_ianode && ledArray[currentDataSet][_ianode]>0 ){
        digitalWrite(_4anode_4cathode[_ianode], HIGH);
      }
      else{
        digitalWrite(_4anode_4cathode[i4], LOW);
      }
    }
  */
    
  if(ledArray[currentDataSet][_ianode]>0){
    digitalWrite(_4anode_4cathode[_ianode], HIGH);
  }/*else{
    digitalWrite(_4anode_4cathode[_ianode], LOW);
  }*/

    int _icathode=4;
    for(_icathode=4; _icathode <= 8 - 1; 
       _icathode=_icathode+1){
        if(ledArray[currentDataSet][_icathode]>0){
        digitalWrite(_4anode_4cathode[_icathode], HIGH);
      }
      else{
        digitalWrite(_4anode_4cathode[_icathode], LOW);
      }    
    }

    //reset cathode
    for(_icathode=4; _icathode <= 8 - 1; 
       _icathode=_icathode+1){
        digitalWrite(_4anode_4cathode[_icathode], HIGH);
    }
   
   //Power down Current Anode move to next
   digitalWrite(_4anode_4cathode[_ianode], LOW);


  }

  //reset anode
  /*
  for(_ianode=0; _ianode <= 4 - 1; 
       _ianode=_ianode+1){
      digitalWrite(_4anode_4cathode[_ianode], LOW);
  }*/


  dataSetStep();
}








