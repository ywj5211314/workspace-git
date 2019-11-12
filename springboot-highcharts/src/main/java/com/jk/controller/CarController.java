package com.jk.controller;

import com.jk.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("car")
public class CarController {

    @Autowired
    private CarService carService;

    @RequestMapping("toQueryCar")
    public String toQueryCar(HttpServletRequest request){

        return "carshow";
    }

    //展示2019年每一个月的销量
    @RequestMapping("queryCar")
    @ResponseBody
    public List<Map<String,Object>> queryCarList(){
        //查询数据库数据
        List<Map<String,Object>> map1 =carService.queryCar();
        //前台展示的返回的数据
        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();

        for (Map<String,Object> map:map1) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("y",map.get("y"));
            if(map.get("类型").toString().equals("1")){
                map3.put("name","跑车");
                map3.put("sliced",true);
                map3.put("selected",true);

            }else if(map.get("类型").toString().equals("2")){
                map3.put("name","轿车");
            }else if(map.get("类型").toString().equals("3")){
                map3.put("name","suv");
            }
            else if(map.get("类型").toString().equals("4")){
                map3.put("name","豪车");
            }else {
                map3.put("name","其他车");
            }
            map2.add(map3);
        }



        return map2;
    }

    //使用折线图展示每一周的销量
    @RequestMapping("queryCarAll")
    @ResponseBody
    public List<Map<String,Object>> queryCarAll(){

        /**
         *
         * 2.
         * 3.使用面积图展示每个品牌的销量
         * 4.使用柱状图展示一个月内每一天的销量
         */
        List<Map<String,Object>> map =carService.queryCarAll();

        List<Map<String,Object>> map2 =new ArrayList<Map<String,Object>>();

        for (Map<String,Object> map1:map) {
            Map<String,Object> map3=new HashMap<>();
            map3.put("name",map1.get("Time"));
            map3.put("data",map1.get("y"));
            map2.add(map3);

        }




        return map;
    }


}
