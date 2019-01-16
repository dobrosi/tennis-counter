package com.github.dobrosi.tenniscounter;

import java.util.ArrayList;
import java.util.List;

public class TennisSet extends TennisCounter {
	public List<TennisCounter> tennisGames = new ArrayList<>();
	public int actualPlayerIndex;

	@Override
	public short getGoal() {
		return 6;
	}

	@Override
	public short getDifference() {
		return 2;
	}

	@Override
	public TennisCounter startNext() {
		actualPlayerIndex++;
		actualPlayerIndex %= 2;
		tennisGames.add(new TennisGame(tennisGames.size() >= 12).startNext());
		return this;
	}

	@Override
	public boolean step(int winnerIndex) {
		return tennisGames.get(tennisGames.size() - 1).newPoint(winnerIndex);
	}

	@Override
	public boolean checkFinished() {
		return super.checkFinished() || getLastTennisGame().isTiebreak() && getLastTennisGame().isFinished();
	}

	public TennisGame getLastTennisGame() {
		return (TennisGame) tennisGames.get(tennisGames.size() - 1);
	}

	@Override
	public String toString() {
		return "\tset: " + print(tennisGames);
	}
}