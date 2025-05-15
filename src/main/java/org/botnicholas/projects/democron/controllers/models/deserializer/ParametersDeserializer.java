package org.botnicholas.projects.democron.controllers.models.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.botnicholas.projects.democron.controllers.models.ParameterDTO;
import org.botnicholas.projects.democron.controllers.models.SoneObjectA;
import org.botnicholas.projects.democron.controllers.models.SoneObjectB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ParametersDeserializer extends StdDeserializer<List<ParameterDTO>> {
    protected ParametersDeserializer() {
        this(ParameterDTO.class);
    }

    protected ParametersDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<ParameterDTO> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode tree = mapper.readTree(p);
        List<ParameterDTO> result = new ArrayList<>();

        if(tree.isArray()) {
            for (JsonNode parameter: tree) {
                var key = parameter.get("key");
                var azazaz = parameter.get("azazaz");
                var value = parameter.get("values");

                if (!key.textValue().equals("occurrenceDetails")) {
                    result.add(new ParameterDTO(key.textValue(), mapper.convertValue(parameter.get("values"), new TypeReference<List<String>>() {})));
                } else {
                    var type = findOccurrence(tree).map(n -> n.get("values").get(0).textValue()).orElse(null);
                    switch (type) {
                        case "DAILY":
                            var daily = mapper.treeToValue(parameter.get("values").get(0), SoneObjectA.class);
                            result.add(new ParameterDTO(key.textValue(), List.of(mapper.writeValueAsString(daily))));
                            break;
                        case "EVERY_HOUR":
                            var everyHour = mapper.treeToValue(parameter.get("values").get(0), SoneObjectB.class);
                            result.add(new ParameterDTO(key.textValue(), List.of(mapper.writeValueAsString(everyHour))));
                            break;
                    }
                }
            }
        }
        return result;
    }

    private Optional<JsonNode> findOccurrence(JsonNode tree) {
        for (JsonNode parameter: tree) {
            if (parameter.get("key").textValue().equals("occurrence")) {
                return Optional.of(parameter);
            }
        }
        return Optional.empty();
    }

//    FOR OBJECTS OR STRINGS
//
//    @Override
//    public List<ParameterDTO> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
//        ObjectMapper mapper = (ObjectMapper) p.getCodec();
//        JsonNode tree = mapper.readTree(p);
//        String type = "";
//        List<ParameterDTO> result = new ArrayList<>();
//
//        if(tree.isArray()) {
//            for (JsonNode parameter: tree) {
//                var key = parameter.get("key");
//                var value = parameter.get("values");
//
//                if (!key.textValue().equals("occurrenceDetails")) {
//                    if(key.textValue().equals("occurrence")) {
//                        type = parameter.get("values").get(0).textValue();
//                    }
//                    result.add(new ParameterDTO(key.textValue(), Collections.singletonList(mapper.convertValue(parameter.get("values"), new TypeReference<List<String>>() {
//                    }))));
//                } else {
//                    switch (type) {
//                        case "DAILY":
////                            result.add(new ParameterDTO(key.textValue(), List.of(mapper.convertValue(parameter.get("values").get(0), SoneObjectA.class))));
//                            result.add(new ParameterDTO(key.textValue(), List.of(mapper.treeToValue(parameter.get("values").get(0), SoneObjectA.class))));
//                            break;
//                        case "EVERY_HOUR":
//                            result.add(new ParameterDTO(key.textValue(), List.of(mapper.convertValue(parameter.get("values").get(0), SoneObjectB.class))));
//                            break;
//                    }
//                }
//            }
//        }
//        return result;
//    }
}
