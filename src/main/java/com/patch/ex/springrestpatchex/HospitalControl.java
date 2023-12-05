package com.patch.ex.springrestpatchex;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HospitalControl {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/hospitals")
    public List<Hospital> getHospital() {

        return hospitalService.getHospitalLists();
    }

    @PutMapping("/updatehospital/{id}")
    public Hospital updateHospitalByPutUsingId(@RequestBody Hospital newHospital, @PathVariable int id) {

        return hospitalService.updateHospitalById(newHospital, id);
    }

    @PutMapping("/hospitals/{name}")
    public Hospital updateHospitalByPutUsingName(@RequestBody Hospital newHospital, @PathVariable String name) {

        return hospitalService.updateHospitalByName(newHospital, name);
    }

    @PatchMapping("/partialupdate/{id}")
    public Hospital partialUpdate(@RequestBody Map<String, Object> fields, @PathVariable int id) {
        return hospitalService.partialDataUpdate(fields, id);
    }
}