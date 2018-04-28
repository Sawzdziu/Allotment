package servicesTest;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import dto.UserDto;
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
import services.TestServicesConfiguration;
import services.UserService;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestServicesConfiguration.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DatabaseSetup(value = "classpath:userServiceTest.xml")
@DatabaseTearDown(value = "classpath:userServiceTest.xml", type = DatabaseOperation.DELETE_ALL)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void getUserById() {
        UserDto userDto = userService.getUserById(1);

        Assert.assertEquals("ID of obtained user", new Integer(1), userDto.getIdUser());
        Assert.assertEquals("Email of obtained user", "a.kowalski@wp.pl", userDto.getEmail());
        Assert.assertEquals("Last name of obtained user", "Kowalski", userDto.getLastName());
        Assert.assertEquals("Name of obtained user", "Adam", userDto.getName());
        Assert.assertEquals("Phone of obtained user", "123456789", userDto.getPhone());
        Assert.assertTrue("If user is active", userDto.isActive());
        Assert.assertEquals("Role of obtained user", "ADMIN", userDto.getRole());

        userDto = userService.getUserById(2);

        Assert.assertEquals("ID of obtained user", new Integer(2), userDto.getIdUser());
        Assert.assertEquals("Email of obtained user", "a.nowak@onet.pl", userDto.getEmail());
        Assert.assertEquals("Last name of obtained user", "Nowak", userDto.getLastName());
        Assert.assertEquals("Name of obtained user", "Andrzej", userDto.getName());
        Assert.assertEquals("Phone of obtained user", "123452341", userDto.getPhone());
        Assert.assertTrue("If user is active", userDto.isActive());
        Assert.assertEquals("Role of obtained user", "USER", userDto.getRole());
    }

    @Test
    public void updateUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@test.pl");
        userDto.setLastName("Test");
        userDto.setName("Name");
        userDto.setPhone("123");
        userDto.setRole("USER");
        userDto.setIdUser(1);

        userService.editUser(userDto);
        userDto = userService.getUserById(1);

        Assert.assertEquals("ID of obtained user", new Integer(1), userDto.getIdUser());
        Assert.assertEquals("Email of obtained user", "test@test.pl", userDto.getEmail());
        Assert.assertEquals("Last name of obtained user", "Test", userDto.getLastName());
        Assert.assertEquals("Name of obtained user", "Name", userDto.getName());
        Assert.assertEquals("Phone of obtained user", "123", userDto.getPhone());
        Assert.assertTrue("If user is active", userDto.isActive());
        Assert.assertEquals("Role of obtained user", "ADMIN", userDto.getRole());
    }

    @Test
    public void createUser() {
        UserDto userDto = new UserDto();
        userDto.setEmail("test@test.pl");
        userDto.setLastName("TestNew");
        userDto.setName("NameNew");
        userDto.setPhone("12345");

        userService.addUser(userDto);
        userDto = userService.getUserById(4);

        Assert.assertEquals("Size of user list", 4, userService.getAllUsers().size());
        Assert.assertEquals("ID of obtained user", new Integer(4), userDto.getIdUser());
        Assert.assertEquals("Email of obtained user", "test@test.pl", userDto.getEmail());
        Assert.assertEquals("Last name of obtained user", "TestNew", userDto.getLastName());
        Assert.assertEquals("Name of obtained user", "NameNew", userDto.getName());
        Assert.assertEquals("Phone of obtained user", "12345", userDto.getPhone());
        Assert.assertTrue("If user is active", userDto.isActive());
        Assert.assertEquals("Role of obtained user", "USER", userDto.getRole());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void noResultException(){
        userService.getUserById(1000);
    }
}