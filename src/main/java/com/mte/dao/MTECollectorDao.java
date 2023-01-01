package com.mte.dao;

import com.mte.CollectContext;
import com.mte.dto.ColletorTargetInfoDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MTECollectorDao {
    private static final String mapper = "com.mte.sql.collector-mapper.";
    private String getMapper(String id) {
        return mapper+id;
    }
    public List<CollectContext> selectTargetHostInfo(SqlSession sqlSession) {
        return sqlSession.selectList(getMapper("selectTargetHostInfo"));
    }

    public int insertHostInfoNormal(SqlSession sqlSession, ColletorTargetInfoDto dto) {
        return sqlSession.insert(getMapper("insertHostInfoNormal"), dto);
    }

}
