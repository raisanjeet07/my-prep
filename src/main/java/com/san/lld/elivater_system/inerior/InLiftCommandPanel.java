package com.san.lld.elivater_system.inerior;

import com.san.lld.elivater_system.ButtonFactory;
import com.san.lld.elivater_system.Lift;

import java.util.List;

public class InLiftCommandPanel {
    List<Button> buttonList;
    Lift lift;

    public InLiftCommandPanel(int startFloors, int endFloors, Lift lift){
        buttonList = ButtonFactory.getButtons(startFloors, endFloors);
        this.lift = lift;
    }

    public void gotoFloor(int floorNo){

    }
}
