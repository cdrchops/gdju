package com.gerbildrop.loader;

/**
 * @author vivaldi
 * @version $Id: LoadingStatus.java,v 1.1 2007/04/12 20:42:46 vivaldi Exp $
 * @since Oct 12, 2006
 */
public class LoadingStatus implements Comparable {
    private String display;
    private int range;

    public LoadingStatus(String display, int range) {
        this.display = display;
        this.range = range;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int compareTo(Object o) {
        Integer i = (Integer) o;

        if (i > range) {
            return 1;
        } else if (i < range) {
            return -1;
        } else if (i == range) {
            return 0;
        }

        return -1;
    }
}