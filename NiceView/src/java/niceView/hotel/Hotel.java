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
    private XMLGregorianCalendarImpl availableFrom;
    private XMLGregorianCalendarImpl availableTo;
    private boolean needGuarantee;
    
    public Hotel() {
    }
    
    public Hotel(String name, AddressT address,
            XMLGregorianCalendarImpl availableFrom,
            XMLGregorianCalendarImpl availableTo,
            boolean needGuarantee) {
        this.name = name;
        this.address = address;
        this.availableFrom = availableFrom;
        this.availableTo = availableTo;
        this.needGuarantee = needGuarantee;
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

    public XMLGregorianCalendarImpl getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(XMLGregorianCalendarImpl availableFrom) {
        this.availableFrom = availableFrom;
    }

    public XMLGregorianCalendarImpl getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(XMLGregorianCalendarImpl availableTo) {
        this.availableTo = availableTo;
    }

    public boolean isNeedGuarantee() {
        return needGuarantee;
    }

    public void setNeedGuarantee(boolean needGuarantee) {
        this.needGuarantee = needGuarantee;
    }
    
}
