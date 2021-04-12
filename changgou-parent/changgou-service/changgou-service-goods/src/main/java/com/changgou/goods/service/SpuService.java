package com.changgou.goods.service;

import com.changgou.goods.pojo.Goods;
import com.changgou.goods.pojo.Spu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Spu业务层接口
 * @Date 2019/6/14 0:16
 *****/
public interface SpuService {

    /**
     * 恢复被逻辑删除的商品
     * @param spuId
     */
    void restore(Long spuId);

    /**
     * 逻辑删除商品
     * @param spuId
     */
    void logicDelete(Long spuId);

    /**
     * 批量下架商品
     * @param ids
     * @return 下架商品的个数
     */
    int pullMany(Long[] ids);

    /**
     * 批量上架商品
     * @param ids
     * @return 上架商品的个数
     */
    int putMany(Long[] ids);

    /**
     * 上架商品
     * @param spuId
     */
    void put(Long spuId);

    /**
     * 下架商品
     * @param spuId
     */
    void pull(Long spuId);

    /**
     * 审核商品
     * @param spuId
     */
    void audit(Long spuId);

    /**
     * 根据spu id 查询goods
     * @param spuId
     * @return
     */
    Goods findGoodsById(Long spuId);

    /**
     * 保存商品
     * @param goods
     */
    void saveGoods(Goods goods);

    /***
     * Spu多条件分页查询
     * @param spu
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(Spu spu, int page, int size);

    /***
     * Spu分页查询
     * @param page
     * @param size
     * @return
     */
    PageInfo<Spu> findPage(int page, int size);

    /***
     * Spu多条件搜索方法
     * @param spu
     * @return
     */
    List<Spu> findList(Spu spu);

    /***
     * 删除Spu
     * @param id
     */
    void delete(Long id);

    /***
     * 修改Spu数据
     * @param spu
     */
    void update(Spu spu);

    /***
     * 新增Spu
     * @param spu
     */
    void add(Spu spu);

    /**
     * 根据ID查询Spu
     * @param id
     * @return
     */
     Spu findById(Long id);

    /***
     * 查询所有Spu
     * @return
     */
    List<Spu> findAll();
}
