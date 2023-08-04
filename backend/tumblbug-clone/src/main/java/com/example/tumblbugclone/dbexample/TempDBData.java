package com.example.tumblbugclone.dbexample;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "TEMP_DATA")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TempDBData {

    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "TEMP_NAME")
    private String name;
}
