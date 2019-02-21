package com.gnn.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {


    //生成md5
    private static String md5 ( String  key) {
        return DigestUtils.md5Hex(key) ;
    }


    //固定salt值
    private static final  String salt = "1a2b3c4d" ;

    /**
     *对用户密码加密传到后台
     */
    public static  String inputPassFormPass ( String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2)+ inputPass + salt.charAt(5) + salt.charAt(4) ;
        return md5(str);
    }

    /**
     * 传递到后台的加密信息二次加密存到数据库
     */
    public static  String formPassToDBPass ( String formPass ,String salt ) {
        String str = "" + salt.charAt(0) + salt.charAt(2)+ formPass + salt.charAt(5) + salt.charAt(4) ;
        return md5(str);
    }

    /**
     *生成保存在数据库中的最终密码
     */
    public static  String inputPassToDBPass ( String inputPass ,String saltDB ) {
        String formPass = inputPassFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass ,saltDB ) ;
        return dbPass ;
    }

}
