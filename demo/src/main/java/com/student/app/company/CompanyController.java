package com.student.app.company;

import com.student.app.company.model.Company;
import com.student.app.company.model.CompanyDto;
import com.student.app.config.ApiUrl;
import com.student.app.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiUrl.URL + "/drivers/companies")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyCompanyDtoMapper companyDtoMapper;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDto companyDto){
        Company company = companyDtoMapper.companyDtoToCompany(companyDto);
        company.setNewName(company.getName());
        companyService.createCompany(company);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyName}")
    public ResponseEntity deleteCompany(@PathVariable String companyName){
        Company company = companyService.findCompany(companyName).orElseThrow(
                () -> new BusinessException("There is no company with name: " + companyName, HttpStatus.NOT_FOUND)
        );
        companyService.deleteCompany(company);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //there was a problem with put mapping in restTemplate
    @PostMapping("/update/{companyName}")
    public ResponseEntity<Company> updateCompany(@PathVariable String companyName, @RequestBody String newName){
        Company company = companyService.findCompany(companyName).orElseThrow(
                () -> new BusinessException("There is no company with name: " + companyName, HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(companyService.updateCompany(company, newName), HttpStatus.OK);
    }
}
