package com.crabtree.demo;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.junit.Test;

import com.crabtree.persistence.model.Company;

import io.restassured.response.Response;
import io.restassured.RestAssured;

import java.util.List;

public class SpringBootstrapLiveTest {

    private static final String API_ROOT
            = "http://localhost:8081/api";

    @Test
    public void whenGetAllCompanies_thenOK() {
        Response response = RestAssured.get(API_ROOT);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenGetCompaniesByTicker_thenOK() {
        Company company = createRandomCompany();
        createCompanyAsUri(company);
        Response response = RestAssured.get(
                API_ROOT + "/ticker/" + company.getTicker()
        );
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertTrue(response.as(List.class).size() > 0);
    }

    @Test
    public void whenGetCreatedCompaniesById_thenOK() {
        Company company = createRandomCompany();
        String location = createCompanyAsUri(company);
        Response response = RestAssured.get(location);

        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals(company.getName(), response.jsonPath().get("name"));
    }

    @Test
    public void whenGetNotExistsCompanyById_thenNotFound() {
        Response response = RestAssured.get(API_ROOT + "/" + randomNumeric(4));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    // POST
    @Test
    public void whenCreateNewCompany_thenCreated() {
        Company company = createRandomCompany();

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(company)
                .post(API_ROOT);
        assertEquals(HttpStatus.CREATED.value(), response.getStatusCode());
    }

    @Test
    public void whenInvalidCompany_thenError() {
        Company company = createRandomCompany();
        company.setName(null);

        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(company)
                .post(API_ROOT);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

    @Test
    public void whenUpdateCreatedCompany_thenUpdated() {
        Company company = createRandomCompany();
        String location = createCompanyAsUri(company);

        company.setId(Long.parseLong(location.split("api/")[1]));
        company.setName("Amazon, Inc.");
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(company)
                .put(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
        assertEquals("Amazon, Inc.", response.jsonPath()
                .get("name"));
    }

    @Test
    public void whenDeleteCreatedCompany_thenOk() {
        Company company = createRandomCompany();
        String location = createCompanyAsUri(company);

        Response response = RestAssured.delete(location);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());

        response = RestAssured.get(location);
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    ///////////////
    private Company createRandomCompany() {
        Company company = new Company();
        company.setName(randomAlphabetic(10));
        company.setTicker(randomAlphabetic(4));
        return company;
    }

    private String createCompanyAsUri(Company company) {
        Response response = RestAssured.given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(company)
                .post(API_ROOT);
        return API_ROOT + "/" + response.jsonPath().get("id");
    }
}
