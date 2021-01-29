package top.luchchuan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import top.luchchuan.pojo.Sku;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author luch
 * @since 2021-01-27
 */
public interface SkuMapper extends BaseMapper<Sku> {

    @Select("select count(1) from tb_sku")
    Integer findTotal();
}
