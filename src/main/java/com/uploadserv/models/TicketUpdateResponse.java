package com.uploadserv.models;


import java.util.List;

public class TicketUpdateResponse {

    private List<String[]> successRecords;
    private List<String[]> failedRecords;

    public List<String[]> getSuccessRecords() {
        return successRecords;
    }

    public void setSuccessRecords(List<String[]> successRecords) {
        this.successRecords = successRecords;
    }

    public List<String[]> getFailedRecords() {
        return failedRecords;
    }

    public void setFailedRecords(List<String[]> failedRecords) {
        this.failedRecords = failedRecords;
    }

}
