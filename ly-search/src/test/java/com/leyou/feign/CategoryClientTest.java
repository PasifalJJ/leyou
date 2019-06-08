package com.leyou.feign;

import com.leyou.LySearchService;
import com.leyou.item.pojo.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LySearchService.class)
public class CategoryClientTest {

    @Autowired
    private CategoryClient categoryClient;

    @Test
    public void testQueryCategoryListByPid(){
        final ResponseEntity<Category[]> responseEntity = categoryClient.queryCategoryListByPid(0L);
        final Category[] body = responseEntity.getBody();
        for (Category category : body) {
            System.out.println(category);
        }
    }



}