package com.training.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.training.model.Visitor;

public interface VisitorDao extends CrudRepository<Visitor, Integer> {
    List<Visitor> findByPurpose(String purpose);
    List<Visitor> findByMobileNumber(String mobileNumString);
    List<Visitor> findByVisitorNameAndPurpose(String visitorName, String purpose);
    List<Visitor> findByVisitorIdBetween(int lower, int upper);
}
