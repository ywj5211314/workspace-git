package com.jk.mapper;

import com.jk.model.Car;

import java.util.List;
import java.util.Map;

public interface CarMapper {


    List<Map<String, Object>> queryCar();

    List<Map<String, Object>> queryCarAll();
}