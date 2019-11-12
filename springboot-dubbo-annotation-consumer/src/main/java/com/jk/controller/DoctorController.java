package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Doctor;
import com.jk.service.DoctorService;
import com.jk.util.ExportExcel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("doctor")
public class DoctorController {

    @Reference(version = "1.0")
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
        return "update";
    }
    //修改
    @RequestMapping("updatedoctor")
    @ResponseBody
    public String updatedoctor(Doctor doctor){
        doctorService.updatedoctor(doctor);
        return "1";
    }

    @RequestMapping("export")
    public void export(HttpServletResponse response){
        List<Doctor> list= new ArrayList<Doctor>();
        try {
            list = doctorService.queryDoctor();

            //定义表格的标题
            String title ="医生信息";
            //定义列名
            String[] rowName={"医生编号","医生名称","医生年龄","医生性别"};
            //定义工具类要的数据
            List<Object[]>  dataList = new ArrayList<Object[]>();



            //循环数据把数据存放到 List<Object[]>
            for (Doctor doctor:list) {
                Object[] obj=new Object[rowName.length];
                obj[0]=doctor.getDocid();
                obj[1]= doctor.getDocname();
                obj[2]=doctor.getDocage();
                if(doctor.getDocsex()==1){
                    obj[3]="男" ;
                }else{
                    obj[3]="女" ;
                }

                dataList.add(obj);
            }
            ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
            exportExcel.export();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //生成新的文件名称
        String filePath = "./src/main/resources/templates/fileupload/";

        //创建list集合接收传递的参数
        List<Doctor> list= new ArrayList<Doctor>();

        //上传文件
        try {
            uploadFile(file.getBytes(), filePath, fileName);

            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件创建流
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            //创建操作excel的对象   因为xls的文件需要HSSFWorkbook  xlsx需要的XSSFWorkbook 因此直接使用workBook对象就行了
            Workbook workbook= WorkbookFactory.create(fileInputStream) ;
            //通过workbook获得sheet页  sheet有可能有多个
            for(int i=0;i<workbook.getNumberOfSheets();i++){
                //创建sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //根绝sheet创建行  row
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建row对象
                    Row row = sheetAt.getRow(j);

                    //创建对象接收从文件读取的内容
                    Doctor doctor=new Doctor();
                    if( !"".equals(row.getCell(1)) && row.getCell(1)!=null){
                        doctor.setDocname(row.getCell(1).toString());
                    }
                    if( !"".equals(row.getCell(2)) && row.getCell(2)!=null){
                        doctor.setDocage(Integer.parseInt(row.getCell(2).toString()));
                    }
                    if( !"".equals(row.getCell(3)) && row.getCell(3)!=null){
                        if(row.getCell(3).toString().equals("男")){
                            doctor.setDocsex(1);
                        }else {
                            doctor.setDocsex(2);
                        }
                    }

                    list.add(doctor);
                }

            }
            doctorService.addDoctor(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "DoctorShow";
    }



    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    @RequestMapping("exportFile")
    public void exportFile(HttpServletResponse response,String[] rowName){

        List<Doctor> list= new ArrayList<Doctor>();
        try {
            list = doctorService.queryDoctor();

            //定义表格的标题
            String title ="医生信息列表";
            //定义列名
            // String[] rowName={"编号","医生名字","医生年龄","医生性别","日期"};
            //定义工具类要的数据
            List<Object[]>  dataList = new ArrayList<Object[]>();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");



            //循环数据把数据存放到 List<Object[]>
            for (Doctor doctor:list) {
                Object[] obj=new Object[rowName.length];
                for (int i=0;i<rowName.length;i++){
                    if(rowName[i].contains("医生编号")){
                        obj[i]=doctor.getDocid();
                    }
                    if(rowName[i].contains("医生名字")){
                        obj[i]=doctor.getDocname();
                    }
                    if(rowName[i].contains("医生年龄")){
                        obj[i]=doctor.getDocage();
                    }
                    if(rowName[i].contains("医生性别")){
                        if(doctor.getDocsex()==1){
                            obj[i]="男";
                        }
                        if(doctor.getDocsex()==2){
                            obj[i]="女";
                        }
                        /*if(book.getBooktype()==3){
                            obj[i]="穿越";
                        }*/
                    }
                   /* if(rowName[i].contains("出版日期")){
                        obj[i]=sdf.format(book.getBooktime());
                    }*/



                }

                dataList.add(obj);
            }
            ExportExcel exportExcel=new ExportExcel(title,rowName,dataList,response);
            exportExcel.export();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
