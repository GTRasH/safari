package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall MovementEvent der SPaT Nachricht
*/

@Root
public class MovementEvent {

    @Element
    private Speeds speeds;

    @Element
    private Timing timing;

    public Speeds getSpeeds ()
    {
        return speeds;
    }

    public void setSpeeds (Speeds speeds)
    {
        this.speeds = speeds;
    }

    public Timing getTiming ()
    {
        return timing;
    }

    public void setTiming (Timing timing)
    {
        this.timing = timing;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [speeds = "+speeds+", timing = "+timing+"]";
    }

}
