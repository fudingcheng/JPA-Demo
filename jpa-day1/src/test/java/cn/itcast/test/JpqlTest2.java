package cn.itcast.test;

import cn.itcast.domain.Customer;
import cn.itcast.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * 测试jqpl查询
 */
public class JpqlTest2 {

    @Test
    public void testFindAll() {
        EntityManager em = JpaUtils.getEntityManager();

        String jpql = "from Customer ";
        Query query = em.createQuery(jpql);//创建Query查询对象，query对象才是执行jqpl的对象
        List<Customer> list = query.getResultList();
        for (Customer customer : list) {
            System.out.print(customer);
        }

        em.close();
    }


    /**
     * 排序查询： 倒序查询全部客户（根据id倒序）
     */
    @Test
    public void testOrders() {
        EntityManager em = JpaUtils.getEntityManager();
        //编写JPQL语句
        String jpql = "from Customer order by custId desc";
        Query query = em.createQuery(jpql);
        List list = query.getResultList();
        for (Object obj : list) {
            System.out.println(obj);
        }
        em.close();
    }


    /**
     * 使用jpql查询，统计客户的总数
     */
    @Test
    public void testCount() {
        EntityManager em = JpaUtils.getEntityManager();
        //编写JPQL语句
        String jpql = "select count(custId) from Customer";
        Query query = em.createQuery(jpql);
        //查询唯一结果
        Long result = (Long) query.getSingleResult();
        System.out.println(result);
        em.close();
    }


    /**
     * 分页查询
     *      sql：select * from cst_customer limit 0,2
     *      jqpl : from Customer
     */
    @Test
    public void testPaged() {
        EntityManager em = JpaUtils.getEntityManager();
        //编写查询语句
        String jpql = "from Customer";
        Query query = em.createQuery(jpql);
        //起始索引
        query.setFirstResult(9);
        //每页查询的条数
        query.setMaxResults(20);
        List list = query.getResultList();
        for(Object obj : list) {
            System.out.println(obj);
        }
        em.close();
    }


    /**
     * 条件查询
     */
    @Test
    public void testCondition() {
        EntityManager em = JpaUtils.getEntityManager();
        //编写查询语句
        String jpql = "from Customer where custName like ? ";
        Query query = em.createQuery(jpql);
        query.setParameter(1,"传智播客%");
        List list = query.getResultList();
        for(Object obj : list) {
            System.out.println(obj);
        }
        em.close();
    }

}
