package com.jk.service;

import com.jk.mapper.DoctorMapper;
import com.jk.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService{

    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public List<Doctor> getdoctorList() {

        return doctorMapper.getdoctorList();
    }

    @Override
    public void adddoctor(Doctor doctor) {
        doctorMapper.adddoctor(doctor);
    }

    @Override
    public void deletedoctor(String id) {
        doctorMapper.deletedoctor(id);
    }

    @Override
    public Doctor querydoctorById(Integer id) {

        return doctorMapper.querydoctorById(id);
    }

    @Override
    public void updatedoctor(Doctor doctor) {
        doctorMapper.updatedoctor(doctor);
    }
}
