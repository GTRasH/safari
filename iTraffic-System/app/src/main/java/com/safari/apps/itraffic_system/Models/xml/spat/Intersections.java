package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall Intersections der SPaT Nachricht
*/

@Root
public class Intersections
{
    @Element
    private IntersectionState IntersectionState;

    public IntersectionState getIntersectionState ()
    {
        return IntersectionState;
    }

    public void setIntersectionState (IntersectionState IntersectionState)
    {
        this.IntersectionState = IntersectionState;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [IntersectionState = "+IntersectionState+"]";
    }
}
