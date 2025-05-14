package org.botnicholas.projects.democron.controllers.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.List;

public class ParameterDTO {
    private String key;
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "key", defaultImpl = String.class)
    @JsonSubTypes({
            @JsonSubTypes.Type(value = ParameterObject.class, name = "object")
    })
    private List<Object> values;

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
