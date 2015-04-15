package com.oom.halo.mplex;

import com.oom.halo.MPlexHaloConstant;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class MPlexGPIOPin {

	com.pi4j.io.gpio.GpioPinDigitalOutput pinOut = null;
	public boolean state_ON = false;
	String pinType =""; 
	public MPlexGPIOPin(GpioController gpio,Pin pin,PinState pinState){
		pinOut = 
	        		gpio.provisionDigitalOutputPin(pin, 
	        				"GPIOPin_" +  pin.getAddress(), 
	        				pinState);
		
		if(pinState.equals(PinState.HIGH)) pinType="_cathode(-)_";
		else pinType="_anode(+)_";
			
	}
	public void HIGH(){
		if(MPlexHaloConstant.DEBUG_ON){
			System.out.print(pinOut.getName() + pinType +":HIGH\t");
		}
		pinOut.high();
	}
	
	public void LOW(){
		//System.out.println("OFF======================");
		if(MPlexHaloConstant.DEBUG_ON){
			System.out.print(pinOut.getName()  + pinType+":LOW\t");
		}
		pinOut.low();
	}
	
}
