package com.student.app.company;

import com.student.app.company.model.Company;
import com.student.app.event.repository.CompanyEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    private final CompanyEventRepository companyEventRepository;

    public Optional<Company> findCompany(Long id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> findCompanyByName(String name){
        return this.companyRepository.findCompanyByName(name);
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public Company updateCompany(Company company, String newName, Integer numberOfTrucks){
        companyEventRepository.update(company, newName);
        company.setName(newName);
        company.setNumberOfTrucks(numberOfTrucks);
        return companyRepository.save(company);
    }

    @Transactional
    public void createCompany(Company company) {
        companyEventRepository.create(company);
        companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Company company) {
        companyEventRepository.delete(company);
        companyRepository.delete(company);
    }

}
