package com.crabtree.persistence.repo;

import com.crabtree.persistence.model.Company;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {
    List<Company> findByTicker(String ticker);
}
