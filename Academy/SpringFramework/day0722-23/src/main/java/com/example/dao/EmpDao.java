package com.example.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.dto.Dept;
import com.example.dto.Emp;

@Mapper
public interface EmpDao {

    // @Select("select * from emp natural join dept where ename like
    // concat('%',#{ename},'%')")
    // public List<Emp> search(String ename);

    // 부모테이블의 것을 가져올 때는 별칭을 사용
    @Select({ "select empno, dname as 'dept.dname', ename, job, hiredate from",
            "emp inner join dept on emp.deptno=dept.deptno",
            "where ename like concat('%',#{parameter},'%')" })
    // 너무 문자열이 길어 중괄호(배열)로 처리해서 전달
    List<Emp> searchName(String name);

    // 부서번호 부서이름
    @Select("select deptno,dname from dept")
    List<Dept> depts();

    // 부서 내 사원 사원번호 이름
    // @Select("select empno, ename from emp where deptno=#{deptno}")
    // List<Dept> emps(int deptno);

    @Select("select empno, ename from emp where deptno =#{deptno}")
    List<Map<String, Object>> emps(int deptno);
    //key : 컬럼명, value : 값
    //[{empno:7788,ename:"SCOTT"}, {empno:1235,ename:"JONE"}....]

    // 사원정보
    @Select("select * from emp where empno=#{empno}")
    Emp empOne(int empno);

}
