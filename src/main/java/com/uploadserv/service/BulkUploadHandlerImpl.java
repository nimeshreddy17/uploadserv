package com.uploadserv.service;


import com.opencsv.CSVReader;

import com.opencsv.CSVWriter;
import com.uploadserv.constants.Constants;
import com.uploadserv.exceptions.BusinessException;
import com.uploadserv.interfaces.BulkUploadHandler;
import com.uploadserv.models.TicketUpdateResponse;
import com.uploadserv.validator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.*;
import java.util.*;

import static com.uploadserv.constants.Constants.failureFileHeaderList;
import static com.uploadserv.constants.Constants.successFileHeaderList;
import static com.uploadserv.validator.Validator.validateHeader;

@Component
public class BulkUploadHandlerImpl implements BulkUploadHandler {

    private static final Logger LOG = LoggerFactory.getLogger(BulkUploadHandlerImpl.class);

    @Inject
    private UploadHandlerService uploadHandlerService;

    public BulkUploadHandlerImpl(Validator validator, UploadHandlerService uploadHandlerService) {
        this.uploadHandlerService = uploadHandlerService;
    }


    /**
     * @param file
     * @return TicketUpdateResponse
     */
    public ResponseEntity<TicketUpdateResponse> parser(File file) {

        List<String[]> successRecords = new ArrayList<>();
        List<String[]> failedRecords = new ArrayList<>();

        try (Reader bfReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {
            CSVReader reader = new CSVReader(bfReader);

            Writer failWriter = new FileWriter("failed_records.csv");
            CSVWriter failCSVWriter = new CSVWriter(failWriter);
            Writer successWriter = new FileWriter("success_records.csv");
            CSVWriter successCSVWriter = new CSVWriter(successWriter);

            String[] singleRow;
            int rowCount = 0;

            // Iterate throw each row and update records
            while ((singleRow = reader.readNext()) != null) {
                if (rowCount == 0) {
                    validateHeader(singleRow);
                    rowCount++;
                    continue;
                }

                try {
                    Map<String, String[]> parsedRow = uploadHandlerService.parse(singleRow);
                    updateRecords(parsedRow, rowCount, successRecords, successCSVWriter);

                } catch (BusinessException bex) {

                    LOG.info("Row Number " + rowCount + " Failed ");
                    singleRow = Arrays.copyOf(singleRow, singleRow.length + 1);
                    singleRow[singleRow.length - 1] = "Invalid " + bex.getBfsBusinessError().getFieldName();

                    failedRecords.add(singleRow);
                    if (failedRecords.size() == 0) {
                        failCSVWriter.writeNext(failureFileHeaderList);
                    }
                    failCSVWriter.writeNext(singleRow);

                }
                rowCount++;
            }
            reader.close();
            failWriter.close();
            successWriter.close();

            TicketUpdateResponse response = new TicketUpdateResponse();
            response.setSuccessRecords(successRecords);
            response.setFailedRecords(failedRecords);
            return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public ResponseEntity<TicketUpdateResponse> uploadCSVFile(File file) {
        //TODO HANDLE IDEMPOTENCY KEY IN FUTURE
        return parser(file);
    }

    public ResponseEntity<TicketUpdateResponse> uploadCSVFile(String filePath) throws IOException {
        Resource resource = new ClassPathResource(filePath);
        return parser(resource.getFile());
    }

    private void updateRecords(Map<String, String[]> parsedRow, int rowCount,
                               List<String[]> successRecords, CSVWriter successCSVWriter) {
        LOG.info("Row Number " + rowCount + " is successful ");
        successRecords.add(parsedRow.get(Constants.SUCCESS));
        if (successRecords.size() == 0) {
            successCSVWriter.writeNext(successFileHeaderList);
        }
        successCSVWriter.writeNext(parsedRow.get(Constants.SUCCESS));
    }

}
