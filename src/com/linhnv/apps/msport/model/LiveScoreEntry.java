package com.linhnv.apps.msport.model;

public class LiveScoreEntry {
	private int id;
	private String logo;
	private int matchId;
	private int dateTime;
	private int homeId;
	private String homeName;
	private String homeLogo;
	private int awayId;
	private String awayHome;
	private String awayLogo;
	private String stadium;
	private String result;
	private String status;	
	public LiveScoreEntry() {
		super();
	}
	public LiveScoreEntry(int id, String logo, int matchId, int dateTime,
			int homeId, String homeName, String homeLogo, int awayId,
			String awayHome, String awayLogo, String stadium, String result,
			String status) {
		super();
		this.id = id;
		this.logo = logo;
		this.matchId = matchId;
		this.dateTime = dateTime;
		this.homeId = homeId;
		this.homeName = homeName;
		this.homeLogo = homeLogo;
		this.awayId = awayId;
		this.awayHome = awayHome;
		this.awayLogo = awayLogo;
		this.stadium = stadium;
		this.result = result;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public int getDateTime() {
		return dateTime;
	}
	public void setDateTime(int dateTime) {
		this.dateTime = dateTime;
	}
	public int getHomeId() {
		return homeId;
	}
	public void setHomeId(int homeId) {
		this.homeId = homeId;
	}
	public String getHomeName() {
		return homeName;
	}
	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	public String getHomeLogo() {
		return homeLogo;
	}
	public void setHomeLogo(String homeLogo) {
		this.homeLogo = homeLogo;
	}
	public int getAwayId() {
		return awayId;
	}
	public void setAwayId(int awayId) {
		this.awayId = awayId;
	}
	public String getAwayHome() {
		return awayHome;
	}
	public void setAwayHome(String awayHome) {
		this.awayHome = awayHome;
	}
	public String getAwayLogo() {
		return awayLogo;
	}
	public void setAwayLogo(String awayLogo) {
		this.awayLogo = awayLogo;
	}
	public String getStadium() {
		return stadium;
	}
	public void setStadium(String stadium) {
		this.stadium = stadium;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
