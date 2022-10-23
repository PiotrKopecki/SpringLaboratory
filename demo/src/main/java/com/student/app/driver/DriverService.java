package com.student.app.driver;

import com.student.app.company.CompanyService;
import com.student.app.company.model.Company;
import com.student.app.driver.model.Driver;
import com.student.app.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DriverService {

    private final DriverRepository driverRepository;

    private final CompanyService companyService;

    public Optional<Driver> findDriver(Long id) {
        return driverRepository.findById(id);
    }

    public Optional<Driver> findDriverByName(String name) {
        return driverRepository.findByName(name);
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public List<Driver> findAllForCompanyName(String companyName){
        List<Driver> drivers = findAll();
        return drivers.stream()
                .filter(driver -> driver.getCompany().getNewName().equals(companyName))
                .toList();
    }

    @Transactional
    public Driver updateDriver(Driver driver, String newName, Integer newAge){
        driver.setName(newName);
        driver.setAge(newAge);
        return driverRepository.save(driver);
    }

    @Transactional
    public Driver createDriver(Driver driver, String companyName) {
        Company company = companyService.findAll()
                .stream()
                .filter(company1 -> company1.getNewName().equals(companyName))
                .findAny()
                .orElseThrow(
                        () -> new BusinessException("There is no Company " + companyName, HttpStatus.NOT_FOUND)
                );
        driver.setCompany(company);
        return driverRepository.save(driver);
    }

    @Transactional
    public void deleteDriver(Driver driver) {
        driverRepository.delete(driver);
    }

}
