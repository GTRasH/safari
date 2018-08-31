package com.safari.apps.itraffic_system.Models.xml.spat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall Type der SPaT Nachricht
*/

@Root
public class Type {

    @Element(required=false)
    private String greenwave;

    public String getGreenwave ()
    {
        return greenwave;
    }

    public void setGreenwave (String greenwave)
    {
        this.greenwave = greenwave;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [greenwave = "+greenwave+"]";
    }

}
