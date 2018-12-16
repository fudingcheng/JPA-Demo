package cn.itcast.test;

import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;

import javax.persistence.criteria.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecificationQuery {

    @Autowired
    private LinkManDao linkManDao;

    /**
     * Specification的多表查询
     */
    @Test
    public void testFind() {
        Specification<LinkMan> spec = new Specification<LinkMan>() {
            public Predicate toPredicate(Root<LinkMan> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                /**
                 * Join代表连接查询，通过root对象获取
                 * JoinType.INNER
                 * JoinType.LEFT
                 * JoinType.RIGHT
                 */
                Join<LinkMan, Customer> join = root.join("customer", JoinType.INNER);
                return cb.like(join.get("custName").as(String.class),"百度");
            }
        };
        List<LinkMan> list = linkManDao.findAll(spec);
        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
    }
}
