package me.interakt.app;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ReadBoard extends Activity implements OnTouchListener,
		android.view.View.OnClickListener {
	public static final String TAG = "ReadBoard";
	private static boolean isBeingClicked = false;

	public Node root;
	private ArrayList<Node> startNodes;
	private NodeDataSource nodeDataSource;
	private static int currentPage; // começa no 0, exibindo os seis primeiros,
									// e assim vai...

	ArrayList<TextView> labels = new ArrayList<TextView>();
	ArrayList<ImageView> symbols = new ArrayList<ImageView>();

	ImageView navLeft;
	ImageView navRight;
	EditText sentence;

	private ArrayList<GradientDrawable> genericBg = new ArrayList<GradientDrawable>();

	/*
	 * int textViewUp1 = R.id.textViewUp1; int textViewUp2 = R.id.textViewUp2;
	 * int textViewUp3 = R.id.textViewUp3; int textViewDown1 =
	 * R.id.textViewDown1; int textViewDown2 = R.id.textViewDown2; int
	 * textViewDown3 = R.id.textViewDown3;
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_board_root);

		// DataBase starters and loaders
		nodeDataSource = new NodeDataSource(this);
		nodeDataSource.open();

		// Root nodes and current page
		startNodes = nodeDataSource.getAllNodesWithParentX(0L);
		ReadBoard.currentPage = 0;

		// Assigning views...
		labels.add((TextView) findViewById(R.id.textViewUp1));
		labels.add((TextView) findViewById(R.id.textViewUp2));
		labels.add((TextView) findViewById(R.id.textViewUp3));
		labels.add((TextView) findViewById(R.id.textViewDown1));
		labels.add((TextView) findViewById(R.id.textViewDown2));
		labels.add((TextView) findViewById(R.id.textViewDown3));

		symbols.add((ImageView) findViewById(R.id.imageViewUp1));
		symbols.add((ImageView) findViewById(R.id.imageViewUp2));
		symbols.add((ImageView) findViewById(R.id.imageViewUp3));
		symbols.add((ImageView) findViewById(R.id.imageViewDown1));
		symbols.add((ImageView) findViewById(R.id.imageViewDown2));
		symbols.add((ImageView) findViewById(R.id.imageViewDown3));

		navLeft = (ImageView) findViewById(R.id.imageViewL);
		navRight = (ImageView) findViewById(R.id.imageViewR);

		sentence = (EditText) findViewById(R.id.edittext_sentence);

		// TODO Assigning other buttons

		// -----------------------

		// Creating a drawable for each symbol...
		for (int i = 0; i < 6; i++) {
			genericBg.add(new GradientDrawable());
			genericBg.get(i).setShape(GradientDrawable.RECTANGLE);
			genericBg.get(i).setStroke(5, Color.MAGENTA);
			genericBg.get(i).setColor(Color.WHITE);
			genericBg.get(i).setCornerRadius(3);
		}

		// Creating a drawable for left and right arrows...

		for (int i = 0; i < 2; i++) {
			genericBg.add(new GradientDrawable());
			genericBg.get(6 + i).setShape(GradientDrawable.RECTANGLE);
			genericBg.get(6 + i).setStroke(5, Color.GRAY);
			genericBg.get(6 + i).setColor(Color.WHITE);
			genericBg.get(6 + i).setCornerRadius(3);
		}

		// Setting click listeners to buttons...
		for (ImageView v : symbols) {
			v.setOnTouchListener(this);
		}

		navLeft.setOnTouchListener(this);
		navRight.setOnTouchListener(this);

		// TODO 1) show current buttons!
		updateBoard(ReadBoard.currentPage, startNodes, labels, symbols);
		/*
		 * int i = ReadBoard.currentPage; for (TextView t : labels) { if (i <
		 * startNodes.size()) { t.setText(startNodes.get(i).getLabel()); i++; }
		 * }
		 */
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (event.getPointerCount() > 1) {
			Log.d(TAG, "Multitouch detected - ain't doing nothing");
			return true;
		} else {
			// if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (event.getAction() == MotionEvent.ACTION_DOWN
					&& !ReadBoard.isBeingClicked) {
				((ImageView) v)
						.setBackgroundResource(R.drawable.image_bg_click);

				Log.d(TAG, "onTouched...");
				ReadBoard.isBeingClicked = true;
				return true;
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				((ImageView) v).setBackgroundResource(R.drawable.image_bg);

				switch (v.getId()) {
				case R.id.imageViewL:
					Log.d(TAG, "Nav - LEFT");
					moveLeft();
					break;

				case R.id.imageViewR:
					Log.d(TAG, "Nav - RIGHT");
					moveRight();
					break;

				case R.id.imageViewUp1:
					Log.d(TAG, "Nav - imgUp1");
					selectedASymbol(0);
					break;

				case R.id.imageViewUp2:
					Log.d(TAG, "Nav - imgUp2");
					selectedASymbol(1);
					break;

				case R.id.imageViewUp3:
					Log.d(TAG, "Nav - imgUp3");
					selectedASymbol(2);
					break;

				case R.id.imageViewDown1:
					Log.d(TAG, "Nav - imgDown1");
					selectedASymbol(3);
					break;

				case R.id.imageViewDown2:
					Log.d(TAG, "Nav - imgDown2");
					selectedASymbol(4);
					break;

				case R.id.imageViewDown3:
					Log.d(TAG, "Nav - imgDown3");
					selectedASymbol(5);
					break;

				default:
					Log.d(TAG, "onTouched: barro");
					return true;
				}
				ReadBoard.isBeingClicked = false;
				return true;
			}
		}

		return false;
	}

	private void moveRight() {
		if (canWeMoveItToTheRight()) {
			int origin = 6 * ++ReadBoard.currentPage;
			for (int i = 0; i < 6; i++) {
				// se houver 'node' pra ser exibido na posicao i, ele será!
				if ((origin + i) <= (startNodes.size() - 1)) {
					// TODO setar imagem no symbols.get(i).set, tornando visível
					// o symbol e a strin
					
					labels.get(i)
							.setText(startNodes.get(origin + i).getLabel());
					genericBg.get(i % 6).setColor(Color.WHITE);
					genericBg.get(i % 6).setStroke(5,
							Color.parseColor(startNodes.get(i).getColor()));
					symbols.get(i).setAlpha(255);
					symbols.get(i).setBackgroundDrawable(genericBg.get(i % 6));
					symbols.get(i).setOnTouchListener(this);

				} else {
					// TODO limpar a imagem do ImageView
					/*
					labels.get(i).setText("");
					genericBg.get(i % 6).setStroke(5, Color.BLACK);
					symbols.get(i).setBackgroundDrawable(genericBg.get(i % 6));
					symbols.get(i).setOnTouchListener(null);
					*/
					labels.get(i).setText("");
					symbols.get(i).setAlpha(0);
					genericBg.get(i % 6).setStroke(5, Color.BLACK);
					genericBg.get(i % 6).setColor(Color.BLACK);
					symbols.get(i).setBackgroundDrawable(genericBg.get(i % 6));
					symbols.get(i).setClickable(false);
					symbols.get(i).setOnTouchListener(null);
				}
			}
		}
	}

	private void moveLeft() {
		if (canWeMoveItToTheLeft()) {
			int origin = 6 * --ReadBoard.currentPage;
			for (int i = 0; i < 6; i++) {
				// TODO setar imagem no symbols.get(i).set, tornando visível o
				// symbol e a string
				labels.get(i).setText(startNodes.get(origin + i).getLabel());
				genericBg.get(i % 6).setStroke(5,
						Color.parseColor(startNodes.get(i).getColor()));
				symbols.get(i).setBackgroundDrawable(genericBg.get(i % 6));
			}
		}
	}

	private boolean canWeMoveItToTheRight() {
		if (startNodes.size() <= 6 * (ReadBoard.currentPage + 1))
			return false;
		return true;
	}

	private boolean canWeMoveItToTheLeft() {
		if (startNodes.size() <= 6)
			return false;
		if (ReadBoard.currentPage == 0)
			return false;
		return true;
	}

	public void updateBoard(int pCurrentPage, ArrayList<Node> pStartNodes,
			ArrayList<TextView> pLabel, ArrayList<ImageView> pSymbols) {
		for (int i = pCurrentPage * 6; i < (pCurrentPage + 1) * 6; i++) {
			if (i < pStartNodes.size()) {
				pLabel.get(i).setText(pStartNodes.get(i).getLabel());
				// TODO como mostrar imagem?
				// pSymbols.get(i).setImageBitmap(??carteei???);

				genericBg.get(i % 6).setStroke(5,
						Color.parseColor(pStartNodes.get(i).getColor()));
				pSymbols.get(i).setBackgroundDrawable(genericBg.get(i % 6));
				pSymbols.get(i).setClickable(true);
				pSymbols.get(i).setOnTouchListener(this);
				
			} else {
				pLabel.get(i).setText("");
				// TODO como esconder imagem?
				pSymbols.get(i).setAlpha(0);

				genericBg.get(i % 6).setStroke(5, Color.BLACK);
				genericBg.get(i % 6).setColor(Color.BLACK);
				pSymbols.get(i).setBackgroundDrawable(genericBg.get(i % 6));
				pSymbols.get(i).setClickable(false);
				pSymbols.get(i).setOnTouchListener(null);
				
			}
		}
	}

	public void selectedASymbol(int i) {
		// TODO: fazer um booleano que indique: caso seja raiz, nao concatenar
		// sentença! caso contrário, concatenar!

		// Marking the index of the first symbol on screen
		int origin = 6 * ReadBoard.currentPage;

		// It can select a node that is out of
		// bounds
		if (origin + i <= startNodes.size() - 1) {
			// Resetting the page number
			ReadBoard.currentPage = 0;

			//This is the selected node!
			Node selectedNode = startNodes.get(origin + i);

			// Updating the sentence and storing the parent of the future
			// startNodes...
			sentence.append(selectedNode.getLabel() + " ");
			Long parent = selectedNode.getId();

			// Refreshing startNodes!

			// TODO: what if startNodes is now empty? If so, the sentence has
			// ended,
			// and we should end the sentence
			startNodes = nodeDataSource.getAllNodesWithParentX(parent);
			updateBoard(ReadBoard.currentPage, startNodes, labels, symbols);
		}

	}
}
