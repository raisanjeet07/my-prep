package com.coding.whatsapp.util;

import java.util.UUID;

public class Util {
    public static String getId(){
        return UUID.randomUUID().toString();
    }
}
