
import com.alibaba.fastjson.JSONObject;
import top.jfunc.json.impl.JSONArray;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class draft {
    public static void main(String[] args){
        //JSONObject内部封装了一个HashMap<String, String>，用于存储json对象的属性名（String类型）和属性值。
//JSONObject构造1
//        JSONObject obj = new JSONObject();
//        System.out.println(obj.toString());
//        obj.put("100", 1);
//        obj.put("1000", 2);
//        System.out.println(obj.toString());
//
//////JSONObject构造2，参数传入json格式的字符串
//        JSONObject obj2 = new JSONObject(obj.toString());
//        System.out.println(obj2.toString());
//
////JSONObject属性遍历
//        Iterator<String> it = obj2.keySet().iterator();
//        while (it.hasNext()) {
//            String key = it.next();
//            System.out.println(key+"="+(int)obj2.get(key));
//        }
////JSONArray构造1
//        JSONArray array = new JSONArray();
//        array.put(obj);
//        array.put(obj);
//        System.out.println(array.toString());
////
////JSONArray构造2，传入json数组格式的字符串
//        String jsonArrStr = "[{\"1000\":2,\"100\":111},{\"1000\":2,\"100\":222}]";
//        array = new JSONArray(jsonArrStr);
//        System.out.println(array.toString());
////
////JSONArray遍历
//        for(int i=0; i<array.length(); i++) {
//            JSONObject jsonObj = array.getJSONObject(i);
//            System.out.println(jsonObj.toString());
//        }

//
//        //1. 创建JSONArray对象
//        String json = "[{\"name\":\"张三\",\"code\":\"123\"},{\"name\":\"李四\",\"code\":\"123\"}]";
//        JSONArray jsonArray = new JSONArray(json);
//        System.out.println("jsonArray: "+jsonArray);
//        System.out.println();
//
//        //2. JSONArray转String
//        String jsonString = jsonArray.toString();
//        System.out.println("JSONArray转String: "+jsonString);
//        System.out.println(jsonArray);
//        Iterator<Object> iterator = jsonArray.unwrap().iterator();
//        while (iterator.hasNext()) {
//            JSONObject jo = (JSONObject) iterator.next();
//            if(jo.getIntValue("key") == 123) {
//                iterator.remove();
//            }
//        }
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(df);

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
    }
}
