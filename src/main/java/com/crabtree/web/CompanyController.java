package com.crabtree.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.crabtree.persistence.model.Company;
import com.crabtree.persistence.repo.CompanyRepository;
import com.crabtree.web.exception.CompanyNotFoundException;
import com.crabtree.web.exception.CompanyMismatchException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public Iterable<Company> findAll() {
        return companyRepository.findAll();
    }

    @GetMapping("/testing")
    public String testMyApi() {
        return "Hey you did it, bro";
    }

    @GetMapping("/ticker/{companyTicker}")
    public List<Company> findByTicker(@PathVariable String companyTicker) {
        return companyRepository.findByTicker(companyTicker);
    }

    @GetMapping("/{id}")
    public Company findOne(@PathVariable long id) {
        return companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company company) {
        Company company1 = companyRepository.save(company);
        return company1;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        companyRepository.findById(id)
                .orElseThrow((CompanyNotFoundException::new));
        companyRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@RequestBody Company company, @PathVariable long id) {
        if (company.getId() != id) {
            throw new CompanyMismatchException();
        }
        companyRepository.findById(id)
                .orElseThrow(CompanyNotFoundException::new);
        return companyRepository.save(company);
    }
}
