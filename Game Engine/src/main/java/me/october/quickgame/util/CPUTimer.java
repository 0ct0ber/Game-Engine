package me.october.quickgame.util;

public class CPUTimer {
	
	private long start;
	private long end;
	
	private boolean started = false;
	
	public synchronized void start() {
		start = System.nanoTime();
		started = true;
	}
	
	public synchronized boolean started() {
		return started;
	}
	
	/**@throws IllegalStateException If {@code start()} has not been called*/
	public synchronized void end() throws IllegalStateException {
		if (!started) throw new IllegalStateException("CPUTimer was not started yet");
		end = System.nanoTime();
	}
	
	public void print(String message) {
		long nano = Math.abs(end - start);
		String nanos = Long.toString(nano);
		String millis = Long.toString(nano/1000000);
		message = message.replace("[millis]", millis).replace("[nano]", nanos);
		System.out.println(message);
	}

}
