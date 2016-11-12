/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lameduck.ws;

import javax.jws.WebService;

/**
 *
 * @author s134622
 */
@WebService(serviceName = "lameDuckService", portName = "lameDuckPortTypeBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.lameduck.data.lameduck.LameDuckPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/LameDuck/data/lameDuck", wsdlLocation = "WEB-INF/wsdl/LameDuckWS/lameDuck.wsdl")
public class LameDuckWS {

    public LameDuckWS() {
        System.setProperty("javax.xml.accessExternalSchema", "all");
    }
    
    

    public org.netbeans.xml.schema.hotel.ReservationListT getHotels(org.netbeans.j2ee.wsdl.lameduck.data.lameduck.BookingQuerryT in) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public org.netbeans.xml.schema.hotel.ReservationListT bookHotel(org.netbeans.j2ee.wsdl.lameduck.data.lameduck.BookingRequestT in) throws org.netbeans.j2ee.wsdl.lameduck.data.lameduck.InsufficientFounds, org.netbeans.j2ee.wsdl.lameduck.data.lameduck.CardNotFound {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean cancelHotel(java.lang.String in) throws org.netbeans.j2ee.wsdl.lameduck.data.lameduck.ItineraryStarted, org.netbeans.j2ee.wsdl.lameduck.data.lameduck.BookingNotFound {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
