package com.bookstoreapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Table(name = "bsa_book")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {

    @Id
    @Column(nullable = false, unique = true, updatable = false, name = "isbn")
    String ISBN;

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String author;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false, name = "stock_quantity")
    int stockQuantity;

    @CreatedDate
    @Column(updatable = false, name = "created_at")
    Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    Date updatedAt;

    @PrePersist
    void onCreate() {
        setCreatedAt(new Date());
    }

    @PreUpdate
    void onPersist() {
        setUpdatedAt(new Date());
    }

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<OrderBook> orderBookList;

}
