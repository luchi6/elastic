package top.luchchuan.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;
import top.luchchuan.entity.PageResult;
import top.luchchuan.mapper.SkuMapper;
import top.luchchuan.pojo.Sku;
import top.luchchuan.service.SkuService;

import java.io.IOException;
import java.util.List;

//import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author luch
 * @since 2021-01-27
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {


    @Override
    public Boolean init() throws Exception {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        HttpHost.create("http://node1:9200")
                ));

        GetIndexRequest request = new GetIndexRequest("sku");
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        if (!exists) {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("sku");
            CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        }

        Integer total = baseMapper.findTotal();
        int num = total % 1000;
        int totalPage = num == 0 ? total / 1000 : total / 1000 + 1;
        for (int i = 0; i < totalPage; i++) {
            Page<Sku> page = page(new Page<Sku>(i, 1000));
            List<Sku> skus = page.getRecords();
            for (Sku sku : skus) {
                IndexRequest addRequest = new IndexRequest("sku");
                addRequest.id(sku.getId());
                addRequest.source(JSON.toJSONString(sku), XContentType.JSON);
                IndexResponse indexResponse = client.index(addRequest, RequestOptions.DEFAULT);
            }

        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
