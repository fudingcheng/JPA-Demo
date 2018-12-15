package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //声明spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml")//指定spring容器的配置信息
public class JpqlTest {
    @Autowired
    private CustomerDao customerDao;

    @Test
    public void  testFindJPQL() {
        Customer customer = customerDao.findJpql("黑马程序员1");
        System.out.println(customer);
    }


    @Test
    public void testFindCustNameAndId() {
       // Customer customer =  customerDao.findCustNameAndId("传智播客",1l);
        Customer customer =  customerDao.findCustNameAndId(1l,"传智播客");
        System.out.println(customer);
    }

    /**
     * 测试jpql的更新操作
     */
    @Test
    @Transactional //添加事务的支持
    @Rollback(value = false)    //测试方法会在执行完毕后,默认回滚事务
    public void testUpdateCustomer() {
        customerDao.updateCustomer(9L,"黑马程序员");
    }

    //测试sql查询
    @Test
    public void testFindSql() {
        List<Object[]> list = customerDao.findSql("传智播客%");
        for (Object[] objects : list) {
            System.out.println(objects);
        }
    }


    //测试方法命名规则的查询
    @Test
    public void testNaming() {
        Customer customer = customerDao.findByCustName("黑马程序员");
        System.out.println(customer);
    }


    //测试方法命名规则的查询
    @Test
    public void testFindByCustNameLike() {
        List<Customer> list = customerDao.findByCustNameLike("传智播客%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }


    //测试方法命名规则的查询
    @Test
    public void testFindByCustNameLikeAndCustIndustry() {
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("传智播客1%", "it教育");
        System.out.println(customer);
    }
}
