package me.interakt.app;

import java.util.ArrayList;

public class PBoard {
	ArrayList<PNode> mContent;
	static PBoard end;

	PBoard() {
		mContent = new ArrayList<PNode>();
	}

	PBoard(PBoard pBoard) {
		mContent = new ArrayList<PNode>(pBoard.mContent);
	}

	void add(PSymbol pSymbol, PBoard pBoard, int pPos) {
		PNode tNode = new PNode(pSymbol, pBoard);
		mContent.add(pPos, tNode);
	}

	void add(PSymbol pSymbol, int pPos) {
		PNode tNode = new PNode(pSymbol, PBoard.end);
		mContent.add(pPos, tNode);
	}

	void add(PSymbol pSymbol, PBoard pBoard) {
		PNode tNode = new PNode(pSymbol, pBoard);
		mContent.add(tNode);
	}

	void add(PSymbol pSymbol) {
		PNode tNode = new PNode(pSymbol, PBoard.end);
		mContent.add(tNode);
	}
}
