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

@WebServlet(name = "addHero")
public class AddHero extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("data");
        JSONObject jb = JSONObject.parseObject(s);
        Hero hero = (Hero) JSONObject.toJavaObject(jb, Hero.class);
        hero = new HeroDAO().add(hero);
        jb = new JSONObject();
        jb.put("hero", JSONObject.toJSON(hero));
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(JSONObject.toJSONString(jb));

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
