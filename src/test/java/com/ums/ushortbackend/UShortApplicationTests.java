package com.ums.ushortbackend;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.ums.ushortbackend.domains.ShortURL;
import com.ums.ushortbackend.repositories.ShortURLRepository;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = UShortApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
		locations = "classpath:application-integrationtest.properties")
public class UShortApplicationTests {
	@LocalServerPort
	private int port;

	private MockMvc mockMvc;

//	@Autowired
//	private DurationConfigRespository durationConfigRespository;
//
//	@Autowired
//	private TOTPRepository totpRepository;

	@Autowired
	private WebApplicationContext context;

//	@Autowired
//	private FilterChainProxy springSecurityFilterChain;

//	@Autowired
//	UserRepository userRepository;


	@Autowired
	ShortURLRepository shortURLRepository;

//	private Users user;

	@Before
	public void setUp(){
//		mockMvc = MockMvcBuilders.webAppContextSetup(context)
//				.addFilters(springSecurityFilterChain)
//				.build();
		ShortURL shortURL = new ShortURL(1L, LocalDateTime.now(),
				"123456", "http://google.com",
				"http://localhost:8080/");
		shortURLRepository.save(shortURL);
		ShortURL shortURL2 = new ShortURL(1L, LocalDateTime.now(),
				"1234", "http://googlee.com", "http://localhost:8080/");
		shortURLRepository.save(shortURL2);
	}

	@Test
	public void contextLoads() {

//		assertThat(durationConfigRespository.count()).isNotNull();
//		assertThat(durationConfigRespository.count()).isEqualTo(1L);

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
	public void testShortenURLS() throws Exception{
		given().port(port).when().get("/api").then().statusCode(HttpStatus.SC_OK);
		given().port(port).when().post("/api/shorten").then().statusCode(HttpStatus.SC_BAD_REQUEST);
		// THIS SHOULD BE OK
		Response response = given().port(port)
				.param("url", "http://google.com").when()
				.post("/api/shorten").then().statusCode(HttpStatus.SC_CREATED).extract().response();
		System.out.println(response.prettyPrint());
	}

	@Test
	public void testFetchAll() throws Exception{
		given().port(port).when().get("/api").then().statusCode(HttpStatus.SC_OK);
		ShortURL shortURL = new ShortURL(1L, LocalDateTime.now(),
				"123456", "http://google.com",
				"http://localhost:8080/");
		shortURLRepository.save(shortURL);
		Response response = given().port(port).when().
				get("/api/fetchAllShortened").then().statusCode(HttpStatus.SC_OK).extract().response();
		// THIS SHOULD BE OK
		System.out.println(response.prettyPrint());

	}

	@Test
	public void testToDelete() throws Exception{
		ShortURL shortURL = new ShortURL(1L, LocalDateTime.now(),
				"123456", "http://google.com",
				"http://localhost:8080/");
		shortURLRepository.save(shortURL);
		given().port(port).when().delete("/api/delete/"+shortURL.getCode()).then()
				.statusCode(HttpStatus.SC_OK);


	}


}
