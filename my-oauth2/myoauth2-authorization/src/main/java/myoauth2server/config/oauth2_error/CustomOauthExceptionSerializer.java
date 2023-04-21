package myoauth2server.config.oauth2_error;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomOauthExceptionSerializer extends StdSerializer<CustomOauthException>{

    protected CustomOauthExceptionSerializer() {
        super(CustomOauthException.class);
    }

    @Override
    public void serialize(CustomOauthException value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("code", value.getHttpErrorCode());
        jsonGenerator.writeBooleanField("status", false);
        jsonGenerator.writeObjectField("data", null);
        jsonGenerator.writeObjectField("errors", Arrays.asList(value.getOAuth2ErrorCode(),value.getMessage()));
        if (value.getAdditionalInformation() == null) {
            for (Map.Entry<String, String> entry : value.getAdditionalInformation().entrySet()) {
                String key = entry.getKey();
                String add = entry.getValue();
                jsonGenerator.writeStringField(key, add);
            }
        }
        jsonGenerator.writeEndObject();
    }

}