package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall SPAT der SPaT Nachricht
*/

@Root
public class SPAT
{
    @Element
    private Intersections intersections;

    public Intersections getIntersections ()
    {
        return intersections;
    }

    public void setIntersections (Intersections intersections)
    {
        this.intersections = intersections;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [intersections = "+intersections+"]";
    }
}
