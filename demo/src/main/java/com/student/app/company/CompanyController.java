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

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping(ApiUrl.URL + "/companies")
@RestController
public class CompanyController {

    private final CompanyService companyService;

    private final CompanyCompanyDtoMapper companyDtoMapper;

    @GetMapping("/get-all")
    public ResponseEntity<List<CompanyDto>> getAllCompanies(){
        List<Company> companies = companyService.findAll();
        return new ResponseEntity<>(companyDtoMapper.companiesToCompaniesDto(companies), HttpStatus.OK);
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long companyId){
        Company company = companyService.findCompany(companyId).orElseThrow(
                () -> new BusinessException("There is no company with id: " + companyId, HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(companyDtoMapper.companyToCompanyDto(company), HttpStatus.OK);
    }

    @GetMapping("/name/{companyName}/get-id")
    public ResponseEntity<Long> getCompanyIdByName(@PathVariable String companyName){
        Company company = companyService.findCompanyByName(companyName).orElseThrow(
                () -> new BusinessException("There is no company with name: " + companyName, HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(company.getId(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDto companyDto){
        Company company = companyDtoMapper.companyDtoToCompany(companyDto);
        companyService.createCompany(company);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity deleteCompany(@PathVariable Long companyId){
        Company company = companyService.findCompany(companyId).orElseThrow(
                () -> new BusinessException("There is no company with id: " + companyId, HttpStatus.NOT_FOUND)
        );
        companyService.deleteCompany(company);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/name/{companyName}")
    public ResponseEntity deleteCompanyByName(@PathVariable String companyName){
        Company company = companyService.findCompanyByName(companyName).orElseThrow(
                () -> new BusinessException("There is no company with name: " + companyName, HttpStatus.NOT_FOUND)
        );
        companyService.deleteCompany(company);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{companyId}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long companyId, @RequestParam String newName, @RequestParam Integer numberOfTrucks){
        Company company = companyService.findCompany(companyId).orElseThrow(
                () -> new BusinessException("There is no company with id: " + companyId, HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(companyService.updateCompany(company, newName, numberOfTrucks), HttpStatus.OK);
    }
}
