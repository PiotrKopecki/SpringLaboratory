package com.student.app.driver;

import com.student.app.driver.model.Driver;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface DriverDtoMapper {

    DriverDto driverToDriverDto(Driver driver);

    Driver driverDtoToDriver(DriverDto driverDto);

    List<DriverDto> driversToDriversDto(List<Driver> drivers);
}
