package com.leyou.mapper;

import com.leyou.item.pojo.Stock;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

public interface StockMapper extends Mapper<Stock>, InsertListMapper<Stock> {
}
