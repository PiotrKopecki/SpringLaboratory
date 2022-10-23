package com.student.app.company;

import com.student.app.company.model.Company;
import com.student.app.company.model.CompanyDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CompanyCompanyDtoMapper {

    CompanyDto companyToCompanyDto(Company company);

    Company companyDtoToCompany(CompanyDto companyDto);

    List<CompanyDto> companiesToCompaniesDto(List<Company> companies);
}
