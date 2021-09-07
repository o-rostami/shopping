package com.energizeglobal.shopping.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Set;

@Audited
@Setter
@Getter
@Entity
@Table(name = ProductEntity.TABLE_NAME)
public class ProductEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "PRODUCT";

    @Column(name = "NAME")
    private String name;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "RATE")
    private short rate;


    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
    private CategoryEntity category;

    @NotAudited
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<CommentEntity> comments;


}
