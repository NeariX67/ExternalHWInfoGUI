package com.nearix.hwinfoMonitor;

import java.io.IOException;

import com.nearix.ui.ProgressIndicator.RingProgressIndicator;
import com.nearix.ui.ProgressIndicator.RingProgressIndicator_Clock;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

	public static RingProgressIndicator cpuTemp, cpuUsage, cpuPower, gpuTemp, gpuUsage, gpuPower, ramTemp, ramPercentage, ramPower;
	public static RingProgressIndicator_Clock cpuClock, gpuClock, ramUsed;
	static Label cpuLabel = new Label("CPU"), gpuLabel = new Label("GPU"), ramLabel = new Label("RAM");
	
	boolean run = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		cpuLabel.setStyle("-fx-font-weight: bold; \r\n" + 
				"-fx-font-size: 2.5em;\r\n" + 
				"-fx-text-fill: #009383;\r\n" + 
				"-fx-padding: 5.0;");
		cpuLabel.setPrefSize(148, 148);
		cpuLabel.setAlignment(Pos.CENTER);

		gpuLabel.setStyle("-fx-font-weight: bold; \r\n" + 
				"-fx-font-size: 2.5em;\r\n" + 
				"-fx-text-fill: #009383;\r\n" + 
				"-fx-padding: 5.0;");
		gpuLabel.setPrefSize(148, 148);
		gpuLabel.setAlignment(Pos.CENTER);
		
		ramLabel.setStyle("-fx-font-weight: bold; \r\n" + 
				"-fx-font-size: 2.5em;\r\n" + 
				"-fx-text-fill: #009383;\r\n" + 
				"-fx-padding: 5.0;");
		ramLabel.setPrefSize(148, 148);
		ramLabel.setAlignment(Pos.CENTER);
		
		
		stage.setTitle("Test");
		stage.setWidth(800);
		stage.setHeight(480);
		stage.setResizable(false);
		stage.setFullScreen(true);
		
		
		VBox hbox = new VBox();
		HBox cpuBox = new HBox();
		HBox ramBox = new HBox();
		HBox gpuBox = new HBox();

		cpuTemp = new RingProgressIndicator();
		cpuTemp.createDefaultSkin();
		cpuTemp.max = 110;
		cpuTemp.min = 0;
		cpuTemp.unit = "°C";
		cpuUsage = new RingProgressIndicator();
		cpuUsage.createDefaultSkin();
		cpuClock = new RingProgressIndicator_Clock();
		cpuClock.createDefaultSkin();
		cpuClock.max = 4500;
		cpuClock.min = 0;
		cpuClock.unit = "MHz";
		cpuPower = new RingProgressIndicator();
		cpuPower.createDefaultSkin();
		cpuPower.max = 130;
		cpuPower.min = 0;
		cpuPower.unit = "W";

		gpuTemp = new RingProgressIndicator();
		gpuTemp.createDefaultSkin();
		gpuTemp.max = 100;
		gpuTemp.min = 0;
		gpuTemp.unit = "°C";
		gpuUsage = new RingProgressIndicator();
		gpuUsage.createDefaultSkin();
		gpuClock = new RingProgressIndicator_Clock();
		gpuClock.createDefaultSkin();
		gpuClock.max = 2200;
		gpuClock.min = 0;
		gpuClock.unit = "MHz";
		gpuPower = new RingProgressIndicator();
		gpuPower.createDefaultSkin();
		gpuPower.max = 250;
		gpuPower.min = 0;
		gpuPower.unit = "W";

		ramTemp = new RingProgressIndicator();
		ramTemp.createDefaultSkin();
		ramTemp.max = 100;
		ramTemp.min = 0;
		ramTemp.unit = "°C";
		ramUsed = new RingProgressIndicator_Clock();
		ramUsed.createDefaultSkin();
		ramUsed.max = 32768;
		ramUsed.min = 0;
		ramUsed.unit = "MB";
		ramPercentage = new RingProgressIndicator();
		ramPercentage.createDefaultSkin();
		ramPower = new RingProgressIndicator();
		ramPower.createDefaultSkin();
		ramPower.max = 5;
		ramPower.min = 0;
		ramPower.unit = "W";

		cpuBox.getChildren().addAll(cpuLabel, cpuTemp, cpuUsage, cpuClock, cpuPower);
		gpuBox.getChildren().addAll(gpuLabel, gpuTemp, gpuUsage, gpuClock, gpuPower);
		ramBox.getChildren().addAll(ramLabel, ramTemp, ramPercentage, ramUsed, ramPower);

		
		
		hbox.getChildren().addAll(cpuBox, gpuBox, ramBox);
		stage.setScene(new Scene(hbox, 300, 400));

		stage.show();
		
		
		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				Runnable updater = new Runnable() {
					
					@Override
					public void run() {
						SensorDataList list = HWInfoConnection.getHWInfoJSON();
						refresh(list);
						System.out.println(HWInfoConnection.badConnectionCount);
						if(HWInfoConnection.badConnectionCount > 30) {
							try {
								ProcessBuilder pb = new ProcessBuilder("sudo" , "bash", "/home/pi/Documents/DisplayOff.sh");
								Process p = pb.start();
//								System.out.println(p.exitValue());
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						else {

							try {
								ProcessBuilder pb = new ProcessBuilder("sudo" , "bash", "/home/pi/Documents/DisplayOn.sh");
								Process p = pb.start();
//								System.out.println(p.exitValue());
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
						
						
						
						
					}
				};
				
				
				while(true) {
					try {
						Thread.sleep(500);
					}
					catch(InterruptedException e) {
						e.printStackTrace();
					}
					
					Platform.runLater(updater);
				}
				
			}
		});
		thread.start();

	}

	public static void refresh(SensorDataList list) {
		if (list != null) {
			cpuTemp.setProgress(list.getCPU_CoreTemp());
			cpuClock.setProgress(list.getCPU_Clock());
			cpuUsage.setProgress((int) Math.round(list.getCPU_Usage()));
			cpuPower.setProgress((int) Math.round(list.getCPU_Power()));

			gpuTemp.setProgress(list.getGPU_CoreTemp());
			gpuClock.setProgress(list.getGPU_Clock());
			gpuUsage.setProgress((int) Math.round(list.getGPU_Usage()));
			gpuPower.setProgress((int) Math.round(list.getGPU_Power()));

			ramPercentage.setProgress((int) Math.round(list.getRAM_Usage()));
			ramUsed.setProgress(list.getRAM_Used());
			ramTemp.setProgress(list.getRAM_Temp());
			ramPower.setProgress((int) Math.round(list.getRAM_Power()));
		}
	}

}
