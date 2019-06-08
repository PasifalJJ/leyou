package com.leyou.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leyou.common.enums.ExceptionsEnums;
import com.leyou.common.exception.LyException;
import com.leyou.common.utils.JsonUtils;
import com.leyou.feign.CategoryClient;
import com.leyou.feign.GoodsClient;
import com.leyou.feign.SpecificationClient;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.search.pojo.Goods;
import com.leyou.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements SearchService {
    private int i = 0;

    @Autowired
    private GoodsClient goodsClient;

    @Autowired
    private SpecificationClient specificationClient;

    @Autowired
    private CategoryClient categoryClient;

    private ObjectMapper om = new ObjectMapper();

    @Override
    public Goods buildGoods(Spu spu) {
        //查询所有的sku信息
        List<Sku> skus = goodsClient.querySkuBySpuId(spu.getId()).getBody();
        System.out.println(i + "-------------------->" + spu.getId());
        i++;
        if (skus == null) {
            throw new LyException(ExceptionsEnums.CANNOT_FIND_SKU);
        }
        Set<Long> prices = skus.stream().map(sku -> sku.getPrice()).collect(Collectors.toSet());
        //sku只保存需要展示的信息
        List<Map<String, Object>> skuList = new ArrayList<>();
        for (Sku sku : skus) {
            Map<String, Object> skuMap = new HashMap<>();
            skuMap.put("id", sku.getId());
            skuMap.put("title", sku.getTitle());
            skuMap.put("price", sku.getPrice());
            skuMap.put("images", sku.getImages());
            skuList.add(skuMap);
        }
        String skus_json = JsonUtils.serialize(skuList);

        //从spuDetail中查出所有的规格

        SpuDetail spuDetail = goodsClient.querySpuDetailById(spu.getId()).getBody();

        List<Map<String, Object>> spuDetailList = null;
        Map<String, Object> searchMaps = new HashMap<>();
        try {
            spuDetailList = om.readValue(spuDetail.getSpecifications(), new TypeReference<List<Map<String, Object>>>() {
            });
            String searchable = "searchable";
            String v = "v";
            String k = "k";
            String options = "options";
            spuDetailList.forEach(map -> {
                List<Map<String, Object>> params = (List<Map<String, Object>>) map.get("params");
                params.forEach(param -> {
                    if ((boolean) param.get("searchable")) {
                        if (param.get(v) != null) {
                            if (param.get("numerical") == null ? false : (boolean) param.get("numerical")) {
                                //调用分级方法，对数字进行分级
                                Double num = Double.parseDouble(param.get(v).toString());
                                StringBuffer sb = new StringBuffer();
                                sb.append(Math.floor(num - num / 3));
                                sb.append("-");
                                sb.append(Math.floor(num + num / 3));
                                searchMaps.put(param.get(k).toString(), sb.toString());
                            } else {
                                searchMaps.put(param.get(k).toString(), param.get(v));
                            }
                        }
                        if (param.get(options) != null) {
                            searchMaps.put(param.get(k).toString(), param.get(options));
                        }
                    }
                });
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new LyException(ExceptionsEnums.JSON_TO_MAP_ERROR);
        }

        //All所搜字段合集
        StringBuffer all = new StringBuffer();
        all = all.append(spu.getCname().replace("/", " ")).
                append(spu.getTitle()).append(spu.getBname()).append(spu.getTitle());

        Goods goods = new Goods();
        goods.setId(spu.getId());
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setSubTitle(spu.getSubTitle());
        goods.setPrice(prices);         //   商品价格的集合 set集合
        goods.setSkus(skus_json);      //   查询所有的sku信息，是一个json字符串
        goods.setSpecs(searchMaps);   //   所有可用于搜索的规格集合
        goods.setAll(all.toString()); //  所有搜索索引字段的集合，包括标题和商品名称 字符串
        return goods;
    }
}
