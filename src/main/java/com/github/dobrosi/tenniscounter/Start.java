package com.github.dobrosi.tenniscounter;

import static def.dom.Globals.alert;
import static def.jquery.Globals.$;

import def.jquery.JQuery;

public class Start {

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

		$("#target").css("background", "black");
		$("#target").css("color", "white");
		$("#target").css("padding", "20px");
		$("#target").css("margin", "20px");
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
