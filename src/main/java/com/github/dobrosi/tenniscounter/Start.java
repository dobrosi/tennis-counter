package com.github.dobrosi.tenniscounter;

import static def.jquery.Globals.$;

import def.jquery.JQuery;
import jsweet.dom.Globals;

public class Start {

	private static JQuery button0;
	private static JQuery button1;
	private static JQuery target;
	private static JQuery buttonGame0;
	private static JQuery buttonGame1;
	private static JQuery buttonResult0;
	private static JQuery buttonResult1;
	private static boolean finished;

	public static void main(String[] args) {
		new Start().init();
	}

	private double interval;

	private void init() {
		target = $("#target");
		button0 = $("#button0");
		button1 = $("#button1");
		buttonGame0 = $("#button0 .game");
		buttonGame1 = $("#button1 .game");
		buttonResult0 = $("#button0 .result");
		buttonResult1 = $("#button1 .result");

		TennisMatch m = new TennisMatch();
		m.startNext();

		button0.click(t -> {
			addNewPoint(m, 0);
			return null;
		});
		button1.click(t -> {
			addNewPoint(m, 1);
			return null;
		});

		$("#target").css("background", "black");
		$("#target").css("color", "white");
		$("#target").css("padding", "20px");
		$("#target").css("margin", "20px");
	}

	private void addNewPoint(TennisMatch m, int i) {
		if (finished) {
			return;
		}
		m.newPoint(i);
		System.out.println(m.toString());
		setText(target, m.toString());
		setText(buttonGame0, m.printGamePoint(0));
		setText(buttonGame1, m.printGamePoint(1));
		setText(buttonResult0,
				"<div class=\"match digit\">" + m.val[0] + "</div>" + formatSub(m.printSetPoints(0, false)) + "</div>");
		setText(buttonResult1,
				"<div class=\"match digit\">" + m.val[1] + "</div>" + formatSub(m.printSetPoints(1, false)) + "</div>");
		if (m.isFinished()) {
			finished = true;
			setText(buttonGame0, "");
			setText(buttonGame1, "");
			$("body").css("zoom", "2");
			interval = Globals.setInterval(jsweet.util.Globals.function(() -> {
				Globals.alert("Finished");
				Globals.clearInterval(this.interval);
			}));

		}
	}

	public void alert() {

	}

	private String formatSub(String str) {
		return str.replace("(", "<sub>").replace(")", "</sub>").replace("\t", "</div><div class=\"digit\">");
	}

	private void setText(JQuery t, String text) {
		if (t != null) {
			t.html(text);
		}
	}
}
