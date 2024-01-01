package com.san.lld.elivater_system;

import com.san.lld.elivater_system.inerior.Door;
import com.san.lld.elivater_system.inerior.InLiftCommandPanel;
import com.san.lld.elivater_system.inerior.Status;

import java.util.Queue;

public class Lift {
    Door door;
    Queue<Command> commandQueue;
    Status status;

    Command currentExecutingCommand;

    InLiftCommandPanel inLiftCommandPanel;

    int startFloor;
    int endFloor;


    public Lift(int startFloor, int endFloor){
        door = new Door();
        inLiftCommandPanel = new InLiftCommandPanel(startFloor, endFloor, this);
    }



    public boolean acceptCommand(Command command){
        return false;
    }

    private void executeCommand(Command command){}

    public void notifySource(Command command){}
}
