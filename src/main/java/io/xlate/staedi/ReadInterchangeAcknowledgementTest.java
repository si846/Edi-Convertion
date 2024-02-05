package io.xlate.staedi;

import java.io.FileInputStream;
import java.io.InputStream;

import io.xlate.edi.stream.EDIInputFactory;
import io.xlate.edi.stream.EDIStreamEvent;
import io.xlate.edi.stream.EDIStreamReader;

public class ReadInterchangeAcknowledgementTest {
   
     public boolean isAcknowledgementSuccess() throws Exception {
        EDIInputFactory factory = EDIInputFactory.newFactory();
        String ta104 = null;
        String ta105 = null;

        /* (1) Open the EDI file - any InputStream can be used. */
        try (InputStream stream = new FileInputStream("C:/Users/Siddayya/Downloads/staedi/target/test-classes/io/xlate/AcknowledgementStatus.edi")) {
            /* (2) Create a new EDIStreamReader */
            EDIStreamReader reader = factory.createEDIStreamReader(stream);
            EDIStreamEvent event;
            String segment = null;

            /* (3) Loop over the reader's events */
            while (reader.hasNext()) {
                event = reader.next();

                if (event == EDIStreamEvent.START_SEGMENT) {
                    /* (4)
                     * Each time a segment is encountered, save the
                     * segment tag in a local variable */
                    segment = reader.getText();
                } else if (event == EDIStreamEvent.ELEMENT_DATA) {
                    if ("TA1".equals(segment)) {
                        /* (5)
                         * When reading element data, if the current
                         * segment is TA1 and the current element is
                         * in either position 4 or 5, save the element
                         * data in a local variable */
                        if (reader.getLocation().getElementPosition() == 4) {
                            ta104 = reader.getText();
                        } else if (reader.getLocation().getElementPosition() == 5) {
                            ta105 = reader.getText();
                        }
                    }
                }
            }
        }

        return "A".equals(ta104) && "000".equals(ta105);
    }
}
