package com.example.west2_test4.controller;

import com.example.west2_test4.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import java.io.IOException;
import java.util.List;
import java.util.Random;

import static com.example.west2_test4.Dao.sql_Methods.*;


@RestController
@RequestMapping("/index")
public class login {

    @RequestMapping("/login")
    public String login(@RequestBody String a) throws IOException {
        int x = a.indexOf('&');
        int id = Integer.parseInt(a.substring(3, x));
        String password = a.substring(x + 10);
        List<User> users = selectUser();
        for (User i : users) {
            if (id == i.getId()) {
                if (password.equals(i.getPassword())) {
                    Random r = new Random();
                    int number = r.nextInt(10000);
                    updateUser(id, password, number, i.getCollection(), i.getHistory());
                    return Integer.toString(number);
                }
            }
        }
        return "failed";
    }

    @RequestMapping("/register")
    public String register(@RequestBody String a) throws IOException {
        int x = a.indexOf('&');
        int id = Integer.parseInt(a.substring(3, x));
        String password = a.substring(x + 10);
        insertUser(id, password);
        return "success";
    }

    @RequestMapping("/changePassword")
    public String changePassword(@RequestParam("id") int id,@RequestBody String b) throws IOException {
        int y = b.indexOf("&");
        String a=b.substring(y+1);
        int x = a.indexOf('&');
        int password=Integer.parseInt(a.substring(9,x));
        int new_password=Integer.parseInt(a.substring(x+14));
        User user=selectOneUser(id);
        if(password==Integer.parseInt(user.getPassword())){
            updateUser(user.getId(),Integer.toString(new_password),user.getRandCode(),user.getCollection(),user.getHistory());
            return "success";
        }else{
            return "failed";
        }
    }

}
