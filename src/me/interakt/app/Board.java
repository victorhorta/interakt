package me.interakt.app;

import java.util.ArrayList;

public class Board {
	// insercao e remocao de nos
	ArrayList<Node> mContent;

	Board() {
		mContent = new ArrayList<Node>();
	}

	public ArrayList<Node> getNodeArrayList() {
		return mContent;
	}

	public void setNodeArrayList(ArrayList<Node> mContent) {
		this.mContent = mContent;
	}

	Board(Board pBoard) {
		mContent = new ArrayList<Node>(pBoard.mContent);
	}

	void add(Node pNode) {
		mContent.add(pNode);
	}
	
	void add(Node pNode, int pPos) {
		mContent.add(pPos, pNode);
	}
	
	boolean del(Node pNode){
		if(!mContent.isEmpty()){
			return mContent.remove(pNode);
		}
		else return false;
	}
}
