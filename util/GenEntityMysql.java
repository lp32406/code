package com.eb.dianlianbao_server.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;




public class GenEntityMysql {
	// private static final String URL = "jdbc:mysql://127.0.0.1:3306/mysql";
	// private static final String NAME = "root";
	// private static final String PASS = "";
	// private static final String DRIVER = "com.mysql.jdbc.Driver";

	/*
	 *
	 * 构造函数
	 *
	 */

	private String packageOutPath = "com.eb.dianlianbao_server.pojo";// 指定实体生成所在包的路径

	private String authorName = "wmz";// 作者名字

	private String tablename = "";// 表名

	private String[] colnames; // 列名数组

	private String[] colTypes; // 列名类型数组

	private int[] colSizes; // 列名大小数组

	private boolean f_util = false; // 是否需要导入包java.util.*

	private boolean f_sql = false; // 是否需要导入包java.sql.*

	private boolean f_jpa = true; // 是否需要生成基于注解的JPA实体对象

	// 数据库连接

	private static final String URL = "jdbc:mysql://120.79.193.36:3306/dian_lian_bao?useUnicode=true&characterEncoding=utf-8";

	private static final String NAME = "dian_lian_bao";

	private static final String PASS = "AGm7DhspcyW5HMAR";

	private static final String DRIVER = "com.mysql.jdbc.Driver";


	/*
	 *
	 * 构造函数
	 *
	 */

	public GenEntityMysql() {

		List<String> list = getTableName();

		for (int p = 0; p < list.size(); p++) {

			tablename = list.get(p);

			// 创建连接

			Connection con;

			// 查要生成实体类的表

			String sql = "select * from " + tablename;

			PreparedStatement pStemt = null;

			try {

				try {

					Class.forName(DRIVER);

				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();

				}

				con = DriverManager.getConnection(URL, NAME, PASS);

				pStemt = con.prepareStatement(sql);

				ResultSetMetaData rsmd = pStemt.getMetaData();

				int size = rsmd.getColumnCount();// 统计列

				colnames = new String[size];

				colTypes = new String[size];

				colSizes = new int[size];

				for (int i = 0; i < size; i++) {

					colnames[i] = rsmd.getColumnName(i + 1);
					colnames[i]=initNamecap(colnames[i],false);

					colTypes[i] = rsmd.getColumnTypeName(i + 1);

					if (colTypes[i].equalsIgnoreCase("datetime")) {

						f_util = true;

					}

					if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {

						f_sql = true;

					}

					colSizes[i] = rsmd.getColumnDisplaySize(i + 1);

				}

				String content = parse(colnames, colTypes, colSizes);

				try {

					File directory = new File("");

					String outputPath = directory.getAbsolutePath() + "/src/" + this.packageOutPath.replace(".", "/")
							+ "/" + initNamecap(tablename,true) + ".java";
					System.out.println(outputPath);
					FileWriter fw = new FileWriter(outputPath);

					PrintWriter pw = new PrintWriter(fw);

					pw.println(content);

					pw.flush();

					pw.close();

				} catch (IOException e) {

					e.printStackTrace();

				}

			} catch (SQLException e) {

				e.printStackTrace();

			} finally {

				// try {

				// con.close();

				// } catch (SQLException e) {

				//// TODO Auto-generated catch block

				// e.printStackTrace();

				// }

			}

		}

		System.out.println("生成完毕!");

	}

	/**
	 * 将含有"_"转换 eg. tb_user_dsd -- TbUserDsd
	 * @param colnames
	 * @return
	 */
	private String initNamecap(String colnames,boolean firstToUpperCase) {
		if(firstToUpperCase){
			colnames=initcap(colnames);
		}
		int endIndex = colnames.indexOf("_");
		int startIndex = 0;
		while(endIndex!=-1){
			String temp1 = colnames.substring(startIndex, endIndex);
			String temp2=initcap(colnames.substring(endIndex+1));
			colnames=temp1+temp2;
			endIndex=colnames.indexOf("_");
		}
		return colnames;
	}

	/**
	 *
	 * Java方法 得到当前数据库下所有的表名
	 *
	 * @param con
	 *
	 */

	private List<String> getTableName() {

		List<String> list = new ArrayList<String>();

		try {

			DatabaseMetaData meta = DriverManager.getConnection(URL, NAME, PASS).getMetaData();

			ResultSet rs = meta.getTables(null, null, "tb_chat_message_dialog", new String[] { "TABLE" });

			while (rs.next()) {

				list.add(rs.getString(3));

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return list;

	}

	/**
	 *
	 * 功能:生成实体类主体代码
	 *
	 * @param colnames
	 *
	 * @param colTypes
	 *
	 * @param colSizes
	 *
	 * @return
	 *
	 */

	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {

		StringBuffer sb = new StringBuffer();

		sb.append("package " + this.packageOutPath + ";").append(System.getProperty("line.separator"));

		sb.append(System.getProperty("line.separator"));

		// 判断是否导入工具包

		if (f_util) {

			sb.append("import java.util.Date;").append(System.getProperty("line.separator"));

		}

		if (f_sql) {

			sb.append("import java.sql.*;").append(System.getProperty("line.separator"));

		}

		// jpa

		// if(f_jpa){
		//
		// sb.append("import
		// javax.persistence.Entity;").append(System.getProperty("line.separator"));
		//
		// sb.append("import
		// javax.persistence.GeneratedValue;").append(System.getProperty("line.separator"));
		//
		// sb.append("import
		// javax.persistence.GenerationType;").append(System.getProperty("line.separator"));
		//
		// sb.append("import
		// javax.persistence.Id;").append(System.getProperty("line.separator"));
		//
		// }

		// 注释部分

		sb.append("/**").append(System.getProperty("line.separator"));

		sb.append(" * " + tablename + " 实体类").append(System.getProperty("line.separator"));

		sb.append(" * " + new Date()).append(System.getProperty("line.separator"));

		sb.append(" * @" + this.authorName).append(System.getProperty("line.separator"));

		sb.append(" */").append(System.getProperty("line.separator"));

		// if(f_jpa){
		//
		// sb.append("@Entity").append(System.getProperty("line.separator"));
		//
		// }

		// 实体部分

		sb.append("public class " + initNamecap(tablename,true)).append(" extends Page{").append(System.getProperty("line.separator"));

		processAllAttrs(sb);// 属性

		processAllMethod(sb);// get set方法

		sb.append("}").append(System.getProperty("line.separator"));

		// System.out.println(sb.toString());

		return sb.toString();

	}

	/**
	 *
	 * 功能:生成所有属性
	 *
	 * @param sb
	 *
	 */

	private void processAllAttrs(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {

			sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";")
					.append(System.getProperty("line.separator"));

		}

		sb.append(System.getProperty("line.separator"));

	}

	/**
	 *
	 * 功能:生成所有方法
	 *
	 * @param sb
	 *
	 */

	private void processAllMethod(StringBuffer sb) {

		for (int i = 0; i < colnames.length; i++) {

			if (f_jpa) {

				if (i == 0) {

					// sb.append("/aliyunzixun@xxx.com").append(System.getProperty("line.separator"));
					//
					// sb.append("/aliyunzixun@xxx.com(strategy =
					// GenerationType.AUTO)").append(System.getProperty("line.separator"));

					sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){")
							.append(System.getProperty("line.separator"));

				} else {

					sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){")
							.append(System.getProperty("line.separator"));

				}

			} else {

				sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){")
						.append(System.getProperty("line.separator"));

			}

			sb.append("\t\treturn " + colnames[i] + ";").append(System.getProperty("line.separator"));

			sb.append("\t}").append(System.getProperty("line.separator"));

			sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " "
					+ colnames[i] + "){").append(System.getProperty("line.separator"));

			sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";").append(System.getProperty("line.separator"));

			sb.append("\t}").append(System.getProperty("line.separator"));

		}

	}

	/**
	 *
	 * 功能:将输入字符串的首字母改成大写
	 *
	 * @param str
	 *
	 * @return
	 *
	 */

	private String initcap(String str) {

		char[] ch = str.toCharArray();

		if (ch[0] >= 'a' && ch[0] <= 'z') {

			ch[0] = (char) (ch[0] - 32);

		}

		return new String(ch);

	}

	/**
	 *
	 * 功能:获得列的数据类型
	 *
	 * @param sqlType
	 *
	 * @return
	 *
	 */

	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {

			return "boolean";

		} else if (sqlType.equalsIgnoreCase("tinyint")) {

			return "byte";

		} else if (sqlType.equalsIgnoreCase("smallint")) {

			return "short";

		} else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("INT UNSIGNED")) {

			// INT UNSIGNED无符号整形

			return "int";

		} else if (sqlType.equalsIgnoreCase("bigint")) {

			return "long";

		} else if (sqlType.equalsIgnoreCase("float")) {

			return "float";

		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("double")

				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")

				|| sqlType.equalsIgnoreCase("smallmoney")) {

			return "double";

		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")

				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")

				|| sqlType.equalsIgnoreCase("text")) {

			return "String";

		} else if (sqlType.equalsIgnoreCase("datetime")) {

			return "Date";

		} else if (sqlType.equalsIgnoreCase("image")) {

			return "Blod";

		}

		return null;

	}

	/**
	 *
	 * 出口
	 *
	 * TODO
	 *
	 * @param args
	 *
	 */

	public static void main(String[] args) {

		new GenEntityMysql();
		System.exit(0);
	}
	public void gg(){
		new GenEntityMysql();
	}
}
