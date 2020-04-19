package com.pjq.service;

import com.pjq.dao.PostDao;
import com.pjq.pojo.Comments;
import com.pjq.pojo.Post;
import com.pjq.pojo.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("postService")
@Scope("prototype")
public class PostService {

    @Resource
    private PostDao postmapper;

    public List<Post> showAllPost() {
        return postmapper.getAllPost();
    }

    public Result showAPost(int id) {
        Post post = new Post();
        post = postmapper.getAPost(id);
        List<Comments> comments;
        comments = postmapper.getComents(id);
        Result result = new Result();
        result.setResult(post);
        return result;
    }

    public Result showComments(int id) {
        List<Comments> comments;
        comments = postmapper.getComents(id);
        Result result = new Result();
        result.setMessage("success");
        result.setCode("200");
        result.setResult(comments);
        return result;
    }

    public void addComments(String comments, String name, int post_id) {
        Post post = postmapper.getAPost(post_id);
        double number = post.getNumber();
        number = number + 1;
        System.out.println(number);
        postmapper.insertIntoComments(post_id, comments, name);
        postmapper.updateCommentsNumber(post_id, number);
    }

    public void addPost(String theme, String content, String blogger) {
        postmapper.insertIntoPost(theme, content, blogger);

    }

}
