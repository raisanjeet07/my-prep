package com.san.lld.elivater_system;

import java.util.List;
import java.util.Queue;

public class CentralCommandPanel {
    List<Lift> lifts;

    Queue<Command> commandQueue;

    public boolean acceptCommand(Command command){
        return lifts.stream().anyMatch(lift -> lift.acceptCommand(command));
    }

    public void notifySource(Command command){}
}
