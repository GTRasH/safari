package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall IntersectionState der SPaT Nachricht
*/

@Root
public class IntersectionState
{  @Element
    private States states;

    public States getStates ()
    {
        return states;
    }

    public void setStates (States states)
    {
        this.states = states;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [states = "+states+"]";
    }
}
