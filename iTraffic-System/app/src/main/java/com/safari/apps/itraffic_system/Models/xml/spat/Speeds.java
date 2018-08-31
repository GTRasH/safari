package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall Speeds der SPaT Nachricht
*/

@Root
public class Speeds {

    @Element
    private AdvisorySpeed AdvisorySpeed;

    public AdvisorySpeed getAdvisorySpeed ()
    {
        return AdvisorySpeed;
    }

    public void setAdvisorySpeed (AdvisorySpeed AdvisorySpeed)
    {
        this.AdvisorySpeed = AdvisorySpeed;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [AdvisorySpeed = "+AdvisorySpeed+"]";
    }

}
