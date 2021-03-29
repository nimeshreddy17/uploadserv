package com.uploadserv.service;

import com.uploadserv.constants.Constants;
import com.uploadserv.interfaces.INotificationService;
import com.uploadserv.interfaces.UploadHandlerInterface;
import com.uploadserv.models.TicketUpdate;
import com.uploadserv.validator.Validator;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.uploadserv.constants.Constants.*;


@Component
public class UploadHandlerService implements UploadHandlerInterface {

    @Inject
    private INotificationService emailNotificationServiceImpl;

    private static final Logger LOG = LoggerFactory.getLogger(UploadHandlerService.class);


    /**
     * @param input Input row to be parsed
     * @return String[]
     */
    @Override
    public Map<String, String[]> parse(String[] input) {
        Map<String, String[]> outputMap = new HashMap<>();

        // Validate will throw appropriate business Exception
        Validator.validate(input);

        // The order should be changed here as well
        TicketUpdate ticketUpdate = new TicketUpdate();
        ticketUpdate.setFirstName(input[0]);
        ticketUpdate.setLastName(input[1]);
        ticketUpdate.setPnr(input[2]);

        ticketUpdate.setFareClass(input[3]);
        ticketUpdate.setTravelDate(input[4]);
        ticketUpdate.setPax(Integer.parseInt(input[5].trim()));

        ticketUpdate.setTicketingDate(input[6]);
        ticketUpdate.setEmail(input[7]);
        ticketUpdate.setMobilePhone(input[8]);
        ticketUpdate.setBookedCabin(getCabin(input[9]));

        //Adding logic for offer codes
        input = Arrays.copyOf(input, input.length + 1);
        input[input.length - 1] = getOfferCode(ticketUpdate.getFareClass());
        outputMap.put(Constants.SUCCESS, input);
        return outputMap;

    }


    //Business Check
    // Apart from the validation, we need to add a new column called discount code
    // to the processed records file whose value will be calculated based on the fare class field
    // in the input record. Fare class A - E will have discount code OFFER_20,
    // F - K will have discount code OFFER_30, L - R will have OFFER_25; rest will have no offer code
    private String getOfferCode(String fareClass) {

        //TODO SEND AN EMAIL TO THE CUSTOMER USING THE EMAIL NOTIFICATION SERVICE
        if (StringUtils.isNotBlank(fareClass)) {
            char curr = fareClass.trim().charAt(0);
            if (curr >= 65 && curr <= 69) {
                return OFFER_20;
            } else if (curr >= 70 && curr <= 75) {
                return OFFER_30;
            } else if (curr >= 76 && curr <= 82) {
                return OFFER_25;
            }
        }
        return "";
    }

    private TicketUpdate.BookedCabin getCabin(String cabin) {

        if (StringUtils.isNotBlank(cabin)) {
            return TicketUpdate.BookedCabin.fromValue(cabin.trim());
        }
        return null;
    }

}