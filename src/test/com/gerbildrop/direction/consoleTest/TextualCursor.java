package com.gerbildrop.direction.consoleTest;

import com.jme.input.KeyInput;
import com.jme.input.KeyInputListener;

public class TextualCursor extends TextField implements KeyInputListener {
    private StringBuffer cursorBuffer = new StringBuffer();
	private StringBuffer current = new StringBuffer();

	private int cursorPosition;

    private TextField entry;

    public TextualCursor(final String name, final float x, final float y, TextField _entry, boolean addThisListener) {
        cursorBuffer = new StringBuffer(40);
		cursorBuffer.append(" _");
		current = new StringBuffer(40);

        this.entry = _entry;

        this._name = name;
        this._value = cursorBuffer.toString();
        this._x = x;
        this._y = y;

        init();
        if (addThisListener) {
            KeyInput.get().addListener(this);
        }
    }

    public boolean moveCursorLeft() {
		if (cursorPosition > 0) {
			cursorPosition--;
			cursorBuffer.deleteCharAt(1);
			textEntry.print(cursorBuffer);
			return true;
		}

        return false;
	}

	public void moveCursorHome() {
		while (moveCursorLeft()) {}
	}

	public boolean moveCursorRight() {
		if (cursorPosition < current.length()) {
			cursorPosition++;
			cursorBuffer.insert(1, ' ');
			textEntry.print(cursorBuffer);
			return true;
		}

        return false;
	}

	public void moveCursorEnd() {
		while (moveCursorRight()) {}
	}

    public void type(char character) {
		if (Character.isISOControl(character)) {
            return;
        }

		current.insert(cursorPosition++, character);
		cursorBuffer.insert(1, ' ');
		entry.print(current);
		textEntry.print(cursorBuffer);
	}

    public void enter() {
		if (current.length() > 0) {
			current.delete(0, current.length());
			cursorBuffer.delete(0, cursorBuffer.length());
			cursorBuffer.append(" _");
			cursorPosition = 0;
			entry.print(current);
			textEntry.print(cursorBuffer);
		}
	}

    public String getCurrent() {
        return current.toString();
    }

    public void setText(String text) {
		current.delete(0, current.length());
		current.append(text);
		cursorBuffer.delete(0, cursorBuffer.length());
		cursorBuffer.append(" _");
		for (int i = 0; i < text.length(); i++) {
			cursorBuffer.insert(1, ' ');
		}
		cursorPosition = text.length();
		entry.print(current);
		textEntry.print(cursorBuffer);
	}

    public void backspace() {
		if ((current.length() > 0) && (cursorPosition > 0)) {
			current.deleteCharAt(--cursorPosition);
			cursorBuffer.deleteCharAt(1);
			entry.print(current);
			textEntry.print(cursorBuffer);
		}
	}

    public void onKey(char character, int keyCode, boolean pressed) {
		if (pressed) {
            if (keyCode == KeyInput.KEY_BACK) {
				backspace();
			} else if (keyCode == KeyInput.KEY_RETURN) {
				enter();
			} else if (keyCode == KeyInput.KEY_PGUP) {
//				moveUpInHistory();
			} else if (keyCode == KeyInput.KEY_PGDN) {
//				moveDownInHistory();
			} else if (keyCode == KeyInput.KEY_LEFT) {
				moveCursorLeft();
			} else if (keyCode == KeyInput.KEY_RIGHT) {
				moveCursorRight();
			} else if (keyCode == KeyInput.KEY_UP) {
//				moveUpInCommandHistory();
			} else if (keyCode == KeyInput.KEY_DOWN) {
//				moveDownInCommandHistory();
			} else if (keyCode == KeyInput.KEY_HOME) {
				moveCursorHome();
			} else if (keyCode == KeyInput.KEY_END) {
				moveCursorEnd();
			} else {
				type(character);
			}
		}
	}
}