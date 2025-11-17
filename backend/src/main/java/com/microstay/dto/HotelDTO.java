package com.microstay.dto;

import com.microstay.model.enums.HotelStatus;

public class HotelDTO {
    private Long id;
    private String name;
    private String address;
    private String contact;
    private HotelStatus status;

    public HotelDTO() {}

    public HotelDTO(Long id, String name, String address, String contact, HotelStatus status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.status = status;
    }

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
