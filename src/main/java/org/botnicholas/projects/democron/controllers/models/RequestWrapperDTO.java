package org.botnicholas.projects.democron.controllers.models;

import java.util.List;

public class RequestWrapperDTO {
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
