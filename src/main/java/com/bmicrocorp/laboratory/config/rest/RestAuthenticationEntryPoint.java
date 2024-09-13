package com.bmicrocorp.laboratory.config.rest;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.bmicrocorp.laboratory.Utils.JsonUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response, 
        final AuthenticationException authException) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        RestResponse result = new RestResponse(HttpServletResponse.SC_UNAUTHORIZED, null, authException.getMessage());
        writer.println(JsonUtils.getJsonMapper().writeValueAsString(result));
    }

}
