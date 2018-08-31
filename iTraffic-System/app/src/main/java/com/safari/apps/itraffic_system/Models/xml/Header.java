package com.safari.apps.itraffic_system.Models.xml;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall Header
*/

@Root
public class Header
{
    @Element(required=false)
    private String response;
    @Element(required=false)
    private String request;

    public String getResponse ()
    {
        return response;
    }

    public void setResponse (String response)
    {
        this.response = response;
    }

    public String getRequest ()
    {
        return request;
    }

    public void setRequest (String request)
    {
        this.request = request;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [response = "+response+", request = "+request+"]";
    }
}