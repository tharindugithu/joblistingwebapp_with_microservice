package com.tharindu.joblisting.controller;


import com.tharindu.joblisting.repository.PostRepository;
import com.tharindu.joblisting.model.Post;
import com.tharindu.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PostController {

  @Autowired  //spring will create object and it mapping
  PostRepository repo;

  @Autowired
  SearchRepository srepo;


  @ApiIgnore//it will remove predefined methods
  @RequestMapping(value = "/")
    public void redirect(HttpServletResponse response) throws IOException {
      response.sendRedirect("/swagger-ui.html");
  }

  @GetMapping( "/allPosts")
  @CrossOrigin
  public List<Post> getAllPosts(){
     return repo.findAll();
  }

  @GetMapping("/posts/{text}")
  @CrossOrigin
  public List<Post> getSearchPosts(@PathVariable  String text){
    return srepo.findByText(text);
  }

  @PostMapping("/post")
  @CrossOrigin
  public  Post addPost(@RequestBody Post post){
    return repo.save(post);
  }
}
