package com.homestock.shoppinglist_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "listitems")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Group_id can not be 0 or less!")
    private Long groupId;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Group_id can not be 0 or less!")
    private Long productId;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Quantity can not be negative!")
    private Float quantity;

    @Column
    private String note;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Position can not be negative!")
    private Long position;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}
