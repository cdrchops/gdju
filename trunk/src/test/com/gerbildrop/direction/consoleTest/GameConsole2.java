package com.gerbildrop.direction.consoleTest;


import java.util.HashMap;

import com.captiveimagination.game.console.command.CommandProcessor;
import com.captiveimagination.game.control.FloatSpring;
import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;
import com.jme.math.Vector2f;
import com.jme.renderer.Renderer;
import com.jme.system.DisplaySystem;
import com.jmex.game.state.BasicGameState;

public class GameConsole2 extends BasicGameState implements KeyInputListener {
    MessageHistory history;

	private int key;

	private TextField entry;
	private TextualCursor cursor;

	CommandHistory commandHistory;
	private boolean echo;

	private float targetOnState;
	private FloatSpring spring;

	private String currentMode;
	private HashMap<String, CommandProcessor> modes;

	private Vector2f onPosition;
	private Vector2f offPosition;
	private boolean on;


	public GameConsole2(int key, int rows, boolean echo) {
		this(key, rows, echo,
				new float[]{0, 20, 0, 0},
				new Vector2f(0, 0),
				new Vector2f(0, (((5 + 1) * 14.0f) + 10.0f + 5)),
				DisplaySystem.getDisplaySystem().getWidth());
	}

	public GameConsole2(int key, int rows, boolean echo,
			float[] borders,
			Vector2f onPosition, Vector2f offPosition,
			float width) {
		super("GameConsole");
		this.key = key;
		commandHistory = new CommandHistory();
		this.echo = echo;

		this.onPosition = onPosition;
		this.offPosition = offPosition;

        init(borders, width);

    }

	private void init(float[] borders, float width) {
		modes = new HashMap<String, CommandProcessor>();

		getRootNode().setRenderQueueMode(Renderer.QUEUE_ORTHO);

		spring = new FloatSpring(400);

		//Start switched off, positioned off
		spring.setPosition(0);
		this.targetOnState = 0;
		this.on = false;


        history = new MessageHistory(borders, width, DisplaySystem.getDisplaySystem().getHeight() - 18.0f, rootNode);
        rootNode.attachChild(history.getBackdrop());

        entry = new TextField("Console Entry", "", 10.0f, history.getY());
		cursor = new TextualCursor("Cursor", 0.0f, history.getY(), entry, false);
		TextField bracket = new TextField("Bracket", ">", 0.0f, history.getY());
		bracket.getText().getLocalTranslation().x = -2.0f;

        rootNode.attachChild(entry.getText());
        rootNode.attachChild(cursor.getText());
        rootNode.attachChild(bracket.getText());

        getRootNode().updateRenderState();

		KeyInput.get().addListener(this);
	}

	public void update(float time) {
		super.update(time);

		spring.update(targetOnState, time);

		float onNess = spring.getPosition();
		getRootNode().getLocalTranslation().x = onPosition.x * onNess + offPosition.x * (1-onNess);
		getRootNode().getLocalTranslation().y = onPosition.y * onNess + offPosition.y * (1-onNess);
	}

	public void registerCommandProcessor(String name, CommandProcessor processor) {
		name = name.toLowerCase();
		if (currentMode == null) {
			currentMode = name;
		}
		modes.put(name, processor);
	}

	public void enter() {
        cursor.enter();

        String s = cursor.getCurrent();
        if (echo) {
            history.log(s);
        }

        execute(s);
        commandHistory.enter(s);
    }

	public void execute(String command) {
//		CommandProcessor processor = modes.get(currentMode);
//
//		if (command.startsWith("mode")) {
//			if (command.length() > "mode ".length()) {
//				String mode = command.substring("mode ".length());
//				mode = mode.toLowerCase();
//				processor = modes.get(mode);
//				if (processor != null) {
//					history.log("Mode Changed: " + mode);
//					currentMode = mode;
//				} else {
//					history.log("Unknown Mode: " + mode);
//				}
//			} else {
//				StringBuffer buffer = new StringBuffer();
//				for (String mode : modes.keySet()) {
//					if (buffer.length() > 0) buffer.append(", ");
//					buffer.append(mode);
//				}
//				history.log("Current Mode: " + currentMode + " (Available: " + buffer + ")");
//			}
//			return;
//		}

        history.log("No processor");
	}



	public void onKey(char character, int keyCode, boolean pressed) {
		if (pressed) {
			if (keyCode == key) {
                on = !on;
				targetOnState = on ? 1 : 0;
            } else if (on) {
                if (keyCode == KeyInput.KEY_BACK) {
                    cursor.backspace();
                } else if (keyCode == KeyInput.KEY_RETURN) {
                    enter();
                } else if (keyCode == KeyInput.KEY_PGUP) {
                    history.moveUpInHistory();
                } else if (keyCode == KeyInput.KEY_PGDN) {
                    history.moveDownInHistory();
                } else if (keyCode == KeyInput.KEY_LEFT) {
                    cursor.moveCursorLeft();
                } else if (keyCode == KeyInput.KEY_RIGHT) {
                    cursor.moveCursorRight();
                } else if (keyCode == KeyInput.KEY_UP) {
                    commandHistory.moveUpInCommandHistory(cursor);
                } else if (keyCode == KeyInput.KEY_DOWN) {
                    commandHistory.moveDownInCommandHistory(cursor);
                } else if (keyCode == KeyInput.KEY_HOME) {
                    cursor.moveCursorHome();
                } else if (keyCode == KeyInput.KEY_END) {
                    cursor.moveCursorEnd();
                } else {
                    cursor.type(character);
                }
            }
        }
	}
}