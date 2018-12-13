package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.lang.reflect.Field;

public class JpaTest1 {


    /**
     * 保存数据
     */
    @Test
    public void testSave() {
        //获得实体管理类
        EntityManager em = JpaUtils.getEntityManager();
        //获得事务对象
        EntityTransaction tx = em.getTransaction();
        //开启事务
        tx.begin();
        //创建对象,封装数据
        Customer customer = new Customer();
        customer.setCustName("传智播客");
        customer.setCustIndustry("教育");
        //保存数据
        em.persist(customer);

        tx.commit();
        em.close();
    }

    /**
     * 根据ID查询对象,立即加载
     */
    @Test
    public  void testFind() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        //参数一:返回结果封装的类型; 参数二:主键
        Customer customer = entityManager.find(Customer.class, 11L);
        System.out.print(customer);
        entityManager.close();
    }


    /**
     * 根据ID查询对象,懒加载
     */
    @Test
    public  void testReference() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //参数一:返回结果封装的类型; 参数二:主键
        Customer customer = entityManager.getReference(Customer.class, 11L);
        System.out.print(customer);
        tx.commit();
        entityManager.close();
    }


    /**
     * 删除客户的案例
     *
     */
    @Test
    public  void testRemove() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //查询客户
        Customer customer = entityManager.find(Customer.class,1L);
        //删除客户
        entityManager.remove(customer);
        tx.commit();
        entityManager.close();
    }


    /**
     * 更新客户的操作
     *      merge(Object)
     */
    @Test
    public  void testUpdate() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        //查询客户
        Customer customer = entityManager.find(Customer.class,1l);
        //更新客户
        customer.setCustIndustry("it教育");
        entityManager.merge(customer);
        //4.提交事务
        tx.commit();
        //5.释放资源
        entityManager.close();
    }

}
