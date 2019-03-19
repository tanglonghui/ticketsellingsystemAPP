package org.ironman.ticketsellingsystem.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.widget.Toast;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.SettingService;

import org.ironman.ticketsellingsystem.App;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @data Created by Archer on 2019/3/17.
 * @describe TODO : 工具类
 */

public class CommonUtil {






    /**
     * 申请权限
     */
    public static void checkPermissions(final Context context, String[]... var1) {
        // 权限
        AndPermission.with(context).permission(var1)
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        // TODO what to do.
                    }
                }).onDenied(new Action() {
            @Override
            public void onAction(List<String> permissions) {
                // TODO what to do
                CommonUtil.showMsg("请打开相应的权限");
                // 这些权限被用户总是拒绝。
                final SettingService settingService = AndPermission.permissionSetting(context);
                AlertDialog.Builder localBuilder = new AlertDialog.Builder(context);
                localBuilder.setTitle("打开权限提示");
                localBuilder.setMessage("请在设置中打开相应的权限，否则功能将无法正常使用");
                localBuilder.setPositiveButton("前往设置", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        settingService.execute();
                    }
                });
                localBuilder.setNegativeButton("放弃", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                        settingService.cancel();
                    }
                });
                localBuilder.show();
            }
        }).start();
    }

    /**
     * 解析json文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 生成随机文件名：当前年月日时分秒+五位随机数
     *
     * @return
     */
    public static String getRandomFileName() {
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String str = simpleDateFormat.format(date);
        Random random = new Random();
        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数
        return rannum + str;// 当前时间
    }



    public static void showMsg(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showMsgLong(String msg) {
        Toast.makeText(App.getContext(), msg, Toast.LENGTH_LONG).show();
    }

    public static String formatJuli(String m) {
        int juli = Integer.parseInt(m);
        return Math.round(juli / 100d) / 10d + "";
    }

    /**
     * 手机号验证
     *
     * @param mobile
     * @return 验证通过返回true
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 验证密码：6-16 为必须为英文数字
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isPassword(String str) {
        Pattern p = Pattern.compile("^[0-9a-zA-Z]{6,16}$"); // 验证密码
        Matcher m = p.matcher(str);
        return m.matches();
    }


    /**
     * 将实体类转换成json字符串对象
     * 注意此方法需要第三方gson  jar包
     *
     * @param obj 对象
     * @return map
     */
    public static String toJson(Object obj, int method) {
        // TODO Auto-generated method stub
        if (method == 1) {
            //字段是首字母小写，其余单词首字母大写
            Gson gson = new Gson();
            String obj2 = gson.toJson(obj);
            return obj2;
        } else if (method == 2) {
            // FieldNamingPolicy.LOWER_CASE_WITH_DASHES    全部转换为小写，并用空格或者下划线分隔
            //FieldNamingPolicy.UPPER_CAMEL_CASE    所以单词首字母大写
            Gson gson2 = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
            String obj2 = gson2.toJson(obj);
            return obj2;
        }
        return "";
    }
}
