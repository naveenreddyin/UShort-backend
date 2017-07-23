package com.ums.umsbackend;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ums.umsbackend.configurations.UMSSecurityConfigurator;
import com.ums.umsbackend.controllers.HomeController;
import com.ums.umsbackend.domains.Duration;
import com.ums.umsbackend.domains.UserTOTP;
import com.ums.umsbackend.domains.Users;
import com.ums.umsbackend.repositories.DurationConfigRespository;
import com.ums.umsbackend.repositories.TOTPRepository;
import com.ums.umsbackend.repositories.UserRepository;
import com.ums.umsbackend.utils.Utils;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = UMSApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-integrationtest.properties")
public class UMSApplicationTests {
	@LocalServerPort
	private int port;

	private MockMvc mockMvc;

	@Autowired
	private DurationConfigRespository durationConfigRespository;

	@Autowired
	private TOTPRepository totpRepository;

	@Autowired
	private WebApplicationContext context;

//	@Autowired
//	private FilterChainProxy springSecurityFilterChain;

	@Autowired
	UserRepository userRepository;

	private Users user;

	@Before
	public void setUp(){
//		mockMvc = MockMvcBuilders.webAppContextSetup(context)
//				.addFilters(springSecurityFilterChain)
//				.build();
		final String email = UUID.randomUUID().toString() + "@dispostable.com";
		System.out.println(email);
		user = new Users(1L,email, "123456");
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testIfRootURLIsAccessibleByAll() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/").accept(
				MediaType.TEXT_HTML);



//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		assertThat(result.getResponse().getStatus()).isEqualTo(200);

	}

	@Test
	public void testIfAnyOneCouldCreateAUser() throws Exception{
		final Users user2 = new Users(1L, "naveenrandom@dispostable,com", "123456");
		final Users user3 = new Users(2L, "naveenrandom@dispostable,com", "123456");

		given().port(port).when().get("/api").then().statusCode(HttpStatus.SC_OK);
		given().port(port).when().get("/api/userses").then().statusCode(HttpStatus.SC_OK);

		given().port(port).contentType("application/json").body(Utils.asJsonString(user))
				.when().post("/api/userses").then().statusCode(HttpStatus.SC_CREATED);

		given().port(port).contentType("application/json").body(Utils.asJsonString(user2))
				.when().post("/api/userses").then().statusCode(HttpStatus.SC_CREATED);

		// let us test if we could find user by email
		given().port(port).when().get("/api/userses/search/findByEmail?email="+user2.getEmail()).then().statusCode(HttpStatus.SC_OK);

		// This should give 409
		given().port(port).contentType("application/json").body(Utils.asJsonString(user3))
				.when().post("/api/userses").then().statusCode(HttpStatus.SC_CONFLICT);
	}


	@Test
	public void testToCreateTOTP(){
		Response userCreateResponse = given().port(port).contentType("application/json").body(Utils.asJsonString(user))
				.when().post("/api/userses").then().statusCode(HttpStatus.SC_CREATED).extract().response();

//		System.out.println(userCreateResponse.body().prettyPrint());

		Response response = given().port(port).param("userId", "1").param("code", "1DF2C0")
				.when().post("/api/usertotp").then().statusCode(HttpStatus.SC_CREATED).extract().response();

		Response response2 = given().port(port).param("userId", "1").param("code", "1DF2C0")
				.when().post("/api/usertotp").then().statusCode(HttpStatus.SC_CONFLICT).extract().response();

	}

	@Test
	public void testIfCodeIsValid(){
//		Response userCreateResponse = given().port(port).contentType("application/json").body(Utils.asJsonString(user))
//				.when().post("/api/userses").then().statusCode(HttpStatus.SC_CREATED).extract().response();
//
//		given().port(port).param("userId", "1").param("code", "1DF2C9")
//				.when().post("/api/usertotp").then().statusCode(HttpStatus.SC_CREATED).extract().response();
//
		Duration duration = new Duration(1L, 30);
		durationConfigRespository.save(duration);

		Users user = new Users(2L,"naveenrandom123@dispostable.com", "123456");
		Users userSaveResponse = userRepository.save(user);
		System.out.println(userSaveResponse.getEmail());
		final LocalDateTime today = LocalDateTime.now();
		UserTOTP userTOTP = new UserTOTP(2L, "123456", today, userSaveResponse);
		UserTOTP userTOTP1 = totpRepository.save(userTOTP);

		given().port(port).when().get("/api/validate/"+userTOTP1.getCode()).then().statusCode(HttpStatus.SC_OK);

	}



}
