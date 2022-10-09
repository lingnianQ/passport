package com.syt.passport.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.syt.passport.pojo.dto.AdminAddNewDTO;
import com.syt.passport.pojo.vo.AdminListItemVO;
import com.syt.passport.service.IAdminService;
import com.syt.passport.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sytsnb@gmail.com
 * @date 2022 2022/9/29 10:30
 */
@Api(tags = "01. 管理员管理模块")
@Slf4j
@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 100)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(@RequestBody AdminAddNewDTO adminAddNewDTO) {
        log.info("开始添加用户数据");
        adminService.addNew(adminAddNewDTO);
        String message = "添加用户数据成功";
        log.info(message);
        return JsonResult.ok(message);
    }

    @ApiOperation("删除管理员")
    @ApiOperationSupport(order = 2)
    @GetMapping("/delById/{id:[0-9]+}")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        log.debug("开始处理删除deleteById: {}", id);
        adminService.deleteById(id);
        String message = "删除管理员成功";
        log.debug(message);
        return JsonResult.ok(message);
    }

    @PostMapping("")
    public JsonResult<List<AdminListItemVO>> select() {
        String message = "开始查询管理员列表";
        log.debug(message);
        return JsonResult.ok(adminService.list(), message);
    }
}
