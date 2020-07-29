package com.chenkeda.transaction;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
@Data
public class Beans {
    @Autowired
    private A a;
    @Autowired
    private B b;
    @Autowired
    private C c;


}

@Component
class A {
    @Autowired
    private B b;

    public void a() {
        System.out.println("a");
        b.b();
    }
}

@Component
class B {
    @Autowired
    private C c;

    @Transactional(rollbackFor = Exception.class)
    public void b() {
        System.out.println("b");
            c.c();

    }
}

@Component
class C {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(rollbackFor = Exception.class)
    public void c() {
        System.out.println("c");
        jdbcTemplate.execute("insert into person(name) values('ckd')");
        throw new RuntimeException();
    }
}
