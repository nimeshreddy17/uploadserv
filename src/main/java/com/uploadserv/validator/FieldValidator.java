package com.uploadserv.validator;

import com.uploadserv.exceptions.BusinessException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import java.util.regex.Pattern;

public class FieldValidator {

    public static void validate(String input, String fieldName, String patternString) {

        // Check if field is invalid
        if (StringUtils.isNotBlank(input) && !Pattern.matches(patternString, input.trim())) {
            throw new BusinessException("Field " + fieldName + " is invalid",
                    fieldName,
                    input,
                    HttpStatus.BAD_REQUEST);
        }
    }

}
