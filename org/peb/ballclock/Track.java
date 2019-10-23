package org.peb.ballclock;

import java.util.ArrayDeque;

public class Track<T extends Comparable<T>> extends ArrayDeque<T> {
	private Track<T> nextTrack;
	private Track<T> mainTrack;
	private int limit;
	private int day = 0;
	
	public Track(Track<T> nextTrack, Track<T> mainTrack, int limit) {
		super();
		this.nextTrack = nextTrack;
		this.mainTrack = mainTrack;
		this.limit = limit;
	}
	
	public void push(T ball) {
		if (size() == limit) {
			while (!isEmpty()) {
				mainTrack.addLast(pop());
			}
			if (nextTrack != null) {
				nextTrack.push(ball);
			} else {
				day++;
				mainTrack.addLast(ball);
			}
		} else {
			super.push(ball);
		}
	}
	
	public int getDay() {
		return day;
	}
	
	public boolean isBallsInOrder() {
		T previous = null;
		for (T i : this) {
			if (previous != null) {
				if (previous.compareTo(i) > 0) {
					return false;
				}
			}
			previous = i;
		}		
		return true;
	}
	
	public void dumpTrack() {
		boolean firstTime = true;
		System.out.print("[");
		for (T i : this) {
			if (firstTime) {
				System.out.print(i);
				firstTime = false;
			} else {
				System.out.print("," + i);
			}
		}
		System.out.print("]");
		System.out.println();
	}
}
