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
		String firstLine = "|#PLAYER#\t|R\t|";
		for (int i = 1; i <= tennisSets.size(); i++) {
			firstLine += "" + i + ".\t|";			
		}
		String res = "";
		for (int i = 0; i < 2; i++) {
			res += "|Player" + (i + 1) + "\t|" + val[i];

			TennisSet lastTennisSet = null;
			for (TennisCounter tennisSet : tennisSets) {
				lastTennisSet = (TennisSet) tennisSet;
				res += "\t|" + lastTennisSet.val[i] + printGamePoints(lastTennisSet, i);
			}
			printGamePoints(lastTennisSet, i);
			res += "\n";
		}

		return firstLine + "\n" + res;
	}

	private String printGamePoints(TennisSet lastTennisSet, int playerIndex) {
		String res = "";
		if (lastTennisSet == null) {
			return res;
		}
		TennisGame lastTennisGame = lastTennisSet.getLastTennisGame();
		if (lastTennisSet.isFinished()) {
			if (lastTennisGame.isTiebreak() && playerIndex != lastTennisGame.getWinnerIndex()) {
				res = "/" + lastTennisGame.getVal()[playerIndex];
			}
		} else {
			res = "\t|" + lastTennisGame.getVal()[playerIndex];
		}

		return res;

	}
}
