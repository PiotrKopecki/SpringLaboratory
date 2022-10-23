package com.student.app.company;

import com.student.app.company.model.Company;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Optional<Company> findCompany(String name) {
        return companyRepository.findByNewName(name);
    }


    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Transactional
    public Company updateCompany(Company company, String newName){
        company.setNewName(newName);
        return companyRepository.save(company);
    }

    @Transactional
    public void createCompany(Company company) {
        companyRepository.save(company);
    }

    @Transactional
    public void deleteCompany(Company company) {
        companyRepository.delete(company);
    }

}
