package com.oom.halo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oom.halo.mplex.MPlexHaloLEDController;
import com.oom.halo.mplex.MPlexGPIOPin;
import com.oom.halo.mplex.MPlexHaloLEDSet;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class MultiPlexedHaloLED {

public static void main(String[] args) throws IOException, InterruptedException {
		
		final GpioController gpio = GpioFactory.getInstance();
		MPlexGPIOPin anodeSet[] = new MPlexGPIOPin[]{
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_26,PinState.LOW),
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_27,PinState.LOW),
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_28,PinState.LOW),
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_29,PinState.LOW)
		};

		MPlexGPIOPin cathode25 =  
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_25,PinState.HIGH);
		
		MPlexGPIOPin cathode22 =  
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_22,PinState.HIGH);
		
		MPlexGPIOPin cathode23 =  
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_23,PinState.HIGH);
		
		MPlexGPIOPin cathode24 =  
				new MPlexGPIOPin(gpio,RaspiPin.GPIO_24,PinState.HIGH);

		
		boolean testMode=false;
		if(args.length>0){
			testMode = args[0].equalsIgnoreCase("test"); 
					//Boolean.getBoolean(args[0]);
		}
		
		
		
		if(testMode){
			System.out.println("Running Test Mode 1 Run");
			for (int i = 0; i < 10; i++) 
			//while(true)
			{
			
				/*cathode25.LOW();
				cathode22.LOW();
				cathode23.LOW();
				cathode24.LOW();*/
				
				testSwitchLED(anodeSet[0], cathode23);
				testSwitchLED(anodeSet[1], cathode23);
				testSwitchLED(anodeSet[2], cathode23);
				testSwitchLED(anodeSet[3], cathode23);
				
				testSwitchLED(anodeSet[0], cathode22);
				testSwitchLED(anodeSet[1], cathode22);
				testSwitchLED(anodeSet[2], cathode22);
				testSwitchLED(anodeSet[3], cathode22);
				
				testSwitchLED(anodeSet[0], cathode25);
				testSwitchLED(anodeSet[1], cathode25);
				testSwitchLED(anodeSet[2], cathode25);
				testSwitchLED(anodeSet[3], cathode25);
				
				
				testSwitchLED(anodeSet[0], cathode24);
				testSwitchLED(anodeSet[1], cathode24);
				testSwitchLED(anodeSet[2], cathode24);
				testSwitchLED(anodeSet[3], cathode24);
				
				
				
			
			
			
			
			
			
			
			
			
			}
		}else {
		
			MPlexConfigMatrix configMatrix = new MPlexConfigMatrix();
			
			MPlexHaloLEDSet firstPinSet = new MPlexHaloLEDSet(
					anodeSet,cathode23,0,configMatrix);
			MPlexHaloLEDSet secondPinSet = new MPlexHaloLEDSet(
					anodeSet,cathode22,1,configMatrix);
			MPlexHaloLEDSet thirdPinSet = new MPlexHaloLEDSet(
					anodeSet,cathode25,2,configMatrix);
			MPlexHaloLEDSet fourthPinSet = new MPlexHaloLEDSet(
					anodeSet,cathode24,3,configMatrix);
			
			MPlexHaloLEDController controller = new MPlexHaloLEDController(
	        		new MPlexHaloLEDSet[]{
	        				firstPinSet,secondPinSet,
	        				thirdPinSet, 
	        				fourthPinSet
	        			}
	        		);
	        
	        //readLineInput(firstPinSet);
	        autoStep(configMatrix);
	        System.out.println("Shuting down");
	        controller.shutdown();
		}
        
        
		
        gpio.shutdown();
		System.exit(0);
	}
	

	public static void autoStep(MPlexConfigMatrix configMatrix) throws IOException, InterruptedException{
	    while (true) {
	    	if(!configMatrix.next()) break;
	    	Thread.sleep(100);
	    	
	    } 
	}

	public static void DISreadLineInput(MPlexHaloLEDSet firstPinSet) throws IOException, InterruptedException{
		BufferedReader 	bReader = null;
        bReader = new BufferedReader(new InputStreamReader(System.in));
        int userVal =0;
        
        for (int outLoop = 0; outLoop < 2; outLoop++) {
	        for (int i = 0; i < 16; i++) {
	        	//firstPinSet.setBinaryStringSwitch(Integer.toString(i,2));
	        	Thread.sleep(500);
			}
        }
         
        
		/*while(userVal>-1){
			System.out.print("Input > ");
			try{
			String readLine = bReader.readLine();
			userVal = Integer.parseInt(readLine);
			firstPinSet.setBinaryStringSwitch(Integer.toString(userVal,2));
			}catch(RuntimeException rEx){
				//unknown int, exiting.
				userVal=-1;
			}
		}*/
	}
	
	
	/* Does not work, have to use JNI 
	public static void readKeyPressInput(GpioPWMPinSet firstPinSet) throws IOException{
		BufferedReader 	bReader = null;
        //bReader = new BufferedReader(new InputStreamReader(System.in));
		
		Scanner keyboard = new Scanner(System.in);
		byte mybyte = keyboard.nextByte();
		
		
		
		System.out.println( mybyte);
        
        
        int userVal =0;
		while(userVal>-1){
			System.out.println( System.in.read());
		}
	} */
	static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss ");
	public static void testSwitchLED(MPlexGPIOPin anode, MPlexGPIOPin cathode
			
			) throws InterruptedException{
		System.out.print(sdf.format(new Date()) + " LED ON:");
		anode.HIGH();
		cathode.LOW();System.out.println();
		
		Thread.sleep(70);
		
		System.out.print(sdf.format(new Date()) + " LED OFF:");
		anode.LOW();
		cathode.HIGH();System.out.println();
		
		System.out.println();
	}
}
