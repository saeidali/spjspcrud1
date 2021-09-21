package com.example.spjspcrud.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @CsvBindByName
    @Column
    @Size(min = 2, max = 10, message = "{input.size.invalid}")
    @Pattern(regexp = "^[A-Za-z]*$", message = "{input.invalid.msg}")
    private String name;
    @CsvBindByName
    @Column
    @Email(message = "{invalid.email}")
    private String email;
    @CsvBindByName
    @Column
    private String gender;
    @CsvBindByName
    @Column
    @Size(min = 6, max = 15, message = "{invalid.password}")
    private String password;
    @Column
    @CsvBindByName
    private String profession;
    @Column
    @CsvBindByName
    @Size(max = 25, message = "{note.size}")
    private String note;
    @CsvBindByName
    @CsvDate(value = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Builder.Default
    @NotNull(message = "{not.null.date}")
    @Past(message = "{past.birthday}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private LocalDate birthday = LocalDate.of(1985, 1, 1);
    @Column
    @CsvBindByName
    @Builder.Default
    private boolean married = false;
}
