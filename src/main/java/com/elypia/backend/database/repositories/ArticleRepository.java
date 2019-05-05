package com.elypia.backend.database.repositories;

import com.elypia.backend.database.entities.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {

    Article findById(int id);
    List<Article> findTop3ByOrderByCreatedDateDesc();
}
