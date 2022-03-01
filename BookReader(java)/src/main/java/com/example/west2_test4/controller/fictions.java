package com.example.west2_test4.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.west2_test4.entity.Fiction;
import com.example.west2_test4.entity.Details;

import com.example.west2_test4.entity.User;
import org.springframework.web.bind.annotation.*;
import top.jfunc.json.impl.JSONArray;


import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.example.west2_test4.Dao.sql_Methods.*;

@RestController
@RequestMapping("/fiction")
public class fictions {

    @RequestMapping("/text")
    public String keyword(@RequestParam(value = "fic_id", defaultValue = "0") int fic_id) throws IOException {
        List<Fiction> fictions = selectFiction();
        for (Fiction i : fictions) {
            if (i.getId() == fic_id) {
                return i.getText();
            }
        }
        return "not found";
    }

    @RequestMapping("/details")
    public Details Details(@RequestParam(value = "id", defaultValue = "0") int id) throws IOException {
        List<Fiction> fictions = selectFiction();
        for (Fiction i : fictions) {
            if (i.getId() == id) {
                Details details = new Details();
                details.setId(i.getId());
                details.setIntro(i.getIntro());
                details.setMarks(i.getMarks());
                details.setName(i.getName());
                details.setPicture_path(i.getPicture_path());
                details.setWriter(i.getWriter());
                return details;
            }
        }
        Details details = new Details();
        return details;
    }

    @RequestMapping("/recommend")
    public String recommend(@RequestParam("id") int id) throws IOException {
        List<Fiction> fictions = selectFiction();
        User user = selectOneUser(id);
        JSONArray jsonArray = new JSONArray(user.getCollection());
        Details[] details = {new Details(), new Details(), new Details()};
        int index = 0;
        for (Fiction i : fictions) {
            details[index].setId(i.getId());
            details[index].setIntro(i.getIntro());
            details[index].setMarks(i.getMarks());
            details[index].setName(i.getName());
            details[index].setPicture_path(i.getPicture_path());
            details[index].setWriter(i.getWriter());
            if((user.getCollection()==null)||(user.getCollection().equals(""))){
                details[index].setCollected(true);
            }else{
                Iterator<Object> iterator = jsonArray.unwrap().iterator();
                boolean check=false;
                while (iterator.hasNext()) {
                    JSONObject jo = (JSONObject) iterator.next();
                    if (jo.getIntValue("collected") == i.getId()) {
                        check=true;
                    }
                }
                if(check){
                    details[index].setCollected(true);
                }else{
                    details[index].setCollected(false);
                }
            }
            index++;
            if (index == 3) {
                break;
            }
        }
        JSONArray array = new JSONArray();
        array.put(details[0]);
        array.put(details[1]);
        array.put(details[2]);
        return array.toString();
    }


    @RequestMapping("/search")
    public String search(@RequestParam("id") int id,@RequestParam("search") String key) throws IOException {
        List<Fiction> fictions = selectFiction();
        Details details = new Details();
        User user=selectOneUser(id);
        JSONArray jsonArray=new JSONArray(user.getCollection());
        for(Fiction i : fictions){
            if(i.getName().equals(key)){
                details.setId(i.getId());
                details.setWriter(i.getWriter());
                details.setPicture_path(i.getPicture_path());
                details.setName(i.getName());
                details.setMarks(i.getMarks());
                details.setIntro(i.getIntro());
                Iterator<Object> iterator = jsonArray.unwrap().iterator();
                boolean check=false;
                while (iterator.hasNext()) {
                    JSONObject jo = (JSONObject) iterator.next();
                    if (jo.getIntValue("collected") == i.getId()) {
                        check = true;
                    }
                }
                if(check){
                    details.setCollected(true);
                }else{
                    details.setCollected(false);
                }
            }
        }
        JSONArray array = new JSONArray();
        array.put(details);
        return array.toString();
    }


    @RequestMapping("/searchInCollection")
    public String searchInCollection(@RequestParam("id") int id,@RequestParam("search") String search) throws IOException {
        List<Fiction> fictions =selectFiction();
        List<Details> details = new LinkedList<>();
        User user = selectOneUser(id);
        JSONArray jsonArray = new JSONArray(user.getCollection());
        for(Fiction i :fictions){
            Iterator<Object> iterator = jsonArray.unwrap().iterator();
            boolean check=false;
            while (iterator.hasNext()) {
                JSONObject jo = (JSONObject) iterator.next();
                if (jo.getIntValue("collected") == i.getId()) {
                    check = true;
                }
            }
            Details d = new Details(i.getId(), i.getName(), i.getIntro(), i.getMarks(), i.getPicture_path(), check, i.getWriter());
            details.add(d);
        }
        JSONArray array=new JSONArray();
        for(Details details1:details){
            if(details1.isCollected()&details1.getName().equals(search)) {
                array.put(details1);
            }
        }
        return array.toString();
    }
}