package com.example.west2_test4.controller;


import com.example.west2_test4.entity.Fiction;
import com.example.west2_test4.entity.Infomation;
import top.jfunc.json.impl.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.west2_test4.Dao.sql_Methods;
import com.example.west2_test4.entity.History;
import com.example.west2_test4.entity.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static com.example.west2_test4.Dao.sql_Methods.*;


@RestController
@RequestMapping("")
public class history {

    @RequestMapping("/getTheHistory")
    public static String getHistory(@RequestParam("id") int id) throws IOException {
        User user =selectOneUser(id);
        JSONArray array=new JSONArray(user.getHistory());
        return array.toString();
    }


    @RequestMapping("/setTheHistory")
    public String setHistory(@RequestParam("id") int id,@RequestParam("fic_id") int fic_id) throws IOException {
        User user=selectOneUser(id);
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Date date = new Date(System.currentTimeMillis());

        Fiction fiction =selectOneFiction(fic_id);
        JSONArray array=new JSONArray(user.getCollection());
        boolean check=false;
        if(!(user.getHistory()==null||user.getHistory().equals(""))) {
            Iterator<Object> iterator = array.unwrap().iterator();
            while (iterator.hasNext()) {
                JSONObject jo = (JSONObject) iterator.next();
                if (jo.getIntValue("collected") == fiction.getId()) {
                    check=true;
                }
            }
        }
//        History hst=new History(fiction.getId(),fiction.getName(),fiction.getIntro(),fiction.getMarks(),fiction.getPicture_path(),check,fiction.getWriter(),formatter.format(date));
        History hst=new History(fiction.getId(),fiction.getName(),fiction.getIntro(),fiction.getMarks(),fiction.getPicture_path(),check,fiction.getWriter(),String.valueOf(System.currentTimeMillis()));
        if(user.getHistory()==null||user.getHistory().equals("")){
            JSONArray jsonArray=new JSONArray();
            jsonArray.put(hst);
            updateUser(user.getId(),user.getPassword(),user.getRandCode(),user.getCollection(),jsonArray.toString());
        }else {
            JSONArray jsonArray = new JSONArray(user.getHistory());
            jsonArray.put(hst);
            updateUser(user.getId(),user.getPassword(),user.getRandCode(),user.getCollection(),jsonArray.toString());
        }
        return "success";
    }


}
