package service;

import model.Audit;
import java.util.List;
import java.util.Map;

public interface AuditService {

    public boolean insertAudit(Audit audit);

    public Boolean removeAudit(Integer aid);

    public Audit getById(Integer aid);

    public List<Audit> selectAudits(Map<String, Object> map);

    public boolean modifyAudit(Audit audit);

    public List<Audit> selectPage(Map<String, Object> map);

    public int selectPageCount(Map<String, Object> map);

}
