package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

import util.StringUtil;

/**
 * oracle 序列工具类
 */
public class SequenceUtil{
	private static DataSource dataSource;

	/**
	 * 根据序列名称获取序列值
	 *
	 * @param sequenceName
	 * @return
	 * @throws RuntimeException
	 */
	public static final String getSequence(String sequenceName) throws RuntimeException{
		if(StringUtil.isEmpty(sequenceName)) {
			throw new RuntimeException("序列名称为空");
		}
		String sequenceValue = "";
		String selectSequenceSql = "SELECT " + sequenceName + ".nextVal FROM DUAL";
		Connection conn=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(selectSequenceSql);
			rs = ps.executeQuery();
			if(rs.next()) {
				sequenceValue = rs.getString(1);
			}
		}
		catch (SQLException e) {
			throw new RuntimeException(sequenceName + "序列获取失败", e);
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {}
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		return sequenceValue;
	}

	public static void setDataSource(DataSource dataSource) {
		SequenceUtil.dataSource = dataSource;
	}

}
