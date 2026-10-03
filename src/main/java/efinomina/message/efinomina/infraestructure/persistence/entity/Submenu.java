package efinomina.message.efinomina.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "submenus")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "icon", length = 100)
    private String icon;

    @Column(name = "path", length = 255)
    private String path;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
