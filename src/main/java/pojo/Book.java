package pojo;

import java.math.BigDecimal;

/**
 * 	img_path需要设置一个默认路径，即页面打开后显示默认的那张图片
 * 	构造方法中也对其进行判断
 * @author 32326
 *
 */
public class Book {
	private Integer id;
	private String name;
	private BigDecimal price;
	private String author;
	private Integer sales;
	private Integer stock;
	private String img_path="favicon.ico/aaa.ico";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getSales() {
		return sales;
	}
	public void setSales(Integer sales) {
		this.sales = sales;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public String getImg_path() {
		return img_path;
	}
	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}
	
	public Book(Integer id, String name, BigDecimal price, String author, Integer sales, Integer stock, String img_path) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.sales = sales;
		this.stock = stock;
		
		//要求给定的图书封面不能为空
		if(img_path!=null && img_path!="") {
			this.img_path = img_path;
		}
	}
	public Book() {
		super();
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", author=" + author + ", sales=" + sales
				+ ", stock=" + stock + ", img_path=" + img_path + "]";
	}
}
