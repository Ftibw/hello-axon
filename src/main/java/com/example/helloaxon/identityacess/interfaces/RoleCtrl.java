package com.example.helloaxon.identityacess.interfaces;

import com.example.helloaxon.common.Result;
import com.example.helloaxon.identityacess.domain.command.CreateRoleCmd;
import com.example.helloaxon.identityacess.domain.command.DeleteRoleCmd;
import com.example.helloaxon.identityacess.domain.command.UpdateRoleCmd;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Ftibw
 * @date : 2021/4/20 14:56
 */
@RestController
@RequestMapping("/api/role")
public class RoleCtrl {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public RoleCtrl(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    //    @ApiOperation("添加平台角色")
    @PostMapping
    public Object addRole(@RequestBody CreateRoleCmd cmd) {
        Object aggregateId = commandGateway.sendAndWait(cmd);
        return aggregateId;
    }

    //    @ApiOperation("编辑角色")
    @PatchMapping
    public Result<?> updateRole(@RequestBody UpdateRoleCmd cmd) {
        return commandGateway.sendAndWait(cmd);
    }

    //    @ApiOperation("删除角色")
    @DeleteMapping("/{id}")
    public Result<?> deleteRole(/*@ApiParam("角色ID")*/ @PathVariable String id) {
        return commandGateway.sendAndWait(new DeleteRoleCmd(id));
    }

////    @ApiOperation(value = "查询所有角色", notes = "用于前端下拉列表")
//    @GetMapping("/list")
//    public Result<List<Role>> listRole(Integer type) {
//        return roleService.listRole(type);
//    }
//
////    @ApiOperation("分页查询角色")
//    @GetMapping("/page")
//    public Result<PageVO<Role>> listRolePage(PageQO page, RoleQO qo) {
//        return roleService.listRolePage(page, qo);
//    }
//
////    @ApiOperation("查询单个角色的权限树")
//    @GetMapping("/{id}/perm/tree")
//    public Result<List<TreeNode>> getPermTreeByRoleId(@ApiParam("角色ID") @PathVariable String id) {
//        return roleService.getPermTreeByRoleId(id);
//    }
//
////    @ApiOperation("查询完整的权限树")
//    @GetMapping("/perm/tree")
//    public Result<List<TreeNode>> getPermTree() {
//        return roleService.getSelectivePermTree();
//    }

}
