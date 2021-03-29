package com.uploadserv.interfaces;

import java.util.Map;

public interface UploadHandlerInterface {
    Map<String, String[]> parse(String[] input);
}
