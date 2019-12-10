package com.github.zj.dreamly.user.controller;

import com.github.zj.dreamly.tool.api.ResponseEntity;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import javax.validation.Valid;
import com.github.zj.dreamly.tool.util.PageQuery;
import com.github.zj.dreamly.swagger.constant.DataType;
import com.github.zj.dreamly.swagger.constant.ParamType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.zj.dreamly.user.entity.User;
import com.github.zj.dreamly.user.service.UserService;

/**
 * 分享 控制器
 *
 * @author 苍海之南
 * @since 2019-12-10
 */
@RestController
@AllArgsConstructor
@RequestMapping("Users")
@Api(value = "分享", tags = "分享接口")
@Validated
public class UserController {

	private UserService userService;

	/**
	* 获取分享详情
	*/
	@GetMapping("/{id}")
    @ApiOperationSupport(order = 1)
	@ApiOperation(value = "获取分享详情数据", notes = "传入主键id")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户编号", dataType = DataType.STRING, paramType = ParamType.PATH)})
	public ResponseEntity<User> detail(@PathVariable("id") String id) {
		return ResponseEntity.data(userService.getUserById(Long.valueOf(id)));
	}

	/**
	* 获取分享列表
	*/
	@GetMapping("/page")
    @ApiOperationSupport(order = 2)
	@ApiOperation(value = "获取分享列表", notes = "传入page")
    @ApiImplicitParams({
        @ApiImplicitParam(value = "当前页", name = "current", dataType = DataType.LONG, paramType = ParamType.QUERY),
        @ApiImplicitParam(value = "页面大小", name = "size", dataType = DataType.LONG, paramType = ParamType.QUERY)
    })
	public ResponseEntity<IPage<User>> page(@Valid PageQuery query) {
		return ResponseEntity.data(userService.getUserPage(query));
	}

	/**
	* 新增分享
	*/
	@PostMapping
    @ApiOperationSupport(order = 3)
	@ApiOperation(value = "新增分享", notes = "传入user")
	public ResponseEntity save(@Valid @RequestBody User user) {
        userService.saveUser(user);
	    return ResponseEntity.success("新增成功");
	}

	/**
	* 修改分享
	*/
	@PutMapping
    @ApiOperationSupport(order = 4)
	@ApiOperation(value = "修改分享", notes = "传入user")
	public ResponseEntity update(@Valid @RequestBody User user) {
        userService.updateUserById(user);
        return ResponseEntity.success("修改成功");
	}

	/**
	* 删除分享
	*/
	@DeleteMapping("/{id}")
    @ApiOperationSupport(order = 5)
	@ApiOperation(value = "删除分享", notes = "传入主键id")
	public ResponseEntity remove(@PathVariable("id") String id) {
        userService.removeUserById(Long.valueOf(id));
        return ResponseEntity.success("删除成功");
	}

}
