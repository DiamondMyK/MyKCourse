package mapper;

import model.Audit;
import java.util.List;
import java.util.Map;

public interface AuditMapper {

    public int insertAudit(Audit audit);

    public int deleteAudit(Integer aid);

    public Audit getById(Integer aid);

    public List<Audit> selectAudits(Map<String, Object> map);

    public List<Audit> selectPage(Map<String, Object> map);

    public int selectPageCount(Map<String, Object> map);

    public int modifyAudit(Audit audit);
}
