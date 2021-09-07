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
@Table(name = CategoryEntity.TABLE_NAME)
public class CategoryEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "CATEGORY";

    @Column(name = "NAME")
    private String name;

    @NotAudited
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ProductEntity> products;


}
