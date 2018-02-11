package model.entity;

import javax.persistence.*;

@Entity
public class Allotment {
    private Integer idAllotment;
    private Integer number;
    private Integer squaremeter;
    private Integer treeNumber;
    private Boolean composter;
    private Boolean bower;

    @Id
    @Column(name = "id_allotment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdAllotment() {
        return idAllotment;
    }

    public void setIdAllotment(Integer idAllotment) {
        this.idAllotment = idAllotment;
    }

    @Basic
    @Column(name = "number")
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Basic
    @Column(name = "squaremeter")
    public Integer getSquaremeter() {
        return squaremeter;
    }

    public void setSquaremeter(Integer squaremeter) {
        this.squaremeter = squaremeter;
    }

    @Basic
    @Column(name = "tree_number")
    public Integer getTreeNumber() {
        return treeNumber;
    }

    public void setTreeNumber(Integer treeNumber) {
        this.treeNumber = treeNumber;
    }

    @Basic
    @Column(name = "composter")
    public Boolean getComposter() {
        return composter;
    }

    public void setComposter(Boolean composter) {
        this.composter = composter;
    }

    @Basic
    @Column(name = "bower")
    public Boolean getBower() {
        return bower;
    }

    public void setBower(Boolean bower) {
        this.bower = bower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Allotment allotment = (Allotment) o;

        if (idAllotment != null ? !idAllotment.equals(allotment.idAllotment) : allotment.idAllotment != null)
            return false;
        if (number != null ? !number.equals(allotment.number) : allotment.number != null) return false;
        if (squaremeter != null ? !squaremeter.equals(allotment.squaremeter) : allotment.squaremeter != null)
            return false;
        if (treeNumber != null ? !treeNumber.equals(allotment.treeNumber) : allotment.treeNumber != null) return false;
        if (composter != null ? !composter.equals(allotment.composter) : allotment.composter != null) return false;
        if (bower != null ? !bower.equals(allotment.bower) : allotment.bower != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAllotment != null ? idAllotment.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (squaremeter != null ? squaremeter.hashCode() : 0);
        result = 31 * result + (treeNumber != null ? treeNumber.hashCode() : 0);
        result = 31 * result + (composter != null ? composter.hashCode() : 0);
        result = 31 * result + (bower != null ? bower.hashCode() : 0);
        return result;
    }
}
