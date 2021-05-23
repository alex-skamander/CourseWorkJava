package com.coursework.Kotik.Models;

import jdk.jfr.DataAmount;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "user_purchase")
@Data
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "user_id")
    Long userId;
    @Column(name = "product_id")
    Long productId;
    @Column(name = "product_count")
    int product_count;

}
