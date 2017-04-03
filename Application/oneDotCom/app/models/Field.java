package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Field {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long idField;
	private String fieldName;
	private String fieldContent;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PROPOSAL_CONTENT_ID")
	private ProposalContent container;
	
	@OneToMany(mappedBy="field")
	private List<FieldChannel> listOfFieldChannels;

	public Long getIdField() {
		return idField;
	}

	public void setIdField(Long idField) {
		this.idField = idField;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldContent() {
		return fieldContent;
	}

	public void setFieldContent(String fieldContent) {
		this.fieldContent = fieldContent;
	}

	public ProposalContent getContainer() {
		return container;
	}

	public void setContainer(ProposalContent container) {
		this.container = container;
	}

	public List<FieldChannel> getListOfFieldChannels() {
		return listOfFieldChannels;
	}

	public void setListOfFieldChannels(List<FieldChannel> listOfFieldChannels) {
		this.listOfFieldChannels = listOfFieldChannels;
	}
}
