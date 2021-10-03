package vo;

public class Product {
	private int serial_code_p; 		//제퓸코두
	private String name;			//제품이름
	private int kcal;				//칼로리
	private String allergy;			//알레르기
	private int price;				//가격
	private String choice;			//선택
	private String status;			//상태	
	private String PI_date;			//날짜
	private int count;				//갯수
	private String id;				//사용자id
	
	public Product() {}

	public Product(int serial_code_p, String name, int kcal, String allergy, int price, String choice, String status,
			String pI_date, int count, String id) {
		super();
		this.serial_code_p = serial_code_p;
		this.name = name;
		this.kcal = kcal;
		this.allergy = allergy;
		this.price = price;
		this.choice = choice;
		this.status = status;
		PI_date = pI_date;
		this.count = count;
		this.id = id;
	}

	public int getSerial_code_p() {
		return serial_code_p;
	}

	public void setSerial_code_p(int serial_code_p) {
		this.serial_code_p = serial_code_p;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKcal() {
		return kcal;
	}

	public void setKcal(int kcal) {
		this.kcal = kcal;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getChoice() {
		return choice;
	}

	public void setChoice(String choice) {
		this.choice = choice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPI_date() {
		return PI_date;
	}

	public void setPI_date(String pI_date) {
		PI_date = pI_date;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
