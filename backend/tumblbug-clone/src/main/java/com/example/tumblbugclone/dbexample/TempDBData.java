package com.example.tumblbugclone.dbexample;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
