package com.syt.passport.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.syt.passport.pojo.dto.AdminAddNewDTO;
import com.syt.passport.pojo.dto.AdminLoginDTO;
import com.syt.passport.pojo.vo.AdminListItemVO;
import com.syt.passport.security.LoginPrincipal;
import com.syt.passport.service.IAdminService;
import com.syt.passport.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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

    @ApiOperation("管理员登录")
    @ApiOperationSupport(order = 1)
    @PostMapping("/login")
    public JsonResult<String> login(AdminLoginDTO adminLoginDTO) {
        log.debug("开始处理【管理员登录】的请求，参数：{}", adminLoginDTO);
        String jwt = adminService.login(adminLoginDTO);
        String message = "登陆成功";
        log.debug(message);
        return JsonResult.ok(jwt, message);
    }

    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 1)
    @PostMapping("/add-new")
    @PreAuthorize("hasAuthority('/ams/admin/add-new')")
    public JsonResult<Void> addNew(@RequestBody AdminAddNewDTO adminAddNewDTO) {
        log.info("开始添加用户数据");
        adminService.addNew(adminAddNewDTO);
        String message = "添加用户数据成功";
        log.info(message);
        return JsonResult.ok(message);
    }

    @ApiOperation("删除管理员")
    @ApiOperationSupport(order = 2)
    @ApiImplicitParam(name = "id", value = "管理员id", required = true, dataType = "long")
    @GetMapping("/delById/{id:[0-9]+}")
    @PreAuthorize("hasAuthority('/ams/admin/delete')")
    public JsonResult<Void> deleteById(@PathVariable Long id,
                                       @ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
        log.debug("开始处理【删除管理员】的请求，参数：{}", id);
        log.debug("当前登录的当事人：{}", loginPrincipal);
        adminService.deleteById(id);
        String message = "删除管理员成功";
        log.debug(message);
        return JsonResult.ok(message);
    }

    @ApiOperation("启用管理员")
    @ApiOperationSupport(order = 3)
    @GetMapping("/{id:[0-9]+}/enable")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult<Void> setEnable(@PathVariable Long id) {
        log.debug("开始处理setEnable: {}", id);
        adminService.setEnable(id);
        String message = "setEnable管理员成功";
        log.debug(message);
        return JsonResult.ok(message);
    }

    @ApiOperation("禁用管理员")
    @ApiOperationSupport(order = 4)
    @GetMapping("/{id:[0-9]+}/disable")
    @PreAuthorize("hasAuthority('/ams/admin/update')")
    public JsonResult<Void> setDisable(@PathVariable Long id) {
        log.debug("开始处理setDisable: {}", id);
        adminService.setDisable(id);
        String message = "setDisable管理员成功";
        log.debug(message);
        return JsonResult.ok(message);
    }

    @ApiOperation("查询管理员列表")
    @ApiOperationSupport(order = 5)
    @PostMapping("")
    @PreAuthorize("hasAuthority('/ams/admin/read')")
    public JsonResult<List<AdminListItemVO>> list() {
        String message = "开始查询管理员列表";
        log.debug(message);
        List<AdminListItemVO> adminList = adminService.list();
        return JsonResult.ok(adminList, message);
    }
}
