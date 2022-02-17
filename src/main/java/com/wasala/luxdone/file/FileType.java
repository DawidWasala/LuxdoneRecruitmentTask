package com.wasala.luxdone.file;

import com.wasala.luxdone.file.exceptions.FileTypeNotSupportedException;

import java.util.Arrays;

public enum FileType {
    PDF("application/pdf"),
    JPEG("image/jpeg"),
    TXT("text/plain");

    private final String type;

    FileType(String type) {
        this.type = type;
    }

    public static FileType fromString(String input) throws FileTypeNotSupportedException {
        return Arrays.stream(FileType.values()).filter(fileType -> fileType.type.equals(input)).findFirst().orElseThrow(FileTypeNotSupportedException::new);
    }

    @Override
    public String toString() {
        return type;
    }
}
