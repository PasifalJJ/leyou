package com.leyou.service.impl;

import com.leyou.common.vo.PageResult;
import com.leyou.feign.GoodsClient;
import com.leyou.item.pojo.Spu;
import com.leyou.repository.GoodsRepository;
import com.leyou.search.pojo.Goods;
import com.leyou.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SearchServiceImplTest {
    @Autowired
    private SearchService searchService;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void buildGoods() {
        int pageNum = 1;
        int rows = 100;
        List<Spu> spus = null;
        do {
            ResponseEntity<PageResult<Spu>> pageResultResponseEntity = goodsClient.queryForPage(null, pageNum, rows, null, false, true);
            PageResult<Spu> pageResult = pageResultResponseEntity.getBody();
            spus = pageResult.getItems();
            List<Goods> goodsList = spus.stream().map(searchService::buildGoods).collect(Collectors.toList());
            Iterable<Goods> goods = goodsRepository.saveAll(goodsList);
            System.out.println("-------------------------" + pageResult.getTotal());
            System.out.println("-------------------------" + pageNum);
            pageNum++;
        } while (spus.size() == rows);
    }
}