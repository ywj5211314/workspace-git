package com.jk.controller;

import com.jk.model.Doctor;
import com.jk.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @RequestMapping("toDoctorShow")
    public String toDoctorShow() {
        return "DoctorShow";
    }

    @RequestMapping("getdoctorList")
    @ResponseBody
    public List<Doctor> getdoctorList(){
        List<Doctor> list = doctorService.getdoctorList();
        return list;
    }
    @RequestMapping("toAdddoctor")
    public String toAdddoctor(){
        return "add";
    }

    @RequestMapping("adddoctor")
    @ResponseBody
    public String adddoctor(Doctor doctor){
        doctorService.adddoctor(doctor);
        return "1";
    }
    @RequestMapping("deletedoctor")
    @ResponseBody
    public void deletedoctor(String id){
       doctorService.deletedoctor(id);
    }

    //根据id查询一条数据
    @RequestMapping("querydoctorById")
    public String querydoctorById(Integer id, Model model){
        Doctor doc = doctorService.querydoctorById(id);
        model.addAttribute("dd",doc);
        return "add";
    }
    //修改
    @RequestMapping("updatedoctor")
    @ResponseBody
    public String updatedoctor(Doctor doctor){
        doctorService.updatedoctor(doctor);
        return "1";
    }

}
