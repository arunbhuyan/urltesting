package com.market.company.repository;


import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.market.company.model.Company;

//@Repository
//public interface CompanyRepo extends MongoRepository<Company, String>{
//
//}
@Repository
public interface CompanyRepo extends JpaRepository<Company, String>{

}
