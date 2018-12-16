package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 单条件查询:查询名称是'黑马程序员1'的数据
     */
    @Test
    public void testSpec() {
        /**
         * 创建Specification对象,封装查询条件
         *      root对象:获得条件查询的属性;
         *      cb对象:构建查询条件;
         */

        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获得条件查询属性
                Path<Object> custName = root.get("custName");
                //2. 构造查询条件;
                //同功能JPQL语句:from Customer where  customer = "黑马程序员1"
                Predicate predicate = cb.equal(custName, "黑马程序员1");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件查询:根据客户名（传智播客）和客户所属行业查询（it教育）
     */
    @Test
    public void testSpec1() {

        /**
         * 创建Specification对象,封装查询条件
         *      root对象:获得条件查询的属性;
         *      cb对象:构建查询条件;
         */
        Specification<Customer> spec = new Specification<Customer>() {

            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //属性1:客户名
                Path<Object> custName = root.get("custName");
                //属性2:所属行业
                Path<Object> custIndustry = root.get("custIndustry");

                //构造查询条件
                //1.构造客户名的精准匹配查询
                Predicate p1 = cb.equal(custName, "传智播客");
                //2..构造所属行业的精准匹配查询
                Predicate p2 = cb.equal(custIndustry.as(String.class), "it教育");
                //3.将多个查询条件进行组合
                //同功能JPQL语句:from Customer where custName = "传智播客" and custIndustry="it教育"
                Predicate and = cb.and(p1, p2);
                return and;
            }
        };
		
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }


    /**
     * 模糊查询:查询客户名称以 ’黑马程序员‘ 开头的客户列表,并按照ID排序
     */
    @Test
    public void testSpec3() {
		
        //构造查询条件
        Specification<Customer> spec = new Specification<Customer>() {
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查询属性：客户名
                Path<Object> custName = root.get("custName");
                //查询方式：模糊匹配,除过equals的其他查询方式必须指定比较的属性类型
                Predicate like = cb.like(custName.as(String.class), "黑马程序员%");
                return like;
            }
        };

        //创建排序对象
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        //执行查询
        List<Customer> list = customerDao.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 带条件分页查询:查询客户名称以 ’黑马程序员‘ 开头的客户列表,显示0-5条数据
     */
    @Test
    public void testSpec4() {

        //条件条件对象
        Specification spec = new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                //查询属性：客户名
                Path<Object> custName = root.get("custName");
                //查询方式：模糊匹配,除过equals的其他查询方式必须指定比较的属性类型
                Predicate like = cb.like(custName.as(String.class), "黑马程序员%");
                return like;
            }
        };
        //创建分页对象,参数1:从哪里开始查;参数2:查询多少条
        Pageable pageable = new PageRequest(0,5);
        //分页查询
        Page<Customer> page = customerDao.findAll(spec, pageable);
        System.out.println("数据集合:"+page.getContent());
        System.out.println("总条数:"+page.getTotalElements());
        System.out.println("总页数:"+page.getTotalPages());
    }
}
