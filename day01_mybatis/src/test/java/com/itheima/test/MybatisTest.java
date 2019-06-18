package com.itheima.test;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * 入门案例
 */
public class MybatisTest {
    private InputStream is;
    private SqlSession session;
    private UserDao userDao;

    @Before
    public void init() throws Exception {
        //读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        // InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("SqlMapConfig.xml");

        //获得SqlSessionFactory工厂对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);

        //使用工厂生产SqlSession对象
        session = factory.openSession();

        //使用对象创建UserDao接口的代理对象
        userDao = session.getMapper(UserDao.class);
    }

    @After
    public void detory() throws Exception {
        //手动提交事务
        session.commit();
        //释放资源
        session.close();
        is.close();
    }

    @Test
    public void testFindAll() {
        //查询所有
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testInsertUser() {
        //添加用户
        User user = new User();
        user.setUsername("李四");
        user.setBirthday(new Date());
        user.setSex("男");
        user.setAddress("武汉");
        userDao.insertUser(user);
    }

    @Test
    public void testDeleteUserById() {
        //根据id删除
        userDao.deleteUserById(49);
    }

    @Test
    public void testUpdateUser() {
        //修改用户信息
        User user = new User();
        user.setId(46);
        user.setUsername("李四");
        user.setBirthday(new Date());
        user.setSex("女");
        user.setAddress("潜江");
        userDao.updateUser(user);
    }

    @Test
    public void testFindByName() {
        //模糊查询
        List<User> users = userDao.findByName("%王%");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
