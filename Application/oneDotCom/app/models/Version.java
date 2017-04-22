package models;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("VERSION")
public class Version extends ProposalContent {

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ORIGINAL_ID")
	private ProposalContent original;

	private int versionNumber;

	public ProposalContent getOriginal() {
		return original;
	}

	public void setOriginal(ProposalContent original) {
		this.original = original;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
}





