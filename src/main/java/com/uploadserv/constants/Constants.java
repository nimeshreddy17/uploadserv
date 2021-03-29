package com.uploadserv.constants;

import java.util.regex.Pattern;

public class Constants {
    public final static String[] expectedHeaderList =
            {"First_name", "Last_name", "PNR", "Fare_class", "Travel_date",
                    "Pax", "Ticketing_date", "Email", "Mobile_phone", "Booked_cabin"};

    public final static String[] successFileHeaderList =
            {"First_name", "Last_name", "PNR", "Fare_class", "Travel_date",
                    "Pax", "Ticketing_date", "Email", "Mobile_phone", "Booked_cabin", "Discount_code"};

    public final static String[] failureFileHeaderList =
            {"First_name", "Last_name", "PNR", "Fare_class", "Travel_date",
                    "Pax", "Ticketing_date", "Email", "Mobile_phone", "Booked_cabin", "Error"};

    public final static String SUCCESS = "SUCCESS";
    public final static String FAILURE = "FAILURE";

    public final static String OFFER_20 = "OFFER_20";
    public final static String OFFER_30 = "OFFER_30";
    public final static String OFFER_25 = "OFFER_25";

    public final static String FARE_CLASS_PATTERN = "^[A-Z]{1}$";
    public final static String PNR_PATTERN = "^[a-zA-Z0-9]{6}$";
    public final static String DATE_PATTERN = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
    public final static String MOBILE_NUMBER_PATTERN = "^[0-9]{10}$";
    public final static String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)$";


}
