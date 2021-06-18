package com.test.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTestDemo {


    public static void main(String[] args) {


        //当前时间
        long currentTimeMillis = System.currentTimeMillis();
        Date date = new Date(currentTimeMillis);
        JwtBuilder builder= Jwts.builder();

        //header
        //playload
        //signature

        //设置编号
        builder.setId("888")    //设置唯一编号
        .setSubject("测试")    //主题
        .setIssuedAt(new Date())   //设置签发日期
       // .setExpiration(date)
        .claim("name","zhangsan")
        .claim("phone","16985741236")
        .signWith(SignatureAlgorithm.HS256,"123456");


        //构建 并返回一个字符串
        System.out.println( builder.compact() );
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLmtYvor5UiLCJpYXQiOjE2MjMyODc1MDN9.19qTckhBDbcRAsRLJstRZGvZJ1RAZykdm17bwkPpnT4
        //eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLmtYvor5UiLCJpYXQiOjE2MjMyODc1MzB9.YK37T-JLswZLzX2h6d3VCyureq5X7FMN0kQEp_ZcC_4

        String token = builder.compact();


        //解析
        Claims claims = Jwts.parser().setSigningKey("123456").parseClaimsJws(token).getBody();

        System.out.println(claims);
    }

}
