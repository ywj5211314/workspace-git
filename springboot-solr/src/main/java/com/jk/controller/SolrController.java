package com.jk.controller;

import com.jk.model.Car;
import com.jk.service.CarService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("car")
public class SolrController {

    @Autowired
    private SolrClient client;

    @Autowired
    private CarService carService;

     @RequestMapping("addcar")
     public String addcar(){
         return "addcar";
     }


    @RequestMapping("add")
    @ResponseBody
    public String add(Car car){
      /*  Car car1 = new Car();*/
      /*  car1.setCarname("比亚迪");*/
      /*  car1.setCarprice(100000);*/
      /*  car1.setCarsales(2000);*/
      /*  car1.setCartypeid(1);*/
      /*  car1.setCartime(new Date().toString());*/

        //执行数据库新增
        carService.addCar(car);
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        try{
            SolrInputDocument doc = new SolrInputDocument();
            doc.setField("id",car.getCarid());
            doc.setField("carname",car.getCarname());
            doc.setField("carprice",car.getCarprice());
            doc.setField("carsales",car.getCarsales());
            //doc.setField("cartypeid",car.getCartypeid());
            doc.setField("cartime",car.getCartime());
           /* 如果spring.data.solr.host 里面配置到 core了, 那么这里就不需要传 collection1 这个参数
           * 下面都是一样的
          */
           client.add("core1", doc);
           //client.commit();
           client.commit("core1");
            return uuid;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }

   /* @RequestMapping("getcarList")
    public String getcarList(){

    }
*/
    @RequestMapping("toindex")
    public String toindex(){
        return "index";
    }
    /**
     * 综合查询: 在综合查询中, 有按条件查询, 条件过滤, 排序, 分页, 高亮显示, 获取部分域信息
     * @return
     */
    @RequestMapping("search")
    @ResponseBody
    public Map<String, Object> search(Car car, Integer page, Integer rows){


        //返回到前台
        Map<String, Object> map1=new HashMap<>();

        try {
            //存放所有的查询条件
            SolrQuery params = new SolrQuery();

            //查询条件, 这里的 q 对应 下面图片标红的地方
            if(car.getCarname()!=null && !"".equals(car.getCarname())){
                params.set("q", car.getCarname());
            }else {
                params.set("q", "*:*");
            }


            //过滤条件
            // params.set("fq", "carPrice:["+car.get+" TO "++"]");

            //排序
            params.addSort("carprice", SolrQuery.ORDER.desc);

            //分页
            if(page==null){
                params.setStart(0);
            }else {
                params.setStart((page-1)*rows);
            }
            if(rows==null){
                params.setRows(5);
            }else {
                params.setRows(rows);
            }


            //默认域
            params.set("df", "carname");

            //只查询指定域
            //params.set("fl", "id,product_title,product_price");

            //高亮
            //打开开关
            params.setHighlight(true);
            //指定高亮域
            params.addHighlightField("carname");
            //设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            //设置后缀
            params.setHighlightSimplePost("</span>");

            //查询后返回的对象
            QueryResponse queryResponse = client.query("core1",params);
            //查询后返回的对象 获得真正的查询结果
            SolrDocumentList results = queryResponse.getResults();
            //查询的总条数
            long numFound = results.getNumFound();

            System.out.println(numFound);

            //获取高亮显示的结果, 高亮显示的结果和查询结果是分开放的
            Map<String, Map<String, List<String>>> highlight = queryResponse.getHighlighting();

            //创建list集合接收我们循环的参数
            List<Car> list1 =new ArrayList<>();
            for (SolrDocument result : results) {

                Car car1=new Car();
                String highFile="";

                Map<String, List<String>> map = highlight.get(result.get("id"));
                SimpleDateFormat sd = new SimpleDateFormat();
                List<String> list = map.get("carname");
                if(list==null){
                    highFile= result.get("carname").toString();
                }else {
                    highFile=list.get(0);
                }

                car1.setCarid(Integer.parseInt(result.get("id").toString()));
                car1.setCarname(result.get("carname").toString());
                car1.setCarprice((Integer) result.get("carprice"));
                car1.setCarsales(Integer.parseInt(result.get("carsales").toString()));
                car1.setCartime(result.get("cartime").toString());

                list1.add(car1);
            }
            map1.put("total",numFound);
            map1.put("rows",list1);
            return map1;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }
}
