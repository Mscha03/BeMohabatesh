package com.example.bemohabatesh.utils.convert;

public class BoolInt {

    public static int boolToInt(boolean b) {
        if (b) {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean intToBool(int i) {
        if (i == 0) {
            return false;
        } else return i == 1;
    }
}
