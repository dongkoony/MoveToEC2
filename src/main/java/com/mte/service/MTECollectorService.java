package com.mte.service;

import com.google.gson.Gson;
import com.mte.CollectContext;
import com.mte.dao.MTECollectorDao;
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

    public List<CollectContext> retrieveTargetHostInfoList() throws Exception {

        List<CollectContext> tartgetHostList = null;
        SqlSession session = mteSession.openSession();
        try {
            tartgetHostList = mteCollectorDao.selectTargetHostInfo(session);
            for(CollectContext ctx : tartgetHostList) {
               System.out.println(ctx.toString());
            }
        } finally {
            session.close();
        }

        return tartgetHostList;
    }

}
