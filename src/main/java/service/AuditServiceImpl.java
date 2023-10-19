package service;

import dao.AuditDao;
import model.Audit;
import java.util.List;
import java.util.Map;

public class AuditServiceImpl implements AuditService {
    AuditDao auditDao = new AuditDao();

    @Override
    public boolean insertAudit(Audit audit) {
        return auditDao.insertAudit(audit);
    }

    @Override
    public Boolean removeAudit(Integer cid) {
        return auditDao.deleteAudit(cid);
    }

    @Override
    public Audit getById(Integer aid) {
        return auditDao.getById(aid);
    }

    @Override
    public List<Audit> selectAudits(Map<String, Object> map) {
        List<Audit> audits = auditDao.selectAudits(map);
        return audits;
    }

    @Override
    public boolean modifyAudit(Audit audit) {
        return auditDao.modifyAudit(audit);
    }

    @Override
    public int selectPageCount(Map<String, Object> map) {
        return auditDao.selectPageCount(map);
    }

    @Override
    public List<Audit> selectPage(Map<String, Object> map) {
        return auditDao.selectPage(map);
    }
}
