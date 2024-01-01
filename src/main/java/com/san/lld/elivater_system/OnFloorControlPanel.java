package com.san.lld.elivater_system;

import com.san.lld.elivater_system.inerior.Button;

import java.util.List;

public class OnFloorControlPanel {
    List<Button> buttonList;

    CentralCommandPanel centralCommandPanel;

    int floor;

    public OnFloorControlPanel(CentralCommandPanel centralCommandPanel, int floor){
        this.floor = floor;
        this.centralCommandPanel = centralCommandPanel;
    }

    public void directionToGo(int i){
        Command command = new Command(this.floor);
        centralCommandPanel.acceptCommand(command);
    }

}
