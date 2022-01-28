package com.example.demo.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import com.example.demo.model.Issue;
import com.example.demo.repository.IssueRepository;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class IssueController {
	@Autowired
	private IssueRepository issueRepository;
	
	// get all issues
	
	@GetMapping("/issues")
	public List<Issue> getAllIssues(){
		return issueRepository.findAll();
	}	
	
	// create issue rest api
	
	@PostMapping("/issues")
	public Issue createIssue(@RequestBody Issue issue) {
		return issueRepository.save(issue);
	}
	
	// get issue by id rest api
	@GetMapping("/issues/{id}")
	public ResponseEntity<Issue> getIssueById(@PathVariable Long id) {
		Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(issue);
		
	}
	
	// update issue rest api
	@PutMapping("/issues/{id}")
	public ResponseEntity<Issue> updateIssue(@PathVariable Long id,@RequestBody Issue issueDetails){
		
		Issue issue = issueRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));
		
		issue.setName(issueDetails.getName());
		issue.setPriority(issueDetails.getPriority());
		issue.setStatus(issueDetails.getStatus());
		issue.setDescription(issueDetails.getDescription());
		
		Issue updatedIssue = issueRepository.save(issue);
		return ResponseEntity.ok(updatedIssue);
		
	}
	
	//delete issue rest api
	@DeleteMapping("/issues/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteIssue(@PathVariable Long id){
		Issue issue = issueRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Issue not exist with id :" + id));
				
				issueRepository.delete(issue);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
		
	}
	
}
