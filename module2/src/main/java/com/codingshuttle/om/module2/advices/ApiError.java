package com.codingshuttle.om.module2.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

//THIS CLASS IS FOR CUSTOM CREATION OF THE ERROR SHOWN WHEN EXCEPTION IS CAUGHT INSTEAD OF RETURNING PLAIN STRING
@Data
@Builder
public class ApiError {

    private HttpStatus httpStatus;
    private String message;
}
