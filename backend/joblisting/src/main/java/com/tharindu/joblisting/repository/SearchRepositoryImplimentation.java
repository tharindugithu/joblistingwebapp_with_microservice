package com.tharindu.joblisting.repository;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.tharindu.joblisting.model.Post;
import org.bson.BsonDocument;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component  //@Component is an annotation that allows Spring to automatically detect our custom beans.
public class SearchRepositoryImplimentation implements SearchRepository{

    @Autowired
    MongoClient mongoClient;

    @Autowired
    MongoConverter converter;

    @Override
    public List<Post> findByText(String text) {
        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = mongoClient.getDatabase("jobDB");
        MongoCollection<Document> collection = database.getCollection("jobList");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
                new Document("$limit", 5L),
                new Document("$sort", new Document("exp", 1L))));

        result.forEach(doc-> posts.add(converter.read(Post.class,doc)));

        return posts;
    }
}
