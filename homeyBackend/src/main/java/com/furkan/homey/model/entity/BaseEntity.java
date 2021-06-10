package com.furkan.homey.model.entity;

import com.furkan.homey.model.type.Status;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
public abstract class BaseEntity {

    private int status = Status.ACTIVE; //1 aktif, 0 pasif

    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime updateDateTime;

    public boolean isActive() {
        return this.status == Status.ACTIVE;
    }

    public void setInactive() {
        this.status = Status.PASSIVE;
    }

    public void setActive() {
        this.status = Status.ACTIVE;
    }

    public abstract Long getId();

    public abstract void setId(Long id);

}
