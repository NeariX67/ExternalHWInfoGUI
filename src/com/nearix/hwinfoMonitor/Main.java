package com.nearix.hwinfoMonitor;

public class Main {

	public static void main(String[] args) {
		SensorDataList list = HWInfoConnection.getHWInfoJSON();
		System.out.println("CPU: ");
		System.out.println(list.getCPU_Clock() + " MHz");
		System.out.println(list.getCPU_CoreTemp() + " °C");
		System.out.println(list.getCPU_Power() + " W");
		System.out.println(list.getCPU_Usage() + " %");
		System.out.println(list.getCPU_Voltage() + " V");
		System.out.println();
		
		System.out.println("RAM:");
		System.out.println(list.getRAM_Used() + " MB");
		System.out.println(list.getRAM_Free() + " MB");
		System.out.println(list.getRAM_Usage() + " %");
		System.out.println(list.getRAM_Temp() + " °C");
		System.out.println(list.getRAM_Power() + " W");
		System.out.println();
		
		System.out.println("GPU:");
		System.out.println(list.getGPU_Clock() + " MHz");
		System.out.println(list.getGPU_CoreTemp() + " °C");
		System.out.println(list.getGPU_Power() + " W");
		System.out.println(list.getGPU_Usage() + " %");
		System.out.println(list.getGPU_Voltage() + " V");
	}

}
