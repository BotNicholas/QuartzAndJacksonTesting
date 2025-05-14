package org.botnicholas.projects.democron.controllers.models;

import java.util.List;

public class ParameterDTO {
    private String key;
    private List<Object> values;

    public ParameterDTO() {
    }

    public ParameterDTO(String key, List<Object> values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "ParameterDTO{" +
                "key='" + key + '\'' +
                ", values=" + values +
                '}';
    }
}
