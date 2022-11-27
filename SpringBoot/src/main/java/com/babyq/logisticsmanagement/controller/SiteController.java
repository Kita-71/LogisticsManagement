package com.babyq.logisticsmanagement.controller;

import com.babyq.logisticsmanagement.entity.Order;
import com.babyq.logisticsmanagement.entity.Site;
import com.babyq.logisticsmanagement.entity.Site;
import com.babyq.logisticsmanagement.entity.User;
import com.babyq.logisticsmanagement.service.SiteService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/site")
public class SiteController {
    @Autowired
    private SiteService siteService;
    @GetMapping("/pagefilter")
    public IPage<Site> getFilteredSitePaged(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam(defaultValue = "") String searchMode, @RequestParam(defaultValue = "") String search_input ){
        IPage<Site> page=new Page<>(pageNum,pageSize);
        QueryWrapper<Site> queryWrapper= new QueryWrapper<>();

        if(search_input.isEmpty()==false)
        {
            if(searchMode.equals("site_name"))
            {
                queryWrapper.like("site_name",search_input);
            }
            else if (searchMode.equals("site_region"))
            {
                queryWrapper.like("site_region",search_input);
            }
            else if (searchMode.equals("site_principle"))
            {
                queryWrapper.like("site_principle",search_input);
            }
        }
        return siteService.page(page,queryWrapper);
    }
    @DeleteMapping("/delete")
    //删除指定数据
    public boolean deleteSite(@RequestParam Integer id) {
        return siteService.deleteSite(id);
    }
    @PostMapping("/changeInfo")
    //新增或更新
    public boolean changeInfo(@RequestBody Site site) {
        return siteService.saveSite(site);
    }


    @GetMapping("/getSitesByRegion")
    public List<Site> getSitesByRegion(@RequestParam String region)
    {
      return siteService.getSitesByRegion(region);
    };
}
