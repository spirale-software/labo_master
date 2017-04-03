package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ProposalContent {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private Date writingDate;
	
	@OneToMany(mappedBy="container")
	private List<Field> listOfFields;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getWritingDate() {
		return writingDate;
	}

	public void setWritingDate(Date writingDate) {
		this.writingDate = writingDate;
	}

	public List<Field> getListOfFields() {
		return listOfFields;
	}

	public void setListOfFields(List<Field> listOfFields) {
		this.listOfFields = listOfFields;
	}	
}
