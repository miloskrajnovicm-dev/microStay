package com.microstay.model;

import com.microstay.model.enums.HotelStatus;
import jakarta.persistence.*;

@Entity
@Table(name = "hotels")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String contact;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private HotelStatus status = HotelStatus.PENDING_VERIFICATION;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public HotelStatus getStatus() { return status; }
    public void setStatus(HotelStatus status) { this.status = status; }
}



