package com.jk.service;

import com.jk.model.Doctor;

import java.util.List;

public interface DoctorService {

    List<Doctor> getdoctorList();

    void adddoctor(Doctor doctor);

    void deletedoctor(String id);

    Doctor querydoctorById(Integer id);

    void updatedoctor(Doctor doctor);

}
