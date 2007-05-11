package com.gerbildrop.direction.consoleTest;

import java.util.LinkedList;

public class CommandHistory {
    private LinkedList<String> commandHistory;
    private int maxCommandHistory;
    private int commandHistoryPosition;

    public CommandHistory() {
        commandHistory = new LinkedList<String>();

        maxCommandHistory = 250;
		commandHistoryPosition = -1;
    }


    public void enter(String s) {
        if (commandHistory.size() >= maxCommandHistory) {
            commandHistory.poll();
        }
        commandHistory.add(s);
    }

    public void moveUpInCommandHistory(TextualCursor cursor) {
		if (commandHistory.size() > 0) {
			if (commandHistoryPosition == 0) return;
			if (commandHistoryPosition == -1) {
				commandHistoryPosition = commandHistory.size();
			}
			commandHistoryPosition--;
			cursor.setText(commandHistory.get(commandHistoryPosition));
		}
	}

	public void moveDownInCommandHistory(TextualCursor cursor) {
		if (commandHistory.size() > 0) {
			if (commandHistoryPosition == commandHistory.size() - 1) {
				commandHistoryPosition = -1;
				cursor.setText("");
				return;
			}
			if (commandHistoryPosition == -1) return;
			commandHistoryPosition++;
			cursor.setText(commandHistory.get(commandHistoryPosition));
		}
	}

}
