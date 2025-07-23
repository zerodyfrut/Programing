package com.example.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.Book;
import com.example.dto.Customer;

@Mapper
public interface MadangDao {

    @Select("SELECT * FROM customer")
    public List<Customer> allCustomer();

    @Select("SELECT * FROM book")
    public List<Book> allBook();

    @Select({ "select name, bookname, saleprice, orderdate",
            "from orders natural join book natural join customer",
            "where custid=#{custid}"})
    List<Map<String, Object>> orders(int custid);
    

    @Select("select sum(saleprice) as sum, count(*) as count from orders where custid=#{custid}")
    Map<String,Object> sum (int custid);
}
