/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niceViewClient.test;

import java.util.logging.Level;
import java.util.logging.Logger;
import niceViewClient.CardNotFound;
import niceViewClient.InsufficientFounds;
import niceViewClient.ReservationListT;
import org.junit.Test;
import static org.junit.Assert.*;

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
        } catch (CardNotFound ex) {
            Logger.getLogger(NiceViewClientTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InsufficientFounds ex) {
            Logger.getLogger(NiceViewClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static ReservationListT bookHotel(niceViewClient.BookingRequestT in) throws CardNotFound, InsufficientFounds {
        niceViewClient.NiceViewService service = new niceViewClient.NiceViewService();
        niceViewClient.NiceViewPortType port = service.getNiceViewPortTypeBindingPort();
        return port.bookHotel(in);
    }
}
