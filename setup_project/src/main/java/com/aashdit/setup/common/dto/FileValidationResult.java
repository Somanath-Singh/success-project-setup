package com.aashdit.setup.common.dto;

import lombok.Data;

@Data
public class FileValidationResult {

    private final boolean suspicious;  
    private final String message; 

    public boolean isError() {
        return suspicious;
    }
}
