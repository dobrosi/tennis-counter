package com.github.dobrosi.tenniscounter;

import java.util.ArrayList;
import java.util.List;

public class TennisMatch extends TennisCounter {
	public List<TennisCounter> tennisSets = new ArrayList<>();

	@Override
	public short getGoal() {
		return 2;
	}

	@Override
	public short getDifference() {
		return 1;
	}

	@Override
	public TennisCounter startNext() {
		tennisSets.add(new TennisSet().startNext());
		return this;
	}

	@Override
	public boolean step(int winnerIndex) {
		if (isFinished()) {
			throw new RuntimeException("This match is finished already.");
		}
		return tennisSets.get(tennisSets.size() - 1).newPoint(winnerIndex);
	}

	public TennisSet getLastTennisSet() {
		return (TennisSet) tennisSets.get(tennisSets.size() - 1);
	}

	@Override
	public String toString() {
		String firstLine = "R\t";
		for (int i = 1; i <= tennisSets.size(); i++) {
			firstLine += "" + i + "\t";			
		}
		String res = "";
		res += toString(0);
		res += "\n";
		res += toString(1);

		return firstLine + "\n" + res;
	}

	public String toString(int i) {
		String res = "";
		res += val[i];

		res += printSetPoints(i, true);
		return res;
	}

	public String printGamePoint(int playerIndex) {
		return printGamePoint(getLastTennisSet(), playerIndex, true);
	}
	
	public String printGamePoint(TennisSet tenniSet, int playerIndex, boolean printLastGame) {
		String res = "";
		if (tenniSet == null) {
			return res;
		}
		TennisGame lastTennisGame = tenniSet.getLastTennisGame();
		if (tenniSet.isFinished()) {
			if (lastTennisGame.isTiebreak() && playerIndex != lastTennisGame.getWinnerIndex()) {
				res = "(" + lastTennisGame.getVal()[playerIndex] + ")";
			}
		} else if (printLastGame) {
			res = "\t" + lastTennisGame.getVal()[playerIndex];
		}

		return res;

	}

	public String printSetPoints(int playerIndex, boolean printLastGame) {
		String res = "";
		for (TennisCounter tennisSet : tennisSets) {
			res += "\t" + tennisSet.val[playerIndex] + printGamePoint((TennisSet) tennisSet, playerIndex, printLastGame);
		}
		return res;
	}
}
