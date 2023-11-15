package es.jllopezalvarez.tools.formviewer.models;

import java.util.HashMap;
import java.util.Map;


public class FormData {
    private String method;
    private Map<String, String> values;

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

    public FormData(String method, Map<String, String> values) {
        this.method = method;
        this.values = values;
    }

    public FormData(String method) {
        this(method, new HashMap<>());
    }
}
