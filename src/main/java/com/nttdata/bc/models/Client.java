package com.nttdata.bc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clientId")
    private Integer clientId;

    @NotNull(message = "El campo clientType es requerido.")
    @ManyToOne
    @JoinColumn(name = "clientTypeId")
    private ClientType clientType;

    @NotEmpty(message = "El campo documentIdentityType es requerido.")
    @Pattern(regexp = "^DNI$|^CEX$|^RUC$", message = "El campo documentIdentityType debe tener uno de estos valores: [DNI, CEX, RUC].")
    @Column(name = "documentIdentityType", nullable = false, length = 3)
    private String documentIdentityType;

    @NotEmpty(message = "El campo documentIdentity es requerido.")
    @Column(name = "documentIdentity", nullable = false, length = 15)
    private String documentIdentity;

    @NotEmpty(message = "El campo name es requerido.")
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @NotEmpty(message = "El campo email es requerido.")
    @Email(message = "El campo email debe ser una dirección de correo electrónico con formato correcto.")
    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @NotEmpty(message = "El campo phone es requerido.")
    @Column(name = "phone", nullable = false, length = 9)
    private String phone;
}
