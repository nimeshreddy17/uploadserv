package com.uploadserv.service;


import com.uploadserv.interfaces.BulkUploadHandler;
import com.uploadserv.models.TicketUpdateResponse;
import com.uploadserv.validator.Validator;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static com.uploadserv.constants.Constants.OFFER_25;
import static com.uploadserv.constants.Constants.OFFER_30;


public class BulkUploadHandlerImplTest {


    private Validator validator = new Validator();
    private UploadHandlerService uploadHandlerService = new UploadHandlerService();

    private BulkUploadHandlerImpl bulkUploadHandler = new BulkUploadHandlerImpl(validator, uploadHandlerService);

    @Test
    public void testFunctionality() throws IOException {
        ResponseEntity<TicketUpdateResponse> response = bulkUploadHandler.uploadCSVFile("upload.csv");
        TicketUpdateResponse updateResponse = response.getBody();
        Assert.assertEquals(updateResponse.getFailedRecords().size(), 2);
        Assert.assertEquals(updateResponse.getFailedRecords().get(0)[10], "Invalid Email");
        Assert.assertEquals(updateResponse.getFailedRecords().get(1)[10], "Invalid Booked_cabin");

        //OFFER_30 OFFER_25
        Assert.assertEquals(updateResponse.getSuccessRecords().size(), 3);
        Assert.assertEquals(updateResponse.getSuccessRecords().get(0)[10], OFFER_30);
        Assert.assertEquals(updateResponse.getSuccessRecords().get(1)[10], "");
        Assert.assertEquals(updateResponse.getSuccessRecords().get(2)[10], OFFER_25);

    }
}
