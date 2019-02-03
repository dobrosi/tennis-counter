package com.github.dobrosi.tenniscounter;

import static def.jquery.Globals.$;

import def.jquery.JQuery;
import jsweet.dom.Globals;

public class Start {
	private static JQuery buttonBack;
	private static JQuery buttonReset;
	private static JQuery button0;
	private static JQuery button1;
	private static JQuery target;
	private static JQuery textGame0;
	private static JQuery textGame1;
	private static JQuery textResult0;
	private static JQuery textResult1;
	private static boolean finished;

	public static void main(final String[] args) {
		new Start().init();
	}

	private double interval;
	private TennisMatch match;
	private boolean confirmedBack;

	private void init() {
		target = $("#target");
		buttonBack = $("#back");
		buttonReset = $("#reset");
		button0 = $("#button0");
		button1 = $("#button1");
		textGame0 = $("#button0 .game");
		textGame1 = $("#button1 .game");
		textResult0 = $("#button0 .result .text");
		textResult1 = $("#button1 .result .text");

		reset(true);

		buttonBack.click(t -> {
			back();
			return null;
		});
		buttonReset.click(t -> {
			reset(false);
			return null;
		});
		button0.click(t -> {
			addNewPoint(0);
			return null;
		});
		button1.click(t -> {
			addNewPoint(1);
			return null;
		});

		$("#target").css("background", "black");
		$("#target").css("color", "white");
		$("#target").css("padding", "20px");
		$("#target").css("margin", "20px");
	}

	private void reset(final boolean confirmed) {
		if (confirmed || Globals.confirm("Reset. Are you sure?")) {
			match = null;
			setText(textGame0, "Start");
			setText(textGame1, "Start");
			setText(textResult0, "");
			setText(textResult1, "");
		}
	}

	private void back() {
		if (confirmedBack || Globals.confirm("Undo. Are you sure?")) {
			confirmedBack = true;
			match.steps.pop();
			Object[] stepArrays = match.steps.toArray();

			match = new TennisMatch();
			match.startNext();
			for (Object winnerIndex : stepArrays) {
				match.newPoint((int) winnerIndex);
			}
			showResult();
		}
	}

	private void addNewPoint(final int i) {
		confirmedBack = false;
		if (finished) {
			return;
		}

		if (match == null) {
			match = new TennisMatch();
			match.startNext();
			match.getLastTennisSet().actualPlayerIndex = i;
		} else {
			match.newPoint(i);
		}
		showResult();
	}

	private void setBallVisible(final int index, final boolean visible) {
		$("#button" + index + " .result .text .ball").css("visibility", visible ? "visible" : "hidden");
	}

	private void showResult() {
		System.out.println(match.toString());
		setText(target, match.toString());
		setText(textGame0, match.printGamePoint(0));
		setText(textGame1, match.printGamePoint(1));
		setText(textResult0, "<div class=\"ball\"></div><div class=\"match digit\">" + match.val[0] + "</div>"
				+ formatSub(match.printSetPoints(0, false)) + "</div>");
		setText(textResult1, "<div class=\"ball\"></div><div class=\"match digit\">" + match.val[1] + "</div>"
				+ formatSub(match.printSetPoints(1, false)) + "</div>");
		if (match.isFinished()) {
			finished = true;
			setText(textGame0, "");
			setText(textGame1, "");
			setBallVisible(0, false);
			setBallVisible(1, false);
			interval = Globals.setInterval(jsweet.util.Globals.function(() -> {
				Globals.alert("Finished");
				Globals.clearInterval(interval);
			}));
		} else {
			TennisSet lastTennisSet = match.getLastTennisSet();
			boolean v = lastTennisSet.actualPlayerIndex == 0;

			TennisGame lastTennisGame = lastTennisSet.getLastTennisGame();
			if (lastTennisGame.isTiebreak()) {
				v = ((lastTennisGame.val[0] + lastTennisGame.val[1] + (v ? 1 : 3)) % 4) < 2;
			}

			setBallVisible(0, v);
			setBallVisible(1, !v);
		}
	}

	private String formatSub(final String str) {
		return str.replace("(", "<sub>").replace(")", "</sub>").replace("\t", "</div><div class=\"digit\">");
	}

	private void setText(final JQuery t, final String text) {
		if (t != null) {
			t.html(text);
		}
	}
}
