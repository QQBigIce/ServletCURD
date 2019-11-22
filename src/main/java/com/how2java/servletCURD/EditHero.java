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

@WebServlet(name = "EditHero")
public class EditHero extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Hero h = new HeroDAO().get(id);
        JSONObject json = (JSONObject) JSONObject.toJSON(h);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().print(json);
    }
}
