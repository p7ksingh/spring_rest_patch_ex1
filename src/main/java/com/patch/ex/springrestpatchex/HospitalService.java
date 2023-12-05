package com.patch.ex.springrestpatchex;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

@Service
public class HospitalService {

    List<Hospital> hospitalList = new ArrayList<Hospital>(List.of(new Hospital(101, "AIIMS", "Delhi", 5.0),
            new Hospital(102, "PMCH", "Bihar", 3.0), new Hospital(105, "KIMS", "Bihar", 4.0),
            new Hospital(103, "KMCH", "Kerla", 3.5)));

    public List<Hospital> getHospitalLists() {
        return hospitalList;
    }

    public Hospital updateHospitalById(Hospital newHospital, int id) {
        Hospital existingHospital = hospitalList.stream().filter(hospital -> hospital.getId() == id).findFirst().get();
        existingHospital.setId(newHospital.getId());
        existingHospital.setName(newHospital.getName());
        existingHospital.setRating(newHospital.getRating());
        existingHospital.setLocation(newHospital.getLocation());
        return existingHospital;
    }

    public Hospital updateHospitalByName(Hospital newHospital, String name) {
        Hospital oldHospital = hospitalList.stream().filter(hospital -> hospital.getName().equals(name)).findFirst()
                .get();
        oldHospital.setRating(newHospital.getRating());
        return oldHospital;
    }

    public Hospital partialDataUpdate(Map<String, Object> fields, int id) {
        Hospital existingHospital = hospitalList.stream().filter(hospital -> hospital.getId() == id).findFirst().get();
        // ReflactionUtils use for finding field
        // finding key in Hospital class
        // here forEach taken due to map key and value
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Hospital.class, key); // found field from Hospital class
            field.setAccessible(true);
            ReflectionUtils.setField(field, existingHospital, value); // the value taken from body
        });
        return existingHospital;
    }
}