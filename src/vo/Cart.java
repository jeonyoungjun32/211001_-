package vo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Cart {
	//private String image; // 상품 이미지 :: 이 이미지는  img폴더에 넣고 가져오는 방식으로 한다 
	private String kind; 	// 상품명
	private String option; 	//옵션
	private int price; 		// 가격
	private int qty; 		// 수량 .

	//추가
	private String parcel; //택배
	private String encodingKind; // ★★★ 인코딩된 개 상품명 추가, //UTF-8 인코딩 할라고 get만 만들었다!!

	// 기본생성자 만들고
	public Cart() {
	}
	
	//이건 참고 임
	public String getEncodingKind() { // UTF-8 인코딩 할라고 get만 만들었다!!
		try {
			encodingKind = URLEncoder.encode(kind, "UTF-8"); // 서버로 전송하기에 net안에 있다! URLEncoder = encoder인코딩이란
		} catch (UnsupportedEncodingException e) {
			// TODO kind(개종류)를 UTF-8로 인코딩해서 보낸다잉!!
			e.printStackTrace();
		}
		return encodingKind;
	}


	
	
	public Cart(String kind, String option, int price, int qty, String parcel, String encodingKind) {
		super();
		this.kind = kind;					//상품명
		this.option = option;				//옵션
		this.price = price;					//가격
		this.qty = qty;						//수량
		this.parcel = parcel;				//택배
		this.encodingKind = encodingKind;	//쓸지 안쓸지 모름
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setEncodingKind(String encodingKind) {
		this.encodingKind = encodingKind;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getParcel() {
		return parcel;
	}

	public void setParcel(String parcel) {
		this.parcel = parcel;
	}
	
	
	
	

}
