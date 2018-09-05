package com.springdemo.ax1.controller;

import com.springdemo.ax1.exception.ResourceNotFoundException;
import com.springdemo.ax1.model.Manager;
import com.springdemo.ax1.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
public class ManagerController {

    @Autowired
    private ManagerRepository managerRepository;

    @GetMapping("/managers")
    public List<Manager> getAllManagers(){
        return this.managerRepository.findAll();
    }

    @GetMapping("/managers/{managerId}")
    public Manager getManagerById(@PathVariable (name = "managerId") Long managerId){
        return this.managerRepository.findById(managerId)
                .orElseThrow(() -> new ResourceNotFoundException("manager id " + managerId + " not found"));
    }

    @PostMapping("/managers")
    public Manager createManager(@Valid @RequestBody Manager manager){
        return this.managerRepository.save(manager);
    }

    @DeleteMapping("/managers/{managerId}")
    public ResponseEntity<?> deleteManager(@PathVariable (name = "managerId") Long managerId){
        return this.managerRepository.findById(managerId).map(manager -> {
            this.managerRepository.delete(manager);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("manager id " + managerId + " not found"));
    }

}
