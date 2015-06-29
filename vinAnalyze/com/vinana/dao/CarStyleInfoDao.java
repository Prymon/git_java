package com.vinana.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.PropertyConfigurator;

import com.vinana.entity.CarStyleInfo;
import com.vinana.mysql.ConnectionPool;
import com.vinana.mysql.ConnectionPoolFactory;
import com.vinana.util.XMLUtil;

/**
 * 获得指定条件的CarStyleInfo内容
 * 
 * @author Deamon
 */
public class CarStyleInfoDao {

	private CarStyleInfoDao() {}

	/**
	 * 得到所有存在VIN的CarStyleInfo
	 * @return CarStyleInfo列表
	 */
	public static List<CarStyleInfo> getCarStyleInfoHaveVin() {
		List<CarStyleInfo> listCarStyleInfo = new ArrayList<CarStyleInfo>();
		Statement state = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql = "select count(*) as count,carstyle.* from carstyle,carvin where carstyle.SF_CarStyleCode=carvin.SF_CarStyleCode group by carstyle.SF_CarStyleCode order by count(*) asc limit 0,10;";
		ConnectionPool connpool = ConnectionPoolFactory.getMysqlConnectionPool();
		try {
			conn = connpool.getConnectionFromPool();
			state = conn.createStatement();
			rs = state.executeQuery(sql);
			while(rs.next()){
				CarStyleInfo csinfo = new CarStyleInfo();
				csinfo.setCountVin(rs.getInt("count"));
				csinfo.setCarKind(rs.getString("CarKind"));
				csinfo.setCarStyleType(rs.getString("CarStyleType"));
				csinfo.setSF_CarBrandName(rs.getString("SF_CarBrandName"));
				csinfo.setSF_CarFamilyName(rs.getString("SF_CarFamilyName"));
				csinfo.setSF_CarStyleCode(rs.getString("SF_CarStyleCode"));
				csinfo.setSF_CarStyleName(rs.getString("SF_CarStyleName"));
				listCarStyleInfo.add(csinfo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			closeResource(conn, state, rs);
		}
		return listCarStyleInfo;
	}

	private static void closeResource(Connection conn, Statement state, ResultSet rs) {
		if(null != rs){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(null != state){
			try {
				state.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				state = null;
			}
		}
		if(null != conn){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
		
	}
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("conf/log4j.properties");
		List<CarStyleInfo> list = CarStyleInfoDao.getCarStyleInfoHaveVin();
		int i=1;
		for(CarStyleInfo c : list)
			System.out.println(c.getSF_CarStyleName()+"----"+i++);
	}
	
}
