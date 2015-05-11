package models.recommandation.bddInterface;

import models.recommandation.bddMySQL.MySqlNoSQL;

public abstract class BddNoSqlFactory {
	public static BddNoSQLMere getBDD() 
	{
		return MySqlNoSQL._Singleton;
	}
}
