package model;

public class User {
    private Integer userid;
    private String userrole;
    private String username;
    private String userpwd;
    private String realname;
    private String status = "1";
    private String userremark;

    public User() {
        super();
    }

    public User(String userrole, String username, String userpwd, String realname, String userremark) {
        super();
        this.userrole = userrole;
        this.username = username;
        this.userpwd = userpwd;
        this.realname = realname;
        this.userremark = userremark;
    }

    public User(String userrole, String username, String userpwd, String realname, String status, String userremark) {
        super();
        this.userrole = userrole;
        this.username = username;
        this.userpwd = userpwd;
        this.realname = realname;
        this.status = status;
        this.userremark = userremark;
    }

    public User(Integer userid, String userrole, String username, String userpwd, String realname, String status,
            String userremark) {
        super();
        this.userid = userid;
        this.userrole = userrole;
        this.username = username;
        this.userpwd = userpwd;
        this.realname = realname;
        this.status = status;
        this.userremark = userremark;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUserrole() {
        return userrole;
    }

    public void setUserrole(String userrole) {
        this.userrole = userrole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserremark() {
        return userremark;
    }

    public void setUserremark(String userremark) {
        this.userremark = userremark;
    }

}
