package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.douzone.mysite.vo.BoardVo;

public class BoardRepository {
	private Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");

			String url = "jdbc:mysql://192.168.10.39:3306/webdb?charset=utf8";
			connection = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		}
		return connection;
	}

	public boolean insert(BoardVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();
			if (vo.getgNo() == 0) {
				String sql = "insert" + "	into board" + " values (null, ?, ?, 0, now(),"
						+ "	(select ifnull(max(g_no)+1, 1) from board b)," + " 1, 1, ?)";

				pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getUserNo());

			} else {
				String sql = "insert into board" + "	values (null, ?, ?, 0, now(), ?, ?, ?, ?)";
				pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getgNo());
				pstmt.setLong(4, vo.getoNo() + 1);
				pstmt.setLong(5, vo.getDepth() + 1);
				pstmt.setLong(6, vo.getUserNo());
			}
			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll(String keyword) {
		List<BoardVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = getConnection();
			
			if(keyword == null) {
				String sql = "select no, title," 
						+ "	(select name from user where no = a.user_no),"
						+ "	hit, date_format(reg_date, \"%Y-%m-%d %H:%m:%s\")," 
						+ " user_no, depth" 
						+ "    from board a"
						+ " order by g_no desc, o_no asc";
				
				pstmt = connection.prepareStatement(sql);
				
			} else {
				String sql = "select no, title," 
						+ "	(select name from user where no = a.user_no),"
						+ "	hit, date_format(reg_date, \"%Y-%m-%d %H:%m:%s\")," 
						+ " user_no, depth" 
						+ "    from board a"
						+ " where title like ?"
						+ " order by g_no desc, o_no asc";
				
				pstmt = connection.prepareStatement(sql);
				
				pstmt.setString(1, "%"+keyword+"%");
			}
			
			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				long userNo = rs.getLong(6);
				long depth = rs.getLong(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setDepth(depth);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List<BoardVo> paging(int num, String keyword) {
		List<BoardVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = getConnection();
			if(keyword != null) {
				String sql = "select no, title," 
						+ "	(select name from user where no = a.user_no),"
						+ "	hit, date_format(reg_date, \"%Y-%m-%d %H:%m:%s\")," 
						+ " user_no, depth" 
						+ "    from board a"
						+ " where title like ?"
						+ " order by g_no desc, o_no asc" + " limit ?, 5";

				pstmt = connection.prepareStatement(sql);

				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setLong(2, (num - 1) * 5);
			} else {
				String sql = "select no, title," 
						+ "	(select name from user where no = a.user_no),"
						+ "	hit, date_format(reg_date, \"%Y-%m-%d %H:%m:%s\")," 
						+ " user_no, depth" 
						+ "    from board a"
						+ " order by g_no desc, o_no asc" + " limit ?, 5";

				pstmt = connection.prepareStatement(sql);

				pstmt.setLong(1, (num - 1) * 5);
			}

			rs = pstmt.executeQuery();

			// 6. 결과처리
			while (rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String regDate = rs.getString(5);
				long userNo = rs.getLong(6);
				long depth = rs.getLong(7);

				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(hit);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				vo.setDepth(depth);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public BoardVo findByNo(long num) {
		BoardVo result = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			connection = getConnection();

			String sql = "select no, title, contents, user_no," + " g_no, o_no, depth" + "	from board"
					+ " where no = ?";
			pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long userNo = rs.getLong(4);
				Long gNo = rs.getLong(5);
				Long oNo = rs.getLong(6);
				Long depth = rs.getLong(7);

				result = new BoardVo();
				result.setNo(no);
				result.setTitle(title);
				result.setContents(content);
				result.setUserNo(userNo);
				result.setgNo(gNo);
				result.setoNo(oNo);
				result.setDepth(depth);
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean delete(long no) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "delete from board where no=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public long update(BoardVo vo) {
		long result = 0;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update board set title = ?, contents = ?" + "	where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
			pstmt.setLong(3, vo.getNo());

			pstmt.executeUpdate();

			result = vo.getNo();

		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean replyUpdate(BoardVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update board set o_no = (o_no+1) where o_no > ? and g_no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, vo.getoNo());
			pstmt.setLong(2, vo.getgNo());

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean updateHit(long no) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update board set hit = hit+1 where no = ?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);

			int count = pstmt.executeUpdate();
			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

}