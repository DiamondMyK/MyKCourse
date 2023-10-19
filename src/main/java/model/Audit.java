package model;

public class Audit {
    private Integer aid;
    private Integer cid;
    private Integer uid;
    private String reason;
    private String prove;
    private Integer finaloneid;
    private Integer finaltwoid;
    private String status;
    private String auditonename;
    private String audittwoname;
    private String finalonename;
    private String finaltwoname;
    private String uname;
    private String cname;
    private String rejectedreason;
    private String userorle;

    public Audit() {
        super();
    }

    public Audit(Integer cid, Integer uid, String reason, String prove, String status) {
        this.cid = cid;
        this.uid = uid;
        this.reason = reason;
        this.prove = prove;
        this.status = status;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getProve() {
        return prove;
    }

    public void setProve(String prove) {
        this.prove = prove;
    }

    public Integer getFinaloneid() {
        return finaloneid;
    }

    public void setFinaloneid(Integer finaloneid) {
        this.finaloneid = finaloneid;
    }

    public Integer getFinaltwoid() {
        return finaltwoid;
    }

    public void setFinaltwoid(Integer finaltwoid) {
        this.finaltwoid = finaltwoid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAuditonename() {
        return auditonename;
    }

    public void setAuditonename(String auditonename) {
        this.auditonename = auditonename;
    }

    public String getAudittwoname() {
        return audittwoname;
    }

    public void setAudittwoname(String audittwoname) {
        this.audittwoname = audittwoname;
    }

    public String getFinalonename() {
        return finalonename;
    }

    public void setFinalonename(String finalonename) {
        this.finalonename = finalonename;
    }

    public String getFinaltwoname() {
        return finaltwoname;
    }

    public void setFinaltwoname(String finaltwoname) {
        this.finaltwoname = finaltwoname;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getRejectedreason() {
        return rejectedreason;
    }

    public void setRejectedreason(String rejectedreason) {
        this.rejectedreason = rejectedreason;
    }

    public String getUserorle() {
        return userorle;
    }

    public void setUserorle(String userorle) {
        this.userorle = userorle;
    }
}
