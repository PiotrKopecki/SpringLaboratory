package com.student.app.event.repository;

import com.student.app.company.model.Company;
import com.student.app.event.CompanyEventCompanyDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CompanyEventRepository {

    private final RestTemplate restTemplate;

    private final CompanyEventCompanyDtoMapper companyEventCompanyDtoMapper;

    @Autowired
    public CompanyEventRepository(@Value("${drivers.url}") String baseUrl, CompanyEventCompanyDtoMapper companyEventCompanyDto) {
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
        companyEventCompanyDtoMapper = companyEventCompanyDto;
    }

    public void delete(Company company) {
        restTemplate.delete("/drivers/companies/{companyName}", company.getName());
    }

    public void create(Company company) {
        restTemplate.postForLocation("/drivers/companies", companyEventCompanyDtoMapper.companyToEventCompanyDto(company));
    }

    public void update(Company company, String newName) {
        restTemplate.postForLocation("/drivers/companies/update/" + company.getName(), newName);
    }
}
