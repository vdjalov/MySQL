package dtos;

public class CategoriesByProductsCountDto {

	private String category;
	private String productsCount;
	private String averagePrice;
	private String totalRevenue;
	
	
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProductsCount() {
		return productsCount;
	}
	public void setProductsCount(String productsCount) {
		this.productsCount = productsCount;
	}
	public String getAveragePrice() {
		return averagePrice;
	}
	public void setAveragePrice(String averagePrice) {
		this.averagePrice = averagePrice;
	}
	public String getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(String totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
}
