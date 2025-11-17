package com.microstay.service;

import com.microstay.dto.HotelDTO;
import com.microstay.model.Hotel;
import com.microstay.model.enums.HotelStatus;
import com.microstay.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<HotelDTO> findAll() {
        return hotelRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Optional<HotelDTO> findById(long id) {
        return hotelRepository.findById(id).map(this::toDto);
    }

    @Transactional
    public HotelDTO createHotel(HotelDTO dto) {
        Hotel hotel = new Hotel();
        hotel.setName(dto.getName());
        hotel.setAddress(dto.getAddress());
        hotel.setContact(dto.getContact());
        hotel.setStatus(dto.getStatus() != null ? dto.getStatus() : HotelStatus.PENDING_VERIFICATION);
        hotel = hotelRepository.save(hotel);
        return toDto(hotel);
    }

    private HotelDTO toDto(Hotel hotel) {
        return new HotelDTO(
            hotel.getId(),
            hotel.getName(),
            hotel.getAddress(),
            hotel.getContact(),
            hotel.getStatus()
        );
    }
}
