package com.quyennv.datn.communication_service.core.utils;

import org.springframework.web.multipart.MultipartFile;

public class FileHelper {
    public static String getFileType(MultipartFile multipartFile) {
        // Get the original filename
        String originalFilename = multipartFile.getOriginalFilename();

        // If originalFilename is null, cannot determine file type
        if (originalFilename == null) {
            return null;
        }

        // Get the last index of '.' to extract file extension
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < originalFilename.length() - 1) {
            // Extract file extension
            return originalFilename.substring(dotIndex + 1);
        } else {
            // If no file extension found, cannot determine file type
            return null;
        }
    }
}
