package com.wasala.luxdone.file;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

public class File {

    @Id
    private String id;
    private FileType type;
    private String originalName;
    private long size;
    private byte[] data;

    public File() {
    }

    public File(FileType type, String originalName, long size, byte[] data) {
        this.type = type;
        this.originalName = originalName;
        this.size = size;
        this.data = data;
    }

    public File(String id, FileType type, String originalName, long size, byte[] data) {
        this.id = id;
        this.type = type;
        this.originalName = originalName;
        this.size = size;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
