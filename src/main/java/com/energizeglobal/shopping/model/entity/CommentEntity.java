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
@Table(name = CommentEntity.TABLE_NAME)
public class CommentEntity extends BaseEntity<Long> {

    public static final String TABLE_NAME = "COMMENT";

    @Column(name = "BODY")
    private String body;

    @NotAudited
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMMENT_ID", referencedColumnName = "ID")
    private ProductEntity product;


}
