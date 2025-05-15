package org.botnicholas.projects.democron.controllers.models.deserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.botnicholas.projects.democron.controllers.models.ParameterDTO;
import org.botnicholas.projects.democron.controllers.models.SoneObjectA;
import org.botnicholas.projects.democron.controllers.models.SoneObjectB;

import java.io.IOException;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Optional;

public class ParameterSerializer extends StdSerializer<List<ParameterDTO>> {
    protected ParameterSerializer() {
        this(null);
    }

    protected ParameterSerializer(Class<List<ParameterDTO>> t) {
        super(t);
    }

    @Override
    public void serialize(List<ParameterDTO> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        String type = "";
        ObjectMapper mappper = new ObjectMapper();

        gen.writeStartArray();
        for (ParameterDTO parameter : value) {
//            gen.writeStartObject();
//            gen.writeStringField("key", parameter.getKey());
//            gen.writeArrayFieldStart("values");
//            //...
            if (!"occurrenceDetails".equals(parameter.getKey())) {
                gen.writeObject(parameter);
            } else {
                var occurrence = getOccurrence(value);
                if (occurrence.isPresent()) {
                    gen.writeStartObject();
                    gen.writeStringField("key", parameter.getKey());
                    //...
                    gen.writeArrayFieldStart("values");
                    //todo: replace with Mapper from OccurrenceType enum
                    var occurrenceType = occurrence.get().getValues().get(0);

                    gen.writeObject(switch (occurrenceType) {
                        case "DAILY" -> mappper.readValue(parameter.getValues().get(0), SoneObjectA.class);
                        case "EVERY_HOUR" -> mappper.readValue(parameter.getValues().get(0), SoneObjectB.class);
                        default -> throw new IllegalStateException("Unexpected value: " + occurrenceType);
                    });
                    gen.writeEndArray();
                } else {
                    gen.writeObject(parameter);
                }



//                gen.writeString("object");
            }
//            gen.writeEndArray();
//            gen.writeEndObject();
        }
        gen.writeEndArray();
    }

    private Optional<ParameterDTO> getOccurrence(List<ParameterDTO> parameters) {
        return parameters.stream()
                .filter(parameter -> "occurrence".equals(parameter.getKey()))
                .findFirst();
    }
}
