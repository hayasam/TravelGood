/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView.ws;

import javax.jws.WebService;

/**
 *
 * @author andreisuciu
 */
@WebService(serviceName = "niceViewService", portName = "niceViewPortTypeBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/NiceView/data/niceView", wsdlLocation = "WEB-INF/wsdl/NiceViewWS/niceView.wsdl")
public class NiceViewWS {

    public org.netbeans.xml.schema.hotel.ReservationListT getHotels(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingQuerryT in) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public org.netbeans.xml.schema.hotel.ReservationListT bookHotel(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingRequestT in) throws org.netbeans.j2ee.wsdl.niceview.data.niceview.CardNotFound, org.netbeans.j2ee.wsdl.niceview.data.niceview.InsufficientFounds {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public boolean cancelHotel(java.lang.String in) throws org.netbeans.j2ee.wsdl.niceview.data.niceview.ItineraryStarted, org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingNotFound {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
