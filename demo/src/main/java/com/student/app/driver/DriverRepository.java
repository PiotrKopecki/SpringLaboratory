package com.student.app.driver;


import com.student.app.driver.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query("SELECT t FROM Driver t WHERE t.name = ?1")
    Optional<Driver> findByName(String name);
}
