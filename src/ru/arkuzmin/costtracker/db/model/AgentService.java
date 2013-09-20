package ru.arkuzmin.costtracker.db.model;

import java.util.List;

import ru.arkuzmin.costtracker.db.bean.Agent;

public interface AgentService {

	public List<Agent> getAllAgents();
	public void addAgent(Agent newAgent);
	public void updateAgent(Agent agent);
	public void deleteAgent(Agent agent);
	
}
