package com.ohgiraffers.section01.statement;

import java.sql.*;
import java.util.ArrayList;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {
        /* 트랜잭션 처리를 위한 DBMS 연동용 Connection 객체 생성 */
        Connection con = getConnection();
        System.out.println("con = " + con);

        Statement stmt = null;   // 쿼리를 운반하고 결과를 반환
        ResultSet rset = null;      // 조회할 예정(DML 작업이면 ResultSet 대신 int로 처리)

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery("SELECT * FROM tbl_menu");

//            while (rset.next()) {
//                System.out.println(rset.getString("menu_name"));
//                System.out.println(rset.getInt("menu_price") + "\n");
//            }

            ArrayList<MenuDTO> list = null;
            while (rset.next()) {
                list = new ArrayList<>();
                list.add(new MenuDTO(rset.getString("menu_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(stmt);
            close(con);
        }
    }
}
