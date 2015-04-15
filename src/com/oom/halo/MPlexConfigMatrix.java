package com.oom.halo;

public class MPlexConfigMatrix {

	int pattern_FORWARD_AND_BACK[][]
			= new int[][]{
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
			};
	
	
	int pattern_FORWARD_AND_OVERLAP[][]
			= new int[][]{
				{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
				{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1},
				{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1}
			};
	
	
	
	int configMatrix[][] = pattern_FORWARD_AND_OVERLAP;
	
	
	public boolean isON(int set, int index, int matrixSize){
		return configMatrix[line][index+(set*matrixSize)]==1;
	}
	
	int line = 0;
	public boolean next(){
		if(line>=configMatrix.length-1) return false;
		line++;
		return true;//simple return true
	}
	
}
