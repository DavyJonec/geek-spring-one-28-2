package ru.geekbrains.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "line_items",
        indexes = {
                @Index(name = "ux_product_customer", columnList = "product_id, customer_id, color, size", unique = true)
        }
)
public class LineItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(nullable = false)
    private BigDecimal cost;

    @Column(nullable = false)
    private Long qty;

    @Column
    private String color;

    @Column
    private String size;

    public LineItem() {
    }

    public LineItem(Product product, Customer customer, BigDecimal cost, Long qty, String color, String size) {
        this.product = product;
        this.customer = customer;
        this.cost = cost;
        this.qty = qty;
        this.color = color;
        this.size = size;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal price) {
        this.cost = price;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
