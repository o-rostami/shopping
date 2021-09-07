package com.energizeglobal.shopping.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Audited
@Setter
@Getter
@Entity
@Table(name = RateEntity.TABLE_NAME)
public class RateEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "RATE";

    @Column(name = "RATE")
    private short rate;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity product;


}
