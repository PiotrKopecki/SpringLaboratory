package com.student.app.driver;

import com.student.app.config.ApiUrl;
import com.student.app.driver.model.Driver;
import com.student.app.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(ApiUrl.URL + "/drivers")
@RestController
public class DriverController {

    private final DriverService driverService;

    private final DriverDtoMapper driverDtoMapper;

    @GetMapping("/get-all")
    public ResponseEntity<List<DriverDto>> getAllDrivers(){
        List<Driver> drivers = driverService.findAll();
        return new ResponseEntity<>(driverDtoMapper.driversToDriversDto(drivers), HttpStatus.OK);
    }

    @GetMapping("/get-all/{companyName}")
    public ResponseEntity<List<Driver>> getAllDriversForCompanyName(@PathVariable String companyName){
        List<Driver> drivers = driverService.findAllForCompanyName(companyName);
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable Long driverId){
        Driver driver = driverService.findDriver(driverId).orElseThrow(
                () -> new BusinessException("There is no driver with id: " + driverId, HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(driverDtoMapper.driverToDriverDto(driver), HttpStatus.OK);
    }

    @GetMapping("/name/{driverName}/get-id")
    public ResponseEntity<Long> getDriverIdByName(@PathVariable String driverName){
        Driver driver = driverService.findDriverByName(driverName).orElseThrow(
                () -> new BusinessException("There is no driver with name: " + driverName, HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(driver.getId(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody DriverDto driverDto){
        Driver driver = driverService.createDriver(driverDtoMapper.driverDtoToDriver(driverDto), driverDto.getCompany().getName());
        return new ResponseEntity<>(driver, HttpStatus.CREATED);
    }

    @DeleteMapping("/{driverId}")
    public ResponseEntity deleteDriver(@PathVariable Long driverId){
        Driver driver = driverService.findDriver(driverId).orElseThrow(
                () -> new BusinessException("There is no driver with id: " + driverId, HttpStatus.NOT_FOUND)
        );
        driverService.deleteDriver(driver);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/name/{driverName}")
    public ResponseEntity deleteDriverByName(@PathVariable String driverName){
        Driver driver = driverService.findDriverByName(driverName).orElseThrow(
                () -> new BusinessException("There is no driver with name: " + driverName, HttpStatus.NOT_FOUND)
        );
        driverService.deleteDriver(driver);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{driverId}")
    public ResponseEntity<Driver> updateDriver(@PathVariable Long driverId, @RequestParam String newName, @RequestParam Integer newAge){
        Driver driver = driverService.findDriver(driverId).orElseThrow(
                () -> new BusinessException("There is no driver with id: " + driverId, HttpStatus.NOT_FOUND)
        );
        return new ResponseEntity<>(driverService.updateDriver(driver, newName, newAge), HttpStatus.OK);
    }
}