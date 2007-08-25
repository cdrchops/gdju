package com.gerbildrop.gc.data;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class FSUIPC {
    public FSUIPC() {
    }

    public byte getByte(int aOffset) {
        byte[] data = new byte[1];
        fsuipc_wrapper.ReadData(aOffset, 1, data);
        return data[0];
    }

    public short getShort(int aOffset) {
        ByteBuffer buf = ByteBuffer.allocate(2);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        byte[] data = new byte[2];
        fsuipc_wrapper.ReadData(aOffset, 2, data);
        buf.put(data, 0, 2);
        return buf.getShort(0);
    }

    public int getInt(int aOffset) {
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        byte[] data = new byte[4];
        fsuipc_wrapper.ReadData(aOffset, 4, data);
        buf.put(data, 0, 4);
        return buf.getInt(0);
    }

    public long getLong(int aOffset) {
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        byte[] data = new byte[8];
        fsuipc_wrapper.ReadData(aOffset, 8, data);
        buf.put(data, 0, 8);
        return buf.getLong(0);
    }

    public float getFloat(int aOffset) {
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        byte[] data = new byte[4];
        fsuipc_wrapper.ReadData(aOffset, 4, data);
        buf.put(data, 0, 4);
        return buf.getFloat(0);
    }

    public double getDouble(int aOffset) {
        ByteBuffer buf = ByteBuffer.allocate(8);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        byte[] data = new byte[8];

        fsuipc_wrapper.ReadData(aOffset, 8, data);
        buf.put(data, 0, 8);

        return buf.getDouble(0);
    }

    public String getString(int aOffset, int aLength) {
        byte[] data = new byte[aLength];
        fsuipc_wrapper.ReadData(aOffset, aLength, data);
        return String.valueOf(data);
    }

    public boolean getBoolean(int aOffset) {
        ByteBuffer buf = ByteBuffer.allocate(1);
        buf.order(ByteOrder.LITTLE_ENDIAN);
        byte[] data = new byte[1];
        fsuipc_wrapper.ReadData(aOffset, 1, data);
        buf.put(data, 0, 1);

        short bln = buf.getShort(0);

        return bln == 1;
    }
}