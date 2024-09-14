package com.bmicrocorp.laboratory.rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.security.core.userdetails.UserDetails;

import com.bmicrocorp.laboratory.Utils.JsonUtils;
import com.bmicrocorp.laboratory.config.rest.RestResponse;
import com.bmicrocorp.laboratory.model.entities.User;

import jakarta.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SecurityAccessTest {


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testUnauthorized() throws Exception {
        final RestResponse expected = new RestResponse(HttpServletResponse.SC_UNAUTHORIZED, null, "Full authentication is required to access this resource");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class).trim()).isEqualTo(JsonUtils.getJsonMapper().writeValueAsString(expected).trim());
	}

	@Test
	void testAuthorized() throws Exception {
		// Not a known user
		final RestResponse expectedFail = new RestResponse(HttpServletResponse.SC_UNAUTHORIZED, null, "Unknown user testFail@test.com");
		final String data = this.restTemplate.withBasicAuth("testFail@test.com", "test").getForObject("http://localhost:" + port + "/user/hello",
			String.class).trim();
		assertThat(data).isEqualTo(JsonUtils.getJsonMapper().writeValueAsString(expectedFail).trim());

		// Not a right tuple user/pass 
		final String invalidData = this.restTemplate.withBasicAuth("1@gmail.com", "test").getForObject("http://localhost:" + port + "/user/hello",
			String.class).trim();
		final RestResponse expectedFailBadCred = new RestResponse(HttpServletResponse.SC_UNAUTHORIZED, null, "Bad credentials");
		assertThat(invalidData).isEqualTo(JsonUtils.getJsonMapper().writeValueAsString(expectedFailBadCred).trim());
		
		// Auth ok
		final String validData = this.restTemplate.withBasicAuth("1@gmail.com", "test1").getForObject("http://localhost:" + port + "/user/hello/2",
			String.class).trim();
		assertThat(validData).isNotNull();
		assertThat(validData.contains("1@gmail.com"));
	}

}
