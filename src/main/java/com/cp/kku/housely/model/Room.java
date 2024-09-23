package com.cp.kku.housely.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "imageBase64")
    private String imageBase64;

    @Column(name = "room_name", nullable = false)
    private String roomName;

    @ManyToMany(mappedBy = "rooms", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Product> productsInRoom = new ArrayList<>();
    
}

