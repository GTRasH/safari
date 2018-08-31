package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall Timing der SPaT Nachricht
*/

@Root
public class Timing {

    @Element
    private String likelyTime;

    public String getLikelyTime ()
    {
        return likelyTime;
    }

    public void setLikelyTime (String likelyTime)
    {
        this.likelyTime = likelyTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [likelyTime = "+likelyTime+"]";
    }

}
