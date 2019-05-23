package com.xiaoliu.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    /**
     * 生成32位md5码
     *
     * @param password
     * @return
     */
    public static String md5Password(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String MD5(String key) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = key.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String admin = "admin";
        String code1 = MD5Util.md5Password(MD5Util.MD5(admin.substring(3) + "admin"));
        System.out.println(code1 + "++++++++++++++");//698d51a19d8a121ce581499d7b701668
        String code = MD5Util.MD5(admin.substring(3) + "admin");//生成了加密 字符串。
        System.out.println(code);//698D51A19D8A121CE581499D7B701668
        //撒盐  。放置批量破解。
        String account = "test";
        code = MD5Util.MD5(account + "123456");
        System.out.println(code);
    }
}
