package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
public interface BrandMapper extends Mapper<Brand> {
    /**
     * 根据分类id查询品牌信息
     * 1. 在分类品牌表tb_category_brand中，根据分类id查询出品牌id
     * 2. 在品牌表tb_brand中，根据品牌id查询品牌信息
     * 注意：通用mapper中如何写sql！！！
     */
    @Select("select tb.* from tb_brand tb, tb_category_brand tcb where tcb.category_id = #{categoryId} and tcb.brand_id = tb.id")
    List<Brand> findByCategoryId(Integer categoryId);

}
