package com.nearix.hwinfoMonitor;

public class SensorDataList {
	
	
	private Double CPU_Usage, CPU_Voltage, CPU_Power;
	private Double GPU_Usage, GPU_Voltage, GPU_Power;
	private Double RAM_Usage, RAM_Power;
	
	
	
	public Double getRAM_Power() {
		return RAM_Power;
	}
	public void setRAM_Power(Double rAM_Power) {
		RAM_Power = rAM_Power;
	}
	private int RAM_Used, RAM_Free, RAM_Temp, CPU_Clock, CPU_CoreTemp, GPU_Clock, GPU_CoreTemp;
	
	
	public Double getCPU_Usage() {
		return CPU_Usage;
	}
	public void setCPU_Usage(Double cPU_Usage) {
		CPU_Usage = cPU_Usage;
	}
	public Double getCPU_Voltage() {
		return CPU_Voltage;
	}
	public void setCPU_Voltage(Double cPU_Voltage) {
		CPU_Voltage = cPU_Voltage;
	}
	public Double getCPU_Power() {
		return CPU_Power;
	}
	public void setCPU_Power(Double cPU_Power) {
		CPU_Power = cPU_Power;
	}
	public Double getGPU_Usage() {
		return GPU_Usage;
	}
	public void setGPU_Usage(Double gPU_Usage) {
		GPU_Usage = gPU_Usage;
	}
	public Double getGPU_Voltage() {
		return GPU_Voltage;
	}
	public void setGPU_Voltage(Double gPU_Voltage) {
		GPU_Voltage = gPU_Voltage;
	}
	public Double getGPU_Power() {
		return GPU_Power;
	}
	public void setGPU_Power(Double gPU_Power) {
		GPU_Power = gPU_Power;
	}
	public Double getRAM_Usage() {
		return RAM_Usage;
	}
	public void setRAM_Usage(Double rAM_Usage) {
		RAM_Usage = rAM_Usage;
	}
	public int getRAM_Used() {
		return RAM_Used;
	}
	public void setRAM_Used(int rAM_Used) {
		RAM_Used = rAM_Used;
	}
	public int getRAM_Free() {
		return RAM_Free;
	}
	public void setRAM_Free(int rAM_Free) {
		RAM_Free = rAM_Free;
	}
	public int getRAM_Temp() {
		return RAM_Temp;
	}
	public void setRAM_Temp(int rAM_Temp) {
		RAM_Temp = rAM_Temp;
	}
	public int getCPU_Clock() {
		return CPU_Clock;
	}
	public void setCPU_Clock(int cPU_Clock) {
		CPU_Clock = cPU_Clock;
	}
	public int getCPU_CoreTemp() {
		return CPU_CoreTemp;
	}
	public void setCPU_CoreTemp(int cPU_CoreTemp) {
		CPU_CoreTemp = cPU_CoreTemp;
	}
	public int getGPU_Clock() {
		return GPU_Clock;
	}
	public void setGPU_Clock(int gPU_Clock) {
		GPU_Clock = gPU_Clock;
	}
	public int getGPU_CoreTemp() {
		return GPU_CoreTemp;
	}
	public void setGPU_CoreTemp(int gPU_CoreTemp) {
		GPU_CoreTemp = gPU_CoreTemp;
	}
	
	
	
	
}
