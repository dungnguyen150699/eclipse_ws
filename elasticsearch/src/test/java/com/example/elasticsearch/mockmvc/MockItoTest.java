package com.example.elasticsearch.mockmvc;

import com.example.elasticsearch.repository.ArticleRepository;
import com.example.elasticsearch.repository.ElasticSearchHighClientQuery;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;

import java.io.IOException;


// @RunWith attaches a runner with the test class to initialize the mock data
@RunWith(MockitoJUnitRunner.class)
public class MockItoTest {
    // Create a mock object
//    @Mock
//    private UserDao userDao;

//    @Mock
//    private
    @Mock
    private ElasticSearchHighClientQuery elasticSearchHighClientQuery;
    @Mock
    private ArticleRepository articleRepository;

    @Before
    public void setUp() {
//        userService = new UserServiceImpl(userDao);
    }

    @Test
    public void createUser_WhenEmailExisted_ReturnFailed() throws IOException {
        // Define return value for method createUser()
        Mockito.lenient().when(elasticSearchHighClientQuery.searchBucketThenSourceMapping()).thenThrow(new IOException());
        Mockito.lenient().when(elasticSearchHighClientQuery.searchBucketThenSourceMapping()).thenThrow(new IOException());
        // Use mock in test
//        Assert.assertEquals("FAILED", userService.createUser("existed@gpcoder.com"));
    }

//    @Test
//    public void createUser_WhenEmailNotExisted_ReturnSuccess() {
//        // Define return value for method createUser()
//        Mockito.when(articleRepository
//
//        // Use mock in test
//        Assert.assertEquals("SUCCESS", userService.createUser("not_existed@gpcoder.com"));
//    }
}
