package org.botnicholas.projects.democron.controllers.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.botnicholas.projects.democron.controllers.models.deserializer.ParameterObjectDeserializer;

//@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = SoneObjectA.class, name = "SoneObjectA"),
//        @JsonSubTypes.Type(value = SoneObjectB.class, name = "SoneObjectB")
//})
@JsonDeserialize(as = ParameterObjectDeserializer.class)
public interface ParameterObject {
}
