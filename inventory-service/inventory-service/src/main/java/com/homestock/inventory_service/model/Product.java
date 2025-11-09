package com.homestock.inventory_service.model;

import com.homestock.inventory_service.enums.Type;
import com.homestock.inventory_service.enums.Unity;
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
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Quantity can not be negative!")
    private Float quantity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Unity unity;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = true, message = "Threshold can not be negative!")
    private Float threshold;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Ideal point can not be 0 or less!")
    private Float idealPoint;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", inclusive = false, message = "Group_id can not be 0 or less!")
    private Long groupId;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}
