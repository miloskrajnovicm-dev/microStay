package com.microstay.controller;

import com.microstay.dto.HotelDTO;
import com.microstay.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/hotels")
@Tag(name = "Hotels", description = "Endpoints for hotels management")
public class HotelController {
    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    @Operation(summary = "List all hotels.")
    public List<HotelDTO> getAll() {
        return hotelService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hotel by id.")
    public ResponseEntity<HotelDTO> getById(@PathVariable Long id) {
        return hotelService.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('HOTEL','ADMIN')")
    @Operation(summary = "Create a new hotel. Only users with the 'HOTEL' or 'ADMIN' role can access this endpoint.")
    public ResponseEntity<HotelDTO> create(@Valid @RequestBody HotelDTO dto) {
        HotelDTO saved = hotelService.createHotel(dto);
        return ResponseEntity.created(Objects.requireNonNull(URI.create("/api/hotels/" + saved.getId()))).body(saved);
    }
}
