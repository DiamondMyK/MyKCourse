package dao;

import mapper.AuditMapper;
import model.Audit;
import util.DBUtil;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class AuditDao {

    public boolean insertAudit(Audit audit) {
        boolean result = false;
        SqlSession sqlSession = null;
        int mysql_affected_rows = 0;

        try {
            sqlSession = DBUtil.getSession();
            AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
            mysql_affected_rows = auditMapper.insertAudit(audit);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        result = mysql_affected_rows != 0 ? true : false;
        return result;
    }

    public Boolean deleteAudit(Integer aid) {
        boolean result = false;
        int mysql_affected_rows = 0;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
            mysql_affected_rows = auditMapper.deleteAudit(aid);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        result = mysql_affected_rows != 0 ? true : false;
        return result;
    }

    public Audit getById(Integer aid) {
        boolean result = false;
        SqlSession sqlSession = null;
        Audit audit = null;

        try {
            sqlSession = DBUtil.getSession();
            AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
            audit = auditMapper.getById(aid);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }
        return audit;
    }

    public List<Audit> selectAudits(Map<String, Object> map) {
        List<Audit> auditList = null;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
            auditList = auditMapper.selectAudits(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        return auditList;
    }

    public List<Audit> selectPage(Map<String, Object> map) {
        List<Audit> auditList = null;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
            auditList = auditMapper.selectPage(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        return auditList;
    }

    public int selectPageCount(Map<String, Object> map) {
        int count = 0;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
            count = auditMapper.selectPageCount(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }
        return count;
    }

    public boolean modifyAudit(Audit audit) {
        boolean result = false;
        int mysql_affected_rows = 0;
        SqlSession sqlSession = null;

        try {
            sqlSession = DBUtil.getSession();
            AuditMapper auditMapper = sqlSession.getMapper(AuditMapper.class);
            mysql_affected_rows = auditMapper.modifyAudit(audit);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeSession(sqlSession);
        }

        result = mysql_affected_rows != 0 ? true : false;
        return result;
    }
}
