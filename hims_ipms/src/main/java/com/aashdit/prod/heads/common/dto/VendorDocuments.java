package com.aashdit.prod.heads.common.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class VendorDocuments {
    private MultipartFile gstDocc;
    private MultipartFile panDocc;
    private MultipartFile liorDocc;
    private MultipartFile tanDocc;
}
