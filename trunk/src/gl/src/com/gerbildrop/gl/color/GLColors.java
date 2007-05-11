package com.gerbildrop.gl.color;

import java.nio.ByteBuffer;

public class GLColors {
    public static final ByteBuffer CLBlack = ByteBuffer.wrap(new byte[]{(byte) 0, (byte) 0, (byte) 0});
    public static final ByteBuffer CLWhite = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 255, (byte) 255});
    public static final ByteBuffer CLYellow = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 255, (byte) 0});
    public static final ByteBuffer CLGrey = ByteBuffer.wrap(new byte[]{(byte) 95, (byte) 95, (byte) 95});
    public static final ByteBuffer CLGreen = ByteBuffer.wrap(new byte[]{(byte) 0, (byte) 255, (byte) 0});
    public static final ByteBuffer CLAmber = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 96, (byte) 64});
    public static final ByteBuffer CLRed = ByteBuffer.wrap(new byte[]{(byte) 240, (byte) 0, (byte) 0});
    public static final ByteBuffer CLBlue = ByteBuffer.wrap(new byte[]{(byte) 140, (byte) 156, (byte) 255});
    public static final ByteBuffer CLMagenta = ByteBuffer.wrap(new byte[]{(byte) 255, (byte) 0, (byte) 200});

    public static enum COLORS {CLBLACK, CLWHITE, CLYELLOW, CLGREY, CLGREEN, CLAMBER, CLRED, CLBLUE, CLMAGENTA}

    public static ByteBuffer getColor(COLORS color) {
        switch (color) {
            case CLBLACK:
                return CLBlack;
            case CLWHITE:
                return CLWhite;
            case CLYELLOW:
                return CLYellow;
            case CLGREY:
                return CLGrey;
            case CLGREEN:
                return CLGreen;
            case CLAMBER:
                return CLAmber;
            case CLRED:
                return CLRed;
            case CLBLUE:
                return CLBlue;
            case CLMAGENTA:
                return CLMagenta;
            default:
                return CLWhite;
        }
    }
}
