package com.oom.halo.mplex;

import com.oom.halo.MPlexHaloConstant;



public class MPlexHaloLEDController extends Thread{

	MPlexHaloLEDSet[] pwmPinSets = null;
	//int cycleLength = 5;
	//int cycleLengthForHigh = 75;
	boolean exitController = false;
	public MPlexHaloLEDController(MPlexHaloLEDSet[] pwmPinSets){
		this.pwmPinSets = pwmPinSets;
		this.start();
	}
	
	@Override
	public void run() {
		while(!exitController){
			try {
				if(MPlexHaloConstant.DEBUG_ON){
					System.out.println();
					System.out.println("+++++++++++++++++++++++++++++++++++");
				}
				
				for (int i = 0; i < pwmPinSets.length; i++) {
					pwmPinSets[i].cycle();
				}
				waitForCycleEnd();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		//set all off to shutdown
		for (int i = 0; i < pwmPinSets.length; i++) {
			pwmPinSets[i].switchAllOff();
		}
	}
	
	public void shutdown(){
		exitController= true;
		try {
			this.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	
	long lastCycleMilSec= System.currentTimeMillis();
	public void waitForCycleEnd() throws InterruptedException{
		long nowCycle = System.currentTimeMillis();
		long cycleWait = MPlexHaloConstant.CYCLE_LENGTH_MSEC-(nowCycle-lastCycleMilSec);
		if(cycleWait>0)
		{
			if(MPlexHaloConstant.DEBUG_ON){ 
				System.out.println("Cycle Sleep:" + cycleWait);
			}
			Thread.sleep(cycleWait);
			//System.out.println("Sleep:" + cycleWait);
		}
		lastCycleMilSec= nowCycle;
	}
}
