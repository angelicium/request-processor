package com.request_processor.model.request;

import com.request_processor.model.Type;
import lombok.Data;

@Data
public class RequestDto {

    private Type type;

    private String message;
}
