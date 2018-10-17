package edu.otus.hw15.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Nik Bespalov on 03/10/2018.
 *
 * @author Nik Bespalov.
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private int id;
    private String city;
    private String street;
    private int buildingNumber;
}
