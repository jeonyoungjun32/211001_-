package vo;

public class Icecream {
	private int serial_code_i;  //상품 번호 도 쓰고 이미지번호랑 같아야한다
	private String name;		//상품 이름
	private int kcal;			//상품 칼로리
	private String allergy;		//알레르기
	private int price;			//상품 가격
	private String choice;		//선택 할거 1번 커피 2번 아이스크림
	private String status;		//상품 상태
	private String PI_date;		//날짜
	private int count;			//갯수
	private String id;			//상품 아이디
	//private String image;		//상품 이미지 안쓴다 뺏다
	
	public Icecream() {}

	public Icecream(int serial_code_i, String name, int kcal, String allergy, int price, String choice, String status,
			String pI_date, int count, String id) {
		super();
		this.serial_code_i = serial_code_i;
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

	public int getSerial_code_i() {
		return serial_code_i;
	}

	public void setSerial_code_i(int serial_code_i) {
		this.serial_code_i = serial_code_i;
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

	public String getAllergy() {	//알레르기
		return allergy;
	}

	public void setAllergy(String allergy) {//알레르기
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

	public String getPI_date() { //현재 날짜
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

	public String getId() {		//상품id를 해야 하나   회원id로 해야하나
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
}
