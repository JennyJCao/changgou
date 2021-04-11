package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.cj.util.StringUtils;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    /**
     * 查询所有品牌
     * @return
     */
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * 新增品牌
     * @param brand
     */
    @Override
    public void add(Brand brand) {
        brandMapper.insertSelective(brand);
    }

    /**
     * 修改品牌  selective：忽略空值
     * @param brand
     */
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * 删除品牌
     * @param id
     */
    @Override
    public void delete(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据条件查询品牌
     * @param brand
     * @return
     */
    @Override
    public List<Brand> findList(Brand brand) {
        // 根据条件构造查询实例
        Example example = createExample(brand);
        return brandMapper.selectByExample(example);
    }

    /**
     * 查询全部品牌并展示对应页面
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(int page, int size) {
        PageHelper.startPage(page,size); // 直接实现了分页
        List<Brand> brandList = brandMapper.selectAll();
        return new PageInfo<Brand>(brandList);
    }

    /**
     * 根据条件查询品牌并展示对应的页面
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @Override
    public PageInfo<Brand> findPage(Brand brand, int page, int size) {
        PageHelper.startPage(page, size); // 分页
        Example example = createExample(brand);
        List<Brand> brandList = brandMapper.selectByExample(example);
        return new PageInfo<Brand>(brandList);
    }

    /**
     * 构建条件查询的实例example
     * @param brand
     * @return
     */
    public Example createExample(Brand brand) {
        Example example = new Example(Brand.class); // 条件是要构建实例的类
        Example.Criteria criteria = example.createCriteria();
        if (brand != null) { // name image id letter seq
            // 品牌名称
            if (!StringUtils.isNullOrEmpty(brand.getName())) {
                criteria.andLike("name", "%" + brand.getName() + "%"); // column 查询条件
            }
            // image
            if (!StringUtils.isNullOrEmpty(brand.getImage())) {
                criteria.andLike("image", "%" + brand.getImage() + "%");
            }
            // id
            if (brand.getId() != null) {
                criteria.andEqualTo("id", brand.getId());
            }
            // letter
            if (!StringUtils.isNullOrEmpty(brand.getLetter())) {
                criteria.andEqualTo("letter", brand.getLetter());
            }
            // seq 数据库里都是null，没必要实现
        }
        return example;
    }


}
