package ru.arkuzmin.costtracker.model.dto;

import ru.arkuzmin.costtracker.db.bean.Agent;

/** 
 * Содержит информацию по агенту и сумме расходов данного агента.
 * @author ArKuzmin
 *
 */
public class AgentCostAmount {
	
	private Agent agent;
	
	private Double amount;
	
	public AgentCostAmount(Agent agent, Double amount) {
		this.agent = agent;
		this.amount = amount;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
