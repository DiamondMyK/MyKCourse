package util;

public enum StatusEnum {
    AUDIT_ONE("课程主讲教师审批中"),
    AUDIT_TWO("主管教师审批中"),
    SUCCESS("申请成功"),
    REJECTED("申请驳回"),
    /*SUCCESSFINISH("申请成功（已完成）"),
    REJECTEDFINISH("申请驳回（已完成）"),*/
    FINISH("结束");

    String status;

    StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
