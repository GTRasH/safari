package com.safari.apps.itraffic_system.Models.xml.spat;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall MovementState der SPaT Nachricht
*/

@Root
public class MovementState
{
    @Element(name="state-time-speed")
    private StateTimeSpeed stateTimeSpeed;

    public StateTimeSpeed getStateTimeSpeed ()
{
    return stateTimeSpeed;
}

    public void setStateTimeSpeed (StateTimeSpeed stateTimeSpeed)
{
    this.stateTimeSpeed = stateTimeSpeed;
}

    @Override
    public String toString()
    {
        return "ClassPojo [state-time-speed = "+stateTimeSpeed+"]";
    }
}
