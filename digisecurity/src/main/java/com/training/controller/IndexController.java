package com.training.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.training.dao.VisitorDao;
import com.training.model.Visitor;

@RestController
@RequestMapping("visitor")
public class IndexController {
    @Autowired
    VisitorDao v;

    @GetMapping()
    public ResponseEntity<List<Visitor>> getAllVisitor() {
        List<Visitor> vis = (List<Visitor>) v.findAll();
        return new ResponseEntity<>(vis, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Void> postVisitor(@RequestBody Visitor vn) {
        v.save(vn);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{lower}/{upper}") // GET visitors by ID range
    public ResponseEntity<List<Visitor>> getVisitor(
            @PathVariable("lower") int lower,
            @PathVariable("upper") int upper) {
        List<Visitor> visitors = (List<Visitor>) v.findByVisitorIdBetween(lower, upper);
        return new ResponseEntity<>(visitors, HttpStatus.OK);
    }

    @GetMapping("search/{visitorName}/{purpose}") // GET visitors by name and purpose
    public ResponseEntity<List<Visitor>> findByVisitorNameAndPurpose(
            @PathVariable("visitorName") String visitorName,
            @PathVariable("purpose") String purpose) {
        List<Visitor> visitors = (List<Visitor>) v.findByVisitorNameAndPurpose(visitorName, purpose);
        return new ResponseEntity<>(visitors, HttpStatus.OK);
    }

    @PutMapping // Update visitor
    public ResponseEntity<List<Visitor>> updateVisitor(@RequestBody Visitor visitor) {
        v.save(visitor);
        List<Visitor> visitors = (List<Visitor>) v.findAll();
        return new ResponseEntity<>(visitors, HttpStatus.OK);
    }
}
