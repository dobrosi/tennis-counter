package quickstart;

public class TennisGame extends TennisCounter {
	public static final String[] VAL = new String[] {"0", "15", "30", "40", "40"};

	@Override
	public short getGoal() {
		return 4;
	}

	@Override
	public short getDifference() {
		return 2;
	}

	@Override
	public TennisCounter startNext() {
		return this;
	}

	@Override
	public boolean step(int winnerIndex) {
		return true;
	}

	@Override
	public String printVal() {
		String[] v = getVal();
		return  printVal(v[0], v[1]);
	}

	@Override
	public String toString() {
		return "\t\tgame: " + printVal() + "," + isFinished();
	}

	public String[] getVal() {
		String v0, v1;
		if (val[0] >= getGoal() - 1 && val[1] >= getGoal() - 1) {
			v0 = v1 = "40";
			if (val[0] > val[1]) {
				v0 = "A";
			} else if (val[0] < val[1]) {
				v1 = "A";
			}
		} else {
			v0 = VAL[val[0]];
			v1 = VAL[val[1]]; 
		}
		return new String[] {v0, v1};
	}
}