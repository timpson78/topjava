package ru.javawebinar.topjava.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.dao.DataAccessException;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.service.AbstractServiceTest;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.javawebinar.topjava.UserTestData.*;

public abstract class AbstractUserServiceTestForJPA extends AbstractUserServiceTest {

    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }
}