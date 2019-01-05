package quickstart;

import static def.dom.Globals.alert;
import static def.jquery.Globals.$;

import def.jquery.JQuery;

/**
 * This class is used within the webapp/index.html file.
 */
public class QuickStart {

	private static JQuery button0;
	private static JQuery button1;
	public static void main(String[] args) {
		TennisMatch m = new TennisMatch();
		m.startNext();

		button0 = $("#button0");
		button0.click(t -> {
			testNewPoint(m, 0);
			return null;
		});
		button1 = $("#button1");
		button1.click(t -> {
			testNewPoint(m, 1);
			return null;
		});
			
		$("#target").css("background", "red");
		// use of the JavaScript DOM API
		alert(m.toString());
	}
	private static void testNewPoint(TennisMatch m, int i) {
		m.newPoint(i);
		System.out.println(m.toString());
		$("#target").text(m.toString());
		if (m.isFinished()) {
			alert("Finished");
			button0.hide();
			button1.hide();
		}
	}
}
