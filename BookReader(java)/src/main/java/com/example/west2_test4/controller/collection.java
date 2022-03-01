package com.example.west2_test4.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.west2_test4.entity.Details;
import com.example.west2_test4.entity.Fiction;
import com.example.west2_test4.entity.Infomation;
import com.example.west2_test4.entity.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import top.jfunc.json.impl.JSONArray;

import static com.example.west2_test4.Dao.sql_Methods.*;


@RestController
@RequestMapping("")
public class collection {

    @RequestMapping("/getCollection")
    public static String getCollection(@RequestParam("id") int id) throws IOException {
        List<Fiction> fictions = selectFiction();
        User user = selectOneUser(id);
        JSONArray jsonArray = new JSONArray(user.getCollection());
        JSONArray array = new JSONArray();
        if((user.getCollection()==null)||(user.getCollection().equals(""))){
            JSONArray j=new JSONArray();
            return j.toString();
        } else {
            for (Fiction i : fictions) {
                Iterator<Object> iterator = jsonArray.unwrap().iterator();
                while (iterator.hasNext()) {
                    JSONObject jo = (JSONObject) iterator.next();
                    if (jo.getIntValue("collected") == i.getId()) {
                        Details details = new Details(i.getId(), i.getName(), i.getIntro(), i.getMarks(), i.getPicture_path(), true,i.getWriter());
                        array.put(details);
                    }
                }
            }
            return array.toString();
        }
    }


    @RequestMapping("/deleteCollection")
    public String deleteCollection(@RequestParam("id") int id,@RequestParam("fic_id") int fic_id) throws IOException {
        User user=selectOneUser(id);
        String collection=user.getCollection();
        JSONArray jsonArray = new JSONArray(collection);
        Iterator<Object> iterator = jsonArray.unwrap().iterator();
        while (iterator.hasNext()) {
            JSONObject jo = (JSONObject) iterator.next();
            if (jo.getIntValue("collected") == fic_id) {
                iterator.remove();
            }
        }
        updateUser(user.getId(),user.getPassword(),user.getRandCode(),jsonArray.toString(),user.getHistory());
        return "success";
    }

    @RequestMapping("/setCollection")
    public String setCollection(@RequestParam("id") int id,@RequestParam("fic_id") int fic_id) throws IOException {
        User user=selectOneUser(id);
        String str=user.getCollection();
        Infomation info =new Infomation();
        info.setCollected(Integer.toString(fic_id));
        if((str==null)||(str.equals(""))) {
            JSONArray array = new JSONArray();
            array.put(info);
            updateUser(user.getId(),user.getPassword(),user.getRandCode(),array.toString(),user.getHistory());
        }else{
            JSONArray array = new JSONArray(str);
            Iterator<Object> iterator = array.unwrap().iterator();
            boolean alreadyCollected=false;
            while (iterator.hasNext()) {
                JSONObject jo = (JSONObject) iterator.next();
                if (jo.getIntValue("collected") == fic_id) {
                    alreadyCollected = true;
                }
            }
            if(alreadyCollected){
                return "already collected";
            }else {
                array.put(info);
                updateUser(user.getId(), user.getPassword(), user.getRandCode(), array.toString(), user.getHistory());
            }
        }
        return "success";
    }

}
