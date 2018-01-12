package com.chance.commen;

/**
 * @author 付龙
 * @date 2018年1月9日 下午6:33:46
 *
 */
public class CarMess {

	private String originalText;
	private String link;
	private String website; 
	private String thread_or_praise;
	private String year;
	private String quar;
	private String month;
	private String brand;
	private String carName;
	private String carModelVersion;
	private String firstProperty;
	private String secondProperty;
	private String thirdClass;
	private String CommentKey;
	private String realFeel;
	private String nation;
	private String marketCategory;
	private String marketMess;
	public String getOriginalText() {
		return originalText;
	}
	public void setOriginalText(String originalText) {
		this.originalText = originalText;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getThread_or_praise() {
		return thread_or_praise;
	}
	public void setThread_or_praise(String thread_or_praise) {
		this.thread_or_praise = thread_or_praise;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getQuar() {
		return quar;
	}
	public void setQuar(String quar) {
		this.quar = quar;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCarModelVersion() {
		return carModelVersion;
	}
	public void setCarModelVersion(String carModelVersion) {
		this.carModelVersion = carModelVersion;
	}
	public String getFirstProperty() {
		return firstProperty;
	}
	public void setFirstProperty(String firstProperty) {
		this.firstProperty = firstProperty;
	}
	public String getSecondProperty() {
		return secondProperty;
	}
	public void setSecondProperty(String secondProperty) {
		this.secondProperty = secondProperty;
	}
	public String getThirdClass() {
		return thirdClass;
	}
	public void setThirdClass(String thirdClass) {
		this.thirdClass = thirdClass;
	}
	public String getCommentKey() {
		return CommentKey;
	}
	public void setCommentKey(String commentKey) {
		CommentKey = commentKey;
	}
	public String getRealFeel() {
		return realFeel;
	}
	public void setRealFeel(String realFeel) {
		this.realFeel = realFeel;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getMarketCategory() {
		return marketCategory;
	}
	public void setMarketCategory(String marketCategory) {
		this.marketCategory = marketCategory;
	}
	public String getMarketMess() {
		return marketMess;
	}
	public void setMarketMess(String marketMess) {
		this.marketMess = marketMess;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CommentKey == null) ? 0 : CommentKey.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((carModelVersion == null) ? 0 : carModelVersion.hashCode());
		result = prime * result + ((carName == null) ? 0 : carName.hashCode());
		result = prime * result + ((firstProperty == null) ? 0 : firstProperty.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((marketMess == null) ? 0 : marketMess.hashCode());
		result = prime * result + ((marketCategory == null) ? 0 : marketCategory.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((nation == null) ? 0 : nation.hashCode());
		result = prime * result + ((originalText == null) ? 0 : originalText.hashCode());
		result = prime * result + ((quar == null) ? 0 : quar.hashCode());
		result = prime * result + ((realFeel == null) ? 0 : realFeel.hashCode());
		result = prime * result + ((secondProperty == null) ? 0 : secondProperty.hashCode());
		result = prime * result + ((thirdClass == null) ? 0 : thirdClass.hashCode());
		result = prime * result + ((thread_or_praise == null) ? 0 : thread_or_praise.hashCode());
		result = prime * result + ((website == null) ? 0 : website.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarMess other = (CarMess) obj;
		if (CommentKey == null) {
			if (other.CommentKey != null)
				return false;
		} else if (!CommentKey.equals(other.CommentKey))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (carModelVersion == null) {
			if (other.carModelVersion != null)
				return false;
		} else if (!carModelVersion.equals(other.carModelVersion))
			return false;
		if (carName == null) {
			if (other.carName != null)
				return false;
		} else if (!carName.equals(other.carName))
			return false;
		if (firstProperty == null) {
			if (other.firstProperty != null)
				return false;
		} else if (!firstProperty.equals(other.firstProperty))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (marketMess == null) {
			if (other.marketMess != null)
				return false;
		} else if (!marketMess.equals(other.marketMess))
			return false;
		if (marketCategory == null) {
			if (other.marketCategory != null)
				return false;
		} else if (!marketCategory.equals(other.marketCategory))
			return false;
		if (month == null) {
			if (other.month != null)
				return false;
		} else if (!month.equals(other.month))
			return false;
		if (nation == null) {
			if (other.nation != null)
				return false;
		} else if (!nation.equals(other.nation))
			return false;
		if (originalText == null) {
			if (other.originalText != null)
				return false;
		} else if (!originalText.equals(other.originalText))
			return false;
		if (quar == null) {
			if (other.quar != null)
				return false;
		} else if (!quar.equals(other.quar))
			return false;
		if (realFeel == null) {
			if (other.realFeel != null)
				return false;
		} else if (!realFeel.equals(other.realFeel))
			return false;
		if (secondProperty == null) {
			if (other.secondProperty != null)
				return false;
		} else if (!secondProperty.equals(other.secondProperty))
			return false;
		if (thirdClass == null) {
			if (other.thirdClass != null)
				return false;
		} else if (!thirdClass.equals(other.thirdClass))
			return false;
		if (thread_or_praise == null) {
			if (other.thread_or_praise != null)
				return false;
		} else if (!thread_or_praise.equals(other.thread_or_praise))
			return false;
		if (website == null) {
			if (other.website != null)
				return false;
		} else if (!website.equals(other.website))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}
	
	
	
}
