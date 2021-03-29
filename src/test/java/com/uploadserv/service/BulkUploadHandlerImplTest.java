package com.uploadserv.service;


import com.uploadserv.interfaces.BulkUploadHandler;
import com.uploadserv.models.TicketUpdateResponse;
import com.uploadserv.validator.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.uploadserv.constants.Constants.*;


public class BulkUploadHandlerImplTest {


    private Validator validator = new Validator();
    private UploadHandlerService uploadHandlerService = new UploadHandlerService();

    private BulkUploadHandlerImpl bulkUploadHandler = new BulkUploadHandlerImpl(validator, uploadHandlerService);

    private static final String INVALID_PNR = "Invalid PNR";
    private static final String INVALID_Email = "Invalid Email";

    private static final String INVALID_Booked_cabin = "Invalid Booked_cabin";
    private static final String INVALID_TicketingDate = "Invalid TicketingDate";
    private static final String INVALID_Mobile_phone = "Invalid Mobile_phone";
    private static final String INVALID_Travel_date = "Invalid Travel_date";
    private static final String INVALID_Fare_class = "Invalid Fare_class";
    private static final String INVALID_Pax = "Invalid Pax";


    @Test
    public void testFunctionality() throws IOException {
        ResponseEntity<TicketUpdateResponse> response = bulkUploadHandler.uploadCSVFile("upload.csv");
        TicketUpdateResponse updateResponse = response.getBody();
        Assert.assertEquals(11, updateResponse.getFailedRecords().size());
        Assert.assertEquals(updateResponse.getFailedRecords().get(0)[10], INVALID_PNR);
        Assert.assertEquals(updateResponse.getFailedRecords().get(1)[10], INVALID_Email);
        Assert.assertEquals(updateResponse.getFailedRecords().get(2)[10], INVALID_TicketingDate);
        Assert.assertEquals(updateResponse.getFailedRecords().get(3)[10], INVALID_Booked_cabin);
        Assert.assertEquals(updateResponse.getFailedRecords().get(4)[10], INVALID_Mobile_phone);
        Assert.assertEquals(updateResponse.getFailedRecords().get(5)[10], INVALID_TicketingDate);
        Assert.assertEquals(updateResponse.getFailedRecords().get(6)[10], INVALID_Travel_date);
        Assert.assertEquals(updateResponse.getFailedRecords().get(7)[10], INVALID_Fare_class);
        Assert.assertEquals(updateResponse.getFailedRecords().get(8)[10], INVALID_Pax);
        Assert.assertEquals(updateResponse.getFailedRecords().get(9)[10], INVALID_Pax);
        Assert.assertEquals(updateResponse.getFailedRecords().get(10)[10], INVALID_TicketingDate);

        //Success Records
        Assert.assertEquals(updateResponse.getSuccessRecords().size(), 4);
        Assert.assertEquals(updateResponse.getSuccessRecords().get(0)[10], OFFER_30);
        Assert.assertEquals(updateResponse.getSuccessRecords().get(1)[10], OFFER_20);
        Assert.assertEquals(updateResponse.getSuccessRecords().get(2)[10], OFFER_25);
        Assert.assertEquals(updateResponse.getSuccessRecords().get(3)[10], "");


    }
}
