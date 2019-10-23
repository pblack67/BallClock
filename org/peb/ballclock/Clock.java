package org.peb.ballclock;

public class Clock {

	private Track<Integer> mainTrack;
	private Track<Integer> minuteTrack;
	private Track<Integer> fiveTrack;
	private Track<Integer> hourTrack;
	private int numBalls;
	
	public Clock(int numBalls) {
		super();
		this.numBalls = numBalls;
		initializeTracks(numBalls);
		createMainTrackBalls(numBalls);
	}
	
	private void initializeTracks(int numBalls) {
		mainTrack = new Track<Integer>(null, null, numBalls);
		hourTrack = new Track<Integer>(null, mainTrack, 11);
		fiveTrack = new Track<Integer>(hourTrack, mainTrack, 11);
		minuteTrack = new Track<Integer>(fiveTrack, mainTrack, 4);
	}
	
	private void createMainTrackBalls(int numBalls) {
		for (int i = 1; i <= numBalls; i++) {
			mainTrack.addLast(i);
		}
	}
	
	public int runUntilOrdered() {
		do {
			Integer ball = (Integer)mainTrack.removeFirst();
			minuteTrack.push(ball);
		} while ((mainTrack.size() != numBalls) || (!mainTrack.isBallsInOrder()));

		return hourTrack.getDay() / 2;
	}
	
	public void runWithBallsUntilOrdered() {
		System.out.println(numBalls + " balls cycle after " + runUntilOrdered() + " days");
	}
	
	public void runForMinutes(int minutes) {
		for (int i = 0; i < minutes; i++) {
			Integer ball = (Integer)mainTrack.removeFirst();
			minuteTrack.push(ball);
		}
		minuteTrack.dumpTrack();
		fiveTrack.dumpTrack();
		hourTrack.dumpTrack();
		mainTrack.dumpTrack();
	}
	
	public static void main(String args[]) {
		Clock clock = new Clock(45);
		clock.runWithBallsUntilOrdered();

		System.out.println("================");
		
		Clock clock2 = new Clock(30);
		clock2.runForMinutes(325);
	}
}
