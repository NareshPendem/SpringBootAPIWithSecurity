package com.naresh.shoppingcart.controller;

import com.naresh.shoppingcart.dao.UserRepository;
import com.naresh.shoppingcart.service.CheckoutService;
import com.naresh.shoppingcart.service.CheckoutServiceImpl;
import com.naresh.shoppingcart.service.MyUserDetailsService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CheckoutController.class)
@Import({CheckoutServiceImpl.class,MyUserDetailsService.class})
@AutoConfigureMockMvc
public class CheckoutControllerTest {

//    @TestConfiguration
//    static class Config {
//        @Bean
//        public CheckoutService getCheckoutService() {
//            return new CheckoutServiceImpl();
//        }
//
//        @Bean
//        public UserDetailsService getUserDetailsService() {
//            return new MyUserDetailsService();
//        }
//    }

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CheckoutServiceImpl checkoutService;
    @MockBean
    private MyUserDetailsService userDetailsService;

    @MockBean
    private UserRepository repository;

    private String validRequest = "{\n" +
            "   \"currencyRate\" : 1,\n" +
            "   \"taxRate\":5,\n" +
            "   \"items\":[\n" +
            "      {\n" +
            "        \"product\" : {\n" +
            "\t\t \"id\":5,\n" +
            "\t\t \"name\" : \"Disinfectant Wipes\",\n" +
            "\t\t \"price\":10\n" +
            "\t\t },\n" +
            "         \"quantity\":2\n" +
            "      },\n" +
            "            {\n" +
            "        \"product\" : {\n" +
            "\t\t \"id\":248,\n" +
            "\t\t \"name\" : \"Hand Sanitizer\",\n" +
            "\t\t \"price\":20\n" +
            "\t\t },\n" +
            "         \"quantity\":2\n" +
            "      }\n" +
            "   ]\n" +
            "}";


    @Test
    @WithMockUser(value = "pendem",password = "{bcrypt}$2a$10$GjWjoze29ErXfEEQS2Wz.ONvqZPXf0C807jaDx0LrOE0YXg4GAjPi")
    public void testValidInputRequestToReturn201StatusWithErrorAsNull() throws Exception {
        // Ensure 201 is returned without errors for valid Input Request.
        mvc.perform(post("/checkout").contentType(MediaType.APPLICATION_JSON).content(validRequest))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.errors").doesNotExist());
    }



    @Test
    public void testEmptyInputRequestForErrorJSONResponse() throws Exception {
        // Ensure the response JSON for Invalid Input request has required Error Messages.
        mvc.perform(post("/checkout").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.errors").isNotEmpty())
                .andExpect(jsonPath("$.errors[0]").isNotEmpty())
                .andExpect(jsonPath("$.errors[1]").isNotEmpty())
                .andExpect(jsonPath("$.errors[2]").isNotEmpty())
                .andExpect(jsonPath("$.errors[3]").isNotEmpty())
                .andExpect(jsonPath("$.errors",
                        Matchers.containsInAnyOrder(
                                "CurrencyRate cannot be null",
                                "TaxRate cannot be null",
                                "Items cannot be null",
                                "Items cannot be empty"
                        )));

    }

    @Test
    public void testInvalidRequestForErrorResponse_productNull_quantityEmpty() throws Exception {
        String invalidRequest = "{\n" +
                "   \"currencyRate\" : 1,\n" +
                "   \"taxRate\":5,\n" +
                "   \"items\":[\n" +
                "      {}\n" +
                "   ]\n" +
                "}";

        mvc.perform(post("/checkout").contentType(MediaType.APPLICATION_JSON).content(invalidRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.errors.length()",equalTo(2)))
                .andExpect(jsonPath("$.errors",
                        Matchers.containsInAnyOrder(
                                "Quantity cannot be empty",
                                "Product Cannot be null"
                                )));

    }

    @Test
    public void testInvalidRequestForErrorResponse_productEmpty_quantityEmpty() throws Exception {
        String invalidRequest = "{\n" +
                "   \"currencyRate\" : 1,\n" +
                "   \"taxRate\":5,\n" +
                "   \"items\":[\n" +
                "      {\n" +
                "        \"product\" : {}\n" +
                "        }\n" +
                "   ]\n" +
                "}";

        mvc.perform(post("/checkout").contentType(MediaType.APPLICATION_JSON).content(invalidRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.errors.length()",equalTo(3)))
                .andExpect(jsonPath("$.errors",
                        Matchers.containsInAnyOrder(
                                "Quantity cannot be empty",
                                "Product id cannot be null",
                                "Product price cannot be null"
                        )));

    }

    @Test
    public void testWithHandlerNotDefined() throws Exception {
        mvc.perform(post("/checkout/abc").contentType(MediaType.APPLICATION_JSON).content("{}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.errors[0]", equalTo("Resource Not Available")));
    }

    @Test
    public void testUnsupportedMediaException() throws Exception {
        mvc.perform(post("/checkout").contentType(MediaType.APPLICATION_XML).content("<test/>"))
                .andExpect(status().isUnsupportedMediaType())
                .andExpect(jsonPath("$.errors[0]", equalTo("UnSupported Media Type")));
    }

    @Test
    public void testInternalServerError() throws Exception {
        mvc.perform(post("/checkout").contentType(MediaType.APPLICATION_JSON).content("{"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.errors[0]", equalTo("Internal Application Error")));
    }
}