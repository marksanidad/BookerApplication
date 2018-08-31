package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Services;

@Repository
public interface ServiceRepository extends CrudRepository<Services, Integer> {

}
