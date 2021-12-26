package com.robin.homework.homework.q4.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class UpperCaseStrategy extends PhysicalNamingStrategyStandardImpl {

  private static final long serialVersionUID = 1383021413247872469L;


  @Override
  public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
    String tableName = name.getText().toUpperCase();
    return name.toIdentifier(tableName);
  }
}
