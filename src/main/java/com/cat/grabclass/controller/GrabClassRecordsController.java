package com.cat.grabclass.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.cat.grabclass.common.Result;
import com.cat.grabclass.common.annotations.RateLimiter;
import com.cat.grabclass.common.contants.ResultStatus;
import com.cat.grabclass.common.utils.PageUtils;
import com.cat.grabclass.common.utils.R;
import com.cat.grabclass.entity.GrabClassRecords;
import com.cat.grabclass.entity.User;
import com.cat.grabclass.excption.BusinessException;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cat.grabclass.service.GrabClassRecordsService;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

import static com.cat.grabclass.common.contants.UserConstants.USER_INFO;


/**
 * 
 *
 * @author zx
 * @email 3117383594@qq.com
 */
@RestController
@CrossOrigin("*")
@RequestMapping("grabclass/grabclassrecords")
public class GrabClassRecordsController {
    @Autowired
    private GrabClassRecordsService grabClassRecordsService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = grabClassRecordsService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		GrabClassRecords grabClassRecords = grabClassRecordsService.getById(id);

        return R.ok().put("grabClassRecords", grabClassRecords);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GrabClassRecords grabClassRecords){
		grabClassRecordsService.save(grabClassRecords);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GrabClassRecords grabClassRecords){
		grabClassRecordsService.updateById(grabClassRecords);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		grabClassRecordsService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @Resource
    private GrabClassRecordsService grabClassService;

    //封装下单请求
    @PostMapping(value = "/create")
    @RateLimiter
    public Result<Object> create(@NotNull(message = "课程不能为空") @RequestParam(name = "CourseId") Long CourseId) throws BusinessException, ExecutionException {

        User user = USER_INFO.get();
        if (user == null) {
            throw new BusinessException(ResultStatus.LOGIN_EXPIRED);
        }

        Long recordId = grabClassService.createRecord(user.getId(), CourseId);
        Map<String, Object> data = new HashMap();
        data.put("recordId", recordId);
        return Result.build(ResultStatus.SUECCSS, data);
    }

    @GetMapping("getOrderStatus")
    @ApiOperation(value = "抢课回调接口")
    public Result<Object> getOrderStatus(@RequestParam("recordId") Long recordId) {
        int status = grabClassService.getRecordStatus(recordId);
        /**
         *  0 刚创建
         *  1 创建成功
         *  -1 创建失败
         */
        switch (status) {
            case 1:
                return Result.build(ResultStatus.ORDER_CREATE_SUCCESS, null);
            case -1:
                return Result.build(ResultStatus.ORDER_CREATE_FAIL, null);
            default:
                return Result.build(ResultStatus.ORDER_CREATING, null);
        }
    }
}
