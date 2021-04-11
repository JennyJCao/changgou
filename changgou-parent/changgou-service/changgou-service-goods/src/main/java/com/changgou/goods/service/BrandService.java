package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BrandService {
    /**
     * 查询所有的品牌
     * @return
     */
    List<Brand> findAll();

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    Brand findById(Integer id);

    /**
     * 新增品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     * 更新品牌
     * @param brand
     */
    void update(Brand brand);

    /**
     * 删除品牌
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据条件查询品牌
     * @param brand
     * @return
     */
    List<Brand> findList(Brand brand);

    /**
     * 查询全部品牌并展示对应页面
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(int page, int size);

    /**
     * 查询指定条件的品牌并展示对应页面
     * @param brand
     * @param page
     * @param size
     * @return
     */
    PageInfo<Brand> findPage(Brand brand, int page, int size);
}
