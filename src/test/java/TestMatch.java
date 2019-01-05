import quickstart.TennisMatch;

public class TestMatch {
	public static void main(String[] args) {
		TennisMatch m = new TennisMatch();
		m.startNext();
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

	private static void testNewPoint(TennisMatch m, int i) {
		m.newPoint(i);
		System.out.println(m.toString());
	}
}
