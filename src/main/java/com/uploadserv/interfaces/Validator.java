package com.uploadserv.interfaces;

import com.uploadserv.models.TicketUpdate;

import java.util.Map;

public interface Validator {
    Map<String, String[]> validate(String[] input, Map<String, String[]> outputMap, TicketUpdate ticketUpdate, String fieldName);
}
