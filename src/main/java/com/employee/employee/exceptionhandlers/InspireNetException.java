package com.employee.employee.exceptionhandlers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;





@AllArgsConstructor
@Getter

public class InspireNetException extends Throwable {


    private  ApiErrorCodes apiErrorCodes;
    private  String exData;
    private BindingResult bindingResult;

    //    Gives the message to thrown Exception or Reason Why Exception is  Throwing

    public InspireNetException() {
        //It will show as output the exception Handled  on postman console
        super();

    }
    public InspireNetException(String message) {
        //It will show as output the exception Handled  on postman console
        super(message);

    }

    public InspireNetException(String message, Throwable cause, ApiErrorCodes apiErrorCodes) {
        super(message, cause);
        this.apiErrorCodes = apiErrorCodes;
    }

    public InspireNetException(ApiErrorCodes apiErrorCodes, String s) {


        this.apiErrorCodes = apiErrorCodes;

    }
}



