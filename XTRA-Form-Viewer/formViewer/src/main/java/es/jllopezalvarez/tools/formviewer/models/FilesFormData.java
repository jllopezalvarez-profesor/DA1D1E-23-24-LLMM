package es.jllopezalvarez.tools.formviewer.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FilesFormData {
    private String method;
    private Map<String, String> values;
    private List<FileInfo> files;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }

    public List<FileInfo> getFiles() {
        return files;
    }

    public void setFiles(List<FileInfo> files) {
        this.files = files;
    }

    public FilesFormData(FormData formData , List<FileInfo> files) {
        this.method = formData.getMethod();
        this.values = new HashMap<>(formData.getValues());
        this.files = files;
    }


}
