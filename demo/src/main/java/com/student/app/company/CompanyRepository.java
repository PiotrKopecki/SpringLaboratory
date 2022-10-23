package com.student.app.company;

import com.student.app.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    @Query("SELECT t FROM Company t WHERE t.newName = ?1")
    Optional<Company> findByNewName(String name);
}