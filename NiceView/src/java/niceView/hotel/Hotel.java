/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView.hotel;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import javax.xml.datatype.XMLGregorianCalendar;
import org.netbeans.xml.schema.hotel.AddressT;

/**
 *
 * @author andreisuciu
 */
public class Hotel {
    private String name;
    private AddressT address;
    private XMLGregorianCalendar availableFrom;
    private XMLGregorianCalendar availableTo;
    
    public Hotel() {
    }
    
    public Hotel(String name, AddressT address,
            XMLGregorianCalendarImpl availableFrom,
            XMLGregorianCalendarImpl availableTo) {
        this.name = name;
        this.address = address;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AddressT getAddress() {
        return address;
    }

    public void setAddress(AddressT address) {
        this.address = address;
    }

    public XMLGregorianCalendar getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(XMLGregorianCalendar availableFrom) {
        this.availableFrom = availableFrom;
    }

    public XMLGregorianCalendar getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(XMLGregorianCalendar availableTo) {
        this.availableTo = availableTo;
    }
}
