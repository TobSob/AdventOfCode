package day6;

import java.util.List;

public class Fish {
	int day;
	public Fish(int x) {
		this.day = x;
	}
	public Fish() {
		this.day = 8;
	}
	public void oneDay(List<Fish> newFishies) {
		if(day == 0) {
			day = 6;
			newFishies.add(new Fish());
		} else {
			day--;
		}
		
	}
}
