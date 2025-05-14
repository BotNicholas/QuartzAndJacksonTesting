package org.botnicholas.projects.democron.controllers.models.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.botnicholas.projects.democron.controllers.models.ParameterObject;

import java.io.IOException;

public class ParameterObjectDeserializer extends JsonDeserializer<ParameterObject> {
    @Override
    public ParameterObject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        System.out.println("DESERIALISATION:" + p.getCurrentToken());
        return null;
    }
}
