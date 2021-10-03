package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.net.aso.p;
import vo.AllList;
import vo.IO;
import vo.Icecream;
import vo.Product;

import static db.Jdbcutil.*;

public class ProductDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
//	private MemberDAO() {}
	
	private static ProductDAO memberDAO;
	
	public static ProductDAO getInstance() {
		if(memberDAO == null) {
			memberDAO = new ProductDAO();
		}
		
		return memberDAO;
	}
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	/*관리자 물건 등록*/
	public int insertProduct(Product product, String id) {
		sql="select concat(2,date_format(sysdate(),'%m%d%i%S'))";
		int serial_code=0;
		String product_status="O";
		int insertCount=0;
		int product_count=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) serial_code = rs.getInt(1);
			
			sql ="insert into Product values(?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, serial_code);
			pstmt.setString(2, product.getName());
			pstmt.setInt(3, product.getKcal());
			pstmt.setString(4, product.getAllergy());
			pstmt.setInt(5, product.getPrice());
			pstmt.setString(6, product.getChoice());
			pstmt.setString(7, product_status);
			pstmt.setInt(8, product_count);
			pstmt.setString(9, id);
			
			insertCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}
	
	/*아이스크림 등록*/
	public int insertIceceream(Icecream icecream, String id) {
		sql="select concat(1,date_format(sysdate(),'%m%d%i%S'))";
		int serial_code=0;
		String icecream_status="O";
		int insertCount=0;
		int icecream_count=0;
		try {
			pstmt = con.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) serial_code = rs.getInt(1);
			
			sql ="insert into icecream values(?,?,?,?,?,?,?,now(),?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setLong(1, serial_code);
			pstmt.setString(2, icecream.getName());
			pstmt.setInt(3, icecream.getKcal());
			pstmt.setString(4, icecream.getAllergy());
			pstmt.setInt(5, icecream.getPrice());
			pstmt.setString(6, icecream.getChoice());
			pstmt.setString(7, icecream_status);
			pstmt.setInt(8, icecream_count);
			pstmt.setString(9, id);
			
			insertCount = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("insertIceceream 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return insertCount;
	}

	/*상품 조회*/
	public ArrayList<AllList> getListProduct() {
		sql = "select serial_code_p as serial_code, name, kcal, allergy, price, choice, status, PI_date, count, id from product "
			+ " union "
			+ " select serial_code_i as serial_code, name, kcal, allergy, price, choice, status, PI_date, count, id from icecream ";
		ArrayList<AllList> allList = new ArrayList<AllList>();
		AllList all = null;
		try {
			pstmt=con.prepareStatement(sql);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				all = new AllList();
				
				all.setSerial_code(rs.getInt("serial_code"));
				all.setName(rs.getString("name"));
				all.setKcal(Integer.parseInt(rs.getString("kcal")));
				all.setAllergy(rs.getString("allergy"));
				all.setPrice(Integer.parseInt(rs.getString("price")));
				all.setChoice(rs.getString("choice"));
				all.setStatus(rs.getString("status"));
				all.setPI_date(rs.getString("PI_date"));
				all.setCount(Integer.parseInt(rs.getString("count")));
				all.setId(rs.getString("id"));
				
				allList.add(all);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return allList;
	}
	
	//각 상품의 총 갯수 출력
	public int getAllListCount(int intViewSelect, String choiceNum) {
		int AllListCount = 0;
		/* 아이스크림 */
		if (choiceNum.equalsIgnoreCase("1")) {
			sql = "select count(*) from icecream";
			try {
				pstmt = con.prepareStatement(sql);

				rs = pstmt.executeQuery();
				if (rs.next()) {
					AllListCount = rs.getInt(1);
				}
				
			} catch (Exception e) {
				System.out.println("getAllListCount 1 오류 : "+e);
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			/* 상품 */
		} else if (choiceNum.equalsIgnoreCase("2")) {
			sql = "select count(*) from Product where choice =?";
			try {
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, intViewSelect);
				rs = pstmt.executeQuery();

				if (rs.next()) {
					AllListCount = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println("getAllListCount 2 오류 : "+e);
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
		}
		return AllListCount;
	}
	
	//상품 조회 페이지 뷰 처리
	public ArrayList<AllList> getAllListSelect(int intViewSelect, String choiceNum) {
		
		AllList allList = null;
		try {
			/*아이스크림*/
			if(choiceNum.equalsIgnoreCase("1")) {
			sql ="select serial_code_i, name, kcal, allergy, price, choice, status, PI_date, count, id from icecream where choice = ? ";
			
			pstmt= con.prepareStatement(sql);
			
			pstmt.setInt(1, intViewSelect);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
					
					
				} while(rs.next());
				
			}
			
			/*상품*/
			} else if (choiceNum.equalsIgnoreCase("2")) {
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			
		}
		return null;
	}
	/// 		내가 만들어야 하는게 여기 있다
	
	//아이스크림 조회
	public ArrayList<Icecream> getListIcecream() {
		sql = "select * from icecream where choice = ?";
		ArrayList<Icecream> icecreamList = new ArrayList<Icecream>();
		Icecream icecream= null;
		String product_choice="5";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,product_choice);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				icecream = new Icecream();
				
				icecream.setSerial_code_i(rs.getInt("serial_code_i"));
				icecream.setName(rs.getString("name"));
				icecream.setKcal(Integer.parseInt(rs.getString("kcal")));
				icecream.setAllergy(rs.getString("allergy"));
				icecream.setPrice(Integer.parseInt(rs.getString("price")));
				icecream.setChoice(rs.getString("choice"));
				icecream.setStatus(rs.getString("status"));
				icecream.setPI_date(rs.getString("PI_date"));
				icecream.setCount(Integer.parseInt(rs.getString("count")));
				icecream.setId(rs.getString("id"));
				
				icecreamList.add(icecream);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return icecreamList;
	}
	
	//아이스케잌 조회
	public ArrayList<Product> getListIceCake() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> iceCakeList = new ArrayList<Product>();
		Product product= null;
		String product_choice="1";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,product_choice);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				product = new Product();
				
				product.setSerial_code_p(rs.getInt("serial_code_p"));
				product.setName(rs.getString("name"));
				product.setKcal(Integer.parseInt(rs.getString("kcal")));
				product.setAllergy(rs.getString("allergy"));
				product.setPrice(Integer.parseInt(rs.getString("price")));
				product.setChoice(rs.getString("choice"));
				product.setStatus(rs.getString("status"));
				product.setPI_date(rs.getString("PI_date"));
				product.setCount(Integer.parseInt(rs.getString("count")));
				product.setId(rs.getString("id"));
				
				iceCakeList.add(product);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return iceCakeList;
	}
	
	//디저트 조회
	public ArrayList<Product> getListDessert() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> dessertList = new ArrayList<Product>();
		Product product= null;
		String product_choice="4";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,product_choice);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				product = new Product();
				
				product.setSerial_code_p(rs.getInt("serial_code_p"));
				product.setName(rs.getString("name"));
				product.setKcal(Integer.parseInt(rs.getString("kcal")));
				product.setAllergy(rs.getString("allergy"));
				product.setPrice(Integer.parseInt(rs.getString("price")));
				product.setChoice(rs.getString("choice"));
				product.setStatus(rs.getString("status"));
				product.setPI_date(rs.getString("PI_date"));
				product.setCount(Integer.parseInt(rs.getString("count")));
				product.setId(rs.getString("id"));
				
				dessertList.add(product);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return dessertList;
	}
	
	//음료 조회
	public ArrayList<Product> getListBeverage() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> beverageList = new ArrayList<Product>();
		Product product= null;
		String product_choice="2";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,product_choice);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				product = new Product();
				
				product.setSerial_code_p(rs.getInt("serial_code_p"));
				product.setName(rs.getString("name"));
				product.setKcal(Integer.parseInt(rs.getString("kcal")));
				product.setAllergy(rs.getString("allergy"));
				product.setPrice(Integer.parseInt(rs.getString("price")));
				product.setChoice(rs.getString("choice"));
				product.setStatus(rs.getString("status"));
				product.setPI_date(rs.getString("PI_date"));
				product.setCount(Integer.parseInt(rs.getString("count")));
				product.setId(rs.getString("id"));
				
				beverageList.add(product);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return beverageList;
	}
	
	//커피 조회
	public ArrayList<Product> getListCoffee() {
		sql = "select * from Product where choice = ?";
		ArrayList<Product> coffeeList = new ArrayList<Product>();
		Product product= null;
		String product_choice="3";
		try {
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1,product_choice);
			
			rs= pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				product = new Product();
				
				product.setSerial_code_p(rs.getInt("serial_code_p"));
				product.setName(rs.getString("name"));
				product.setKcal(Integer.parseInt(rs.getString("kcal")));
				product.setAllergy(rs.getString("allergy"));
				product.setPrice(Integer.parseInt(rs.getString("price")));
				product.setChoice(rs.getString("choice"));
				product.setStatus(rs.getString("status"));
				product.setPI_date(rs.getString("PI_date"));
				product.setCount(Integer.parseInt(rs.getString("count")));
				product.setId(rs.getString("id"));
				
				coffeeList.add(product);
				} while(rs.next());
			}
		} catch (Exception e) {
			System.out.println("getListProduct 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return coffeeList;
	}
	
	//상품 코드로 상품 조회
	public AllList getAllFindCode(int serial_code) {
		AllList allList = null;
		
		String strserial = String.valueOf(serial_code);
		strserial = strserial.substring(0, 1);
		
		try {
			if(strserial.equalsIgnoreCase("2")) {
			sql="select * from Product where serial_code_p = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, serial_code);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				allList = new AllList();
				allList.setSerial_code(rs.getInt("serial_code_p"));
				allList.setName(rs.getString("name"));
				allList.setKcal(rs.getInt("kcal"));
				allList.setAllergy(rs.getString("allergy"));
				allList.setPrice(rs.getInt("price"));
				allList.setChoice(rs.getString("choice"));
				allList.setStatus(rs.getString("status"));
				allList.setPI_date(rs.getString("PI_date"));
				allList.setCount(rs.getInt("count"));
				allList.setId(rs.getString("id"));
				} 
			} else if (strserial.equalsIgnoreCase("1")) {
			sql="select * from icecream where serial_code_i = ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, serial_code);
			
			rs =pstmt.executeQuery();
			if(rs.next()) {
				allList = new AllList();
				allList.setSerial_code(rs.getInt("serial_code_i"));
				allList.setName(rs.getString("name"));
				allList.setKcal(rs.getInt("kcal"));
				allList.setAllergy(rs.getString("allergy"));
				allList.setPrice(rs.getInt("price"));
				allList.setChoice(rs.getString("choice"));
				allList.setStatus(rs.getString("status"));
				allList.setPI_date(rs.getString("PI_date"));
				allList.setCount(rs.getInt("count"));
				allList.setId(rs.getString("id"));
				}
			}
		} catch (Exception e) {
			System.out.println("getAllFindCode 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(rs);
			close(pstmt);
		}
		return allList;
	}

	//상품 정보 수정
	public int AllUpdate(int serial_code, String name, String allergy, int kcal, int price) {
		String strserial = String.valueOf(serial_code);
		strserial = strserial.substring(0, 1);
				
		int allUpdateCount=0;
		try {
			if(strserial.equalsIgnoreCase("2")) {
			sql ="update Product set name = ?, kcal = ?, allergy = ?, price = ? where serial_code_p = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, kcal);
			pstmt.setString(3, allergy);
			pstmt.setInt(4, price);
			pstmt.setInt(5, serial_code);
			
			allUpdateCount = pstmt.executeUpdate();
			} else if (strserial.equalsIgnoreCase("1")) {
			sql ="update icecream set name = ?, kcal = ?, allergy = ?, price = ? where serial_code_i = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setInt(2, kcal);
			pstmt.setString(3, allergy);
			pstmt.setInt(4, price);
			pstmt.setInt(5, serial_code);
			allUpdateCount = pstmt.executeUpdate();
			}
			 
		} catch (Exception e) {
			System.out.println("ProductUpdate 오류 : "+e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return allUpdateCount;
	}

	
	//상품리스트에서 체크한것을 찾기
	public ArrayList<AllList> adminGetAll(String[] productCheckCode) {
		ArrayList<AllList> AllList = new ArrayList<AllList>();
		AllList all = null;
		int j = 0;
		for (int i = 0; i < productCheckCode.length; i++) {
			j++;
		}
		
		String charCode[] = new String[j];
		for (int i = 0; i < productCheckCode.length; i++) {
			charCode[i] = productCheckCode[i].substring(0, 1);
		}

		if (productCheckCode != null) {
			
			for (int i = 0; i < productCheckCode.length; i++) {
				
				try {
					if (charCode[i].equalsIgnoreCase("2")) {
						sql = "select * from Product where serial_code_p = ?";
						pstmt = con.prepareStatement(sql);

						pstmt.setString(1, productCheckCode[i]);

						rs = pstmt.executeQuery();

						if (rs.next()) {
							all = new AllList(rs.getInt("serial_code_p"), rs.getString("name"), rs.getInt("kcal"),
									rs.getString("allergy"), rs.getInt("price"), rs.getString("choice"),
									rs.getString("status"), rs.getString("PI_date"), rs.getInt("count"),
									rs.getString("id"));

							AllList.add(all);
						}
					} else if (charCode[i].equalsIgnoreCase("1")) {
						sql = "select * from icecream where serial_code_i = ?";
						pstmt = con.prepareStatement(sql);

						pstmt.setString(1, productCheckCode[i]);

						rs = pstmt.executeQuery();

						if (rs.next()) {
							all = new AllList(rs.getInt("serial_code_i"), rs.getString("name"), rs.getInt("kcal"),
									rs.getString("allergy"), rs.getInt("price"), rs.getString("choice"),
									rs.getString("status"), rs.getString("PI_date"), rs.getInt("count"),
									rs.getString("id"));

							AllList.add(all);
						}
					}

					} catch (Exception e) {
						System.out.println("adminGetProduct 오류 : " + e);
						e.printStackTrace();
					} finally {
						close(pstmt);
						close(rs);
					}
				}
			
		}
		return AllList;
	}

	//상품 체크박스 선택시 삭제
	public int arrAllDelete(AllList allList) {
		int arrAllDeleteCount = 0;
		
		String strCode = Integer.toString(allList.getSerial_code());
		
		try {
			if(strCode.substring(0, 1).equalsIgnoreCase("2")) {
			sql = "delete from Product where serial_code_p =?";
	
				pstmt = con.prepareStatement(sql);
	
				pstmt.setInt(1, allList.getSerial_code());
	
				arrAllDeleteCount = pstmt.executeUpdate();
			} else if (strCode.substring(0, 1).equalsIgnoreCase("1")) {
				sql = "delete from icecream where serial_code_i =?";
	
				pstmt = con.prepareStatement(sql);
	
				pstmt.setInt(1, allList.getSerial_code());
	
				arrAllDeleteCount = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			System.out.println("arrProductDelete 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return arrAllDeleteCount;
	}

	//상품 체크박스 선택한 정보 가져오기
	public ArrayList<Product> arrProductInfo(String[] productCheckCode) {
		ArrayList<Product> productList = new ArrayList<Product>();
		Product product = null;
		if (productCheckCode != null) {

			for (int i = 0; i < productCheckCode.length; i++) {
				sql = "select * from Product where serial_code_p = ?";
				try {
					pstmt = con.prepareStatement(sql);

					pstmt.setString(1, productCheckCode[i]);

					rs = pstmt.executeQuery();

					if (rs.next()) {
						product = new Product(
								rs.getInt("serial_code_p"),
								rs.getString("name"),
								rs.getInt("kcal"),
								rs.getString("allergy"),
								rs.getInt("price"),
								rs.getString("choice"),
								rs.getString("status"),
								rs.getString("PI_date"),
								rs.getInt("count"),
								rs.getString("id"));

							productList.add(product);
					}

				} catch (Exception e) {
					System.out.println("arrProductInfo 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
					close(rs);
				}
			}
		}
		return productList;
	}

	//IO 등록(입고)
	//serial_code : 상품 코드 / product_count : 상품 갯수 / strserial_code : 상품 앞번호 추출 (1 : 아이스크림/ 2 :상품)
	public int arrAdminIOupdate(int[] allSerial_code, int[] product_count, String[] strSerial_code) {
		//sql 2개던지기 IO Product
		int IOcount=0;
		int productCode=0;
		int icecreamCode=0;
		String IO_status= "I";
		int ice_insert_count=0;
		int ice_update_count=0;
		int product_insert_count=0;
		int product_update_count=0;
		int ReturnValue=0;
		
		
		for(int i =0; i<allSerial_code.length; i++) {
			try {
				/*입고 번호 */
				sql="select count(IO_index)+1 as IOcount from IO";
				
				pstmt = con.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) IOcount=rs.getInt("IOcount");
				
			} catch (Exception e) {
				System.out.println("arrAdminIOupdate 1-1 오류 : " + e);
				e.printStackTrace();
			} finally {
				close(pstmt);
				close(rs);
			}
			
			/*아이스크림*/ 
			if(strSerial_code[i].equalsIgnoreCase("1")) {
				
				sql="insert into IO values (?,?,?,now(),?,?)";
				try {
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, IOcount);
					pstmt.setString(2, IO_status);
					pstmt.setInt(3, product_count[i]);
					pstmt.setInt(4, productCode);
					pstmt.setInt(5, allSerial_code[i]);
					
					ice_insert_count = pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("arrAdminIOupdate 1-2 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
				sql="update icecream set status = ?, count = count + ? where serial_code_i = ?";
				
				try {
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, IO_status);
					pstmt.setInt(2, product_count[i]);
					pstmt.setInt(3, allSerial_code[i]);
					
					ice_update_count = pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("arrAdminIOupdate 1-3 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
				
			/*상품*/
			} else if (strSerial_code[i].equalsIgnoreCase("2")) {
				
				sql="insert into IO values (?,?,?,now(),?,?)";
				try {
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, IOcount);
					pstmt.setString(2, IO_status);
					pstmt.setInt(3, product_count[i]);
					pstmt.setInt(4, allSerial_code[i]);
					pstmt.setInt(5, icecreamCode);
					
					product_insert_count = pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("arrAdminIOupdate 1-2 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
				sql="update Product set status = ?, count = count + ? where serial_code_p = ?";
				
				try {
					pstmt=con.prepareStatement(sql);
					pstmt.setString(1, IO_status);
					pstmt.setInt(2, product_count[i]);
					pstmt.setInt(3, allSerial_code[i]);
					
					product_update_count = pstmt.executeUpdate();
					
				} catch (Exception e) {
					System.out.println("arrAdminIOupdate 1-3 오류 : " + e);
					e.printStackTrace();
				} finally {
					close(pstmt);
				}
			}
		}
		
		if((product_insert_count>0 && product_update_count >0) || (ice_insert_count>0 && ice_update_count >0)) {
			ReturnValue=1;
		}
		
		return ReturnValue;
	}
	
	//IO 리스트 출력
	public ArrayList<IO> selectIOlist() {
		sql="select * from IO";
		IO io = null;
		ArrayList<IO> ioList =new ArrayList<IO>();
		try {
			pstmt=con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				do {
				io = new IO(
						rs.getInt("IO_index"),
						rs.getString("IO_inout"),
						rs.getInt("IO_count"),
						rs.getString("IO_date"),
						rs.getInt("serial_code_p"),
						rs.getInt("serial_code_i"));
				
				ioList.add(io);
				} while(rs.next());
			}
			
		} catch (Exception e) {
			System.out.println("selectIOlist 오류 : " + e);
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rs);
		}
		return ioList;
	}
	/**************************************************************************************************************************/
	//상품전체 리스트
	public ArrayList<Product> selectProductList(){
		ArrayList<Product> productList = null;
		
		
		try {
			pstmt = con.prepareStatement("select * from Product");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				productList = new ArrayList<Product>();
				
				do {
					Product product = new Product(
							rs.getInt("serial_code_p"),
							rs.getString("name"),
							rs.getInt("kcal"),
							rs.getString("allergy"),
							rs.getInt("price"),
							rs.getString("choice"),
							rs.getString("status"),
							rs.getString("PI_date"),
							rs.getInt("count"),
							rs.getString("id"));
					
					productList.add(product);
					
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("ProductDAO 상품리스트 오류 "+e);
		}finally {
			close(rs);
			close(pstmt);			
		}
		return productList;
	}
	public Product selectProduct(String id) {//상품코드를 던지고  사람id로 받아야한다!!!!!
		Product product = null;
		
		try {
			//pstmt =  con.prepareStatement("select * from Product where id = ? ");
			pstmt =  con.prepareStatement("select * from Product where id =?");
			//pstmt.setInt(1, serial_code_p);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
					product = new Product(
							rs.getInt("serial_code_p"),
							rs.getString("name"),
							rs.getInt("kcal"),
							rs.getString("allergy"),
							rs.getInt("price"),
							rs.getString("choice"),
							rs.getString("status"),
							rs.getString("PI_date"),
							rs.getInt("count"),
							rs.getString("id"));
					
			}		
		} catch (Exception e) {
			System.out.println("int 코드 오류"+ e);
		}finally {
			close(rs);
			close(pstmt);			
		}
		return product;
	}

}
