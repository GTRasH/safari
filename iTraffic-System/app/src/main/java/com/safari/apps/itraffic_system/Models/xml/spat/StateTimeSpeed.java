package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall StateTimeSpeed der SPaT Nachricht
*/

@Root(name="state-time-speed")
public class StateTimeSpeed {

    @Element
    private MovementEvent MovementEvent;

    public MovementEvent getMovementEvent ()
    {
        return MovementEvent;
    }

    public void setMovementEvent (MovementEvent MovementEvent)
    {
        this.MovementEvent = MovementEvent;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MovementEvent = "+MovementEvent+"]";
    }

}
