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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.xml.datatype.XMLGregorianCalendar;
import niceView.hotel.Hotel;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.ItineraryStarted;
import org.netbeans.xml.schema.bookingfaults.BookingNumberNotFoundFault;
import org.netbeans.xml.schema.bookingfaults.ItineraryStartedFault;
import org.netbeans.xml.schema.hotel.AddressT;
import org.netbeans.xml.schema.hotel.ReservationListT;
import org.netbeans.xml.schema.hotel.ReservationT;
/**
 *
 * @author andreisuciu
 */
@WebService(serviceName = "niceViewService", portName = "niceViewPortTypeBindingPort", endpointInterface = "org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewPortType", targetNamespace = "http://j2ee.netbeans.org/wsdl/NiceView/data/niceView", wsdlLocation = "WEB-INF/wsdl/NiceViewWS/niceView.wsdl")
public class NiceViewWS {

    private Map<String, List<ReservationT>> listOfReservations = new HashMap<>();
    //private Map<String, Itinerary> reservationNumberToItinerary = new HashMap<String, Itinerary>();
    private List<Hotel> listOfHotels = new LinkedList<>();
    private Map<String, String> reservationNumberToPeopleMap = new HashMap<>();
    private Random random = new Random();
    //private XMLGregorianCalendar currentTime = new XMLGregorianCalendarImpl();
    
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
    
    /**
     * The bookHotel operation takes a booking number and credit card information (depending
on whether a credit card guarantee is required or not) and books the hotel. If a guarantee is required, the
operation validateCreditCard from the Bank service is called. The bookHotel operation returns true, if
the booking was successful, and returns a fault (i.e., throws an exception) if the credit card information
was not valid, there was not enough money on the client account, or if for other reasons the booking
fails.
     * @param in
     * @return
     * @throws org.netbeans.j2ee.wsdl.niceview.data.niceview.CardNotFound
     * @throws org.netbeans.j2ee.wsdl.niceview.data.niceview.InsufficientFounds 
     */

    public org.netbeans.xml.schema.hotel.ReservationListT bookHotel(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingRequestT in) throws org.netbeans.j2ee.wsdl.niceview.data.niceview.CardNotFound, org.netbeans.j2ee.wsdl.niceview.data.niceview.InsufficientFounds {
        
        return null;
    }
    
    public boolean cancelHotel(java.lang.String in) throws org.netbeans.j2ee.wsdl.niceview.data.niceview.ItineraryStarted, org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingNotFound {
             
        if(reservationNumberToPeopleMap.containsKey(in)) {
//            ReservationT reservation = listOfReservations.get(reservationNumberToPeopleMap.get(in));
//            if(isItineraryStarted(reservation)) {
//                throw new ItineraryStarted("You can not cancel the hotel"
//                        + " because your itinerary has already started.", new ItineraryStartedFault());
//            }
            System.out.println("The size of resList is " + listOfReservations.get(reservationNumberToPeopleMap.get(in)).size() + " before cancelation.");
            //get the reservations list
            List<ReservationT> aux = listOfReservations.get(reservationNumberToPeopleMap.get(in));
            int removeIndex = 0;
            for(ReservationT res: aux) {
                //found the reservation to be removed
                if(res.getBookingNumber().equals(in)) {
                    break;
                }
                removeIndex++;
            }
            //remove the reservation
            aux.remove(removeIndex);
            listOfReservations.put(reservationNumberToPeopleMap.get(in), aux);
            
            System.out.println("Reservation " + in +  " has been canceled.");
            System.out.println("The size of resList is " +
                    listOfReservations.get(reservationNumberToPeopleMap.get(in)).size() + " after cancelation.");
            reservationNumberToPeopleMap.remove(in);
            return true;
        } else throw new org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingNotFound("The booking"
                + " with number " + in + " was not found.", new BookingNumberNotFoundFault());
    }
    
    private void addPersonToReservation(String name, ReservationT reservation) {
        if(listOfReservations.containsKey(name)) {
            List<ReservationT> r = this.listOfReservations.get(name);
            r.add(reservation);
            this.listOfReservations.put(name, r);
        } else {
            List<ReservationT> newList = new LinkedList<>();
            newList.add(reservation);
            listOfReservations.put(name, newList);
        }
        
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
        this.reservationNumberToPeopleMap.put("12345", "Andrei Suciu");
        ReservationT r = new ReservationT();
        r.setBookingNumber("12345");
        addPersonToReservation("Andrei Suciu", r);
        //this.listOfReservations = new HashMap<>();
//        getCurrentTime().setDay(9);
//        getCurrentTime().setMonth(10);
//        getCurrentTime().setYear(2016);
//        updateCurrentTime();
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
    
//    private boolean isItineraryStarted(ReservationT reservation) {
//        boolean result = false;
//        Itinerary itinerary = reservationNumberToItinerary.get(reservation.getBookingNumber());
//        XMLGregorianCalendarImpl start = itinerary.getStart();
//        XMLGregorianCalendarImpl end = itinerary.getEnd();
//        if(start.getDay() >= getCurrentTime().getDay() ||
//                start.getMonth() >= )
//        
//        return result;
//    }
//    
//    private void updateCurrentTime() {
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                while(true) {
//                    if(getCurrentTime().getDay() != 31) {
//                        //System.out.println(" --- " + currentTime.getDay());
//                        getCurrentTime().setDay(getCurrentTime().getDay() + 1);
//                    } else {
//                        getCurrentTime().setDay(1);
//                        if(getCurrentTime().getMonth()!= 12) {
//                            getCurrentTime().setMonth(getCurrentTime().getMonth() + 1);
//                        } else {
//                            getCurrentTime().setMonth(1);
//                            getCurrentTime().setYear(getCurrentTime().getYear() + 1);
//                        }
//                    }
//                    
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException ex) {
//                        Logger.getLogger(NiceViewWS.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                    System.out.println("Day: " + getCurrentTime().getDay() + " Month: " + getCurrentTime().getMonth() + " Year: " + getCurrentTime().getYear());
//                }
//            }
//        }).start();
//    }
//
//    public synchronized XMLGregorianCalendar getCurrentTime() {
//        return currentTime;
//    }
//
//    public synchronized void  setCurrentTime(XMLGregorianCalendar currentTime) {
//        this.currentTime = currentTime;
//    }
    
}