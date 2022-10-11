package com.syt.passport.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.syt.passport.pojo.vo.RoleListItemVO;
import com.syt.passport.service.IRoleService;
import com.syt.passport.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色管理模块
 *
 * @author sytsnb@gmail.com
 * @date 2022 2022/10/10 23:04
 */
@Slf4j
@Api(tags = "02. 角色管理模块")
@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    IRoleService roleService;

    public RoleController() {
        log.info("创建控制器对象：RoleController");
    }

    /**
     * http://127.0.0.1:8082/roles
     */
    @ApiOperation("查询角色列表")
    @ApiOperationSupport(order = 420)
    @GetMapping("")
    public JsonResult<List<RoleListItemVO>> list() {
        log.debug("开始处理【查询角色列表】的请求");
        return JsonResult.ok(roleService.list());
    }
}
