package models;

import javax.persistence.*;

@Entity
@Table(name = "membrefacultaire", schema="onedotcom")
public class MembreFacultaire {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	@Column(name="prenom", length=255)
	public String prenom;
	@Column(name="email", length=255)
	public String email;
	@Column(name="pwd", length=255)
	public String pwd;
}
