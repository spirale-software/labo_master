package services.data.dao;

import java.util.List;

import models.Proposal;

public interface IPropositionDAO {
	
	public Proposal insertNewProposition(Proposal newProposition);
	public List<Proposal> getAllPropositions();
	public Proposal getPropositionById(Long id);
	public Proposal updateProposition(Proposal propositionUpdated);
	public List<Proposal> get10NewestPropositions();
}
