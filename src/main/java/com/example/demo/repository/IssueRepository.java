package com.example.demo.repository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
@Repository
public interface IssueRepository extends JpaRepository<Issue, Long>{

}
