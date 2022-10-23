package com.student.app.event;

import com.student.app.company.model.Company;
import com.student.app.event.dto.EventCompanyDto;
import org.mapstruct.Mapper;

@Mapper
public interface CompanyEventCompanyDtoMapper {

    EventCompanyDto companyToEventCompanyDto(Company company);

    Company eventCompanyDtoToCompany(EventCompanyDto eventCompanyDto);

}
