package com.uploadserv.validator;

import com.uploadserv.constants.Constants;
import com.uploadserv.exceptions.BusinessException;
import com.uploadserv.models.TicketUpdate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.uploadserv.constants.Constants.*;
import static com.uploadserv.constants.Constants.expectedHeaderList;

@Component
public class Validator {


    public static void validate(String[] input) {
        FieldValidator.validate(input[3], expectedHeaderList[3], FARE_CLASS_PATTERN);
        FieldValidator.validate(input[7], expectedHeaderList[7], EMAIL_PATTERN);
        FieldValidator.validate(input[8], expectedHeaderList[8], MOBILE_NUMBER_PATTERN);
        FieldValidator.validate(input[2], expectedHeaderList[2], PNR_PATTERN);
        FieldValidator.validate(input[4], expectedHeaderList[4], DATE_PATTERN);
        FieldValidator.validate(input[6], expectedHeaderList[6], DATE_PATTERN);

        validateBookedCabin(input[9], expectedHeaderList[9]);
        validateBookingDate(input[4], input[6]);
        validateNumber(input[5], expectedHeaderList[5]);
    }


    private static void validateBookedCabin(String input, String fieldName) {
        try {
            TicketUpdate.BookedCabin.fromValue(input.trim());
        } catch (Exception e) {
            throw new BusinessException("Field " + fieldName + " is invalid",
                    fieldName,
                    input,
                    HttpStatus.BAD_REQUEST);
        }
    }

    private static void validateNumber(String input, String fieldName) {
        try {
            if (StringUtils.isBlank(input)) {
                throw new BusinessException("Field " + fieldName + " is invalid",
                        fieldName,
                        input,
                        HttpStatus.BAD_REQUEST);
            }

            Integer.parseInt(input.trim());
        } catch (Exception e) {
            throw new BusinessException("Field " + fieldName + " is invalid",
                    fieldName,
                    input,
                    HttpStatus.BAD_REQUEST);
        }
    }

    private static void validateBookingDate(String travelDateString, String ticketingDateString) {
        //Ticketing date is before travel date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date travelDate = simpleDateFormat.parse(travelDateString);
            Date ticketingDate = simpleDateFormat.parse(ticketingDateString);
            if (ticketingDate.after(travelDate)) {
                throw new BusinessException("Ticketing date should be before Travel date",
                       "TicketingDate" ,
                        "DATE_VALIDATION",
                        HttpStatus.BAD_REQUEST);
            }
        } catch (ParseException e) {
            throw new BusinessException("Date parsing failed",
                    "Date",
                    "Date",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public static void validateHeader(String[] inputHeaderList) {
        for (int i = 0; i < Constants.expectedHeaderList.length; i++) {
            if (StringUtils.isBlank(inputHeaderList[i]) || !Constants.expectedHeaderList[i].equals(inputHeaderList[i].trim())) {
                throw new BusinessException("Header doesn't match with expectedHeader. Given: " + inputHeaderList[i] + " Expected: " + expectedHeaderList[i],
                        "header",
                        expectedHeaderList[i],
                        HttpStatus.BAD_REQUEST);
            }
        }
    }
}
