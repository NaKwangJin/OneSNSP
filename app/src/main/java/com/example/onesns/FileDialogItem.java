package com.example.onesns;

public class FileDialogItem {
    private String filename;
    private String filetype;
    private int filesize;
    private boolean isDir;

    public FileDialogItem(String filename, String filetype, int filesize,boolean isDirectory) {
        this.filename = filename;
        this.filetype = filetype;
        this.filesize = filesize;
        this.isDir = isDirectory;
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

    public boolean isDir() {
        return isDir;
    }
}
