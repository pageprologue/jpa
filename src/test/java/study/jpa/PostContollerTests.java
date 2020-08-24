package study.jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.Matchers;
import org.hamcrest.core.Is;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import study.jpa.domain.Post;
import study.jpa.repository.PostJpaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostContollerTests {
    
    @Autowired
    MockMvc mockMvc;

    @Autowired
    PostJpaRepository postJpaRepository;

    @Test
    public void getPost() throws Exception {
        final Post post = new Post();
        post.setTitle("web");
        postJpaRepository.save(post);
        
        mockMvc.perform(get("/posts/" + post.getId()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getPosts() throws Exception {
        final Post post = new Post();
        post.setTitle("page");
        postJpaRepository.save(post);

        mockMvc.perform(get("/posts/")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "created,desc")
                .param("sort", "title"))
                .andDo(print())           
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].title", Matchers.is("page")));
    }
}