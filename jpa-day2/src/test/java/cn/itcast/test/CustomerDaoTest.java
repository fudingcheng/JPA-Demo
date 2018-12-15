package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据id查询
     */
    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(8L);
        System.out.println(customer);
    }

    /**
     * save : 实体没有设置主键值,就执行保存
     */
    @Test
    public void testSave() {
        Customer customer  = new Customer();
        customer.setCustName("黑马程序员");
        customer.setCustLevel("vip112");
        customer.setCustIndustry("it教育");
        customerDao.save(customer);
    }

    /**
     * save : 实体有主键值,就执行更新
     */
    @Test
    public void testUpdate() {
        Customer customer  = new Customer();
        customer.setCustId(8L);
        customer.setCustName("黑马程序员很厉害");
        customerDao.save(customer);
    }

    /**
     * 根据ID删除
     */
    @Test
    public void testDelete () {
        customerDao.delete(8L);
    }


    /**
     * 查询所有
     *
     * customer:  insert into customer values(?,?,?)
     * person:insert into person values(?,?,?)
     */
    @Test
    public void testFindAll() {
        List<Customer> list = customerDao.findAll();
        for(Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 统计数据总条数
     */
    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println(count);
    }

    /**
     * 判断id为XXX的数据是否存在
     */
    @Test
    public void  testExists() {
        boolean exists = customerDao.exists(4l);
        System.out.println("id为4的客户 是否存在："+exists);
    }


    /**
     * 根据id从数据库查询
     */
    @Test
    @Transactional
    public void  testGetOne() {
        Customer customer = customerDao.getOne(9L);
        System.out.println(customer);
    }

}
