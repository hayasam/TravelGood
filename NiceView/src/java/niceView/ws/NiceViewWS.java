/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niceView.ws;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.jws.WebService;
import niceView.hotel.Hotel;
import org.netbeans.xml.schema.hotel.AddressT;
import org.netbeans.xml.schema.hotel.ReservationListT;
import org.netbeans.xml.schema.hotel.ReservationT;
/**
 *
 * @author andreisuciu
 */
@WebService(serviceName = "niceViewService", portName = "niceViewPortTypeBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/NiceView/data/niceView", wsdlLocation = "WEB-INF/wsdl/NiceViewWS/niceView.wsdl")
public class NiceViewWS {
    /**
     * The getHotels operation takes a city and the arrival date and the departure date. It returns
a list of hotels with the information containing the name of the hotel, the address of the hotel, a booking
number, the price for the whole stay at the hotel, whether a credit card guarantee is required or not,
and the name of the HotelReservation service.
     * @param in
     * @return 
     */
    private Map<, String> personsIds = new HashMap<>();
    private Map<String, ReservationT> listOfReservations = new HashMap<>();
    private List<Hotel> listOfHotels = new LinkedList<>();
    private int currentID;
    
    /*Initial data*/
    {
        initializeHotelsList();
        addPersonToReservation("Andrei Suciu");
        addPersonToReservation("Cezar Pirvu");
    }
    
    public org.netbeans.xml.schema.hotel.ReservationListT getHotels(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingQuerryT in) {
        
        ReservationListT reservationList = new ReservationListT();
        ReservationT reservation = new ReservationT();
        
        reservationList.getNewElement().add(reservation);
        return null;
    }

    public org.netbeans.xml.schema.hotel.ReservationListT bookHotel(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingRequestT in) throws org.netbeans.j2ee.wsdl.niceview.data.niceview.CardNotFound, org.netbeans.j2ee.wsdl.niceview.data.niceview.InsufficientFounds {
        //TODO implement this method
        System.out.println("Totul bine aici. Merge NiceView");
        return null;
    }

    public boolean cancelHotel(java.lang.String in) throws org.netbeans.j2ee.wsdl.niceview.data.niceview.ItineraryStarted, org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingNotFound {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    private void addPersonToReservation(String name, ReservationT reservation) {
        this.personsIds.put(currentID, name);
        this
        this.currentID++;
    }

    private void initializeHotelsList() {
        //make a for
        AddressT address = new AddressT();
        address.setCity("Copenhagen");
        address.setCountry("Denmark");
        address.setStreet("H.C. Andersen");
        XMLGregorianCalendarImpl availableFrom = new XMLGregorianCalendarImpl();
        availableFrom.setDay(10);
        availableFrom.setMonth(10);
        availableFrom.setYear(2016);
        XMLGregorianCalendarImpl availableTo = new XMLGregorianCalendarImpl();
        availableTo.setDay(10);
        availableTo.setMonth(12);
        availableTo.setYear(2016);
        Hotel h = new Hotel("NieHotel", address, availableFrom, availableTo);
        this.listOfHotels.add(h);
    }
    
    //private void addReservationToReservationList
}
