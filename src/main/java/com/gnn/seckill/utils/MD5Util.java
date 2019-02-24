package com.gnn.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * md5加密
 */
public class MD5Util {


    //生成md5
    public static  String md5 ( String  key) {
        return DigestUtils.md5Hex(key) ;
    }


    //固定salt值
    public static final  String salt = "1a2b3c4d" ;


    /**
     *对用户密码加密传到后台
     * @param inputPass
     * @return
     */
    public static  String inputPassToFormPass(String  inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2)+ inputPass + salt.charAt(5) + salt.charAt(4) ;
        return md5(str);
    }

    /**
     * 传递到后台的加密信息二次加密存到数据库
     * @param formPass
     * @param salt
     * @return
     */
    public static  String formPassToDBPass ( String  formPass ,String salt ) {
        String str = "" + salt.charAt(0) + salt.charAt(2)+ formPass + salt.charAt(5) + salt.charAt(4) ;
        return md5(str);
    }

    /**
     * 对原始密码生成保存在数据库中的最终密码
     * @param inputPass
     * @param saltDB
     * @return
     */
    public static  String inputPassToDBPass ( String  inputPass ,String saltDB ) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDBPass(formPass ,saltDB ) ;
        return dbPass ;
    }

}
