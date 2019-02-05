package main;

import java.io.IOException;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Main {
	public static void main(String[] args) {
		//failsafe in case something goes terribly wrong, I have two minutes to shut this program off in task manager
		showMessageInNewThread("starting bedtime...");
		sleep(120);
		
		while (true) {
			sleep(1);
			int hour=Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if (hour>=22) {
				showMessageInNewThread("Goodnight!");
				sleep(10);
				return;
			}
		}
	}
	
	public static void showMessageInNewThread(String message) {
		new Thread(null, null, "showMessageThread", 1<<20) {
			public void run() {
				JOptionPane.showMessageDialog(null, message);
			}
		}.start();
	}
	
	public static void sleep(double seconds) {
		try {
			Thread.sleep((long) (seconds*1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void shutdown() {
		Runtime rt = Runtime.getRuntime();
		try {
			Process pr = rt.exec("shutdown /p");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
