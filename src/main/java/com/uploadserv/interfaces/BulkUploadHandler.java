package com.uploadserv.interfaces;

import com.uploadserv.models.TicketUpdateResponse;
import org.springframework.http.ResponseEntity;

import java.io.File;

public interface BulkUploadHandler {

    ResponseEntity<TicketUpdateResponse> uploadCSVFile(File file);

}
