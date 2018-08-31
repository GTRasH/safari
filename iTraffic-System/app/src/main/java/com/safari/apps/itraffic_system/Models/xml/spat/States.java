package com.safari.apps.itraffic_system.Models.xml.spat;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall States der SPaT Nachricht
*/

@Root
public class States
{
    @Element
    private MovementState MovementState;

    public MovementState getMovementState ()
    {
        return MovementState;
    }

    public void setMovementState (MovementState MovementState)
    {
        this.MovementState = MovementState;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [MovementState = "+MovementState+"]";
    }
}
