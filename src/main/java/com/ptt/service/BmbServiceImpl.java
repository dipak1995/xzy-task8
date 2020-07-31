package com.ptt.service;

import com.ptt.dao.BmbMapper;
import com.ptt.pojo.Bmb;
import com.whalin.MemCached.MemCachedClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("bmbService")
public class BmbServiceImpl implements BmbService {

    @Autowired
    private BmbMapper bmbMapper;

//    public void setBmbMapper(BmbMapper bmbMapper) {
//        this.bmbMapper = bmbMapper;
//    }

    @Resource
    private MemCachedClient memCachedClient;

    Logger logger = Logger.getLogger(BmbServiceImpl.class);


    public int saveBmb(Bmb bmb) {
        return bmbMapper.saveBmb(bmb);
    }

    public int deleteBmbById(Integer id) {
        return bmbMapper.deleteBmbById(id);
    }

    public int updateBmb(Bmb bmb) {
        return bmbMapper.updateBmb(bmb);
    }

    public Bmb getBmbById(Integer id) {
        return bmbMapper.getBmbById(id);
    }

    public List<Bmb> queryAllBmb() {

        List<Bmb> bmbs = (List<Bmb>) memCachedClient.get("list");
        logger.info("缓存值为："+bmbs);

        if(bmbs != null){
            logger.info("memCached执行了");
            return bmbs;
        }else {

            logger.info("缓存为空");
            List<Bmb> bmb = bmbMapper.queryAllBmb();
            logger.info("数据库中查询的数据为："+bmb);

            boolean state = memCachedClient.set("list",bmb);
            logger.info("缓存是否成功："+state);
            return bmb;
        }

    }
}

