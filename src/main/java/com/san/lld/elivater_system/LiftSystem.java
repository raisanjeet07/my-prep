package com.san.lld.elivater_system;

import java.util.ArrayList;
import java.util.List;

public class LiftSystem {
    static  List<Lift> liftList;
    static CentralCommandPanel centralCommandPanel;

    static List<OnFloorControlPanel> onFloorControlPanelList = new ArrayList<>(); // 1 - 10

    public static void main(String[] args){
        onFloorControlPanelList.get(2).directionToGo(0);
    }
}
