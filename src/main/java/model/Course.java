package model;

public class Course {
    private int cid;
    private String cname;
    private String cremark;
    private Integer auditone;
    private Integer audittwo;
    private String nameone;
    private String nametwo;

    public Course() {
        super();
    }

    public Course(String cname, String cremark) {
        this.cname = cname;
        this.cremark = cremark;
    }

    public Course(int cid, int auditone, int audittwo) {
        this.cid = cid;
        this.auditone = auditone;
        this.audittwo = audittwo;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCremark() {
        return cremark;
    }

    public void setCremark(String cremark) {
        this.cremark = cremark;
    }

    public Integer getAuditone() {
        return auditone;
    }

    public void setAuditone(Integer auditone) {
        this.auditone = auditone;
    }

    public Integer getAudittwo() {
        return audittwo;
    }

    public void setAudittwo(Integer audittwo) {
        this.audittwo = audittwo;
    }

    public String getNameone() {
        return nameone;
    }

    public void setNameone(String nameone) {
        this.nameone = nameone;
    }

    public String getNametwo() {
        return nametwo;
    }

    public void setNametwo(String nametwo) {
        this.nametwo = nametwo;
    }
}
