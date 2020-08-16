package com.secure.secure_back_end.repositories;

import com.secure.secure_back_end.domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception
    {
        //arrange
        User user = new User();
        user.setUsername("test-gosho");
        user.setPassword("dsasaddsadas");
        userRepository.save(user);
        User user2 = new User();
        user2.setUsername("test-gosho2");
        user2.setPassword("dsasaddsadas");
        userRepository.save(user2);
    }

    @Test
    public void findByUsername()
    {
    }

    @Test
    public void findByIdIsGreaterThan()
    {
        //act
        User byIdIsGreaterThan = this.userRepository.findByUsername("test-gosho");
        List<User> all = this.userRepository.findAll();

        //assert
        assertEquals("test-gosho", byIdIsGreaterThan.getUsername());
        assertEquals(2, all.size());
        ;
    }
}
