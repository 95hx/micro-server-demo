package cn.luv2code.sample.userprovider.controller;

import cn.luv2code.sample.userprovider.dto.ArticleDto;
import cn.luv2code.sample.userprovider.service.ArticleService;
import cn.luv2code.sample.userprovider.utils.Result;
import cn.luv2code.sample.userprovider.utils.ResultStatus;
import cn.luv2code.sample.userprovider.utils.ResultUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/article")
@RequiresRoles("admin")
public class ArticleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);
    @Resource
    private ArticleService articleService;
    /**
     * 查
     */
    @GetMapping("/{id}")
    public Result<ArticleDto> findById(@PathVariable Long id) throws Exception {
        return ResultUtils.success(articleService.findById(id));
    }

    /**
     * 查
     */
    @GetMapping("/all")
    public Result findAll() {
        return ResultUtils.success(articleService.findAll());
    }

    /**
     * 查
     */
    @GetMapping("/{page}/{size}")
    public Result findPageAll(@PathVariable Integer page, @PathVariable Integer size) {
        return ResultUtils.success(articleService.findAll(new PageRequest(page - 1, size)));
    }

    /**
     * 查
     */
    @GetMapping("/{userId}/{page}/{size}")
    public Result findAllByUserId(@PathVariable Long userId, @PathVariable Integer page, @PathVariable Integer size) {
        return ResultUtils.success(articleService.findAllByUserId(new PageRequest(page - 1, size), userId));

    }

    /**
     * 增/更新
     */
    @PostMapping("/add")
    public Result add(@Valid @RequestBody ArticleDto articleDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return ResultUtils.error(ResultStatus.UNKNOWN_ERROR);
        /**
         * 目前userId全部设置为1
          */
        articleDto.setUserId(1L);
        articleService.save(articleDto);
        return ResultUtils.success();
    }

    /**
     * 删
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Long id) {
        articleService.deleteById(id);
        return ResultUtils.success();
    }
}
