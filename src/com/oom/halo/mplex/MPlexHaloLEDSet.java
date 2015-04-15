package com.oom.halo.mplex;

import com.oom.halo.MPlexConfigMatrix;
import com.oom.halo.MPlexHaloConstant;


public class MPlexHaloLEDSet {

	MPlexGPIOPin anode4x[] = null;
	MPlexConfigMatrix binaryLED_Matrix = null;
	MPlexGPIOPin cathode = null;
	int setIndex = -1;
	
	public MPlexHaloLEDSet(MPlexGPIOPin[] anode4x, 
			MPlexGPIOPin cathode,
			int setIndex,
			MPlexConfigMatrix binaryLED_Matrix
			){
		this.anode4x = anode4x;
		this.cathode = cathode;
		this.setIndex = setIndex;
		this.binaryLED_Matrix = binaryLED_Matrix;
	}
	 
	int cycleCount=0;
	public void cycle() throws InterruptedException{
		MPlexGPIOPin anodeInON_Cycle=null;
		
		if(binaryLED_Matrix.isON(setIndex, cycleCount,anode4x.length)){
			anodeInON_Cycle =anode4x[cycleCount];
			anodeInON_Cycle.HIGH();
			cathode.LOW();// should be low for currrent to flow.
		}
		
		/*for (int i = 0; i < anode4x.length; i++) {
			if(i==cycleCount &&  binaryLED_Matrix.isON(setIndex, i,anode4x.length)
					//binaryLED4x4_Matrix[i+(setIndex*anode4x.length)]==1
					){
				
				anodeInON_Cycle =anode4x[i];
				if(MPlexHaloConstant.DEBUG_ON){
					System.out.print("LED ON\t");
				}
				anodeInON_Cycle.HIGH();
				cathode.LOW();// should be low for currrent to flow.
				if(MPlexHaloConstant.DEBUG_ON){
					System.out.println();
				}
			}
		}*/
		cycleCount++;
		if(cycleCount>=anode4x.length) cycleCount=0;
		
		
		
		waitForPulseEnd();
		if(anodeInON_Cycle!=null) {
			
			if(MPlexHaloConstant.DEBUG_ON){
				System.out.print("LED OFF\t");
			}
			anodeInON_Cycle.LOW();
			cathode.HIGH();
			if(MPlexHaloConstant.DEBUG_ON){
				System.out.println();
			}
		}
	}
	
	
	/*public void setLEDStatus(int binaryLED4x4_Matrix[]){
		if(binaryLED4x4_Matrix.length!=anode4x.length){
			throw new RuntimeException("Invalid Config, array size");
		}
		this.binaryLED4x4_Matrix= binaryLED4x4_Matrix; 
	}*/
	
	long lastPulseMilSec= System.currentTimeMillis();
	public void waitForPulseEnd() throws InterruptedException{
		long nowPulse = System.currentTimeMillis();
		long pulseWait = MPlexHaloConstant.PULSE_LENGTH_MSEC-(nowPulse-lastPulseMilSec);
		if(pulseWait>0)
		{
			if(MPlexHaloConstant.DEBUG_ON){
				System.out.println("Pulse Sleep:" + pulseWait);
			}
			Thread.sleep(pulseWait);
			//System.out.println("Sleep: Over");
			//System.out.println("Sleep:" + cycleWait);
		}
		lastPulseMilSec= nowPulse;
	}
	
	/*public void setBinaryStringSwitch(String switchBinary){
		System.out.println(switchBinary);
		char[] binArray = switchBinary.toCharArray();
		for (int i = 0; i < anode4x.length; i++) {
			if(i<binArray.length && binArray[i] == '1'){
				anode4x[i].state_ON=true;
				anode4x[i].HIGH();
			}else{
				anode4x[i].state_ON=false;
				anode4x[i].LOW();
			}
		}
	}
	
	public void setBinaryArraySwitch(boolean switchBinary[]){
		//char[] binArray = switchBinary.toCharArray();
		for (int i = 0; i < anode4x.length; i++) {
			if(i<switchBinary.length && switchBinary[i]){
				anode4x[i].state_ON=true;
				anode4x[i].HIGH();
			}else{
				anode4x[i].state_ON=false;
				anode4x[i].LOW();
			}
		}
	}*/
	
	public void switchAllOff(){
		for (int i = 0; i < anode4x.length; i++) {
			anode4x[i].LOW();
		}
		this.cathode.HIGH();
	}
}
