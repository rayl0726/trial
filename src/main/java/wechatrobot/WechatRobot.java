package wechatrobot;

import okhttp3.*;

import java.util.concurrent.TimeUnit;

/**
 * @author : liulei
 **/
public class WechatRobot {
    public static void main(String[] args) throws Exception {
        String url = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send?key=c1b3ac70-f716-4f7d-9656-0117ee925ba0";
        String reqBody = "{" +
                "    \"msgtype\":\"text\"," +
                "    \"agentid\":1," +
                "    \"text\":{" +
                "        \"content\":\"红牛教教主弃教" +
                "        \"mentioned_list\":[\"刘峰瑜\"]" +
                "        \"mentioned_mobile_list\":[\"18613816696\"]" +
                "}";

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)// 设置连接超时时间
                .readTimeout(20, TimeUnit.SECONDS)// 设置读取超时时间
                .build();
        MediaType contentType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(contentType, reqBody);
        Request request = new Request.Builder().url(url).post(body).addHeader("cache-control", "no-cache").build();
        Response response = client.newCall(request).execute();
        byte[] datas = response.body().bytes();
        String respMsg = new String(datas);
        System.out.println(respMsg);
    }
}
