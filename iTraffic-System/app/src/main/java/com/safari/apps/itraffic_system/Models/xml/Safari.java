package com.safari.apps.itraffic_system.Models.xml;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/*
Klassen, die die Daten vom Server in Form von Objekten speichert
In diesem Fall Safari
*/


@Root
public class Safari
{
    @Element(required=false)
    private String data;

    @Element(required=false)
    private Header header;

    public String getData ()
    {
        return data;
    }

    public void setData (String data)
    {
        this.data = data;
    }

    public Header getHeader ()
    {
        return header;
    }

    public void setHeader (Header header)
    {
        this.header = header;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [data = "+data+", header = "+header+"]";
    }
}
