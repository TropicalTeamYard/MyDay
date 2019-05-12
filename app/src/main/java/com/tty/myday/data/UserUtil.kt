package com.tty.myday.data

import android.util.Log
import com.tty.myday.util.AsyncTask
import org.json.JSONObject

object  UserUtil{
    fun autoLogin(){
        if (DataSource.user.usertype == "#CLOUD"){
            val data= "method=autologin&credit=${DataSource.user.token}"
            Log.d(TAG,"自动登录，url:${API.getRoute(RegionParam.USER)},data:$data")
            AsyncTask.post(API.getRoute(RegionParam.USER),data){
                if (it==null){
                    DataSource.state = false
                } else  {
                    DataSource.state = true
                    val responceJson:JSONObject = JSONObject(it)
                    val code = responceJson.getInt("code")
                    DataSource.userstate = code == 200
                    Log.d(TAG,"自动登录,code:$code")
                }
            }
        }
    }


    var TAG = "UserUtil"
}