package com.energizeglobal.shopping.model.entity;


import com.energizeglobal.shopping.utils.SecurityUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data //@Getter, @Setter, @ToString, @EqualsAndHashCode and @RequiredArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity<I extends Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false)
    protected I id;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    protected LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "LAST_MODIFIED_DATE")
    protected LocalDateTime lastModifiedDate;

    @CreatedBy
    @Column(name = "CREATED_BY", updatable = false)
    protected String createdBy;

    @LastModifiedBy
    @Column(name = "LAST_MODIFIED_BY")
    protected String lastModifiedBy;

    @Version
    @Column(name = "VERSION")
    protected Long version;

    @PrePersist
    private void touchForCreate() {
        setCreatedBy(SecurityUtils.getCurrentUser());
        setCreatedDate(LocalDateTime.now());
    }

    @PreUpdate
    private void touchForUpdate() {
        setLastModifiedBy(SecurityUtils.getCurrentUser());
        setLastModifiedDate(LocalDateTime.now());
    }

}
