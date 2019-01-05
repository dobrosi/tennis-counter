package com.github.dobrosi.tenniscounter;

public class TennisGame extends TennisCounter {
	public static final String[] VAL = new String[] {"0", "15", "30", "40", "40"};
	
	private boolean tiebreak;
	
	public TennisGame() {
	}
	
	public TennisGame(boolean tiebreak) {
		this.tiebreak = tiebreak;
	}

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
	public boolean checkFinished() {
		return (!tiebreak && super.checkFinished()) || isTiebreakFinished();
	}

	private boolean isTiebreakFinished() {
		return tiebreak && (Math.abs(val[0] - val[1]) >= getDifference()) && (val[0] >= 7 || val[1] >= 7);
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
		if(tiebreak) {
			v0 = "" + val[0];
			v1 = "" + val[1];
		} else if (val[0] >= getGoal() - 1 && val[1] >= getGoal() - 1) {
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

	public boolean isTiebreak() {
		return tiebreak;
	}
}