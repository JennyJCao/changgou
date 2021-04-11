package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@CrossOrigin // 支持跨域：只要域名或者端口号或者协议不同，就是跨域，如果不加支持跨域的配置，则无法访问
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping
    public Result<List<Brand>> findAll() {
        // int i = 1 / 0; // 测试全局异常的拦截：返回{"flag":false,"code":20001,"message":"/ by zero","data":null}
        List<Brand> allBrands = brandService.findAll();
        // 用common-entity中的Result封装数据
        return new Result<>(true, StatusCode.OK, "查询所有品牌成功", allBrands);
    }

    /**
     * 根据id查询品牌
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result<Brand> findById(@PathVariable("id") Integer id) {
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "根据id查询品牌成功", brand);
    }

    /**
     * 新增品牌成功
     * @param brand
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Brand brand) {
        brandService.add(brand);
        return new Result(true, StatusCode.OK, "新增品牌成功");
    }

    /**
     * 更新品牌
     * @param brand
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Brand brand, @PathVariable("id") Integer id) {
        System.out.println(id);
        // 单独传入id，id没有封装在brand中，所有首先把id放进去
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "更新品牌成功");
    }

    /**
     * 删除品牌成功
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        brandService.delete(id);
        return new Result(true, StatusCode.OK, "删除品牌成功");
    }

    /**
     * 根据条件查询品牌成功
     * @param brand
     * @return
     */
    @PostMapping("/search")
    public Result<List<Brand>> findList(@RequestBody Brand brand) {
        List<Brand> brandList = brandService.findList(brand);
        return new Result<>(true, StatusCode.OK, "根据条件查询品牌成功", brandList);
    }

    /**
     * 查询全部品牌并展示对应页面
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@PathVariable("page") int page, @PathVariable("size") int size) {
        PageInfo<Brand> brandPageInfo = brandService.findPage(page, size);
        return new Result<>(true, StatusCode.OK, "查询全部品牌并展示对应页面成功", brandPageInfo);
    }

    /**
     * 根据条件查询品牌并展示对应页面
     * @param brand
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result<PageInfo<Brand>> findPage(@RequestBody Brand brand, @PathVariable("page") int page,
                                            @PathVariable("size") int size) {
        PageInfo<Brand> brandPageInfo = brandService.findPage(brand, page, size);
        return new Result<>(true, StatusCode.OK, "根据条件查询品牌并展示对应页面成功", brandPageInfo);
    }


}
