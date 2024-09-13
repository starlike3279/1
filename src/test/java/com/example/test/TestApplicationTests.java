package com.example.test;

import com.example.test.article.Article;
import com.example.test.article.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class TestApplicationTests {

	@Autowired
	private ArticleRepository articleRepository;

	@Test
	void contextLoads() {
		Article a = new Article();
		a.setTitle("test07");
		a.setContent("내용");
		a.setCreateDate(LocalDateTime.now());
		this.articleRepository.save(a);
	}
}
