package model.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Payment {
    private Integer idPayment;
    private String title;
    private Date date;
    private Double charge;
    private Boolean paid;
    private AllotmentUser allotmentUserByAllotmentUserId;

    @Id
    @Column(name = "id_payment")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(Integer idPayment) {
        this.idPayment = idPayment;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Basic
    @Column(name = "charge")
    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    @Basic
    @Column(name = "paid")
    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (idPayment != null ? !idPayment.equals(payment.idPayment) : payment.idPayment != null) return false;
        if (title != null ? !title.equals(payment.title) : payment.title != null) return false;
        if (date != null ? !date.equals(payment.date) : payment.date != null) return false;
        if (charge != null ? !charge.equals(payment.charge) : payment.charge != null) return false;
        if (paid != null ? !paid.equals(payment.paid) : payment.paid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPayment != null ? idPayment.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (charge != null ? charge.hashCode() : 0);
        result = 31 * result + (paid != null ? paid.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "allotment_user_id", referencedColumnName = "id_allotment_user", nullable = false)
    public AllotmentUser getAllotmentUserByAllotmentUserId() {
        return allotmentUserByAllotmentUserId;
    }

    public void setAllotmentUserByAllotmentUserId(AllotmentUser allotmentUserByAllotmentUserId) {
        this.allotmentUserByAllotmentUserId = allotmentUserByAllotmentUserId;
    }
}
