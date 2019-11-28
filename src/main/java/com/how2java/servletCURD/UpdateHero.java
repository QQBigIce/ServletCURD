package com.how2java.servletCURD;

import com.alibaba.fastjson.JSONObject;
import com.how2java.HeroDao.Hero;
import com.how2java.HeroDao.HeroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateHero")
public class UpdateHero extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        update(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String s = request.getParameter("hero");
        JSONObject js = JSONObject.parseObject(s);
        Hero hero = JSONObject.toJavaObject(js, Hero.class);
        new HeroDAO().update(hero);
        String s1 = JSONObject.toJSONString(js);
        System.out.println(s1);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(s1);
    }
}
