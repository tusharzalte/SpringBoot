package com.training.controller;

import java.util.ArrayList;



import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.Visitor;
import com.training.utility.Message;


@RestController
@RequestMapping("visitor")
public class VsitorController {
//	@Autowired
//	Message start;
//	@GetMapping("test")
//	public String getMsg(){
//		return start.getMessage();
//	}
	  List<Visitor> visitors = new ArrayList<>(Arrays.asList(
	            new Visitor(111, "tushar", "343434", "training"),
	            new Visitor(234, "suyash", "65769", "Meeting"),
	            new Visitor(8768, "Ajay", "8768", "training"),
	            new Visitor(876, "Ajay", "8768", "training")
	        ));
	@GetMapping
	public List<Visitor>getVisitors(@RequestParam(name="VisitorName",required=true)String VisitorName, @RequestParam(name="mobilNumber",required=false)String mobilNumber)
	{
		//Visitor visitor=null;
		List<Visitor> searchedVisitor = new ArrayList<Visitor>();
		if(VisitorName==null && mobilNumber==null)
		{
			return visitors;
		}
		else
		{
			//visitor = visitors.stream().filter(t->t.getVisitorName().equals(VisitorName)).findAny().get();
			for(int i=0;i<visitors.size();i++) {
				 Visitor v=visitors.get(i);
				 if(v.getVisitorName().equals(VisitorName) && v.getMobilNumber().equals(mobilNumber)) {
					 searchedVisitor.add(v);
				 }
			 }
		}
		return searchedVisitor;
	}
	
//	
//	@GetMapping
//	public ResponseEntity<List<Visitor>> getVisitors(
//			@RequestParam(name = "VisitorName", required = false) String visitorName) {
//		ResponseEntity<List<Visitor>> responseEntity = null;
//		List<Visitor> searchedVisitor = new ArrayList<Visitor>();
//		if (visitorName == null) {
//			responseEntity = new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);		//200 - for all records
//		} else {
//			for (int i = 0; i < visitors.size(); i++) {
//				Visitor v = visitors.get(i);
//				if (v.getVisitorName().equals(visitorName))
//					searchedVisitor.add(v);
//			}
//			if (searchedVisitor.size() == 0) {
//				//if no record found 
//				responseEntity = new ResponseEntity<List<Visitor>>(searchedVisitor, HttpStatus.NO_CONTENT); // 204
//			}
//			else
//				responseEntity = new ResponseEntity<List<Visitor>>(searchedVisitor, HttpStatus.I_AM_A_TEAPOT); // 204
//		}
//		return responseEntity;
//	}
	
	@GetMapping("{visitorID}")
	public ResponseEntity<List<Visitor>> getVisitors(
			@RequestParam(name = "VisitorName", required = false) String visitorName) {

		ResponseEntity<List<Visitor>> responseEntity = null;

		List<Visitor> searchedVisitor = new ArrayList<Visitor>();
		if (visitorName == null) {
			responseEntity = new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK);		//200 - for all records
		} else {
			for (int i = 0; i < visitors.size(); i++) {
				Visitor v = visitors.get(i);
				if (v.getVisitorName().equals(visitorName))
					searchedVisitor.add(v);
			}
			if (searchedVisitor.size() == 0) {
				//if no record found 
				responseEntity = new ResponseEntity<List<Visitor>>(searchedVisitor, HttpStatus.NO_CONTENT); // 204
			}
			else
				responseEntity = new ResponseEntity<List<Visitor>>(searchedVisitor, HttpStatus.I_AM_A_TEAPOT); // 204

		}
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<List<Visitor>> setVisitors(
			@RequestBody Visitor visitor) {
		ResponseEntity<List<Visitor>> responseEntity = null;
			for (int i = 0; i < visitors.size(); i++) {
				Visitor v = visitors.get(i);
				if (v.getVisitor_id()==visitor.getVisitor_id()) {
					return  new ResponseEntity<List<Visitor>>(visitors, HttpStatus.NO_CONTENT); // 204
				}
			}
				visitors.add(visitor);
				responseEntity = new ResponseEntity<List<Visitor>>(visitors, HttpStatus.CREATED); // 204
	            return responseEntity; 
	}
	
	@PutMapping("{visitorID}")
	public ResponseEntity<List<Visitor>> updateVisitors(@PathVariable("visitorID") int id,@RequestBody Visitor visitor)
	{
		for(int i=0;i<visitors.size();i++)
		{
			Visitor v = visitors.get(i);
			if(v.getVisitor_id()==id)
			{
				v.setMobilNumber(visitor.getMobilNumber());
				v.setPurpose(visitor.getPurpose());
				v.setMobilNumber(visitor.getMobilNumber());
				return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK); // 204
			}
		}
		return  new ResponseEntity<List<Visitor>>(visitors, HttpStatus.NO_CONTENT);
		
	}
	
	@DeleteMapping("{visitor_ID}")
	public ResponseEntity<List<Visitor>> deleteVisitor(@PathVariable("visitor_ID")int id)
	{
		for(int i=0;i<visitors.size();i++)
		{
			Visitor v= visitors.get(i);
			if(v.getVisitor_id()==id)
			{
				visitors.remove(i);
				return new ResponseEntity<List<Visitor>>(visitors, HttpStatus.OK); // 204
			}
		}
		return  new ResponseEntity<List<Visitor>>(visitors, HttpStatus.NO_CONTENT);
	}
	
}


  
	
