package com.cognizant.stockmarket.companyservice.model;

import java.util.List;
import java.util.Set;



public class CompanyDTO {

	private int id;
	


	private Long companyCode;
	

	private String name;
	
	
	private Long turnover;
	
	
	private String ceo;
	

	private List<BoardOfDirectors> boardOfDirectorsList;
	

	private boolean listed;
	

	private Sector sector;

	private String aboutCompany;

	private Set<StockExchange> stockExchanges;
	
	public Set<StockExchange> getStockExchanges() {
		return stockExchanges;
	}

	public void setStockExchanges(Set<StockExchange> stockExchanges) {
		this.stockExchanges = stockExchanges;
	}

	public CompanyDTO() {
		super();
		}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTurnover() {
		return turnover;
	}

	public void setTurnover(Long turnover) {
		this.turnover = turnover;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public List<BoardOfDirectors> getBoardOfDirectorsList() {
		return boardOfDirectorsList;
	}

	public void setBoardOfDirectorsList(List<BoardOfDirectors> boardOfDirectorsList) {
		this.boardOfDirectorsList = boardOfDirectorsList;
	}

	public boolean isListed() {
		return listed;
	}

	public void setListed(boolean listed) {
		this.listed = listed;
	}

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	public String getAboutCompany() {
		return aboutCompany;
	}

	public void setAboutCompany(String aboutCompany) {
		this.aboutCompany = aboutCompany;
	}

	public CompanyDTO(int id, Long companyCode, String name, Long turnover, String ceo,
			List<BoardOfDirectors> boardOfDirectorsList, boolean listed, Sector sector, String aboutCompany,
			Set<StockExchange> stockExchanges) {
		super();
		this.id = id;
		this.companyCode = companyCode;
		this.name = name;
		this.turnover = turnover;
		this.ceo = ceo;
		this.boardOfDirectorsList = boardOfDirectorsList;
		this.listed = listed;
		this.sector = sector;
		this.aboutCompany = aboutCompany;
		this.stockExchanges = stockExchanges;
	}


	
	
	
}