package com.jk.mapper;


import com.jk.model.Doctor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {

    List<Doctor> getdoctorList();

    void adddoctor(Doctor doctor);

    void deletedoctor(@Param("id") String id);

    Doctor querydoctorById(@Param("id") Integer id);

    void updatedoctor(Doctor doctor);
}