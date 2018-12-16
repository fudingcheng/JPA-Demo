package cn.itcast.test;

import cn.itcast.dao.RoleDao;
import cn.itcast.dao.UserDao;
import cn.itcast.domain.Role;
import cn.itcast.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 一般保存:保存一个用户，保存一个角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void  testAdd() {
        User user = new User();
        user.setUserName("小李");

        Role role = new Role();
        role.setRoleName("java程序员");

        user.getRoles().add(role);  //User关联Role,维护外键关系

        //保存用户
        userDao.save(user);

        //保存角色
        roleDao.save(role);

    }


    /**
     * 级联保存:保存User的时候,级联保存角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void  testCasCadeAdd() {
        User user = new User();
        user.setUserName("小李");

        Role role = new Role();
        role.setRoleName("java程序员");


        user.getRoles().add(role);

        userDao.save(user);

    }

    /**
     * 级联删除:删除用户的时候级联删除角色
     */
    @Test
    @Transactional
    @Rollback(false)
    public void  testCasCadeRemove() {
        userDao.delete(1L);

        User user = userDao.getOne(2L);
    }
}
