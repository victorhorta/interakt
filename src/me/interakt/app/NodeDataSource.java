package me.interakt.app;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/*
 Tabela symbols:

 _id
 bordercolor string (no formato "#AARRGGBB"; usar método parseColor(String s)
 label       string
 filename    string
 filepath    string
 */

public class NodeDataSource {
	private SQLiteDatabase database;
	private NodeDBHelper dbHelper;
	private String[] allColumns = { NodeDBHelper.COLUMN_ID,
			NodeDBHelper.PARENT_ID, NodeDBHelper.LABEL,
			NodeDBHelper.COLOR_HEX_STRING, NodeDBHelper.FILENAME,
			NodeDBHelper.FILEPATH };

	public NodeDataSource(Context context) {
		dbHelper = new NodeDBHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Node createNode(long parent, String label, String color,
			String filename, String filepath) {
		ContentValues values = new ContentValues();
		values.put(NodeDBHelper.PARENT_ID, parent);
		values.put(NodeDBHelper.LABEL, label);
		values.put(NodeDBHelper.COLOR_HEX_STRING, color);
		values.put(NodeDBHelper.FILENAME, filename);
		values.put(NodeDBHelper.FILEPATH, filepath);

		long insertId = database.insert(NodeDBHelper.TABLE_TITLE, null, values);

		Cursor cursor = database.query(NodeDBHelper.TABLE_TITLE, allColumns,
				NodeDBHelper.COLUMN_ID + " = " + insertId, null, null, null,
				null);
		cursor.moveToFirst();
		Node newNode = cursorToNode(cursor);
		cursor.close();
		return newNode;
	}

	public void deleteNode(Node node) {
		long id = node.getId();
		System.out.println("Comment deleted with id: " + id);

		// TODO: verificar remoção de um nó!!!
		// ERRO: Caso apague um nó, seus filhos nunca mais poderao ser
		// acessados!
		// Logo, tenho que prever isso e apagar também os filhos (ou tratar de
		// outra forma...)
		database.delete(NodeDBHelper.TABLE_TITLE, NodeDBHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Node> getAllNodes() {
		List<Node> nodes = new ArrayList<Node>();

		Cursor cursor = database.query(NodeDBHelper.TABLE_TITLE, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Node node = cursorToNode(cursor);
			nodes.add(node);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return nodes;
	}

	public ArrayList<Node> getAllNodesWithParentX(long x) {
		ArrayList<Node> nodes = new ArrayList<Node>();

		Cursor cursor = database.query(NodeDBHelper.TABLE_TITLE, allColumns,
				NodeDBHelper.PARENT_ID + "=" + Long.toString(x), null, null,
				null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Node node = cursorToNode(cursor);
			nodes.add(node);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		return nodes;
	}

	private Node cursorToNode(Cursor cursor) {
		Node node = new Node();
		node.setId(cursor.getLong(0));
		node.setParent(cursor.getLong(1));
		node.setLabel(cursor.getString(2));
		node.setColor(cursor.getString(3));
		node.setFilename(cursor.getString(4));
		node.setFilepath(cursor.getString(5));

		return node;
	}

}
