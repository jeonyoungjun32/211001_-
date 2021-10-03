package vo;

public class Basket_detail {
	  private String basket_detail_name; 	//상품이름
	  private String basket_detail_choice;	//아이스크림 종류
	  private int basket_detail_count; 		//수량
	  private String basket_detail_date; 	//현재 날짜
	  public int basket_basket_num;			//번호
	  private int basket_price;				//가격
	  private String member_id;				//회원아이디
	  
	  
	  public Basket_detail() {
		// TODO Auto-generated constructor stub
	}


	public Basket_detail(String basket_detail_name, String basket_detail_choice, int basket_detail_count,
			String basket_detail_date, int basket_basket_num, int basket_price, String member_id) {
		super();
		this.basket_detail_name = basket_detail_name;
		this.basket_detail_choice = basket_detail_choice;
		this.basket_detail_count = basket_detail_count;
		this.basket_detail_date = basket_detail_date;
		this.basket_basket_num = basket_basket_num;
		this.basket_price = basket_price;
		this.member_id = member_id;
	}


	public String getBasket_detail_name() {
		return basket_detail_name;
	}


	public void setBasket_detail_name(String basket_detail_name) {
		this.basket_detail_name = basket_detail_name;
	}


	public String getBasket_detail_choice() {
		return basket_detail_choice;
	}


	public void setBasket_detail_choice(String basket_detail_choice) {
		this.basket_detail_choice = basket_detail_choice;
	}


	public int getBasket_detail_count() { //수량
		return basket_detail_count;
	}


	public void setBasket_detail_count(int basket_detail_count) { //수량
		this.basket_detail_count = basket_detail_count;
	}


	public String getBasket_detail_date() {
		return basket_detail_date;
	}


	public void setBasket_detail_date(String basket_detail_date) {
		this.basket_detail_date = basket_detail_date;
	}


	public int getBasket_basket_num() {
		return basket_basket_num;
	}


	public void setBasket_basket_num(int basket_basket_num) {
		this.basket_basket_num = basket_basket_num;
	}


	public int getBasket_price() {
		return basket_price;
	}


	public void setBasket_price(int basket_price) {
		this.basket_price = basket_price;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	
	  
	
}
