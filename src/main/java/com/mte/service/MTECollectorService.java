package com.mte.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mte.CollectContext;
import com.mte.dao.MTECollectorDao;
import com.mte.dto.CollectorTargetInfoDto;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class MTECollectorService {
    @Resource(name = "mteSessionFactory")
    private SqlSessionFactory mteSession;

    @Autowired
    private MTECollectorDao mteCollectorDao;

    private Gson gson;

    public List<CollectContext> retriveTargetInfoList() throws Exception {

        List<CollectContext> tartgetHostList = null;
        SqlSession session = mteSession.openSession();
        try {
            tartgetHostList = mteCollectorDao.selectTargetHostInfo(session);
            for(CollectContext collectContext : tartgetHostList) {
               System.out.println(collectContext.toString());
            }
        } finally {
            session.close();
        }

        return tartgetHostList;
    }

    public boolean saveCollectorResult(JsonObject  jsonObject)throws  Exception{
        SqlSession session = mteSession.openSession();

        try{
            jsonObject.entrySet().forEach((e) -> {
                String key = e.getKey();
                JsonElement value = e.getValue();

                switch (key){
                    case "com.mte.dto.CollectorTargetInfoDto":
                        mteCollectorDao.insertHostInfoNormal(session,gson.fromJson(value, new TypeToken<CollectorTargetInfoDto>(){}.getType()));
                        break;
                }
            });
        }finally {
            session.close();
        }
        return true;
    }

}
