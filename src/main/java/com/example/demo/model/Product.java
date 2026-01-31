package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String productName;
    
    @Column(nullable = false)
    private Double costPrice;
    
    @Column(nullable = false)
    private Double sellingPrice;
    
    @Column
    private Double margin;
    
    @Column
    private Double profit;
    
    @Column
    private String category;
    
    @Column(length = 500)
    private String description;
    
    @PrePersist
    @PreUpdate
    private void calculateProfitAndMargin() {
        if (costPrice != null && sellingPrice != null) {
            this.profit = sellingPrice - costPrice;
            if (costPrice != 0) {
                this.margin = (profit / costPrice) * 100;
            }
        }
    }
}
