package cn.springcloud.meisw.kafka.util;

import net.sf.json.JSONObject;

public class GetCityList {
	 /**
     * 调用获取城市列表接口,返回所有数据
     * @return 返回接口数据
     */
    public static String excute(){
        String url="http://v.juhe.cn/weather/citys?key=***a7558b2e0bedaa19673f74a6809ce";//接口URL
        //PureNetUtil是一个封装了get和post方法获取网络请求数据的工具类
        return PureNetUtil.get(url);//使用get方法
    }
    /**
     * 调用接口返回数据后,解析数据,根据输入城市名得到对应ID
     * @param cityName 城市名称
     * @return 返回对应ID
     */
    public static String getIDBycityName(String cityName) {
        String result=excute();//返回接口结果,得到json格式数据
        if(result!=null){
            net.sf.json.JSONObject obj=net.sf.json.JSONObject.fromObject(result);
            result=obj.getString("resultcode");//得到返回状态码
            if(result!=null&&result.equals("200")){//200表示成功返回数据
                result=obj.getString("result");//得到城市列表的json格式字符串数组
                net.sf.json.JSONArray arr=net.sf.json.JSONArray.fromObject(result);
                for(Object o:arr){//对arr进行遍历
                    //将数组中的一个json个数字符串进行解析
                    obj=JSONObject.fromObject(o.toString());
                    /*此时obj如 {"id":"2","province":"北京","city":"北京","district":"海淀"}*/
                    //以city这个key为线索判断所需要寻找的这条记录
                    result=obj.getString("district");
                    //防止输入城市名不全,如苏州市输入为苏州,类似与模糊查询
                    if(result.equals(cityName)||result.contains(cityName)){
                        result=obj.getString("id");//得到ID
                        return result;
                    }
                }
            }
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(getIDBycityName("香港"));
    }
}
