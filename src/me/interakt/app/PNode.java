package me.interakt.app;

public class PNode {
	PSymbol mSymbol;
	PBoard mBoard;
	PNode(){
		mSymbol = new PSymbol();
		mBoard = new PBoard();
	}
	
	PNode(PSymbol pSymbol, PBoard pBoard){
		mSymbol = new PSymbol(pSymbol);
		mBoard = new PBoard(pBoard);
	}
	
	PNode(PSymbol pSymbol){
		mSymbol = new PSymbol(pSymbol);
		//mBoard = new PBoard(pBoard);
	}
	
	
	
	void setBoard(PBoard pBoard){
		mBoard = pBoard;
	}

}
