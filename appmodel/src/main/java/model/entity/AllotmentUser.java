package model.entity;

import javax.persistence.*;

@Entity
@Table(name = "allotment_user", schema = "rodobronca", catalog = "Allotment")
public class AllotmentUser {
    private Integer idAllotmentUser;
    private Boolean isActive;
    private Allotment allotment;
    private User user;

    @Id
    @Column(name = "id_allotment_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdArticle() {
        return idAllotmentUser;
    }

    public void setIdArticle(Integer idAllotmentUser) {
        this.idAllotmentUser = idAllotmentUser;
    }

    @Basic
    @Column(name = "is_active")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllotmentUser that = (AllotmentUser) o;

        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return  isActive != null ? isActive.hashCode() : 0;
    }

    @ManyToOne
    @JoinColumn(name = "allotment_id", referencedColumnName = "id_allotment", nullable = false)
    public Allotment getAllotment() {
        return allotment;
    }

    public void setAllotment(Allotment allotment) {
        this.allotment = allotment;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id_user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
