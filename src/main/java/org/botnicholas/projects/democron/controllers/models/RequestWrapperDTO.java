package org.botnicholas.projects.democron.controllers.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.botnicholas.projects.democron.controllers.models.deserializer.ParameterSerializer;
import org.botnicholas.projects.democron.controllers.models.deserializer.ParametersDeserializer;

import java.util.List;

public class RequestWrapperDTO {
    @JsonDeserialize(using = ParametersDeserializer.class)
    @JsonSerialize(using = ParameterSerializer.class)
    private List<ParameterDTO> parameters;

    public List<ParameterDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterDTO> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        return "RequestWrapperDTO{" +
                "parameters=" + parameters +
                '}';
    }
}
