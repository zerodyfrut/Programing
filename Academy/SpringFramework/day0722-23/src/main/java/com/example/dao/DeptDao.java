package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.dto.Dept;

@Mapper
public interface DeptDao {
    @Select("select * from dept")
    List<Dept> deptAll();

    @Select("select max(deptno)+10 from dept")
    int getNwno();

    @Insert("insert into dept values (#{deptno}, #{dname}, #{loc})")
    int insert(Dept dept); // 전달받은값이 DTO 라면 #{변수명} 으로 인식
                           // 전달받은 값이 Map 이라면 #{key} 으로 인식
    
    // int insert(@Param("deptno")int deptno, @Param("dname") String dname, @Param("loc") String loc);
    // 하나씩 전달하면 이렇게 되나?

    @Update("update dept set loc=#{loc} where deptno=${deptno}")
    int update(@Param("deptno")int deptno, @Param("loc")String loc);
    // 리턴을 안받을거면 void도 가능함

    @Delete("delete from dept where emptno=#{emptno}")
    int delete(int deptno);
    //한개만 보내는 경우 이름 필요없음. Param 써도 되고 안써도 되고
}
