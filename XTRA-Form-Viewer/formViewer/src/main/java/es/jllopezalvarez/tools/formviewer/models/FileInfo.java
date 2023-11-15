package es.jllopezalvarez.tools.formviewer.models;


public class FileInfo {
    private String formFieldname;
    private String fileName;
    private long fileSize;
    private String contentType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public FileInfo(String formFieldname, String fileName, long fileSize, String contentType) {
        this.formFieldname = formFieldname;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.contentType = contentType;
    }

    public String getFormFieldname() {
        return formFieldname;
    }

    public void setFormFieldname(String formFieldname) {
        this.formFieldname = formFieldname;
    }
}
