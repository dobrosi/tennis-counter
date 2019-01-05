import com.github.dobrosi.tenniscounter.TennisMatch;

public class TestMatch {
	TennisMatch m = new TennisMatch();

	public static void main(String[] args) {
		new TestMatch().test();
	}

	private void test() {
		m.startNext();
		//testFullMatch();
		testTiebreakMatch();
	}

	private void testTiebreakMatch() {
		for (int i = 0; i < 7; i++) {
			for (int p = 0; p < 2; p++) {
				for (int j = 0; j < 4; j++) {
					testNewPoint(m, p);
				}
			}
		}
		testNewPoint(m, 0);
		testNewPoint(m, 0);
		testNewPoint(m, 0);
	}

	private void testFullMatch() {
		System.out.println(m.toString());
		testNewPoint(m, 0);
		testNewPoint(m, 0);
		testNewPoint(m, 0);
		testNewPoint(m, 1);
		testNewPoint(m, 1);
		testNewPoint(m, 1);
		testNewPoint(m, 0);
		testNewPoint(m, 1);
		testNewPoint(m, 1);
		testNewPoint(m, 1);

		for (int i = 0; i < 24; i++) {
			testNewPoint(m, 0);
		}

		for (int i = 0; i < 70; i++) {
			testNewPoint(m, 1);
		}
	}

	private void testNewPoint(TennisMatch m, int i) {
		m.newPoint(i);
		System.out.println(m.toString());
	}
}
