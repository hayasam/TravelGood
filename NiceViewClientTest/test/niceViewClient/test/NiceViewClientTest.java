/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niceViewClient.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.CardNotFound;
import org.netbeans.j2ee.wsdl.niceview.data.niceview.InsufficientFounds;
import org.netbeans.xml.schema.hotel.ReservationListT;

/**
 *
 * @author andreisuciu
 */
public class NiceViewClientTest {
    
    public NiceViewClientTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {
        try {
            bookHotel(null);
        } catch (InsufficientFounds ex) {
            Logger.getLogger(NiceViewClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CardNotFound ex) {
            Logger.getLogger(NiceViewClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ReservationListT bookHotel(org.netbeans.j2ee.wsdl.niceview.data.niceview.BookingRequestT in) throws InsufficientFounds, CardNotFound {
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService service = new org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewService();
        org.netbeans.j2ee.wsdl.niceview.data.niceview.NiceViewPortType port = service.getNiceViewPortTypeBindingPort();
        return port.bookHotel(in);
    }
}
