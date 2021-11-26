package club.codehero.util;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description
 * @Author YangYe
 * @Date 2020/7/11
 */
@Slf4j
public class EncryptUtils {


    public static String encrypt(String input) {
        // 创建消息摘要对象
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {

            log.info("-------------------加密出错----------------------");
            log.info(e.getMessage());
        }
        return toHex(input, digest);

    }


    private static String toHex(String input, MessageDigest digest) {
        //执行消息摘要算法
        byte[] bytes = digest.digest(input.getBytes()); //密文数组

        StringBuilder sb = new StringBuilder();

        for (byte b : bytes) {
            //把密文转换16进制
            String s = Integer.toHexString(b & 0xff);
            //如果密文长度为1 ，需要在高位进行补0
            if (s.length() == 1) {
                s = "0" + s;
            }
            sb.append(s);
        }

        return sb.toString();
    }

//    public static void main(String[] args) {
//        String s = encrypt("yangye0309");
//        System.out.println(s);
//    }
}
