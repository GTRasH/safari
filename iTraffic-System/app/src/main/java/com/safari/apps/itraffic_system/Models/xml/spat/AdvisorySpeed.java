package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall AdvisorySpeed der SPaT Nachricht
*/

@Root
public class AdvisorySpeed {

    @Element
    private String speed;

    @Element
    private Type type;

    public String getSpeed ()
    {
        return speed;
    }

    public void setSpeed (String speed)
    {
        this.speed = speed;
    }

    public Type getType ()
    {
        return type;
    }

    public void setType (Type type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [speed = "+speed+", type = "+type+"]";
    }

}
