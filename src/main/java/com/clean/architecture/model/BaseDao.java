package com.clean.architecture.model;

import com.clean.architecture.constant.AppConstant;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseDao {

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @PrePersist
    void onCreate(){
        this.createAt = LocalDateTime.now();
        this.createdBy = AppConstant.CREATE_BY_SYSTEM;
        this.isDeleted = false;
    }

    @PreUpdate
    void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
