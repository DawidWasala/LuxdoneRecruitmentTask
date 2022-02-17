package com.wasala.luxdone.file;

public class FileInfo {

    private String id;
    private String type;
    private String originalName;
    private long size;

    public FileInfo() {
    }

    public FileInfo(String id, String type, String originalName, long size) {
        this.id = id;
        this.type = type;
        this.originalName = originalName;
        this.size = size;
    }

    public static FileInfo mapFileToFileInfo(File file) {
        return new FileInfo(file.getId(), file.getType().toString(), file.getOriginalName(), file.getSize());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
