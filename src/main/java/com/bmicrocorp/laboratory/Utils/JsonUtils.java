package com.bmicrocorp.laboratory.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    public static ObjectMapper getJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        //mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        return mapper;
    }
}
