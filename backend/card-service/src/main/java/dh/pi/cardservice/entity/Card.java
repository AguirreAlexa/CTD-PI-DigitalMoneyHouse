package dh.pi.cardservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Data;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "El campo accountId no puede estar vacío")
    private Integer accountId;

    @NotBlank(message = "El campo type no puede estar vacio")
    private String type;

    @NotBlank(message = "El campo cardNumber no puede estar vacio")
    private String cardNumber;

    @NotBlank(message = "El campo owner no puede estar vacio")
    private String owner;

    @NotBlank(message = "El campo securityNumber no puede estar vacio")
    private String securityNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "El campo expirationDate no puede estar vacio")
    private Timestamp expirationDate;

    @NotNull(message = "El campo balance no puede estar vacio")
    private Integer balance;

    private String lastNumbers;
}
