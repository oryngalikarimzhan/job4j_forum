package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControlTest {

    @MockBean
    private PostService posts;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    public void postTest() throws Exception {
        this.mockMvc.perform(get("/post/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void newPostTest() throws Exception {
        this.mockMvc.perform(get("/new"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post/create"));
    }

    @Test
    @WithMockUser
    public void editPostTest() throws Exception {
        this.mockMvc.perform(get("/edit/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("post", is(nullValue())))
                .andExpect(view().name("post/edit"));
    }

    @Test
    @WithMockUser
    public void deletePostTest() throws Exception {
        this.mockMvc.perform(get("/delete/1"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    @WithMockUser
    public void testCreatePost() throws Exception {
        this.mockMvc.perform(post("/create")
                        .param("name","Куплю ладу-грант. Дорого.")
                        .param("description", "aaaaaaaa"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void testUpdatePost() throws Exception {
        when(posts.getPost(1))
                .thenReturn(Post.of("Куплю ладу-грант. не Дорого.", "чтобы была в хорошем состоянии"));
        this.mockMvc.perform(post("/update/")
                        .param("name","Куплю ладу-грант. Дорого.")
                        .param("id","1")
                        .param("description", "чтобы была в хорошем состоянии"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void testAddCommentToPost() throws Exception {
        Post post = Post.of("someName", "someDescription");
        when(posts.getPost(1))
                .thenReturn(post);
        this.mockMvc.perform(post("/post/1/comment/new")
                        .param("text","someComment"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(posts).save(argument.capture());
        assertThat(argument.getValue().getComments().size(), is(1));
        assertThat(argument.getValue().getComments().get(0).getText(), is("someComment"));
    }
}