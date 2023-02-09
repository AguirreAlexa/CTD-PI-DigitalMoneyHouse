package dh.pi.userservice.entity.utils;

import javax.persistence.Id;

public class UserCvuAlias {
    @Id
    private Integer id;

    private String cvu;
    private String alias;

    public UserCvuAlias(Integer id, String cvu, String alias) {
        this.id = id;
        this.cvu = cvu;
        this.alias = alias;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
