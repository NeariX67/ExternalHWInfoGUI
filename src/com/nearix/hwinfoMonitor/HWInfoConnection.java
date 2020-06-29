package com.nearix.hwinfoMonitor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;

public class HWInfoConnection {
	
	public static int badConnectionCount = 0;
	

	public static SensorDataList getHWInfoJSON() {
		SensorDataList sensorList = null;
		try {
			String str_url = "http://192.168.8.105:46731";
			URL url = new URL(str_url);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(500);
			con.setReadTimeout(500);

			int status = con.getResponseCode();
			if(status == 200) {
				
			}
			else {
				System.out.println("Error: " + status);
				System.out.println(con.getResponseMessage());
				return null;
			}
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			JSONArray hwInfoArray = new JSONArray(content.toString());
//			System.out.println(hwInfoArray.length());
			
			sensorList = new SensorDataList();
			
			double CPU_Voltage = 0, CPU_Clock = 0, RAM_Temp = 0;
			int CPU_VoltageCount = 0, CPU_ClockCount = 0, RAM_TempCount = 0;
			
			
			NumberFormat format = NumberFormat.getInstance(Locale.GERMANY);
			
			for(int i = 0;i < hwInfoArray.length();i++) {
				String sensorClass = hwInfoArray.getJSONObject(i).getString("SensorClass");
//				System.out.println(sensorClass);
				switch(sensorClass) {
				case "System: MSI MS-7C75":
					switch(hwInfoArray.getJSONObject(i).getString("SensorName")) {
					case "Physical Memory Used":
						sensorList.setRAM_Used(Integer.parseInt(hwInfoArray.getJSONObject(i).getString("SensorValue")));
						break;
					case "Physical Memory Available":
						sensorList.setRAM_Free(Integer.parseInt(hwInfoArray.getJSONObject(i).getString("SensorValue")));
						break;
					case "Physical Memory Load":
						sensorList.setRAM_Usage(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue());
						break;
					}
					break;
					
				case "CPU [#0]: Intel Core i7-10700K":
					switch(hwInfoArray.getJSONObject(i).getString("SensorName")) {
					case "Core 0 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 1 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 2 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 3 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 4 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 5 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 6 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 7 VID":
						CPU_Voltage += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_VoltageCount += 1;
						break;
					case "Core 0 Clock (perf #1)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Core 1 Clock (perf #2)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Core 2 Clock (perf #3)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Core 3 Clock (perf #4)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Core 4 Clock (perf #5)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Core 5 Clock (perf #6)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Core 6 Clock (perf #7)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Core 7 Clock (perf #8)":
						CPU_Clock += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						CPU_ClockCount += 1;
						break;
					case "Total CPU Usage":
						sensorList.setCPU_Usage(round(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue(), 1));
						break;
						
					}
					break;
				case "CPU [#0]: Intel Core i7-10700K: Enhanced":
					switch(hwInfoArray.getJSONObject(i).getString("SensorName")) {
					case "CPU Package":
						sensorList.setCPU_CoreTemp(Integer.parseInt(hwInfoArray.getJSONObject(i).getString("SensorValue")));
						break;
					case "CPU Package Power":
						sensorList.setCPU_Power(round(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue(), 1));
						break;
					case "Total DRAM Power":
						sensorList.setRAM_Power(round(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue(), 1));
						break;
					}
					break;
					
				case "DIMM Temperature Sensor":
					switch(hwInfoArray.getJSONObject(i).getString("SensorName")) {
					case "DIMM[0] Temperature":
						RAM_Temp += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						RAM_TempCount += 1;
						break;
					case "DIMM[1] Temperature":
						RAM_Temp += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						RAM_TempCount += 1;
						break;
					case "DIMM[2] Temperature":
						RAM_Temp += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						RAM_TempCount += 1;
						break;
					case "DIMM[3] Temperature":
						RAM_Temp += format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue();
						RAM_TempCount += 1;
						break;
					}
					break;
					
				case "GPU [#0]: NVIDIA GeForce GTX 1070 Ti: ":
					switch(hwInfoArray.getJSONObject(i).getString("SensorName")) {
					case "GPU Temperature":
						sensorList.setGPU_CoreTemp(Integer.parseInt(hwInfoArray.getJSONObject(i).getString("SensorValue")));
						break;
					case "GPU Core Voltage":
						sensorList.setGPU_Voltage(round(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue(), 3));
						break;
					case "GPU Power":
						sensorList.setGPU_Power(round(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue(), 1));
						break;
					case "GPU Clock":
						sensorList.setGPU_Clock(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).intValue());
						break;
					case "GPU Core Load":
						sensorList.setGPU_Usage(round(format.parse(hwInfoArray.getJSONObject(i).getString("SensorValue")).doubleValue(), 3));
						break;
					}
					break;
					
//				case "":
//					break;
					
//				case "":
//					break;
					
//				case "":
//					break;
					
				}
				
			}
			
			in.close();
			con.disconnect();

			sensorList.setCPU_Clock((int) Math.round(CPU_Clock/CPU_ClockCount));
			sensorList.setCPU_Voltage(round(CPU_Voltage/CPU_VoltageCount, 3));
			
			sensorList.setRAM_Temp((int) Math.round(RAM_Temp/RAM_TempCount));
			
			badConnectionCount = 0;
		} catch (IOException | JSONException | ParseException e) {
//			e.printStackTrace();
			if(e.getClass() == SocketTimeoutException.class || e.getClass() == ConnectException.class) {
				badConnectionCount += 1;
			}
		}
		return sensorList;
	}
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
