package dao;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import model.TestModelConfiguration;
import model.dao.RoleRepositoryDAO;
import model.dao.UserRepositoryDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestModelConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:userRepositoryDAOTest.xml")
@DatabaseTearDown(value = "classpath:userRepositoryDAOTest.xml", type = DatabaseOperation.DELETE_ALL)
public class UserRepositoryDAOTest {

    @Autowired
    private UserRepositoryDAO userRepositoryDAO;

    @Autowired
    private RoleRepositoryDAO roleRepositoryDAO;

    @Test
    public void testFindAllUsers(){
        Assert.assertEquals("Number of all users existing in database", 3, userRepositoryDAO.findAll().size());
    }

    @Test
    public void testFindAdmin(){
        Assert.assertEquals("Find user with admin privileges", "Kowalski", userRepositoryDAO.findByRole(roleRepositoryDAO.getByName("Admin")).get(0).getLastName());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testNoResultException() throws Exception {
        userRepositoryDAO.findById(5);
    }
}
