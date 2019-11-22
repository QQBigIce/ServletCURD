package com.how2java.servletCURD;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.how2java.HeroDao.Hero;
import com.how2java.HeroDao.HeroDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchHero")
public class SearchHero extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        search(request, response);
    }

    private void search(HttpServletRequest requestm, HttpServletResponse response) throws IOException {
//        查询数据库中所有键并放到list中
        List<Hero> list = new HeroDAO().list();
        String s = "";
//        创建一个json数组，遍历list,把每一个对象转化为json对象,并添加到json数组中
        JSONArray ja = new JSONArray();
        for (Hero hero : list) {
            ja.add(JSONObject.toJSON(hero));
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print(JSONArray.toJSONString(ja));
        System.out.println(JSONArray.toJSONString(ja));
    }
}
