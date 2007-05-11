package com.gerbildrop.config.dynamicReloading;

/**
 * In your code, you can make Exceptions more robust.  For example, show a dialog to the user, send an email to the QA
 * team, etc.
 *
 * @author Heinz Kabutz
 */
public class Exceptions {
    public static void throwException(Throwable e) {
        e.printStackTrace();
    }
}
