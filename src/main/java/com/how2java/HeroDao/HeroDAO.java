package com.how2java.HeroDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 */
public class HeroDAO {
    /**
     * 默认无参构造
     */
    public HeroDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接数据库方法
     *
     * @return Connection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&serverTimezone=GMT", "root", "admin");
    }

    /**
     * 查询数据库表内数据数量的方法
     *
     * @return total
     */
    public int getTotal() {
        int total = 0;
        try (Connection c = getConnection();
             Statement s = c.createStatement();) {
//            查询数据库有多少数据
            String sql = "select count(*) from hero";
            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    /**
     * 添加hero到数据库并返回执行此语句生成的键，把id赋值给hero.id
     *
     * @param hero
     */
    public Hero add(Hero hero) {
        String sql = "insert into hero values(null, ?, ?, ?)";
        try (Connection c = getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            ps.setString(1, hero.getName());
            ps.setFloat(2, hero.getHp());
            ps.setInt(3, hero.getDamage());

            ps.execute();
//          返回此语句生成的键
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                hero.setId(id);
                return hero;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据id删除数据库内对应的数据
     *
     * @param id
     */
    public void delete(int id) {
        try (Connection c = getConnection();
             Statement s = c.createStatement();) {

            String sql = "delete from hero where id = " + id;
            s.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Hero hero){
        String sql = "update hero set name = ?, hp = ?, damage = ? where id = ?";
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, hero.getName());
            ps.setFloat(2, hero.getHp());
            ps.setInt(3, hero.getDamage());
            ps.setInt(4, hero.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据id查询hero的name hp damage
     * @param id
     * @return
     */
    public Hero get(int id) {
        Hero hero = null;
        try (Connection c = getConnection();
             Statement s = c.createStatement();) {

            String sql = "select * from hero where id = " + id;
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()){
                String name = rs.getString("name");
                float hp = rs.getFloat("hp");
                int damage = rs.getInt("damage");
                hero = new Hero(name, hp, damage);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hero;
    }

    /**
     * 返回数据库内id从0到2^15-1的数据，按照id降序排列
     * @return
     */
    public List<Hero> list(){
        return list(0, Short.MAX_VALUE);
    }

    /**
     * 返回一个给定区间内的所有数据，按照id大小降序排列
     * 从start开始查询count条数据，按照id大小降序排列
     * 把查询到的数据存放到集合中
     * @param start
     * @param count
     * @return
     */
    public List<Hero> list(int start, int count){
        List<Hero> heroes = new ArrayList<>();

//        从start开始查询count条数据，按照id大小降序排列
        String sql = "select * from hero order by id desc limit ?, ?";
        try(Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, start);
            ps.setInt(2, count);

            ResultSet rs = ps.executeQuery();

//            把查询到的数据存放到集合中
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float hp = rs.getFloat("hp");
                int damage = rs.getInt("damage");
                Hero hero = new Hero(id, name, hp, damage);
                heroes.add(hero);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return heroes;
    }
}
