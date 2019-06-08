package com.leyou.service;

import com.leyou.item.pojo.Spu;
import com.leyou.search.pojo.Goods;
import org.springframework.stereotype.Service;


public interface SearchService {

    public Goods buildGoods(Spu spu);


}
