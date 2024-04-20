package br.com.neurotech.challenge.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "client")
@Data
public class NeurotechClient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	private String name;
	private Integer age;
	private Double income;

}