package com.example.mine.mapping.utils.zgch.api;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.example.mine.mapping.utils.http.EAPIConsts;
import com.example.mine.mapping.utils.zgch.bean.Users;
import com.google.gson.Gson;
import com.example.mine.mapping.utils.http.IBindData;

/**
 * Created by dell on 2018/3/31.
 */

public class UserReqUtil extends ReqBase {

    //执行注册操作
    public static void doRegister(Context context, IBindData bind,
                                  Users user, Handler handler){
        String url = EAPIConsts.COMMON_URL + EAPIConsts.ReqUrl.REGISTER;//注册地址
        System.out.println(url);
        Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
        String param=getUserParams(user);
        doExecute(context,bind,param,handler,url,EAPIConsts.ReqType.REGISTER);
    }

    //执行登录操作
    public static void doLogin(Context context, IBindData bind,
                               Users user, Handler handler){
        String url = EAPIConsts.COMMON_URL + EAPIConsts.ReqUrl.LOGIN;  //登录地址
        String param=getUserParams(user);
        doExecute(context,bind,param,handler,url,EAPIConsts.ReqType.LOGIN);
    }

    //读取新用户信息
    public static void getUserInfoById(Context context, IBindData bind,
                                       int userId,Handler handler){
        String url = EAPIConsts.COMMON_URL + EAPIConsts.ReqUrl.GETUSERINFO + userId;
//        String param=getUserParams(user);
        doExecute(context,bind,"",handler,url,EAPIConsts.ReqType.GETUSERINFO);

    }

    //

    /**
     * 工具函数
     * @param user
     * @return
     */
    public static String getUserParams(Users user){
        Gson gson = new Gson();
        String params = gson.toJson(user);
        String str = "userInfo=" + params;
        return str;
    }

}
