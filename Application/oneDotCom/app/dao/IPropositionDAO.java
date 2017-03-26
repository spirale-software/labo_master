package dao;

import java.util.List;

import models.Proposition;

public interface IPropositionDAO {
	
	public Proposition insertNewProposition(Proposition newProposition);
	public List<Proposition> getAllPropositions();
	public Proposition getPropositionById(Long id);
	public Proposition updateProposition(Proposition propositionUpdated);
	public List<Proposition> get10NewestPropositions();
}
