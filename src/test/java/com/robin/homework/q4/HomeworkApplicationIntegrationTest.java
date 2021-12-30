package com.robin.homework.q4;

import com.robin.homework.q4.controller.CustomerController;
import com.robin.homework.q4.controller.ServiceController;
import com.robin.homework.q4.dto.SubscriptionDto;
import com.robin.homework.q4.model.Customer;
import com.robin.homework.q4.model.Service;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { HomeworkApplication.class }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeworkApplicationIntegrationTest {

    @Autowired private TestRestTemplate restTemplate;

    @LocalServerPort private int port;

    @Autowired private ServiceController serviceController;

    @Autowired private CustomerController customerController;

    @Test
    public void contextLoads() {
        Assertions
          .assertThat(serviceController)
          .isNotNull();
        Assertions
          .assertThat(customerController)
          .isNotNull();
    }

    @Test
    public void givenGetServicesApiCall_whenCustomerListRetrieved_thenSizeMatchAndListContainsServiceNames() {
        ResponseEntity<Iterable<Service>> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/services", HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Service>>() {
        });
        Iterable<Service> services = responseEntity.getBody();
        Assertions
          .assertThat(services)
          .hasSize(7);

        assertThat(services, hasItem(hasProperty("name", is("svc1"))));
        assertThat(services, hasItem(hasProperty("name", is("svc2"))));
        assertThat(services, hasItem(hasProperty("name", is("svc3"))));
    }

    @Test
    public void givenGetCustomersApiCall_whenServiceListRetrieved_thenSizeMatchAndListContainsCustomerNames() {
        ResponseEntity<Iterable<Customer>> responseEntity = restTemplate.exchange(
            "http://localhost:" + port + "/api/customers", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        Iterable<Customer> customers = responseEntity.getBody();
        Assertions
          .assertThat(customers)
          .hasSize(0);
    }

    @Test
    public void givenPostCustomer_whenBodyRequestMatcherJson_thenResponseContainsEqualObjectProperties() {
        final ResponseEntity<Customer> postResponse = restTemplate.postForEntity("http://localhost:" + port + "/api/customers", prepareCustomerForm(), Customer.class);
        Customer customer = postResponse.getBody();
        Assertions
          .assertThat(postResponse.getStatusCode())
          .isEqualByComparingTo(HttpStatus.CREATED);

        assertThat(customer, hasProperty("status", is("DONE")));
        assertThat(customer.getSubscriptions(), hasItem(hasProperty("timeInDay", is(2))));
    }

    private CustomerController.CustomerForm prepareCustomerForm() {
        CustomerController.CustomerForm customerForm = new CustomerController.CustomerForm();
        SubscriptionDto customerDto = new SubscriptionDto();
        customerDto.setService(new Service(1L, "svc0", 300.00, "http://placehold.com"));
        customerDto.setQuantity(2);
        customerForm.setSubscriptions(Collections.singletonList(customerDto));

        return customerForm;
    }
}
