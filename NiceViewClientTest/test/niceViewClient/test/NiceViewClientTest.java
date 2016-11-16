/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niceViewClient.test;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingNotFound;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.CardNotFound;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.InsufficientFounds;
import org.netbeans.xml.schema.hotel.ReservationListT;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingQuerryT;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.ItineraryStarted;

/**
 *
 * @author andreisuciu
 */
public class NiceViewClientTest {
    
    public NiceViewClientTest() {
    }

    @Test
    public void testGetHotels() {
        BookingQuerryT bookingQuerry = new BookingQuerryT();
        XMLGregorianCalendarImpl arrival = new XMLGregorianCalendarImpl();
        XMLGregorianCalendarImpl departure = new XMLGregorianCalendarImpl();
        arrival.setDay(10);
        arrival.setMonth(11);
        arrival.setYear(2016);
        departure.setDay(14);
        departure.setMonth(11);
        departure.setYear(2016);
        bookingQuerry.setArrivalDate(arrival);
        bookingQuerry.setDepartureDate(departure);
        bookingQuerry.setCity("Copenhagen");
        ReservationListT res = getHotels(bookingQuerry);
        System.out.println("---" + res.getNewElement().get(0).getHotelName());
    }
    
    @Test
    public void testCancelHotel() {
        try {
            boolean result = cancelHotel("12345");
            if(result) {
                System.out.println("Cancellation ok");
            } else System.out.println("Error");
        } catch (BookingNotFound ex) {
            Logger.getLogger(NiceViewClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ItineraryStarted ex) {
            Logger.getLogger(NiceViewClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ReservationListT bookHotel(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingRequestT in) throws InsufficientFounds, CardNotFound {
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService service = new org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService();
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewPortType port = service.getNiceViewPortTypeBindingPort();
        return port.bookHotel(in);
    }

    private static ReservationListT getHotels(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingQuerryT in) {
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService service = new org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService();
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewPortType port = service.getNiceViewPortTypeBindingPort();
        return port.getHotels(in);
    }

    private static boolean cancelHotel(java.lang.String in) throws BookingNotFound, ItineraryStarted {
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService service = new org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService();
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewPortType port = service.getNiceViewPortTypeBindingPort();
        return port.cancelHotel(in);
    }
}
