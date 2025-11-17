package com.microstay.model;

import com.microstay.model.enums.RoomType;
import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(length = 2000)
    private String description;

    @Column(name = "price_3h")
    private Integer price3h;

    @Column(name = "price_6h")
    private Integer price6h;

    @Column(name = "price_9h")
    private Integer price9h;

    @Column(name = "price_12h")
    private Integer price12h;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public RoomType getType() { return type; }
    public void setType(RoomType type) { this.type = type; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getPrice3h() { return price3h; }
    public void setPrice3h(Integer price3h) { this.price3h = price3h; }
    public Integer getPrice6h() { return price6h; }
    public void setPrice6h(Integer price6h) { this.price6h = price6h; }
    public Integer getPrice9h() { return price9h; }
    public void setPrice9h(Integer price9h) { this.price9h = price9h; }
    public Integer getPrice12h() { return price12h; }
    public void setPrice12h(Integer price12h) { this.price12h = price12h; }
}



