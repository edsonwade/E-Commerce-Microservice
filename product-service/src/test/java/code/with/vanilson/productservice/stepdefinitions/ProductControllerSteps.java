//package code.with.vanilson.productservice.stepdefinitions;
//
///**
// * ProductControllerSteps
// *
// * @author vamuhong
// * @version 1.0
// * @since 2024-06-15
// */
//
//import aj.org.objectweb.asm.ConstantDynamic;
//import code.with.vanilson.productservice.Product;
//import code.with.vanilson.productservice.category.Category;
//import code.with.vanilson.productservice.config.CucumberSpringConfiguration;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.cucumber.java.en.*;
//import net.minidev.json.JSONObject;
//import org.junit.BeforeClass;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.*;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.math.BigDecimal;
//
//import java.util.List;
//import java.util.Map;
//
//import static java.util.EnumSet.allOf;
//import static org.assertj.core.api.Fail.fail;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@TestPropertySource(locations = "classpath:application-test.yml")
//@SpringBootTest
//@ContextConfiguration(classes = CucumberSpringConfiguration.class)
//
//public class ProductControllerSteps {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    private static String baseUrl = "http://localhost:8082/api/v1/products"; // Adjust base URL as per your application
//    private static RestTemplate restTemplate = new RestTemplate();
//    private static HttpHeaders headers = new HttpHeaders();
//
//
//
////    @BeforeClass
////    public static void runBeforeAllTestMethods() {
////        baseUrl = "http://localhost:8082/api/v1/products/create";
////        restTemplate = new RestTemplate();
////        headers = new HttpHeaders();
////        headers.setContentType(MediaType.APPLICATION_JSON);
////        createProduct = new JSONObject();
////        createProduct.put("id", 1);
////        createProduct.put("name", "John");
////    }
//
//    public ProductControllerSteps() {
//        headers.setContentType(MediaType.APPLICATION_JSON);
//    }
//
//    private ResultActions resultActions;
//    private MvcResult mvcResult;
//
//    @Given("the following products exist:")
//    public void theFollowingProductsExist(List<Product> products) throws Exception {
//        for (Product product : products) {
//            createProduct(product);
//        }
//    }
//
//    @When("I send a GET request to {string}")
//    public void iSendAGETRequestTo(String endpoint) throws Exception {
//        resultActions = mockMvc.perform(MockMvcRequestBuilders.get(endpoint)
//                .contentType(MediaType.APPLICATION_JSON));
//    }
//
//    @Then("the response status should be {int}")
//    public void theResponseStatusShouldBe(int status) throws Exception {
//        resultActions.andExpect(status().is(status));
//    }
//
//    @Then("the response should contain the following products:")
//    public void theResponseShouldContainTheFollowingProducts(List<Map<String, String>> expectedProducts)
//            throws Exception {
//        MvcResult mvcResult = resultActions.andReturn();
//        String jsonResponse = mvcResult.getResponse().getContentAsString();
//        List<Map<String, Object>> actualProducts = objectMapper.readValue(jsonResponse, List.class);
//
//        assertThat(actualProducts, hasSize(expectedProducts.size()));
//
//        for (int i = 0; i < expectedProducts.size(); i++) {
//            Map<String, String> expectedProduct = expectedProducts.get(i);
//
//            // Extract actual product details from response
//            Map<String, Object> actualProduct = actualProducts.get(i);
//
//            // Assert individual fields
//            assertThat(actualProduct.get("id"), is(Integer.valueOf(expectedProduct.get("id"))));
//            assertThat(actualProduct.get("name"), is(expectedProduct.get("name")));
//            assertThat(actualProduct.get("description"), is(expectedProduct.get("description")));
//            assertThat(actualProduct.get("price"), is(Double.valueOf(expectedProduct.get("price"))));
//            assertThat(actualProduct.get("quantity"), is(new BigDecimal(expectedProduct.get("quantity"))));
//        }
//    }
//
//    @Given("the following product exists:")
//    public void theFollowingProductExists(Product product) throws Exception {
//        createProduct(product);
//    }
//
//    @Then("the response should contain the product:")
//    public void theResponseShouldContainTheProduct(Product expectedProduct) throws Exception {
//        resultActions.andExpect(jsonPath("$.id", is(expectedProduct.getId())))
//                .andExpect(jsonPath("$.name", is(expectedProduct.getName())))
//                .andExpect(jsonPath("$.description", is(expectedProduct.getDescription())))
//                .andExpect(jsonPath("$.price", is(expectedProduct.getPrice().doubleValue())));
//    }
//
//    @Given("the product with ID {int} does not exist")
//    public void theProductWithIdDoesNotExist(int productId) {
//        // You may implement logic to ensure the product with the given ID does not exist
//    }
//
//    @When("I send a POST request to {string} with the request body:")
//    public void iSendAPostRequestToWithTheRequestBody(String url, String requestBody) throws Exception {
//        Product product = objectMapper.readValue(requestBody, Product.class);
//        createProduct(product);
//    }
//
//    @When("I send a PUT request to {string} with the request body:")
//    public void iSendAPutRequestToWithTheRequestBody(String url, String requestBody) throws Exception {
//        Product product = objectMapper.readValue(requestBody, Product.class);
//        resultActions = mockMvc.perform(MockMvcRequestBuilders.put(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(product)));
//    }
//
//    @When("I send a DELETE request to {string}")
//    public void iSendADeleteRequestTo(String url) throws Exception {
//        resultActions = mockMvc.perform(MockMvcRequestBuilders.delete(url)
//                .contentType(MediaType.APPLICATION_JSON));
//    }
//
//    public void createProduct(Product product) {
//        try {
//            // Serialize product object to JSON string
//            String jsonPayload = objectMapper.writeValueAsString(product);
//
//            // Create HTTP headers with content type application/json
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//
//            // Create HttpEntity with JSON payload and headers
//            HttpEntity<String> requestEntity = new HttpEntity<>(jsonPayload, headers);
//
//            // Send POST request and receive response
//            ResponseEntity<Product> responseEntity = restTemplate.postForEntity(baseUrl + "/create", requestEntity, Product.class);
//
//            // Check if product was created successfully (HTTP status 2xx)
//            if (responseEntity.getStatusCode().is2xxSuccessful()) {
//                Product createdProduct = responseEntity.getBody();
//                assertNotNull(createdProduct);
//                System.out.println("Product created successfully with ID: " + createdProduct.getId());
//            } else {
//                System.err.println("Failed to create product. Status code: " + responseEntity.getStatusCodeValue());
//                System.err.println("Response body: " + responseEntity.getBody());
//                fail("Failed to create product. Status code: " + responseEntity.getStatusCodeValue());
//            }
//        } catch (Exception ex) {
//            System.err.println("Exception occurred while creating product: " + ex.getMessage());
//            ex.printStackTrace();
//            fail("Exception occurred while creating product: " + ex.getMessage());
//        }
//    }
//
//}
