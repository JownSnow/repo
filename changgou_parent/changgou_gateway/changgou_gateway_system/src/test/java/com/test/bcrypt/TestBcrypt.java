package com.test.bcrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class TestBcrypt {

    public static void main(String[] args) {

/*        for (int i = 0; i <100 ; i++) {
            //获取盐
            String gensalt = BCrypt.gensalt();

            System.out.println("盐: " + gensalt);

            String bryptPassword = BCrypt.hashpw("123456", gensalt);

            System.out.println("加密密码: " + bryptPassword);

            System.out.println("============================================================");
        }*/


        String gensalt = BCrypt.gensalt();

        System.out.println("盐: " + gensalt);

        String bryptPassword = BCrypt.hashpw("123456", gensalt);

        System.out.println("加密密码: " + bryptPassword);




        //校验密码是否正确
        boolean checkpw = BCrypt.checkpw("123456", bryptPassword);

        System.out.println("checkpw: " + checkpw);


    }


}
