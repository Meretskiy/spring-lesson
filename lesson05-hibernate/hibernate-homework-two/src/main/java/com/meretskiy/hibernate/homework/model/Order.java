package com.meretskiy.hibernate.homework.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "withBuyer", query = "SELECT o FROM Order o JOIN FETCH o.buyer WHERE o.buyer.id = :id"),
        @NamedQuery(name = "withProduct", query = "SELECT o FROM Order o JOIN FETCH o.product WHERE o.product.id = :id")
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "date")
    private Date date;

    @Column(name = "current_price")
    private Long currentPrice;

    public Order() {
    }

    public Order(Long id, Buyer buyer, Product product, Date date, Long currentPrice) {
        this.id = id;
        this.buyer = buyer;
        this.product = product;
        this.date = date;
        this.currentPrice = currentPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return String.format("[%d %s | %s bought %s | price = %d]",
                id, date.toString(), buyer.getName(), product.getTitle(), currentPrice);
    }
}
