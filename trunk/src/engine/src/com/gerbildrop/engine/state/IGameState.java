package com.gerbildrop.engine.state;

public interface IGameState {
    public void hideMainMenu();

    public void showMainMenu();

    public boolean isActive();

    public void setActive(boolean _active);
}
