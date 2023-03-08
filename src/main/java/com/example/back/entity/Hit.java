package com.example.back.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "hits")
public class Hit {

    public Hit(Float x, Float y, Float r, boolean hit) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit = hit;
    }

    @Id
    @GeneratedValue
    @Column(name = "hit_id", nullable = false)
    private Long id;

    @Column(name = "hit_x")
    private Float x;

    @Column(name = "hit_y")
    private Float y;

    @Column(name = "hit_r")
    private Float r;

    @Column(name = "hit_hit")
    private Boolean hit;
}
