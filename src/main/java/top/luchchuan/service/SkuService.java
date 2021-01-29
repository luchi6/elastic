package top.luchchuan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.luchchuan.pojo.Sku;

import java.io.IOException;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author luch
 * @since 2021-01-27
 */
public interface SkuService extends IService<Sku> {

    Boolean init() throws IOException, Exception;


}
