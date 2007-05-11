package com.gerbildrop.direction.consoleTest;

import java.util.LinkedList;

import com.captiveimagination.game.spatial.DialogBox;
import com.captiveimagination.game.util.TextureLoader;
import com.jme.image.Texture;
import com.jme.scene.Spatial;
import com.jme.scene.Node;
import com.jme.scene.state.AlphaState;
import com.jme.system.DisplaySystem;

public class MessageHistory {
    private LinkedList<String> history;
    private int historyPosition;
    private int maxHistory;
    private TextField[] rows;

    private Spatial backdrop;
    private float width;
    private float[] borders;
	public static int RIGHT 	= 0;
	public static int TOP 		= 1;
	public static int LEFT 		= 2;
	public static int BOTTOM 	= 3;

    public MessageHistory(float[] _borders, float _width, float _y, Node rootNode) {
        history = new LinkedList<String>();
        rows = new TextField[5];//set from constructor
        maxHistory = 250;
        this.width = _width;
        //Create default backdrop
        this.borders = _borders;
        backdrop = createDialogBackdrop();

        this.y = _y;
        init(rootNode);
    }

    public Spatial getBackdrop() {
        return backdrop;
    }
    float y;

    public void init(Node rootNode) {
        for (int i = 0; i < rows.length; i++) {
			rows[i] = new TextField("Console Line" + i, "", 10.0f, y);
            rootNode.attachChild(rows[i].getText());
            y -= 14.0f;
		}
    }

    public float getY() {
        return y;
    }

    public void log(String message) {
		if (history.size() >= maxHistory) {
			history.poll();
		}

        history.add(message);

        if (historyPosition != 0) {
            historyPosition++;
        }

		updateHistory();
	}

	public void updateHistory() {
		// Update history display
		int position = history.size() - rows.length - historyPosition;
        for (TextField row : rows) {
            if (position < 0) {
                row.print("");
            } else {
                row.print(history.get(position));
            }

            position++;
        }
    }

    public void moveUpInHistory() {
		if (historyPosition < history.size() - rows.length) {
			historyPosition++;
			updateHistory();
		}
	}

	public void moveDownInHistory() {
		if (historyPosition > 0) {
			historyPosition--;
			updateHistory();
		}
	}

    private Spatial createDialogBackdrop() {
		//Make default texture
		Texture dialogTexture = TextureLoader.loadUncompressedTexture("resources/dialogArea.png");

		//Make the dialog box
		DialogBox box = new DialogBox("box", dialogTexture);
		box.setDimension(width + borders[LEFT] + borders[RIGHT], calculateHeight(rows.length) + borders[TOP] + borders[BOTTOM]);
		box.setLocalTranslation(-borders[LEFT], DisplaySystem.getDisplaySystem().getRenderer().getHeight() - calculateHeight(rows.length) - borders[BOTTOM], 0.0f);

		//Create alpha state to blend using alpha
		AlphaState bas = DisplaySystem.getDisplaySystem().getRenderer().createAlphaState();
        bas.setBlendEnabled(true);
        bas.setSrcFunction(AlphaState.SB_SRC_ALPHA);
        bas.setDstFunction(AlphaState.DB_ONE_MINUS_SRC_ALPHA);
        bas.setTestEnabled(false);
        bas.setEnabled(true);

        //set box render state
        box.setRenderState(bas);
        box.updateRenderState();

        //return the box
		return box;
	}

    public static float calculateHeight(int _rows) {
        return ((_rows + 1) * 14.0f) + 10.0f;
    }

}