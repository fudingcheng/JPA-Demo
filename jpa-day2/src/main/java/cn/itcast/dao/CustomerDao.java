package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 接口1:JpaRepository<实体类型,主键类型>,封装了基本CRUD操作,包括分页和排序
 * 接口2:JpaSpecificationExecutor<实体类型>,封装了条件查询
 */
public interface CustomerDao extends JpaRepository<Customer,Long> ,JpaSpecificationExecutor<Customer> {

    /**
     * 根据客户名称查询客户
     */
    @Query(value="from Customer where custName = ?")
    public Customer findJpql(String custName);


    /**
     * 根据客户名称和客户id查询客户
     */
    @Query(value = "from Customer where custName = ?2 and custId = ?1")
    public Customer findCustNameAndId(Long id,String name);

    /**
     * 使用jpql完成更新操作
     */
    @Query(value = " update Customer set custName = ?2 where custId = ?1 ")
    @Modifying
    public void updateCustomer(long custId,String custName);


    /**
     * 使用sql的形式查询
     */
    @Query(value="select * from cst_customer where cust_name like ?1",nativeQuery = true)
    public List<Object [] > findSql(String name);


    /**
     * 方法命名规则查询
     * 同功能JPQL:from Customer where custName=?
     */
    public Customer findByCustName(String custName);


    /**
     * 方法命名规则查询
     * 同功能JPQL:from Customer where custName like ?
     */
    public List<Customer> findByCustNameLike(String custName);

    /**
     * 方法命名规则查询
     * 同功能JPQL:from Customer where custName like ? and  custIndustry = ?
     */
    public Customer findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
}
