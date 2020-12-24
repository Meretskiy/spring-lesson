package com.meretskiy.hibernate.homework.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
@NamedQueries( {
        @NamedQuery(name = "productWithOrders", query = "SELECT p FROM Product p JOIN FETCH p.orders WHERE p.id = :id"),
        @NamedQuery(name = "productWithOrdersAll", query = "SELECT p FROM Product p JOIN FETCH p.orders")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "cost")
    private Long cost;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Order> orders;

    public Product() {
    }

    public Product(String title, Long cost) {
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", cost=" + cost +
                ", orders=" + orders +
                '}';
    }
}
