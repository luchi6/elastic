package top.luchchuan.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.luchchuan.service.SkuService;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author luch
 * @since 2021-01-27
 */
@RestController
@RequestMapping("/goods")
public class SkuController {

    @Autowired
    private SkuService skuService;





    @GetMapping("init")
    public String init() throws Exception {
        skuService.init();
        return "初始化成功！";
    }
}
