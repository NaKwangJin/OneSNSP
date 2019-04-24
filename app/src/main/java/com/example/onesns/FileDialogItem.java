package com.example.onesns;

public class FileDialogItem {
    private String filename;
    private String filetype;
    private int filesize;

    public FileDialogItem(String filename, String filetype, int filesize) {
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    public int getFilesize() {
        return filesize;
    }

    public void setFilesize(int filesize) {
        this.filesize = filesize;
    }
}
