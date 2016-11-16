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
import java.util.Random;
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

    private Map<String, ReservationT> listOfReservations = new HashMap<>();
    private List<Hotel> listOfHotels = new LinkedList<>();
    private Random random = new Random();
    
    /*Initial data*/
    {
        initializeHotelsList();
    }
    
    public org.netbeans.xml.schema.hotel.ReservationListT getHotels(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingQuerryT in) {
        
        ReservationListT reservationList = new ReservationListT();
        
        String city = in.getCity();
        XMLGregorianCalendarImpl arrivalDate = (XMLGregorianCalendarImpl) in.getArrivalDate();
        System.out.println(arrivalDate.getDay());
        XMLGregorianCalendarImpl departureDate = (XMLGregorianCalendarImpl) in.getDepartureDate();
        
        for(Hotel h:listOfHotels) {
            
            if(city.equals(h.getAddress().getCity()) && matchesPeriod(arrivalDate, h.getAvailableFrom(), departureDate, h.getAvailableTo())) {
                ReservationT reservation = new ReservationT();
                reservation.setAddress(h.getAddress());
                reservation.setBookingNumber(String.valueOf(random.nextInt(1000)));
                reservation.setHotelName(h.getName());
                reservation.setNeedGuarantee(h.isNeedGuarantee());
                reservation.setPrice(random.nextDouble()*100);
                reservation.setReservationService("All inclusive");
                reservationList.getNewElement().add(reservation);

            }
        }
        
        return reservationList;
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
        this.listOfReservations.put(name, reservation);
    }

    private void initializeHotelsList() {
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
        Hotel h = new Hotel("NiceHotel", address, availableFrom, availableTo, random.nextBoolean());
        this.listOfHotels.add(h);
    }
    
    private boolean matchesPeriod(XMLGregorianCalendarImpl arrivalDate,
            XMLGregorianCalendarImpl availableFrom,
            XMLGregorianCalendarImpl departureDate,
            XMLGregorianCalendarImpl availableTo) {
        
        boolean result = false;
        
        if(arrivalDate.getYear() < availableFrom.getYear()) {
            return result;
        }
        
        if(arrivalDate.getYear() >= availableFrom.getYear() &&
                departureDate.getYear() < availableTo.getYear()) {
            result = true;
        } else if(arrivalDate.getMonth() >= availableFrom.getMonth()&&
                departureDate.getMonth()< availableTo.getMonth()) {
            result = true;
        } else if(arrivalDate.getDay() >= availableFrom.getDay() &&
                departureDate.getDay() <= availableTo.getDay()) {
            result = true;
        }
        return result;
    }
}