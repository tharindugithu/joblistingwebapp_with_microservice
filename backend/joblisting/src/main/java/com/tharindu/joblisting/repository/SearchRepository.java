package com.tharindu.joblisting.repository;

import com.tharindu.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {
    List<Post> findByText(String text);
}
