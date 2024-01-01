package com.san.lld.elivater_system;

import com.san.lld.elivater_system.inerior.Button;

import java.util.ArrayList;
import java.util.List;

public class ButtonFactory {
    public static List<Button> getButtons(int s, int e){
        List<Button> buttons = new ArrayList<>();
        while (s <= e ){
          buttons.add(new Button(s));
          s++;
        }
        return buttons;
    }
}
