package quickstart;

import java.util.List;

public abstract class TennisCounter {
	protected short[] val = new short[] { 0, 0 };

	public abstract short getGoal();

	public abstract short getDifference();

	public abstract TennisCounter startNext();

	public abstract boolean step(int winnerIndex);

	private boolean finished;

	private int winnerIndex = -1;

	public boolean newPoint(int winnerIndex) {
		boolean childFinished = step(winnerIndex);
		if (childFinished) {
			val[winnerIndex]++;
		}
		boolean res = checkFinished();
		if (res) {
			this.winnerIndex = winnerIndex;
			finished = true;
		} else if (childFinished) {
			startNext();
		}
		return res;
	}

	private boolean checkFinished() {
		int v1 = val[0];
		int v2 = val[1];
		return (v1 >= getGoal() || v2 >= getGoal()) && Math.abs(v1 - v2) >= getDifference();
	}

	public String printVal(Object v0, Object v1) {
		return v0 + "-" + v1;
	}

	public String printVal() {
		return printVal(val[0], val[1]);
	}

	public String print(List<TennisCounter> tennisCounterList) {
		String res = printVal() + "," + finished + "\n";
		for (TennisCounter tennisCounter : tennisCounterList) {
			res += tennisCounter + ",\n";
		}
		res += "\n";
		return res;
	}

	public int getWinnerIndex() {
		return winnerIndex;
	}

	public boolean isFinished() {
		return finished;
	}
}