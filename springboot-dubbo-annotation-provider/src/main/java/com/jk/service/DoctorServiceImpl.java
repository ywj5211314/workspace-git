package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.DoctorMapper;
import com.jk.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0")
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

    @Override
    public List<Doctor> queryDoctor() {
        return doctorMapper.queryDoctor();
    }

    @Override
    public void addDoctor(List<Doctor> list) {
        doctorMapper.addDoctor(list);
    }
}
