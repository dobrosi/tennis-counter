package quickstart;

import java.util.ArrayList;
import java.util.List;

public class TennisSet extends TennisCounter {
	public List<TennisCounter> tennisGames = new ArrayList<>();
	
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
		tennisGames.add(new TennisGame().startNext());
		return this;
	}

	@Override
	public boolean step(int winnerIndex) {
		return tennisGames.get(tennisGames.size() - 1).newPoint(winnerIndex);
	}

	@Override
	public String toString() {
		return "\tset: " + print(tennisGames);
	}
}