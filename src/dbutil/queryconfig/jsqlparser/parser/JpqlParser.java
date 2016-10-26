package dbutil.queryconfig.jsqlparser.parser;

import java.util.ArrayList;
import java.util.List;

import dbutil.queryconfig.jsqlparser.expression.AllComparisonExpression;
import dbutil.queryconfig.jsqlparser.expression.AnyComparisonExpression;
import dbutil.queryconfig.jsqlparser.expression.BinaryExpression;
import dbutil.queryconfig.jsqlparser.expression.CaseExpression;
import dbutil.queryconfig.jsqlparser.expression.DateValue;
import dbutil.queryconfig.jsqlparser.expression.DoubleValue;
import dbutil.queryconfig.jsqlparser.expression.Expression;
import dbutil.queryconfig.jsqlparser.expression.Function;
import dbutil.queryconfig.jsqlparser.expression.InverseExpression;
import dbutil.queryconfig.jsqlparser.expression.JpqlParameter;
import dbutil.queryconfig.jsqlparser.expression.LongValue;
import dbutil.queryconfig.jsqlparser.expression.NullValue;
import dbutil.queryconfig.jsqlparser.expression.Parenthesis;
import dbutil.queryconfig.jsqlparser.expression.StringValue;
import dbutil.queryconfig.jsqlparser.expression.TimeValue;
import dbutil.queryconfig.jsqlparser.expression.TimestampValue;
import dbutil.queryconfig.jsqlparser.expression.WhenClause;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Addition;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.BitwiseAnd;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.BitwiseOr;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.BitwiseXor;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Concat;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Division;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Multiplication;
import dbutil.queryconfig.jsqlparser.expression.operators.arithmetic.Subtraction;
import dbutil.queryconfig.jsqlparser.expression.operators.conditional.AndExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.conditional.OrExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.Between;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.EqualsTo;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ExistsExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ExpressionList;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.GreaterThan;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.GreaterThanEquals;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.InExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.IsNullExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.ItemsList;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.LikeExpression;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.Matches;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.MinorThan;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.MinorThanEquals;
import dbutil.queryconfig.jsqlparser.expression.operators.relational.NotEqualsTo;
import dbutil.queryconfig.jsqlparser.schema.Column;
import dbutil.queryconfig.jsqlparser.schema.Table;
import dbutil.queryconfig.jsqlparser.statement.Statement;
import dbutil.queryconfig.jsqlparser.statement.create.table.ColDataType;
import dbutil.queryconfig.jsqlparser.statement.create.table.ColumnDefinition;
import dbutil.queryconfig.jsqlparser.statement.create.table.CreateTable;
import dbutil.queryconfig.jsqlparser.statement.create.table.Index;
import dbutil.queryconfig.jsqlparser.statement.delete.Delete;
import dbutil.queryconfig.jsqlparser.statement.drop.Drop;
import dbutil.queryconfig.jsqlparser.statement.insert.Insert;
import dbutil.queryconfig.jsqlparser.statement.replace.Replace;
import dbutil.queryconfig.jsqlparser.statement.select.AllColumns;
import dbutil.queryconfig.jsqlparser.statement.select.AllTableColumns;
import dbutil.queryconfig.jsqlparser.statement.select.Distinct;
import dbutil.queryconfig.jsqlparser.statement.select.FromItem;
import dbutil.queryconfig.jsqlparser.statement.select.Join;
import dbutil.queryconfig.jsqlparser.statement.select.Limit;
import dbutil.queryconfig.jsqlparser.statement.select.OrderByElement;
import dbutil.queryconfig.jsqlparser.statement.select.PlainSelect;
import dbutil.queryconfig.jsqlparser.statement.select.Select;
import dbutil.queryconfig.jsqlparser.statement.select.SelectBody;
import dbutil.queryconfig.jsqlparser.statement.select.SelectExpressionItem;
import dbutil.queryconfig.jsqlparser.statement.select.SelectItem;
import dbutil.queryconfig.jsqlparser.statement.select.StartWithExpression;
import dbutil.queryconfig.jsqlparser.statement.select.SubJoin;
import dbutil.queryconfig.jsqlparser.statement.select.SubSelect;
import dbutil.queryconfig.jsqlparser.statement.select.Top;
import dbutil.queryconfig.jsqlparser.statement.select.Union;
import dbutil.queryconfig.jsqlparser.statement.select.WithItem;
import dbutil.queryconfig.jsqlparser.statement.truncate.Truncate;
import dbutil.queryconfig.jsqlparser.statement.update.Update;


/**
 * The parser generated by JavaCC
 */
public class JpqlParser implements JpqlParserConstants {

  final public Statement Statement() throws ParseException {
  Statement stm;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_WITH:
    case K_SELECT:
    case 82:
      stm = Select();
      break;
    case K_UPDATE:
      stm = Update();
      break;
    case K_INSERT:
      stm = Insert();
      break;
    case K_DELETE:
      stm = Delete();
      break;
    case K_REPLACE:
      stm = Replace();
      break;
    case K_CREATE:
      stm = CreateTable();
      break;
    case K_DROP:
      stm = Drop();
      break;
    case K_TRUNCATE:
      stm = Truncate();
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 79:
      jj_consume_token(79);
      break;
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(0);
      {if (true) return stm;}
    throw new Error("Missing return statement in function");
  }

  final public Update Update() throws ParseException {
        Update update = new Update();
        FromItem table = null;
        Expression where = null;
        Column tableColumn = null;
        List expList = new ArrayList();
        List columns = new ArrayList();
        Expression value = null;
        Token tk=null;
    tk = jj_consume_token(K_UPDATE);
    table = TableWithAlias();
    jj_consume_token(K_SET);
    tableColumn = Column();
    jj_consume_token(80);
    value = SimpleExpression();
                                                                columns.add(tableColumn); expList.add(value);
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[2] = jj_gen;
        break label_1;
      }
      jj_consume_token(81);
      tableColumn = Column();
      jj_consume_token(80);
      value = SimpleExpression();
                                                                            columns.add(tableColumn); expList.add(value);
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_WHERE:
      where = WhereClause();
                           update.setWhere(where);
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
        update.setHint(tk.next);
                update.setColumns(columns);
                update.setExpressions(expList);
                update.setTable(table);
                {if (true) return update;}
    throw new Error("Missing return statement in function");
  }

  final public Replace Replace() throws ParseException {
        Replace replace = new Replace();
        Table table = null;
        Column tableColumn = null;
        Expression value = null;

        List columns = new ArrayList();
        List expList = new ArrayList();
        ItemsList itemsList = null;
        Expression exp = null;
    jj_consume_token(K_REPLACE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_INTO:
      jj_consume_token(K_INTO);
      break;
    default:
      jj_la1[4] = jj_gen;
      ;
    }
    table = Table();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_SET:
      jj_consume_token(K_SET);
      tableColumn = Column();
      jj_consume_token(80);
      value = SimpleExpression();
                                                                                columns.add(tableColumn); expList.add(value);
      label_2:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 81:
          ;
          break;
        default:
          jj_la1[5] = jj_gen;
          break label_2;
        }
        jj_consume_token(81);
        tableColumn = Column();
        jj_consume_token(80);
        value = SimpleExpression();
                                                                                   columns.add(tableColumn); expList.add(value);
      }
                                replace.setExpressions(expList);
      break;
    case K_SELECT:
    case K_VALUES:
    case 82:
      if (jj_2_1(2)) {
        jj_consume_token(82);
        tableColumn = Column();
                                                              columns.add(tableColumn);
        label_3:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 81:
            ;
            break;
          default:
            jj_la1[6] = jj_gen;
            break label_3;
          }
          jj_consume_token(81);
          tableColumn = Column();
                                                                                                                      columns.add(tableColumn);
        }
        jj_consume_token(83);
      } else {
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_VALUES:
        jj_consume_token(K_VALUES);
        jj_consume_token(82);
        exp = PrimaryExpression();
                                                                          expList.add(exp);
        label_4:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 81:
            ;
            break;
          default:
            jj_la1[7] = jj_gen;
            break label_4;
          }
          jj_consume_token(81);
          exp = PrimaryExpression();
                                                                                expList.add(exp);
        }
        jj_consume_token(83);
                                                                                                             itemsList = new ExpressionList(expList);
        break;
      case K_SELECT:
      case 82:
                                          replace.setUseValues(false);
        itemsList = SubSelect();
        break;
      default:
        jj_la1[8] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                                replace.setItemsList(itemsList);
      break;
    default:
      jj_la1[9] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                if (columns.size() > 0)
                        replace.setColumns(columns);
                replace.setTable(table);
                {if (true) return replace;}
    throw new Error("Missing return statement in function");
  }

  final public Insert Insert() throws ParseException {
    Token tk = null;
        Insert insert = new Insert();
        FromItem table = null;
        Column tableColumn = null;
        List columns = new ArrayList();
        List primaryExpList = new ArrayList();
        ItemsList itemsList = null;
        Expression exp = null;
    tk = jj_consume_token(K_INSERT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_INTO:
      jj_consume_token(K_INTO);
      break;
    default:
      jj_la1[10] = jj_gen;
      ;
    }
    table = TableOrParameter();
    if (jj_2_2(2)) {
      jj_consume_token(82);
      tableColumn = Column();
                                              columns.add(tableColumn);
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 81:
          ;
          break;
        default:
          jj_la1[11] = jj_gen;
          break label_5;
        }
        jj_consume_token(81);
        tableColumn = Column();
                                                                                                      columns.add(tableColumn);
      }
      jj_consume_token(83);
    } else {
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_VALUES:
      jj_consume_token(K_VALUES);
      jj_consume_token(82);
      exp = SimpleExpression();
                                                         primaryExpList.add(exp);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 81:
          ;
          break;
        default:
          jj_la1[12] = jj_gen;
          break label_6;
        }
        jj_consume_token(81);
        exp = SimpleExpression();
                                                               primaryExpList.add(exp);
      }
      jj_consume_token(83);
                                                                                                   itemsList = new ExpressionList(primaryExpList);
      break;
    case K_SELECT:
    case 82:
      if (jj_2_3(2)) {
        jj_consume_token(82);
      } else {
        ;
      }
                          insert.setUseValues(false);
      itemsList = SubSelect();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 83:
        jj_consume_token(83);
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
        insert.setHint(tk.next);
                insert.setItemsList(itemsList);
            insert.setTable(table);
            if (columns.size() > 0)
                insert.setColumns(columns);
        {if (true) return insert;}
    throw new Error("Missing return statement in function");
  }

  final public Delete Delete() throws ParseException {
        Delete delete = new Delete();
        FromItem table = null;
        Expression where = null;
        String alias=null;
        Token tk=null;
    tk = jj_consume_token(K_DELETE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_AS:
    case K_FROM:
    case S_IDENTIFIER:
    case S_QUOTED_IDENTIFIER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_AS:
      case S_IDENTIFIER:
      case S_QUOTED_IDENTIFIER:
        alias = Alias();
        break;
      default:
        jj_la1[15] = jj_gen;
        ;
      }
      jj_consume_token(K_FROM);
      break;
    default:
      jj_la1[16] = jj_gen;
      ;
    }
    table = TableWithAlias();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_WHERE:
      where = WhereClause();
                           delete.setWhere(where);
      break;
    default:
      jj_la1[17] = jj_gen;
      ;
    }
        delete.setAlias(alias);
        delete.setHint(tk.next);
        delete.setTable(table);
        {if (true) return delete;}
    throw new Error("Missing return statement in function");
  }

  final public Column Column() throws ParseException {
        String name1 = null;
        String name2 = null;
        String name3 = null;
    // [schema.][tabella.]colonna
        name1 = RelObjectName();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 84:
      jj_consume_token(84);
      name2 = RelObjectName();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 84:
        jj_consume_token(84);
        name3 = RelObjectName();
        break;
      default:
        jj_la1[18] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[19] = jj_gen;
      ;
    }
        String colName = null;
        Table table = null;
        if (name3 != null) {
                table = new Table(name1, name2);
                colName = name3;
        } else if (name2 != null) {
                table = new Table(null, name1);
                colName = name2;
        } else {
                table = new Table(null, null);
                colName = name1;
        }

        {if (true) return new Column(table, colName);}
    throw new Error("Missing return statement in function");
  }

  final public String RelObjectName() throws ParseException {
        Token tk = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_IDENTIFIER:
      tk = jj_consume_token(S_IDENTIFIER);
      break;
    case S_QUOTED_IDENTIFIER:
      tk = jj_consume_token(S_QUOTED_IDENTIFIER);
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return tk.image;}
    throw new Error("Missing return statement in function");
  }

  final public FromItem TableWithAlias() throws ParseException {
        FromItem table = null;
        String alias = null;
    table = TableOrParameter();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_AS:
    case S_IDENTIFIER:
    case S_QUOTED_IDENTIFIER:
      alias = Alias();
                                                  table.setAlias(alias);
      break;
    default:
      jj_la1[21] = jj_gen;
      ;
    }
          {if (true) return table;}
    throw new Error("Missing return statement in function");
  }

  final public FromItem TableOrParameter() throws ParseException {
  FromItem table=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_IDENTIFIER:
    case S_QUOTED_IDENTIFIER:
      table = Table();
                       {if (true) return table;}
      break;
    case 87:
    case 106:
      table = CaseJPQLParameter();
                                   {if (true) return table;}
      break;
    default:
      jj_la1[22] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Table Table() throws ParseException {
        Table table = null;
        String name1 = null;
        String name2 = null;
        String name3 = null;
    if (jj_2_4(3)) {
      name1 = RelObjectName();
      jj_consume_token(84);
      name2 = RelObjectName();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 85:
        jj_consume_token(85);
        name3 = RelObjectName();
        break;
      default:
        jj_la1[23] = jj_gen;
        ;
      }
                                                                                              table = new Table(name1, name2);
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case S_IDENTIFIER:
      case S_QUOTED_IDENTIFIER:
        name1 = RelObjectName();
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 85:
          jj_consume_token(85);
          name3 = RelObjectName();
          break;
        default:
          jj_la1[24] = jj_gen;
          ;
        }
                                                                    table = new Table(null, name1);
        break;
      default:
        jj_la1[25] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                table.addDbLink(name3);
                {if (true) return table;}
    throw new Error("Missing return statement in function");
  }

  final public Select Select() throws ParseException {
        Select select = new Select();
        SelectBody selectBody = null;
        List with = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_WITH:
      with = WithList();
                            select.setWithItemsList(with);
      break;
    default:
      jj_la1[26] = jj_gen;
      ;
    }
    selectBody = SelectBody();
                select.setSelectBody(selectBody);
                {if (true) return select;}
    throw new Error("Missing return statement in function");
  }

  final public SelectBody SelectBody() throws ParseException {
  SelectBody selectBody = null;
    if (jj_2_5(2147483647)) {
      selectBody = Union();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_SELECT:
        selectBody = PlainSelect();
        break;
      default:
        jj_la1[27] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return selectBody;}
    throw new Error("Missing return statement in function");
  }

  final public PlainSelect PlainSelect() throws ParseException {
        PlainSelect plainSelect = new PlainSelect();
        List selectItems = null;
        FromItem fromItem = null;
        List joins = null;
        List distinctOn = null;
        Expression where = null;
        List orderByElements;
        List groupByColumnReferences = null;
        StartWithExpression startWith = null;
        Expression having = null;
        Limit limit = null;
        Top top = null;
        Token tk=null;
    tk = jj_consume_token(K_SELECT);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ALL:
    case K_DISTINCT:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ALL:
        jj_consume_token(K_ALL);
        break;
      case K_DISTINCT:
        jj_consume_token(K_DISTINCT);
                                       Distinct distinct = new Distinct(); plainSelect.setDistinct(distinct);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_ON:
          jj_consume_token(K_ON);
          jj_consume_token(82);
          distinctOn = SelectItemsList();
                                                                           plainSelect.getDistinct().setOnSelectItems(distinctOn);
          jj_consume_token(83);
          break;
        default:
          jj_la1[28] = jj_gen;
          ;
        }
        break;
      default:
        jj_la1[29] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[30] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_TOP:
      top = Top();
                      plainSelect.setTop(top);
      break;
    default:
      jj_la1[31] = jj_gen;
      ;
    }
    selectItems = SelectItemsList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_INTO:
      IntoClause();
      break;
    default:
      jj_la1[32] = jj_gen;
      ;
    }
    jj_consume_token(K_FROM);
    fromItem = FromItem();
    joins = JoinsList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_WHERE:
      where = WhereClause();
                            plainSelect.setWhere(where);
      break;
    default:
      jj_la1[33] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_GROUP:
      groupByColumnReferences = GroupByColumnReferences();
                                                          plainSelect.setGroupByColumnReferences(groupByColumnReferences);
      break;
    default:
      jj_la1[34] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_HAVING:
      having = Having();
                        plainSelect.setHaving(having);
      break;
    default:
      jj_la1[35] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_START:
      startWith = StartWith();
                                     plainSelect.setStartWith(startWith);
      break;
    default:
      jj_la1[36] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ORDER:
      orderByElements = OrderByElements();
                                                                         plainSelect.setOrderByElements(orderByElements);
      break;
    default:
      jj_la1[37] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_LIMIT:
    case K_OFFSET:
      limit = Limit();
                          plainSelect.setLimit(limit);
      break;
    default:
      jj_la1[38] = jj_gen;
      ;
    }
            plainSelect.setHint(tk.next);
                plainSelect.setSelectItems(selectItems);
                plainSelect.setFromItem(fromItem);
                if (joins.size() > 0)
                        plainSelect.setJoins(joins);
                {if (true) return plainSelect;}
    throw new Error("Missing return statement in function");
  }

  final public Union Union() throws ParseException {
        Union union = new Union();
        List orderByElements = null;
        Limit limit = null;
        PlainSelect select = null;
        ArrayList selects = new ArrayList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 82:
      jj_consume_token(82);
      select = PlainSelect();
                                                  selects.add(select);
      jj_consume_token(83);
      jj_consume_token(K_UNION);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ALL:
      case K_DISTINCT:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_ALL:
          jj_consume_token(K_ALL);
                                                      union.setAll(true);
          break;
        case K_DISTINCT:
          jj_consume_token(K_DISTINCT);
                                                                                                  union.setDistinct(true);
          break;
        default:
          jj_la1[39] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[40] = jj_gen;
        ;
      }
      jj_consume_token(82);
      select = PlainSelect();
                                                  selects.add(select);
      jj_consume_token(83);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_UNION:
          ;
          break;
        default:
          jj_la1[41] = jj_gen;
          break label_7;
        }
        jj_consume_token(K_UNION);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_ALL:
        case K_DISTINCT:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case K_ALL:
            jj_consume_token(K_ALL);
            break;
          case K_DISTINCT:
            jj_consume_token(K_DISTINCT);
            break;
          default:
            jj_la1[42] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[43] = jj_gen;
          ;
        }
        jj_consume_token(82);
        select = PlainSelect();
                                                                                               selects.add(select);
        jj_consume_token(83);
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ORDER:
        orderByElements = OrderByElements();
                                                            union.setOrderByElements(orderByElements);
        break;
      default:
        jj_la1[44] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_LIMIT:
      case K_OFFSET:
        limit = Limit();
                                        union.setLimit(limit);
        break;
      default:
        jj_la1[45] = jj_gen;
        ;
      }
      break;
    case K_SELECT:
      select = PlainSelect();
                                              selects.add(select);
      jj_consume_token(K_UNION);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ALL:
      case K_DISTINCT:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_ALL:
          jj_consume_token(K_ALL);
                                                      union.setAll(true);
          break;
        case K_DISTINCT:
          jj_consume_token(K_DISTINCT);
                                                                                                  union.setDistinct(true);
          break;
        default:
          jj_la1[46] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[47] = jj_gen;
        ;
      }
      select = PlainSelect();
                                              selects.add(select);
      label_8:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_UNION:
          ;
          break;
        default:
          jj_la1[48] = jj_gen;
          break label_8;
        }
        jj_consume_token(K_UNION);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_ALL:
        case K_DISTINCT:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case K_ALL:
            jj_consume_token(K_ALL);
            break;
          case K_DISTINCT:
            jj_consume_token(K_DISTINCT);
            break;
          default:
            jj_la1[49] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[50] = jj_gen;
          ;
        }
        select = PlainSelect();
                                                                                           selects.add(select);
      }
      break;
    default:
      jj_la1[51] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                union.setPlainSelects(selects);
                {if (true) return union;}
    throw new Error("Missing return statement in function");
  }

  final public List WithList() throws ParseException {
        ArrayList withItemsList = new ArrayList();
        WithItem with = null;
    jj_consume_token(K_WITH);
    with = WithItem();
                                   withItemsList.add(with);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[52] = jj_gen;
        break label_9;
      }
      jj_consume_token(81);
      with = WithItem();
                                                                                     withItemsList.add(with);
    }
          {if (true) return withItemsList;}
    throw new Error("Missing return statement in function");
  }

  final public WithItem WithItem() throws ParseException {
        WithItem with = new WithItem();
        String name = null;
        List selectItems = null;
        SelectBody selectBody = null;
    name = RelObjectName();
                                with.setName(name);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 82:
      jj_consume_token(82);
      selectItems = SelectItemsList();
      jj_consume_token(83);
                                                   with.setWithItemList(selectItems);
      break;
    default:
      jj_la1[53] = jj_gen;
      ;
    }
    jj_consume_token(K_AS);
    jj_consume_token(82);
    selectBody = SelectBody();
                                         with.setSelectBody(selectBody);
    jj_consume_token(83);
           {if (true) return with;}
    throw new Error("Missing return statement in function");
  }

  final public List SelectItemsList() throws ParseException {
        ArrayList selectItemsList = new ArrayList();
        SelectItem selectItem = null;
    selectItem = SelectItem();
                              selectItemsList.add(selectItem);
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[54] = jj_gen;
        break label_10;
      }
      jj_consume_token(81);
      selectItem = SelectItem();
                                                                                                selectItemsList.add(selectItem);
    }
      {if (true) return selectItemsList;}
    throw new Error("Missing return statement in function");
  }

  final public SelectItem SelectItem() throws ParseException {
        Function function = null;
        AllColumns allTableColumns = null;
        Column tableColumn = null;
        String alias = null;
        SelectItem selectItem = null;
        SelectExpressionItem selectExpressionItem = null;
        Expression expression = null;
        SubSelect subSelect = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 86:
      jj_consume_token(86);
           selectItem = new AllColumns();
      break;
    default:
      jj_la1[56] = jj_gen;
      if (jj_2_6(2147483647)) {
        selectItem = AllTableColumns();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_NULL:
        case K_CASE:
        case K_REPLACE:
        case S_DOUBLE:
        case S_INTEGER:
        case S_IDENTIFIER:
        case S_CHAR_LITERAL:
        case S_QUOTED_IDENTIFIER:
        case 82:
        case 87:
        case 98:
        case 99:
        case 102:
        case 104:
        case 105:
        case 106:
        case 107:
          expression = SimpleExpression();
                                         selectExpressionItem = new SelectExpressionItem(); selectExpressionItem.setExpression(expression);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case K_AS:
          case S_IDENTIFIER:
          case S_QUOTED_IDENTIFIER:
            alias = Alias();
                                          selectExpressionItem.setAlias(alias);
            break;
          default:
            jj_la1[55] = jj_gen;
            ;
          }
                                                                                     selectItem = selectExpressionItem;
          break;
        default:
          jj_la1[57] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
                {if (true) return selectItem;}
    throw new Error("Missing return statement in function");
  }

  final public AllTableColumns AllTableColumns() throws ParseException {
        Table table = null;
    table = Table();
    jj_consume_token(84);
    jj_consume_token(86);
                {if (true) return new AllTableColumns(table);}
    throw new Error("Missing return statement in function");
  }

  final public String Alias() throws ParseException {
  String retval = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_AS:
      jj_consume_token(K_AS);
      break;
    default:
      jj_la1[58] = jj_gen;
      ;
    }
    retval = RelObjectName();
     {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public void IntoClause() throws ParseException {
    jj_consume_token(K_INTO);
    Table();
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[59] = jj_gen;
        break label_11;
      }
      jj_consume_token(81);
      Table();
    }
  }

  final public FromItem FromItem() throws ParseException {
        FromItem fromItem = null;
        String alias = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 82:
      jj_consume_token(82);
      if (jj_2_7(2147483647)) {
        fromItem = SubJoin();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_SELECT:
        case 82:
          fromItem = SubSelect();
          break;
        default:
          jj_la1[60] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(83);
      break;
    case S_IDENTIFIER:
    case S_QUOTED_IDENTIFIER:
      fromItem = Table();
      break;
    case 87:
    case 106:
      fromItem = CaseJPQLParameter();
      break;
    default:
      jj_la1[61] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_AS:
    case S_IDENTIFIER:
    case S_QUOTED_IDENTIFIER:
      alias = Alias();
                         fromItem.setAlias(alias);
      break;
    default:
      jj_la1[62] = jj_gen;
      ;
    }
                {if (true) return fromItem;}
    throw new Error("Missing return statement in function");
  }

  final public FromItem SubJoin() throws ParseException {
        FromItem fromItem = null;
        Join join = null;
        SubJoin subJoin = new SubJoin();
    fromItem = FromItem();
                              subJoin.setLeft(fromItem);
    join = JoinerExpression();
                                  subJoin.setJoin(join);
                {if (true) return subJoin;}
    throw new Error("Missing return statement in function");
  }

  final public List JoinsList() throws ParseException {
        ArrayList joinsList = new ArrayList();
        Join join = null;
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_JOIN:
      case K_LEFT:
      case K_FULL:
      case K_INNER:
      case K_OUTER:
      case K_RIGHT:
      case K_NATURAL:
      case 81:
        ;
        break;
      default:
        jj_la1[63] = jj_gen;
        break label_12;
      }
      join = JoinerExpression();
                               joinsList.add(join);
    }
      {if (true) return joinsList;}
    throw new Error("Missing return statement in function");
  }

  final public Join JoinerExpression() throws ParseException {
        Join join = new Join();
        FromItem right = null;
        Expression onExpression = null;
        Column tableColumn;
        List columns = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_LEFT:
    case K_FULL:
    case K_RIGHT:
    case K_NATURAL:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_LEFT:
        jj_consume_token(K_LEFT);
                           join.setLeft(true);
        break;
      case K_RIGHT:
        jj_consume_token(K_RIGHT);
                              join.setRight(true);
        break;
      case K_FULL:
        jj_consume_token(K_FULL);
                             join.setFull(true);
        break;
      case K_NATURAL:
        jj_consume_token(K_NATURAL);
                                join.setNatural(true);
        break;
      default:
        jj_la1[64] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[65] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_INNER:
    case K_OUTER:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_OUTER:
        jj_consume_token(K_OUTER);
                            join.setOuter(true);
        break;
      case K_INNER:
        jj_consume_token(K_INNER);
                              join.setInner(true);
        break;
      default:
        jj_la1[66] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[67] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_JOIN:
      jj_consume_token(K_JOIN);
      break;
    case 81:
      jj_consume_token(81);
                               join.setSimple(true);
      break;
    default:
      jj_la1[68] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    right = FromItem();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ON:
    case K_USING:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ON:
        jj_consume_token(K_ON);
        onExpression = Expression();
                                                      join.setOnExpression(onExpression);
        break;
      case K_USING:
        jj_consume_token(K_USING);
        jj_consume_token(82);
        tableColumn = Column();
                                                       columns = new ArrayList(); columns.add(tableColumn);
        label_13:
        while (true) {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 81:
            ;
            break;
          default:
            jj_la1[69] = jj_gen;
            break label_13;
          }
          jj_consume_token(81);
          tableColumn = Column();
                                                            columns.add(tableColumn);
        }
        jj_consume_token(83);
                    join.setUsingColumns(columns);
        break;
      default:
        jj_la1[70] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[71] = jj_gen;
      ;
    }
        join.setRightItem(right);
    {if (true) return join;}
    throw new Error("Missing return statement in function");
  }

  final public Expression WhereClause() throws ParseException {
        Expression retval = null;
    jj_consume_token(K_WHERE);
    retval = Expression();
      {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public List GroupByColumnReferences() throws ParseException {
        Expression columnReference = null;
        List columnReferences = new ArrayList();
    jj_consume_token(K_GROUP);
    jj_consume_token(K_BY);
    columnReference = SimpleExpression();
                                                         columnReferences.add(columnReference);
    label_14:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[72] = jj_gen;
        break label_14;
      }
      jj_consume_token(81);
      columnReference = SimpleExpression();
                                              columnReferences.add(columnReference);
    }
                {if (true) return columnReferences;}
    throw new Error("Missing return statement in function");
  }

  final public StartWithExpression StartWith() throws ParseException {
        Expression startCond = null;
        Expression connectCond = null;
    jj_consume_token(K_START);
    jj_consume_token(K_WITH);
    startCond = Expression();

    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_CONNECT:
      jj_consume_token(K_CONNECT);
      jj_consume_token(K_BY);
      connectCond = Expression();

      break;
    default:
      jj_la1[73] = jj_gen;
      ;
    }
                {if (true) return new StartWithExpression(startCond,connectCond);}
    throw new Error("Missing return statement in function");
  }

  final public Expression Having() throws ParseException {
        Expression having = null;
    jj_consume_token(K_HAVING);
    having = Expression();
                {if (true) return having;}
    throw new Error("Missing return statement in function");
  }

  final public List OrderByElements() throws ParseException {
        List orderByList = new ArrayList();
        OrderByElement orderByElement = null;
    jj_consume_token(K_ORDER);
    jj_consume_token(K_BY);
    orderByElement = OrderByElement();
                                                       orderByList.add(orderByElement);
    label_15:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[74] = jj_gen;
        break label_15;
      }
      jj_consume_token(81);
      orderByElement = OrderByElement();
                                               orderByList.add(orderByElement);
    }
        {if (true) return orderByList;}
    throw new Error("Missing return statement in function");
  }

  final public OrderByElement OrderByElement() throws ParseException {
        OrderByElement orderByElement = new OrderByElement();
        List retval = new ArrayList();
        Expression columnReference = null;
    columnReference = SimpleExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ASC:
    case K_DESC:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ASC:
        jj_consume_token(K_ASC);
        break;
      case K_DESC:
        jj_consume_token(K_DESC);
                           orderByElement.setAsc(false);
        break;
      default:
        jj_la1[75] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[76] = jj_gen;
      ;
    }
        orderByElement.setExpression(columnReference);
                {if (true) return orderByElement;}
    throw new Error("Missing return statement in function");
  }

  final public Limit Limit() throws ParseException {
        Limit limit = new Limit();
        Token token = null;
    if (jj_2_8(3)) {
      jj_consume_token(K_LIMIT);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case S_INTEGER:
        token = jj_consume_token(S_INTEGER);
                                                                    limit.setOffset(Long.parseLong(token.image));
        break;
      case 87:
        jj_consume_token(87);
                                                      limit.setOffsetJdbcParameter(true);
        break;
      default:
        jj_la1[77] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      jj_consume_token(81);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case S_INTEGER:
        token = jj_consume_token(S_INTEGER);
                                                    limit.setRowCount(Long.parseLong(token.image));
        break;
      case 87:
        jj_consume_token(87);
                                                                                                              limit.setRowCountJdbcParameter(true);
        break;
      default:
        jj_la1[78] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_OFFSET:
        jj_consume_token(K_OFFSET);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
          token = jj_consume_token(S_INTEGER);
                                                      limit.setOffset(Long.parseLong(token.image));
          break;
        case 87:
          jj_consume_token(87);
                                                                                                              limit.setOffsetJdbcParameter(true);
          break;
        default:
          jj_la1[79] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      case K_LIMIT:
        jj_consume_token(K_LIMIT);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
          token = jj_consume_token(S_INTEGER);
                                                            limit.setRowCount(Long.parseLong(token.image));
          break;
        case 87:
          jj_consume_token(87);
                                              limit.setRowCountJdbcParameter(true);
          break;
        case K_ALL:
          jj_consume_token(K_ALL);
                                                  limit.setLimitAll(true);
          break;
        default:
          jj_la1[80] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_OFFSET:
          jj_consume_token(K_OFFSET);
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case S_INTEGER:
            token = jj_consume_token(S_INTEGER);
                                                              limit.setOffset(Long.parseLong(token.image));
            break;
          case 87:
            jj_consume_token(87);
                                                                                                                      limit.setOffsetJdbcParameter(true);
            break;
          default:
            jj_la1[81] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[82] = jj_gen;
          ;
        }
        break;
      default:
        jj_la1[83] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
                {if (true) return limit;}
    throw new Error("Missing return statement in function");
  }

  final public Top Top() throws ParseException {
        Top top = new Top();
        Token token = null;
    jj_consume_token(K_TOP);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_INTEGER:
      token = jj_consume_token(S_INTEGER);
                                    top.setRowCount(Long.parseLong(token.image));
      break;
    case 87:
      jj_consume_token(87);
                      top.setRowCountJdbcParameter(true);
      break;
    default:
      jj_la1[84] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
                {if (true) return top;}
    throw new Error("Missing return statement in function");
  }

  final public Expression Expression() throws ParseException {
        Expression retval = null;
    if (jj_2_9(2147483647)) {
      retval = OrExpression();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 82:
        jj_consume_token(82);
        retval = Expression();
        jj_consume_token(83);
                                             retval = new Parenthesis(retval);
        break;
      case 87:
      case 106:
        retval = CaseJPQLParameter();
        break;
      default:
        jj_la1[85] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
      {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public Expression OrExpression() throws ParseException {
        Expression left, right, result;
    left = AndExpression();
                               result = left;
    label_16:
    while (true) {
      if (jj_2_10(2147483647)) {
        ;
      } else {
        break label_16;
      }
      jj_consume_token(K_OR);
      right = AndExpression();
                                result = new OrExpression(left, right);
                                left = result;
    }
                {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression AndExpression() throws ParseException {
        Expression left, right, result;
        boolean not = false;
    if (jj_2_11(2147483647)) {
      left = Condition();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_NOT:
      case 82:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_NOT:
          jj_consume_token(K_NOT);
                        not = true;
          break;
        default:
          jj_la1[86] = jj_gen;
          ;
        }
        jj_consume_token(82);
        left = OrExpression();
        jj_consume_token(83);
                                         left = new Parenthesis(left); if (not) { ((Parenthesis)left).setNot(); not = false; }
        break;
      default:
        jj_la1[87] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          result = left;
    label_17:
    while (true) {
      if (jj_2_12(2147483647)) {
        ;
      } else {
        break label_17;
      }
      jj_consume_token(K_AND);
      if (jj_2_13(2147483647)) {
        right = Condition();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_NOT:
        case 82:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case K_NOT:
            jj_consume_token(K_NOT);
                                not = true;
            break;
          default:
            jj_la1[88] = jj_gen;
            ;
          }
          jj_consume_token(82);
          right = OrExpression();
          jj_consume_token(83);
                                                  right = new Parenthesis(right); if (not) { ((Parenthesis)right).setNot(); not = false; }
          break;
        default:
          jj_la1[89] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
                        result = new AndExpression(left, right);
                        left = result;
    }
                {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression Condition() throws ParseException {
        Expression result;
    if (jj_2_14(2147483647)) {
      result = SQLCondition();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ALL:
      case K_ANY:
      case K_NOT:
      case K_NULL:
      case K_CASE:
      case K_SOME:
      case K_REPLACE:
      case K_PRIOR:
      case S_DOUBLE:
      case S_INTEGER:
      case S_IDENTIFIER:
      case S_CHAR_LITERAL:
      case S_QUOTED_IDENTIFIER:
      case 82:
      case 87:
      case 98:
      case 99:
      case 102:
      case 104:
      case 105:
      case 106:
      case 107:
        result = RegularCondition();
        break;
      default:
        jj_la1[90] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression RegularCondition() throws ParseException {
        Expression result = null;
        Expression leftExpression;
        Expression rightExpression;
        boolean not = false;
        dbutil.queryconfig.jsqlparser.expression.BinaryExpression.Prior prior = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_PRIOR:
      jj_consume_token(K_PRIOR);
                  prior= dbutil.queryconfig.jsqlparser.expression.BinaryExpression.Prior.LEFT;
      break;
    default:
      jj_la1[91] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NOT:
      jj_consume_token(K_NOT);
                not = true;
      break;
    default:
      jj_la1[92] = jj_gen;
      ;
    }
    leftExpression = ComparisonItem();
                                          result = leftExpression;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 88:
      jj_consume_token(88);
              result = new GreaterThan();
      break;
    case 89:
      jj_consume_token(89);
                result = new MinorThan();
      break;
    case 80:
      jj_consume_token(80);
                result = new EqualsTo();
      break;
    case 90:
      jj_consume_token(90);
                 result = new GreaterThanEquals();
      break;
    case 91:
      jj_consume_token(91);
                 result = new MinorThanEquals();
      break;
    case 92:
    case 93:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 92:
        jj_consume_token(92);
        break;
      case 93:
        jj_consume_token(93);
        break;
      default:
        jj_la1[93] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                          result = new NotEqualsTo();
      break;
    case 94:
      jj_consume_token(94);
                 result = new Matches();
      break;
    default:
      jj_la1[94] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_PRIOR:
      jj_consume_token(K_PRIOR);
                     prior= dbutil.queryconfig.jsqlparser.expression.BinaryExpression.Prior.RIGHT;
      break;
    default:
      jj_la1[95] = jj_gen;
      ;
    }
    rightExpression = ComparisonItem();
                BinaryExpression regCond = (BinaryExpression) result;
                regCond.setLeftExpression(leftExpression);
                regCond.setRightExpression(rightExpression);
                regCond.setPrior(prior);
                if (not)
                        regCond.setNot();
          {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression SQLCondition() throws ParseException {
        Expression result;
    if (jj_2_15(2147483647)) {
      result = InExpression();
    } else if (jj_2_16(2147483647)) {
      result = Between();
    } else if (jj_2_17(2147483647)) {
      result = IsNullExpression();
    } else if (jj_2_18(2147483647)) {
      result = ExistsExpression();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_NULL:
      case K_CASE:
      case K_REPLACE:
      case S_DOUBLE:
      case S_INTEGER:
      case S_IDENTIFIER:
      case S_CHAR_LITERAL:
      case S_QUOTED_IDENTIFIER:
      case 82:
      case 87:
      case 98:
      case 99:
      case 102:
      case 104:
      case 105:
      case 106:
      case 107:
        result = LikeExpression();
        break;
      default:
        jj_la1[96] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
          {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression InExpression() throws ParseException {
        InExpression result = new InExpression();
        ItemsList itemsList = null;
        Expression leftExpression = null;
    leftExpression = SimpleExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NOT:
      jj_consume_token(K_NOT);
               result.setNot(true);
      break;
    default:
      jj_la1[97] = jj_gen;
      ;
    }
    jj_consume_token(K_IN);
    jj_consume_token(82);
    if (jj_2_19(2147483647)) {
      itemsList = SubSelect();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_NULL:
      case K_CASE:
      case K_REPLACE:
      case S_DOUBLE:
      case S_INTEGER:
      case S_IDENTIFIER:
      case S_CHAR_LITERAL:
      case S_QUOTED_IDENTIFIER:
      case 82:
      case 87:
      case 98:
      case 99:
      case 102:
      case 104:
      case 105:
      case 106:
      case 107:
        itemsList = SimpleExpressionList();
        break;
      default:
        jj_la1[98] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(83);
                result.setLeftExpression(leftExpression);
                result.setItemsList(itemsList);
                {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression Between() throws ParseException {
        Between result = new Between();
        Expression leftExpression = null;
        Expression betweenExpressionStart = null;
        Expression betweenExpressionEnd = null;
    leftExpression = SimpleExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NOT:
      jj_consume_token(K_NOT);
                       result.setNot(true);
      break;
    default:
      jj_la1[99] = jj_gen;
      ;
    }
    jj_consume_token(K_BETWEEN);
    betweenExpressionStart = SimpleExpression();
    jj_consume_token(K_AND);
    betweenExpressionEnd = SimpleExpression();
                result.setLeftExpression(leftExpression);
                result.setBetweenExpressionStart(betweenExpressionStart);
                result.setBetweenExpressionEnd(betweenExpressionEnd);
                {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression LikeExpression() throws ParseException {
        LikeExpression result = new LikeExpression();
        Expression leftExpression = null;
        Expression rightExpression = null;
    leftExpression = SimpleExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NOT:
      jj_consume_token(K_NOT);
               result.setNot(true);
      break;
    default:
      jj_la1[100] = jj_gen;
      ;
    }
    jj_consume_token(K_LIKE);
    rightExpression = SimpleExpression();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ESCAPE:
      jj_consume_token(K_ESCAPE);
      token = jj_consume_token(S_CHAR_LITERAL);
                                         result.setEscape((new StringValue(token.image)).getValue());
      break;
    default:
      jj_la1[101] = jj_gen;
      ;
    }
                result.setLeftExpression(leftExpression);
                result.setRightExpression(rightExpression);
                {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression IsNullExpression() throws ParseException {
        IsNullExpression result = new IsNullExpression();
        Expression leftExpression = null;
    leftExpression = SimpleExpression();
    jj_consume_token(K_IS);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NOT:
      jj_consume_token(K_NOT);
                      result.setNot(true);
      break;
    default:
      jj_la1[102] = jj_gen;
      ;
    }
    jj_consume_token(K_NULL);
                result.setLeftExpression(leftExpression);
                {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression ExistsExpression() throws ParseException {
        ExistsExpression result = new ExistsExpression();
        Expression rightExpression = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NOT:
      jj_consume_token(K_NOT);
               result.setNot(true);
      break;
    default:
      jj_la1[103] = jj_gen;
      ;
    }
    jj_consume_token(K_EXISTS);
    rightExpression = SimpleExpression();
                result.setRightExpression(rightExpression);
                {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public ExpressionList SQLExpressionList() throws ParseException {
        ExpressionList retval = new ExpressionList();
        List expressions = new ArrayList();
        Expression expr = null;
    expr = Expression();
                        expressions.add(expr);
    label_18:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[104] = jj_gen;
        break label_18;
      }
      jj_consume_token(81);
      expr = Expression();
                                                                          expressions.add(expr);
    }
                retval.setExpressions(expressions);
                {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public ExpressionList SimpleExpressionList() throws ParseException {
        ExpressionList retval = new ExpressionList();
        List expressions = new ArrayList();
        Expression expr = null;
    expr = SimpleExpression();
                              expressions.add(expr);
    label_19:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[105] = jj_gen;
        break label_19;
      }
      jj_consume_token(81);
      expr = SimpleExpression();
                                                                                      expressions.add(expr);
    }
                retval.setExpressions(expressions);
                {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public Expression ComparisonItem() throws ParseException {
        Expression retval = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ALL:
      retval = AllComparisonExpression();
      break;
    case K_ANY:
    case K_SOME:
      retval = AnyComparisonExpression();
      break;
    case K_NULL:
    case K_CASE:
    case K_REPLACE:
    case S_DOUBLE:
    case S_INTEGER:
    case S_IDENTIFIER:
    case S_CHAR_LITERAL:
    case S_QUOTED_IDENTIFIER:
    case 82:
    case 87:
    case 98:
    case 99:
    case 102:
    case 104:
    case 105:
    case 106:
    case 107:
      retval = SimpleExpression();
      break;
    default:
      jj_la1[106] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public Expression AllComparisonExpression() throws ParseException {
        AllComparisonExpression retval = null;
        SubSelect subselect = null;
    jj_consume_token(K_ALL);
    jj_consume_token(82);
    subselect = SubSelect();
    jj_consume_token(83);
                                         retval = new AllComparisonExpression(subselect);
      {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public Expression AnyComparisonExpression() throws ParseException {
        AnyComparisonExpression retval = null;
        SubSelect subselect = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ANY:
      jj_consume_token(K_ANY);
      break;
    case K_SOME:
      jj_consume_token(K_SOME);
      break;
    default:
      jj_la1[107] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(82);
    subselect = SubSelect();
    jj_consume_token(83);
                                                      retval = new AnyComparisonExpression(subselect);
      {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public Expression SimpleExpression() throws ParseException {
        Expression retval = null;
    if (jj_2_20(2147483647)) {
      retval = BitwiseAndOr();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 82:
        jj_consume_token(82);
        retval = BitwiseAndOr();
        jj_consume_token(83);
                                               retval = new Parenthesis(retval);
        break;
      default:
        jj_la1[108] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
      {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public Expression ConcatExpression() throws ParseException {
        Expression result = null;
        Expression leftExpression = null;
        Expression rightExpression = null;
    leftExpression = AdditiveExpression();
                                           result = leftExpression;
    label_20:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 95:
        ;
        break;
      default:
        jj_la1[109] = jj_gen;
        break label_20;
      }
      jj_consume_token(95);
      rightExpression = AdditiveExpression();
                        Concat binExp = new Concat();
                        binExp.setLeftExpression(leftExpression);
                        binExp.setRightExpression(rightExpression);
                        result = binExp;
                        leftExpression = result;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression BitwiseAndOr() throws ParseException {
        Expression result = null;
        Expression leftExpression = null;
        Expression rightExpression = null;
    leftExpression = ConcatExpression();
                                         result = leftExpression;
    label_21:
    while (true) {
      if (jj_2_21(2)) {
        ;
      } else {
        break label_21;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 96:
        jj_consume_token(96);
                                              result = new BitwiseOr();
        break;
      case 97:
        jj_consume_token(97);
                                              result = new BitwiseAnd();
        break;
      default:
        jj_la1[110] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      rightExpression = ConcatExpression();
                        BinaryExpression binExp = (BinaryExpression) result;
                        binExp.setLeftExpression(leftExpression);
                        binExp.setRightExpression(rightExpression);
                        leftExpression = result;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression AdditiveExpression() throws ParseException {
        Expression result = null;
        Expression leftExpression = null;
        Expression rightExpression = null;
    leftExpression = MultiplicativeExpression();
                                                 result = leftExpression;
    label_22:
    while (true) {
      if (jj_2_22(2)) {
        ;
      } else {
        break label_22;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 98:
        jj_consume_token(98);
                            result = new Addition();
        break;
      case 99:
        jj_consume_token(99);
                                                                        result = new Subtraction();
        break;
      default:
        jj_la1[111] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      rightExpression = MultiplicativeExpression();
                        BinaryExpression binExp = (BinaryExpression) result;
                        binExp.setLeftExpression(leftExpression);
                        binExp.setRightExpression(rightExpression);
                        leftExpression = result;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression MultiplicativeExpression() throws ParseException {
        Expression result = null;
        Expression leftExpression = null;
        Expression rightExpression = null;
    if (jj_2_23(2147483647)) {
      leftExpression = BitwiseXor();
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 82:
        jj_consume_token(82);
        leftExpression = AdditiveExpression();
        jj_consume_token(83);
                                                         leftExpression = new Parenthesis(leftExpression);
        break;
      default:
        jj_la1[112] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
        result = leftExpression;
    label_23:
    while (true) {
      if (jj_2_24(2)) {
        ;
      } else {
        break label_23;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 86:
        jj_consume_token(86);
                            result = new Multiplication();
        break;
      case 100:
        jj_consume_token(100);
                                                                        result = new Division();
        break;
      default:
        jj_la1[113] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      if (jj_2_25(2147483647)) {
        rightExpression = BitwiseXor();
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 82:
          jj_consume_token(82);
          rightExpression = AdditiveExpression();
          jj_consume_token(83);
                                                                                                  rightExpression = new Parenthesis(rightExpression);
          break;
        default:
          jj_la1[114] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
                        BinaryExpression binExp = (BinaryExpression) result;
                        binExp.setLeftExpression(leftExpression);
                        binExp.setRightExpression(rightExpression);
                        leftExpression = result;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression BitwiseXor() throws ParseException {
        Expression result = null;
        Expression leftExpression = null;
        Expression rightExpression = null;
    leftExpression = PrimaryExpression();
                                          result = leftExpression;
    label_24:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 101:
        ;
        break;
      default:
        jj_la1[115] = jj_gen;
        break label_24;
      }
      jj_consume_token(101);
      rightExpression = PrimaryExpression();
                        BitwiseXor binExp = new BitwiseXor();
                        binExp.setLeftExpression(leftExpression);
                        binExp.setRightExpression(rightExpression);
                        result = binExp;
                        leftExpression = result;
    }
      {if (true) return result;}
    throw new Error("Missing return statement in function");
  }

  final public Expression PrimaryExpression() throws ParseException {
        Expression retval = null;
        Token token = null;
        boolean isInverse = false;
        String tmp = "";
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_NULL:
      jj_consume_token(K_NULL);
                   retval = new NullValue();
      break;
    case K_CASE:
      retval = CaseWhenExpression();
      break;
    case 87:
    case 106:
      retval = CaseJPQLParameter();
      break;
    default:
      jj_la1[128] = jj_gen;
      if (jj_2_26(2147483647)) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 98:
        case 99:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 98:
            jj_consume_token(98);
            break;
          case 99:
            jj_consume_token(99);
                                                           isInverse = true;
            break;
          default:
            jj_la1[116] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[117] = jj_gen;
          ;
        }
        retval = Function();
      } else if (jj_2_27(2147483647)) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 98:
        case 99:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 98:
            jj_consume_token(98);
            break;
          case 99:
            jj_consume_token(99);
                                                                    tmp = "-";
            break;
          default:
            jj_la1[118] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[119] = jj_gen;
          ;
        }
        token = jj_consume_token(S_DOUBLE);
                                                                                                      retval = new DoubleValue(tmp+token.image);
      } else if (jj_2_28(2147483647)) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 98:
        case 99:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 98:
            jj_consume_token(98);
            break;
          case 99:
            jj_consume_token(99);
                                                                            tmp = "-";
            break;
          default:
            jj_la1[120] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[121] = jj_gen;
          ;
        }
        token = jj_consume_token(S_INTEGER);
                                                                                                               retval = new LongValue(tmp+token.image);
      } else if (jj_2_29(2)) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 98:
        case 99:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 98:
            jj_consume_token(98);
            break;
          case 99:
            jj_consume_token(99);
                                      isInverse = true;
            break;
          default:
            jj_la1[122] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[123] = jj_gen;
          ;
        }
        retval = Column();
      } else if (jj_2_30(2)) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 98:
        case 99:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 98:
            jj_consume_token(98);
            break;
          case 99:
            jj_consume_token(99);
                                     isInverse = true;
            break;
          default:
            jj_la1[124] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
          break;
        default:
          jj_la1[125] = jj_gen;
          ;
        }
        jj_consume_token(82);
        retval = PrimaryExpression();
        jj_consume_token(83);
                                                                                               retval = new Parenthesis(retval);
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_CHAR_LITERAL:
          token = jj_consume_token(S_CHAR_LITERAL);
                                   retval = new StringValue(token.image);
          break;
        case 82:
        case 98:
        case 99:
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case 98:
          case 99:
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case 98:
              jj_consume_token(98);
              break;
            case 99:
              jj_consume_token(99);
                        isInverse = true;
              break;
            default:
              jj_la1[126] = jj_gen;
              jj_consume_token(-1);
              throw new ParseException();
            }
            break;
          default:
            jj_la1[127] = jj_gen;
            ;
          }
          jj_consume_token(82);
          retval = SubSelect();
          jj_consume_token(83);
          break;
        case 102:
          jj_consume_token(102);
          token = jj_consume_token(S_CHAR_LITERAL);
          jj_consume_token(103);
                                                  retval = new DateValue(token.image);
          break;
        case 104:
          jj_consume_token(104);
          token = jj_consume_token(S_CHAR_LITERAL);
          jj_consume_token(103);
                                                  retval = new TimeValue(token.image);
          break;
        case 105:
          jj_consume_token(105);
          token = jj_consume_token(S_CHAR_LITERAL);
          jj_consume_token(103);
                                                   retval = new TimestampValue(token.image);
          break;
        default:
          jj_la1[129] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
                if (isInverse) {
                        retval = new InverseExpression(retval);
                }
        {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public JpqlParameter CaseJPQLParameter() throws ParseException {
        Token name=null;
        Token type=null;
        String typeStr=null;
        JpqlParameter caseExp=null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 87:
      jj_consume_token(87);
      name = jj_consume_token(S_INTEGER);
      if (jj_2_31(2)) {
        jj_consume_token(89);
        type = jj_consume_token(S_IDENTIFIER);
        jj_consume_token(88);
               typeStr=type.image;
      } else {
        ;
      }
        caseExp = new JpqlParameter(name.image,true,typeStr);
        {if (true) return caseExp;}
      break;
    case 106:
      jj_consume_token(106);
      name = jj_consume_token(S_IDENTIFIER);
      if (jj_2_32(2)) {
        jj_consume_token(89);
        type = jj_consume_token(S_IDENTIFIER);
        jj_consume_token(88);
        typeStr=type.image;
      } else {
        ;
      }
        caseExp = new JpqlParameter(name.image,false,typeStr);
        {if (true) return caseExp;}
      break;
    default:
      jj_la1[130] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public Expression CaseWhenExpression() throws ParseException {
        CaseExpression caseExp = new CaseExpression();
        Expression switchExp = null;
        WhenClause clause;
        List whenClauses = new ArrayList();
        Expression elseExp = null;
    jj_consume_token(K_CASE);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_WHEN:
      label_25:
      while (true) {
        clause = WhenThenSearchCondition();
                                                 whenClauses.add(clause);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_WHEN:
          ;
          break;
        default:
          jj_la1[131] = jj_gen;
          break label_25;
        }
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ELSE:
        jj_consume_token(K_ELSE);
        elseExp = PrimaryExpression();
        break;
      default:
        jj_la1[132] = jj_gen;
        ;
      }
      break;
    case K_NULL:
    case K_CASE:
    case K_REPLACE:
    case S_DOUBLE:
    case S_INTEGER:
    case S_IDENTIFIER:
    case S_CHAR_LITERAL:
    case S_QUOTED_IDENTIFIER:
    case 82:
    case 87:
    case 98:
    case 99:
    case 102:
    case 104:
    case 105:
    case 106:
    case 107:
      switchExp = PrimaryExpression();
      label_26:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_WHEN:
          ;
          break;
        default:
          jj_la1[133] = jj_gen;
          break label_26;
        }
        clause = WhenThenValue();
                                        whenClauses.add(clause);
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ELSE:
        jj_consume_token(K_ELSE);
        elseExp = PrimaryExpression();
        break;
      default:
        jj_la1[134] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[135] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    jj_consume_token(K_END);
        caseExp.setSwitchExpression(switchExp);
        caseExp.setWhenClauses(whenClauses);
                caseExp.setElseExpression(elseExp);
        {if (true) return caseExp;}
    throw new Error("Missing return statement in function");
  }

  final public WhenClause WhenThenSearchCondition() throws ParseException {
        WhenClause whenThen = new WhenClause();
        Expression whenExp = null;
        Expression thenExp = null;
    jj_consume_token(K_WHEN);
    whenExp = Expression();
    jj_consume_token(K_THEN);
    thenExp = SimpleExpression();
           whenThen.setWhenExpression(whenExp);
           whenThen.setThenExpression(thenExp);
           {if (true) return whenThen;}
    throw new Error("Missing return statement in function");
  }

  final public WhenClause WhenThenValue() throws ParseException {
        WhenClause whenThen = new WhenClause();
        Expression whenExp = null;
        Expression thenExp = null;
    jj_consume_token(K_WHEN);
    whenExp = PrimaryExpression();
    jj_consume_token(K_THEN);
    thenExp = SimpleExpression();
           whenThen.setWhenExpression(whenExp);
           whenThen.setThenExpression(thenExp);
           {if (true) return whenThen;}
    throw new Error("Missing return statement in function");
  }

  final public Function Function() throws ParseException {
        Function retval = new Function();
        String funcName = null;
        String tmp = null;
        ExpressionList expressionList = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 107:
      jj_consume_token(107);
                 retval.setEscaped(true);
      break;
    default:
      jj_la1[136] = jj_gen;
      ;
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_IDENTIFIER:
    case S_QUOTED_IDENTIFIER:
      funcName = RelObjectName();
      break;
    case K_REPLACE:
      jj_consume_token(K_REPLACE);
                          funcName = "REPLACE";
      break;
    default:
      jj_la1[137] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 84:
      jj_consume_token(84);
      tmp = RelObjectName();
                                  funcName+= "." + tmp;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 84:
        jj_consume_token(84);
        tmp = RelObjectName();
                                                                                     funcName+= "." + tmp;
        break;
      default:
        jj_la1[138] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[139] = jj_gen;
      ;
    }
    jj_consume_token(82);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case K_ALL:
    case K_NULL:
    case K_CASE:
    case K_REPLACE:
    case K_DISTINCT:
    case S_DOUBLE:
    case S_INTEGER:
    case S_IDENTIFIER:
    case S_CHAR_LITERAL:
    case S_QUOTED_IDENTIFIER:
    case 82:
    case 86:
    case 87:
    case 98:
    case 99:
    case 102:
    case 104:
    case 105:
    case 106:
    case 107:
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_ALL:
      case K_DISTINCT:
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_DISTINCT:
          jj_consume_token(K_DISTINCT);
                          retval.setDistinct(true);
          break;
        case K_ALL:
          jj_consume_token(K_ALL);
                                                                  retval.setAllColumns(true);
          break;
        default:
          jj_la1[140] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
      default:
        jj_la1[141] = jj_gen;
        ;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_NULL:
      case K_CASE:
      case K_REPLACE:
      case S_DOUBLE:
      case S_INTEGER:
      case S_IDENTIFIER:
      case S_CHAR_LITERAL:
      case S_QUOTED_IDENTIFIER:
      case 82:
      case 87:
      case 98:
      case 99:
      case 102:
      case 104:
      case 105:
      case 106:
      case 107:
        expressionList = SimpleExpressionList();
        break;
      case 86:
        jj_consume_token(86);
                                                                                                                                                retval.setAllColumns(true);
        break;
      default:
        jj_la1[142] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
    default:
      jj_la1[143] = jj_gen;
      ;
    }
    jj_consume_token(83);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 103:
      jj_consume_token(103);
      break;
    default:
      jj_la1[144] = jj_gen;
      ;
    }
            retval.setParameters(expressionList);
            retval.setName(funcName);
        {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public SubSelect SubSelect() throws ParseException {
        SelectBody selectBody = null;
    selectBody = SelectBody();
        SubSelect subSelect = new SubSelect();
        subSelect.setSelectBody(selectBody);
        {if (true) return subSelect;}
    throw new Error("Missing return statement in function");
  }

  final public CreateTable CreateTable() throws ParseException {
        CreateTable createTable = new CreateTable();
        Table table = null;
        ArrayList columnDefinitions = new ArrayList();
        List columnSpecs = null;
        List tableOptions = new ArrayList();
        Token columnName;
        Token tk = null;
        Token tk2 = null;
        Token tk3 = null;
        ColDataType colDataType = null;
        String stringList = null;
        ColumnDefinition coldef = null;
        List indexes = new ArrayList();
        List colNames = null;
        Index index = null;
        String parameter = null;
    jj_consume_token(K_CREATE);
    label_27:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case K_KEY:
      case K_NOT:
      case K_NULL:
      case K_PRIMARY:
      case S_DOUBLE:
      case S_INTEGER:
      case S_IDENTIFIER:
      case S_CHAR_LITERAL:
      case 80:
      case 82:
        ;
        break;
      default:
        jj_la1[145] = jj_gen;
        break label_27;
      }
      CreateParameter();
    }
    jj_consume_token(K_TABLE);
    table = Table();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case 82:
      jj_consume_token(82);
      columnName = jj_consume_token(S_IDENTIFIER);
      colDataType = ColDataType();
                        columnSpecs = new ArrayList();
      label_28:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_KEY:
        case K_NOT:
        case K_NULL:
        case K_PRIMARY:
        case S_DOUBLE:
        case S_INTEGER:
        case S_IDENTIFIER:
        case S_CHAR_LITERAL:
        case 80:
        case 82:
          ;
          break;
        default:
          jj_la1[146] = jj_gen;
          break label_28;
        }
        parameter = CreateParameter();
                                                columnSpecs.add(parameter);
      }
                        coldef = new ColumnDefinition();
                        coldef.setColumnName(columnName.image);
                        coldef.setColDataType(colDataType);
                        if (columnSpecs.size() > 0)
                                coldef.setColumnSpecStrings(columnSpecs);
                        columnDefinitions.add(coldef);
      label_29:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 81:
          ;
          break;
        default:
          jj_la1[147] = jj_gen;
          break label_29;
        }
        jj_consume_token(81);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_INDEX:
          tk = jj_consume_token(K_INDEX);
          tk3 = jj_consume_token(S_IDENTIFIER);
          colNames = ColumnsNamesList();
                                                        index = new Index();
                                                        index.setType(tk.image);
                                                        index.setName(tk3.image);
                                                        index.setColumnsNames(colNames);
                                                        indexes.add(index);
          break;
        case K_PRIMARY:
          tk = jj_consume_token(K_PRIMARY);
          tk2 = jj_consume_token(K_KEY);
          colNames = ColumnsNamesList();
                                                        index = new Index();
                                                        index.setType(tk.image + " "+ tk2.image);
                                                        index.setColumnsNames(colNames);
                                                        indexes.add(index);
          break;
        case K_KEY:
          tk = jj_consume_token(K_KEY);
          tk3 = jj_consume_token(S_IDENTIFIER);
          colNames = ColumnsNamesList();
                                                        index = new Index();
                                                        index.setType(tk.image);
                                                        index.setName(tk3.image);
                                                        index.setColumnsNames(colNames);
                                                        indexes.add(index);
          break;
        case S_IDENTIFIER:
          columnName = jj_consume_token(S_IDENTIFIER);
          colDataType = ColDataType();
                                                columnSpecs = new ArrayList();
          label_30:
          while (true) {
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case K_KEY:
            case K_NOT:
            case K_NULL:
            case K_PRIMARY:
            case S_DOUBLE:
            case S_INTEGER:
            case S_IDENTIFIER:
            case S_CHAR_LITERAL:
            case 80:
            case 82:
              ;
              break;
            default:
              jj_la1[148] = jj_gen;
              break label_30;
            }
            parameter = CreateParameter();
                                                                              columnSpecs.add(parameter);
          }
                                                coldef = new ColumnDefinition();
                                                coldef.setColumnName(columnName.image);
                                                coldef.setColDataType(colDataType);
                                                if (columnSpecs.size() > 0)
                                                        coldef.setColumnSpecStrings(columnSpecs);
                                                columnDefinitions.add(coldef);
          break;
        default:
          jj_la1[149] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
      jj_consume_token(83);
      label_31:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case K_KEY:
        case K_NOT:
        case K_NULL:
        case K_PRIMARY:
        case S_DOUBLE:
        case S_INTEGER:
        case S_IDENTIFIER:
        case S_CHAR_LITERAL:
        case 80:
        case 82:
          ;
          break;
        default:
          jj_la1[150] = jj_gen;
          break label_31;
        }
        parameter = CreateParameter();
                                                      tableOptions.add(parameter);
      }
      break;
    default:
      jj_la1[151] = jj_gen;
      ;
    }
                createTable.setTable(table);
                if (indexes.size() > 0)
                        createTable.setIndexes(indexes);
                if (tableOptions.size() > 0)
                        createTable.setTableOptionsStrings(tableOptions);
                if (columnDefinitions.size() > 0)
                        createTable.setColumnDefinitions(columnDefinitions);
                {if (true) return createTable;}
    throw new Error("Missing return statement in function");
  }

  final public ColDataType ColDataType() throws ParseException {
        ColDataType colDataType = new ColDataType();
        Token tk = null;
        ArrayList argumentsStringList = new ArrayList();
    tk = jj_consume_token(S_IDENTIFIER);
                              colDataType.setDataType(tk.image);
    if (jj_2_33(2)) {
      jj_consume_token(82);
      label_32:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
        case S_CHAR_LITERAL:
          ;
          break;
        default:
          jj_la1[152] = jj_gen;
          break label_32;
        }
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case S_INTEGER:
          tk = jj_consume_token(S_INTEGER);
          break;
        case S_CHAR_LITERAL:
          tk = jj_consume_token(S_CHAR_LITERAL);
          break;
        default:
          jj_la1[153] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
                                                                     argumentsStringList.add(tk.image);
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case 81:
          jj_consume_token(81);

          break;
        default:
          jj_la1[154] = jj_gen;
          ;
        }
      }
      jj_consume_token(83);
    } else {
      ;
    }
                if (argumentsStringList.size() > 0)
                        colDataType.setArgumentsStringList(argumentsStringList);
                {if (true) return colDataType;}
    throw new Error("Missing return statement in function");
  }

  final public String CreateParameter() throws ParseException {
        String retval = null;
        Token tk = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_IDENTIFIER:
      tk = jj_consume_token(S_IDENTIFIER);
                                            retval = tk.image;
      break;
    case K_NULL:
      tk = jj_consume_token(K_NULL);
                                      retval = tk.image;
      break;
    case K_NOT:
      tk = jj_consume_token(K_NOT);
                                     retval = tk.image;
      break;
    case K_PRIMARY:
      tk = jj_consume_token(K_PRIMARY);
                                         retval = tk.image;
      break;
    case K_KEY:
      tk = jj_consume_token(K_KEY);
                                     retval = tk.image;
      break;
    case S_CHAR_LITERAL:
      tk = jj_consume_token(S_CHAR_LITERAL);
                                              retval = tk.image;
      break;
    case S_INTEGER:
      tk = jj_consume_token(S_INTEGER);
                                         retval = tk.image;
      break;
    case S_DOUBLE:
      tk = jj_consume_token(S_DOUBLE);
                                        retval = tk.image;
      break;
    case 80:
      jj_consume_token(80);
                              retval = "=";
      break;
    case 82:
      retval = AList();
      break;
    default:
      jj_la1[155] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
         {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public String AList() throws ParseException {
        StringBuffer retval = new StringBuffer("(");
        Token tk = null;
    jj_consume_token(82);
    label_33:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case S_DOUBLE:
      case S_INTEGER:
      case S_IDENTIFIER:
      case S_CHAR_LITERAL:
        ;
        break;
      default:
        jj_la1[156] = jj_gen;
        break label_33;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case S_INTEGER:
        tk = jj_consume_token(S_INTEGER);
        break;
      case S_DOUBLE:
        tk = jj_consume_token(S_DOUBLE);
        break;
      case S_CHAR_LITERAL:
        tk = jj_consume_token(S_CHAR_LITERAL);
        break;
      case S_IDENTIFIER:
        tk = jj_consume_token(S_IDENTIFIER);
        break;
      default:
        jj_la1[157] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
                                                                                        retval.append(tk.image);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        jj_consume_token(81);
                                                                                                                         retval.append(",");
        break;
      default:
        jj_la1[158] = jj_gen;
        ;
      }
    }
    jj_consume_token(83);
                retval.append(")");
                {if (true) return retval.toString();}
    throw new Error("Missing return statement in function");
  }

  final public List ColumnsNamesList() throws ParseException {
        List retval = new ArrayList();
        Token tk = null;
    jj_consume_token(82);
    tk = jj_consume_token(S_IDENTIFIER);
                                    retval.add(tk.image);
    label_34:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case 81:
        ;
        break;
      default:
        jj_la1[159] = jj_gen;
        break label_34;
      }
      jj_consume_token(81);
      tk = jj_consume_token(S_IDENTIFIER);
                                          retval.add(tk.image);
    }
    jj_consume_token(83);
                {if (true) return retval;}
    throw new Error("Missing return statement in function");
  }

  final public Drop Drop() throws ParseException {
        Drop drop = new Drop();
        Token tk = null;
        List dropArgs = new ArrayList();
    jj_consume_token(K_DROP);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case S_IDENTIFIER:
      tk = jj_consume_token(S_IDENTIFIER);
      break;
    case K_TABLE:
      tk = jj_consume_token(K_TABLE);
      break;
    case K_INDEX:
      tk = jj_consume_token(K_INDEX);
      break;
    default:
      jj_la1[160] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
      drop.setType(tk.image);
    tk = jj_consume_token(S_IDENTIFIER);
                            drop.setName(tk.image);
    label_35:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case S_IDENTIFIER:
        ;
        break;
      default:
        jj_la1[161] = jj_gen;
        break label_35;
      }
      tk = jj_consume_token(S_IDENTIFIER);
                             dropArgs.add(tk.image);
    }
                if (dropArgs.size() > 0)
                        drop.setParameters(dropArgs);
                {if (true) return drop;}
    throw new Error("Missing return statement in function");
  }

  final public Truncate Truncate() throws ParseException {
        Truncate truncate = new Truncate();
        Table table;
    jj_consume_token(K_TRUNCATE);
    jj_consume_token(K_TABLE);
    table = Table();
                        truncate.setTable(table);
                {if (true) return truncate;}
    throw new Error("Missing return statement in function");
  }

  final private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  final private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  final private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  final private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  final private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  final private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  final private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  final private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  final private boolean jj_2_9(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_9(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(8, xla); }
  }

  final private boolean jj_2_10(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_10(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(9, xla); }
  }

  final private boolean jj_2_11(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_11(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(10, xla); }
  }

  final private boolean jj_2_12(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_12(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(11, xla); }
  }

  final private boolean jj_2_13(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_13(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(12, xla); }
  }

  final private boolean jj_2_14(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_14(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(13, xla); }
  }

  final private boolean jj_2_15(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_15(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(14, xla); }
  }

  final private boolean jj_2_16(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_16(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(15, xla); }
  }

  final private boolean jj_2_17(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_17(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(16, xla); }
  }

  final private boolean jj_2_18(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_18(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(17, xla); }
  }

  final private boolean jj_2_19(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_19(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(18, xla); }
  }

  final private boolean jj_2_20(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_20(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(19, xla); }
  }

  final private boolean jj_2_21(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_21(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(20, xla); }
  }

  final private boolean jj_2_22(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_22(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(21, xla); }
  }

  final private boolean jj_2_23(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_23(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(22, xla); }
  }

  final private boolean jj_2_24(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_24(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(23, xla); }
  }

  final private boolean jj_2_25(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_25(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(24, xla); }
  }

  final private boolean jj_2_26(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_26(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(25, xla); }
  }

  final private boolean jj_2_27(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_27(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(26, xla); }
  }

  final private boolean jj_2_28(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_28(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(27, xla); }
  }

  final private boolean jj_2_29(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_29(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(28, xla); }
  }

  final private boolean jj_2_30(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_30(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(29, xla); }
  }

  final private boolean jj_2_31(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_31(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(30, xla); }
  }

  final private boolean jj_2_32(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_32(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(31, xla); }
  }

  final private boolean jj_2_33(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_33(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(32, xla); }
  }

  final private boolean jj_3R_75() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_128()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_129()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(26)) {
    jj_scanpos = xsp;
    if (jj_3R_130()) return true;
    }
    if (jj_3R_74()) return true;
    xsp = jj_scanpos;
    if (jj_3R_131()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_91() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3R_97() {
    if (jj_scan_token(107)) return true;
    return false;
  }

  final private boolean jj_3R_64() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_97()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_98()) {
    jj_scanpos = xsp;
    if (jj_3R_99()) return true;
    }
    xsp = jj_scanpos;
    if (jj_3R_100()) jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    xsp = jj_scanpos;
    if (jj_3R_101()) jj_scanpos = xsp;
    if (jj_scan_token(83)) return true;
    xsp = jj_scanpos;
    if (jj_scan_token(103)) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_49() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_91()) jj_scanpos = xsp;
    if (jj_scan_token(K_EXISTS)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  final private boolean jj_3_3() {
    if (jj_scan_token(82)) return true;
    return false;
  }

  final private boolean jj_3R_90() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3_19() {
    if (jj_3R_50()) return true;
    return false;
  }

  final private boolean jj_3R_48() {
    if (jj_3R_85()) return true;
    if (jj_scan_token(K_IS)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_90()) jj_scanpos = xsp;
    if (jj_scan_token(K_NULL)) return true;
    return false;
  }

  final private boolean jj_3R_265() {
    if (jj_scan_token(K_WHEN)) return true;
    if (jj_3R_69()) return true;
    if (jj_scan_token(K_THEN)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  final private boolean jj_3R_215() {
    if (jj_3R_75()) return true;
    return false;
  }

  final private boolean jj_3R_157() {
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_215()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_87() {
    if (jj_3R_50()) return true;
    return false;
  }

  final private boolean jj_3_7() {
    if (jj_3R_40()) return true;
    return false;
  }

  final private boolean jj_3_2() {
    if (jj_scan_token(82)) return true;
    if (jj_3R_36()) return true;
    return false;
  }

  final private boolean jj_3R_175() {
    if (jj_3R_50()) return true;
    return false;
  }

  final private boolean jj_3R_136() {
    if (jj_3R_85()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_199()) jj_scanpos = xsp;
    if (jj_scan_token(K_LIKE)) return true;
    if (jj_3R_85()) return true;
    xsp = jj_scanpos;
    if (jj_3R_200()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_200() {
    if (jj_scan_token(K_ESCAPE)) return true;
    if (jj_scan_token(S_CHAR_LITERAL)) return true;
    return false;
  }

  final private boolean jj_3R_40() {
    if (jj_3R_74()) return true;
    if (jj_3R_75()) return true;
    return false;
  }

  final private boolean jj_3R_199() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3R_174() {
    if (jj_3R_40()) return true;
    return false;
  }

  final private boolean jj_3R_264() {
    if (jj_scan_token(K_WHEN)) return true;
    if (jj_3R_224()) return true;
    if (jj_scan_token(K_THEN)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  final private boolean jj_3R_126() {
    if (jj_3R_148()) return true;
    return false;
  }

  final private boolean jj_3R_125() {
    if (jj_3R_73()) return true;
    return false;
  }

  final private boolean jj_3R_231() {
    if (jj_3R_69()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_257()) { jj_scanpos = xsp; break; }
    }
    xsp = jj_scanpos;
    if (jj_3R_258()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_127() {
    if (jj_3R_176()) return true;
    return false;
  }

  final private boolean jj_3R_89() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3R_258() {
    if (jj_scan_token(K_ELSE)) return true;
    if (jj_3R_69()) return true;
    return false;
  }

  final private boolean jj_3R_257() {
    if (jj_3R_265()) return true;
    return false;
  }

  final private boolean jj_3R_47() {
    if (jj_3R_85()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_89()) jj_scanpos = xsp;
    if (jj_scan_token(K_BETWEEN)) return true;
    if (jj_3R_85()) return true;
    if (jj_scan_token(K_AND)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  final private boolean jj_3R_256() {
    if (jj_scan_token(K_ELSE)) return true;
    if (jj_3R_69()) return true;
    return false;
  }

  final private boolean jj_3_1() {
    if (jj_scan_token(82)) return true;
    if (jj_3R_36()) return true;
    return false;
  }

  final private boolean jj_3R_255() {
    if (jj_3R_264()) return true;
    return false;
  }

  final private boolean jj_3R_124() {
    if (jj_scan_token(82)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_174()) {
    jj_scanpos = xsp;
    if (jj_3R_175()) return true;
    }
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_230() {
    Token xsp;
    if (jj_3R_255()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_255()) { jj_scanpos = xsp; break; }
    }
    xsp = jj_scanpos;
    if (jj_3R_256()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_238() {
    if (jj_scan_token(81)) return true;
    if (jj_3R_73()) return true;
    return false;
  }

  final private boolean jj_3R_147() {
    if (jj_scan_token(K_CASE)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_230()) {
    jj_scanpos = xsp;
    if (jj_3R_231()) return true;
    }
    if (jj_scan_token(K_END)) return true;
    return false;
  }

  final private boolean jj_3R_74() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_124()) {
    jj_scanpos = xsp;
    if (jj_3R_125()) {
    jj_scanpos = xsp;
    if (jj_3R_126()) return true;
    }
    }
    xsp = jj_scanpos;
    if (jj_3R_127()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_213() {
    if (jj_scan_token(81)) return true;
    if (jj_3R_212()) return true;
    return false;
  }

  final private boolean jj_3R_46() {
    if (jj_3R_85()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_86()) jj_scanpos = xsp;
    if (jj_scan_token(K_IN)) return true;
    if (jj_scan_token(82)) return true;
    xsp = jj_scanpos;
    if (jj_3R_87()) {
    jj_scanpos = xsp;
    if (jj_3R_88()) return true;
    }
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_18() {
    if (jj_3R_49()) return true;
    return false;
  }

  final private boolean jj_3R_208() {
    if (jj_scan_token(99)) return true;
    return false;
  }

  final private boolean jj_3_17() {
    if (jj_3R_48()) return true;
    return false;
  }

  final private boolean jj_3R_86() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3_16() {
    if (jj_3R_47()) return true;
    return false;
  }

  final private boolean jj_3_15() {
    if (jj_3R_46()) return true;
    return false;
  }

  final private boolean jj_3R_151() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_3R_208()) return true;
    }
    return false;
  }

  final private boolean jj_3R_214() {
    if (jj_scan_token(K_INTO)) return true;
    if (jj_3R_73()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_238()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_84() {
    if (jj_3R_136()) return true;
    return false;
  }

  final private boolean jj_3R_176() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(5)) jj_scanpos = xsp;
    if (jj_3R_37()) return true;
    return false;
  }

  final private boolean jj_3R_83() {
    if (jj_3R_49()) return true;
    return false;
  }

  final private boolean jj_3R_207() {
    if (jj_scan_token(99)) return true;
    return false;
  }

  final private boolean jj_3R_82() {
    if (jj_3R_48()) return true;
    return false;
  }

  final private boolean jj_3R_81() {
    if (jj_3R_47()) return true;
    return false;
  }

  final private boolean jj_3_32() {
    if (jj_scan_token(89)) return true;
    if (jj_scan_token(S_IDENTIFIER)) return true;
    if (jj_scan_token(88)) return true;
    return false;
  }

  final private boolean jj_3R_260() {
    if (jj_3R_176()) return true;
    return false;
  }

  final private boolean jj_3R_80() {
    if (jj_3R_46()) return true;
    return false;
  }

  final private boolean jj_3R_45() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_80()) {
    jj_scanpos = xsp;
    if (jj_3R_81()) {
    jj_scanpos = xsp;
    if (jj_3R_82()) {
    jj_scanpos = xsp;
    if (jj_3R_83()) {
    jj_scanpos = xsp;
    if (jj_3R_84()) return true;
    }
    }
    }
    }
    return false;
  }

  final private boolean jj_3R_150() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_3R_207()) return true;
    }
    return false;
  }

  final private boolean jj_3R_205() {
    if (jj_scan_token(106)) return true;
    if (jj_scan_token(S_IDENTIFIER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_32()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_206() {
    if (jj_scan_token(99)) return true;
    return false;
  }

  final private boolean jj_3R_39() {
    if (jj_3R_73()) return true;
    if (jj_scan_token(84)) return true;
    if (jj_scan_token(86)) return true;
    return false;
  }

  final private boolean jj_3_6() {
    if (jj_3R_39()) return true;
    return false;
  }

  final private boolean jj_3_31() {
    if (jj_scan_token(89)) return true;
    if (jj_scan_token(S_IDENTIFIER)) return true;
    if (jj_scan_token(88)) return true;
    return false;
  }

  final private boolean jj_3R_204() {
    if (jj_scan_token(87)) return true;
    if (jj_scan_token(S_INTEGER)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_31()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_149() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_3R_206()) return true;
    }
    return false;
  }

  final private boolean jj_3R_148() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_204()) {
    jj_scanpos = xsp;
    if (jj_3R_205()) return true;
    }
    return false;
  }

  final private boolean jj_3R_170() {
    if (jj_scan_token(K_DISTINCT)) return true;
    return false;
  }

  final private boolean jj_3R_237() {
    if (jj_3R_85()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_260()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_198() {
    if (jj_scan_token(K_PRIOR)) return true;
    return false;
  }

  final private boolean jj_3R_236() {
    if (jj_3R_39()) return true;
    return false;
  }

  final private boolean jj_3R_197() {
    if (jj_scan_token(94)) return true;
    return false;
  }

  final private boolean jj_3R_104() {
    if (jj_scan_token(99)) return true;
    return false;
  }

  final private boolean jj_3R_196() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(92)) {
    jj_scanpos = xsp;
    if (jj_scan_token(93)) return true;
    }
    return false;
  }

  final private boolean jj_3R_102() {
    if (jj_scan_token(99)) return true;
    return false;
  }

  final private boolean jj_3R_195() {
    if (jj_scan_token(91)) return true;
    return false;
  }

  final private boolean jj_3R_194() {
    if (jj_scan_token(90)) return true;
    return false;
  }

  final private boolean jj_3R_193() {
    if (jj_scan_token(80)) return true;
    return false;
  }

  final private boolean jj_3R_235() {
    if (jj_scan_token(86)) return true;
    return false;
  }

  final private boolean jj_3R_192() {
    if (jj_scan_token(89)) return true;
    return false;
  }

  final private boolean jj_3R_165() {
    if (jj_scan_token(K_DISTINCT)) return true;
    return false;
  }

  final private boolean jj_3R_115() {
    if (jj_scan_token(105)) return true;
    if (jj_scan_token(S_CHAR_LITERAL)) return true;
    if (jj_scan_token(103)) return true;
    return false;
  }

  final private boolean jj_3R_212() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_235()) {
    jj_scanpos = xsp;
    if (jj_3R_236()) {
    jj_scanpos = xsp;
    if (jj_3R_237()) return true;
    }
    }
    return false;
  }

  final private boolean jj_3R_68() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_3R_104()) return true;
    }
    return false;
  }

  final private boolean jj_3R_67() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_3R_102()) return true;
    }
    return false;
  }

  final private boolean jj_3R_114() {
    if (jj_scan_token(104)) return true;
    if (jj_scan_token(S_CHAR_LITERAL)) return true;
    if (jj_scan_token(103)) return true;
    return false;
  }

  final private boolean jj_3R_191() {
    if (jj_scan_token(88)) return true;
    return false;
  }

  final private boolean jj_3R_209() {
    if (jj_scan_token(99)) return true;
    return false;
  }

  final private boolean jj_3R_113() {
    if (jj_scan_token(102)) return true;
    if (jj_scan_token(S_CHAR_LITERAL)) return true;
    if (jj_scan_token(103)) return true;
    return false;
  }

  final private boolean jj_3R_66() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_scan_token(99)) return true;
    }
    return false;
  }

  final private boolean jj_3R_65() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_scan_token(99)) return true;
    }
    return false;
  }

  final private boolean jj_3R_189() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3R_188() {
    if (jj_scan_token(K_PRIOR)) return true;
    return false;
  }

  final private boolean jj_3R_152() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_3R_209()) return true;
    }
    return false;
  }

  final private boolean jj_3_28() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_66()) jj_scanpos = xsp;
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3_14() {
    if (jj_3R_45()) return true;
    return false;
  }

  final private boolean jj_3R_135() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_188()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_189()) jj_scanpos = xsp;
    if (jj_3R_190()) return true;
    xsp = jj_scanpos;
    if (jj_3R_191()) {
    jj_scanpos = xsp;
    if (jj_3R_192()) {
    jj_scanpos = xsp;
    if (jj_3R_193()) {
    jj_scanpos = xsp;
    if (jj_3R_194()) {
    jj_scanpos = xsp;
    if (jj_3R_195()) {
    jj_scanpos = xsp;
    if (jj_3R_196()) {
    jj_scanpos = xsp;
    if (jj_3R_197()) return true;
    }
    }
    }
    }
    }
    }
    xsp = jj_scanpos;
    if (jj_3R_198()) jj_scanpos = xsp;
    if (jj_3R_190()) return true;
    return false;
  }

  final private boolean jj_3R_63() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(98)) {
    jj_scanpos = xsp;
    if (jj_scan_token(99)) return true;
    }
    return false;
  }

  final private boolean jj_3_27() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_65()) jj_scanpos = xsp;
    if (jj_scan_token(S_DOUBLE)) return true;
    return false;
  }

  final private boolean jj_3R_112() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_152()) jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    if (jj_3R_50()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_26() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_63()) jj_scanpos = xsp;
    if (jj_3R_64()) return true;
    return false;
  }

  final private boolean jj_3R_111() {
    if (jj_scan_token(S_CHAR_LITERAL)) return true;
    return false;
  }

  final private boolean jj_3_30() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_68()) jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    if (jj_3R_69()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_29() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_67()) jj_scanpos = xsp;
    if (jj_3R_36()) return true;
    return false;
  }

  final private boolean jj_3R_155() {
    if (jj_3R_212()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_213()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_79() {
    if (jj_3R_135()) return true;
    return false;
  }

  final private boolean jj_3R_110() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_151()) jj_scanpos = xsp;
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3R_78() {
    if (jj_3R_45()) return true;
    return false;
  }

  final private boolean jj_3R_109() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_150()) jj_scanpos = xsp;
    if (jj_scan_token(S_DOUBLE)) return true;
    return false;
  }

  final private boolean jj_3R_171() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(12)) {
    jj_scanpos = xsp;
    if (jj_scan_token(64)) return true;
    }
    return false;
  }

  final private boolean jj_3R_44() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_78()) {
    jj_scanpos = xsp;
    if (jj_3R_79()) return true;
    }
    return false;
  }

  final private boolean jj_3R_108() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_149()) jj_scanpos = xsp;
    if (jj_3R_64()) return true;
    return false;
  }

  final private boolean jj_3R_169() {
    if (jj_scan_token(K_ALL)) return true;
    return false;
  }

  final private boolean jj_3R_121() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_169()) {
    jj_scanpos = xsp;
    if (jj_3R_170()) return true;
    }
    return false;
  }

  final private boolean jj_3_13() {
    if (jj_3R_44()) return true;
    return false;
  }

  final private boolean jj_3R_107() {
    if (jj_3R_148()) return true;
    return false;
  }

  final private boolean jj_3R_263() {
    if (jj_scan_token(87)) return true;
    return false;
  }

  final private boolean jj_3R_226() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3R_60() {
    if (jj_scan_token(100)) return true;
    return false;
  }

  final private boolean jj_3R_106() {
    if (jj_3R_147()) return true;
    return false;
  }

  final private boolean jj_3R_187() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_226()) jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    if (jj_3R_43()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_12() {
    if (jj_scan_token(K_AND)) return true;
    return false;
  }

  final private boolean jj_3R_166() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(12)) {
    jj_scanpos = xsp;
    if (jj_scan_token(64)) return true;
    }
    return false;
  }

  final private boolean jj_3R_105() {
    if (jj_scan_token(K_NULL)) return true;
    return false;
  }

  final private boolean jj_3R_164() {
    if (jj_scan_token(K_ALL)) return true;
    return false;
  }

  final private boolean jj_3R_117() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_164()) {
    jj_scanpos = xsp;
    if (jj_3R_165()) return true;
    }
    return false;
  }

  final private boolean jj_3R_122() {
    if (jj_scan_token(K_UNION)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_171()) jj_scanpos = xsp;
    if (jj_3R_116()) return true;
    return false;
  }

  final private boolean jj_3R_186() {
    if (jj_3R_44()) return true;
    return false;
  }

  final private boolean jj_3R_62() {
    if (jj_scan_token(82)) return true;
    if (jj_3R_93()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_134() {
    if (jj_scan_token(K_AND)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_186()) {
    jj_scanpos = xsp;
    if (jj_3R_187()) return true;
    }
    return false;
  }

  final private boolean jj_3R_69() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_105()) {
    jj_scanpos = xsp;
    if (jj_3R_106()) {
    jj_scanpos = xsp;
    if (jj_3R_107()) {
    jj_scanpos = xsp;
    if (jj_3R_108()) {
    jj_scanpos = xsp;
    if (jj_3R_109()) {
    jj_scanpos = xsp;
    if (jj_3R_110()) {
    jj_scanpos = xsp;
    if (jj_3_29()) {
    jj_scanpos = xsp;
    if (jj_3_30()) {
    jj_scanpos = xsp;
    if (jj_3R_111()) {
    jj_scanpos = xsp;
    if (jj_3R_112()) {
    jj_scanpos = xsp;
    if (jj_3R_113()) {
    jj_scanpos = xsp;
    if (jj_3R_114()) {
    jj_scanpos = xsp;
    if (jj_3R_115()) return true;
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    }
    return false;
  }

  final private boolean jj_3R_118() {
    if (jj_scan_token(K_UNION)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_166()) jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    if (jj_3R_116()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_11() {
    if (jj_3R_44()) return true;
    return false;
  }

  final private boolean jj_3R_185() {
    if (jj_scan_token(K_NOT)) return true;
    return false;
  }

  final private boolean jj_3R_245() {
    if (jj_scan_token(87)) return true;
    return false;
  }

  final private boolean jj_3R_120() {
    if (jj_3R_168()) return true;
    return false;
  }

  final private boolean jj_3R_133() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_185()) jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    if (jj_3R_43()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_25() {
    if (jj_3R_58()) return true;
    return false;
  }

  final private boolean jj_3R_119() {
    if (jj_3R_167()) return true;
    return false;
  }

  final private boolean jj_3R_243() {
    if (jj_scan_token(87)) return true;
    return false;
  }

  final private boolean jj_3R_72() {
    if (jj_3R_116()) return true;
    if (jj_scan_token(K_UNION)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_121()) jj_scanpos = xsp;
    if (jj_3R_116()) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_122()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_132() {
    if (jj_3R_44()) return true;
    return false;
  }

  final private boolean jj_3R_76() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_132()) {
    jj_scanpos = xsp;
    if (jj_3R_133()) return true;
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_134()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_96() {
    if (jj_scan_token(101)) return true;
    if (jj_3R_69()) return true;
    return false;
  }

  final private boolean jj_3R_61() {
    if (jj_3R_58()) return true;
    return false;
  }

  final private boolean jj_3R_56() {
    if (jj_scan_token(99)) return true;
    return false;
  }

  final private boolean jj_3_10() {
    if (jj_scan_token(K_OR)) return true;
    return false;
  }

  final private boolean jj_3R_71() {
    if (jj_scan_token(82)) return true;
    if (jj_3R_116()) return true;
    if (jj_scan_token(83)) return true;
    if (jj_scan_token(K_UNION)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_117()) jj_scanpos = xsp;
    if (jj_scan_token(82)) return true;
    if (jj_3R_116()) return true;
    if (jj_scan_token(83)) return true;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_118()) { jj_scanpos = xsp; break; }
    }
    xsp = jj_scanpos;
    if (jj_3R_119()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_120()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_58() {
    if (jj_3R_69()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_96()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_77() {
    if (jj_scan_token(K_OR)) return true;
    if (jj_3R_76()) return true;
    return false;
  }

  final private boolean jj_3R_59() {
    if (jj_scan_token(86)) return true;
    return false;
  }

  final private boolean jj_3R_38() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_71()) {
    jj_scanpos = xsp;
    if (jj_3R_72()) return true;
    }
    return false;
  }

  final private boolean jj_3R_43() {
    if (jj_3R_76()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_77()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3_23() {
    if (jj_3R_58()) return true;
    return false;
  }

  final private boolean jj_3R_252() {
    if (jj_3R_148()) return true;
    return false;
  }

  final private boolean jj_3R_251() {
    if (jj_scan_token(82)) return true;
    if (jj_3R_224()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_24() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_59()) {
    jj_scanpos = xsp;
    if (jj_3R_60()) return true;
    }
    xsp = jj_scanpos;
    if (jj_3R_61()) {
    jj_scanpos = xsp;
    if (jj_3R_62()) return true;
    }
    return false;
  }

  final private boolean jj_3R_95() {
    if (jj_scan_token(82)) return true;
    if (jj_3R_93()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3_9() {
    if (jj_3R_43()) return true;
    return false;
  }

  final private boolean jj_3R_70() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(70)) {
    jj_scanpos = xsp;
    if (jj_scan_token(77)) return true;
    }
    return false;
  }

  final private boolean jj_3R_162() {
    if (jj_3R_167()) return true;
    return false;
  }

  final private boolean jj_3R_232() {
    if (jj_scan_token(K_ON)) return true;
    if (jj_scan_token(82)) return true;
    if (jj_3R_155()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_94() {
    if (jj_3R_58()) return true;
    return false;
  }

  final private boolean jj_3R_57() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_94()) {
    jj_scanpos = xsp;
    if (jj_3R_95()) return true;
    }
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_24()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_262() {
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3R_250() {
    if (jj_3R_43()) return true;
    return false;
  }

  final private boolean jj_3R_224() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_250()) {
    jj_scanpos = xsp;
    if (jj_3R_251()) {
    jj_scanpos = xsp;
    if (jj_3R_252()) return true;
    }
    }
    return false;
  }

  final private boolean jj_3R_163() {
    if (jj_3R_168()) return true;
    return false;
  }

  final private boolean jj_3R_161() {
    if (jj_3R_219()) return true;
    return false;
  }

  final private boolean jj_3R_248() {
    if (jj_scan_token(K_ALL)) return true;
    return false;
  }

  final private boolean jj_3R_247() {
    if (jj_scan_token(87)) return true;
    return false;
  }

  final private boolean jj_3R_234() {
    if (jj_scan_token(87)) return true;
    return false;
  }

  final private boolean jj_3R_160() {
    if (jj_3R_218()) return true;
    return false;
  }

  final private boolean jj_3R_55() {
    if (jj_scan_token(98)) return true;
    return false;
  }

  final private boolean jj_3R_249() {
    if (jj_scan_token(K_OFFSET)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_262()) {
    jj_scanpos = xsp;
    if (jj_3R_263()) return true;
    }
    return false;
  }

  final private boolean jj_3R_159() {
    if (jj_3R_217()) return true;
    return false;
  }

  final private boolean jj_3R_246() {
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3R_233() {
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3R_158() {
    if (jj_3R_216()) return true;
    return false;
  }

  final private boolean jj_3_33() {
    if (jj_scan_token(82)) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_70()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_156() {
    if (jj_3R_214()) return true;
    return false;
  }

  final private boolean jj_3R_53() {
    if (jj_scan_token(97)) return true;
    return false;
  }

  final private boolean jj_3R_210() {
    if (jj_scan_token(K_DISTINCT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_232()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_42() {
    if (jj_scan_token(87)) return true;
    return false;
  }

  final private boolean jj_3R_52() {
    if (jj_scan_token(96)) return true;
    return false;
  }

  final private boolean jj_3R_154() {
    if (jj_3R_211()) return true;
    return false;
  }

  final private boolean jj_3R_41() {
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3R_223() {
    if (jj_scan_token(K_LIMIT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_246()) {
    jj_scanpos = xsp;
    if (jj_3R_247()) {
    jj_scanpos = xsp;
    if (jj_3R_248()) return true;
    }
    }
    xsp = jj_scanpos;
    if (jj_3R_249()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_211() {
    if (jj_scan_token(K_TOP)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_233()) {
    jj_scanpos = xsp;
    if (jj_3R_234()) return true;
    }
    return false;
  }

  final private boolean jj_3R_244() {
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3_22() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_55()) {
    jj_scanpos = xsp;
    if (jj_3R_56()) return true;
    }
    if (jj_3R_57()) return true;
    return false;
  }

  final private boolean jj_3R_153() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(12)) {
    jj_scanpos = xsp;
    if (jj_3R_210()) return true;
    }
    return false;
  }

  final private boolean jj_3R_242() {
    if (jj_scan_token(S_INTEGER)) return true;
    return false;
  }

  final private boolean jj_3R_93() {
    if (jj_3R_57()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_22()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_172() {
    if (jj_scan_token(85)) return true;
    if (jj_3R_37()) return true;
    return false;
  }

  final private boolean jj_3R_222() {
    if (jj_scan_token(K_OFFSET)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_244()) {
    jj_scanpos = xsp;
    if (jj_3R_245()) return true;
    }
    return false;
  }

  final private boolean jj_3R_116() {
    if (jj_scan_token(K_SELECT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_153()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_154()) jj_scanpos = xsp;
    if (jj_3R_155()) return true;
    xsp = jj_scanpos;
    if (jj_3R_156()) jj_scanpos = xsp;
    if (jj_scan_token(K_FROM)) return true;
    if (jj_3R_74()) return true;
    if (jj_3R_157()) return true;
    xsp = jj_scanpos;
    if (jj_3R_158()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_159()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_160()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_161()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_162()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_163()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3_5() {
    if (jj_3R_38()) return true;
    return false;
  }

  final private boolean jj_3R_146() {
    if (jj_scan_token(86)) return true;
    return false;
  }

  final private boolean jj_3R_140() {
    if (jj_3R_38()) return true;
    return false;
  }

  final private boolean jj_3_8() {
    if (jj_scan_token(K_LIMIT)) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_41()) {
    jj_scanpos = xsp;
    if (jj_3R_42()) return true;
    }
    if (jj_scan_token(81)) return true;
    xsp = jj_scanpos;
    if (jj_3R_242()) {
    jj_scanpos = xsp;
    if (jj_3R_243()) return true;
    }
    return false;
  }

  final private boolean jj_3R_141() {
    if (jj_3R_116()) return true;
    return false;
  }

  final private boolean jj_3_21() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_52()) {
    jj_scanpos = xsp;
    if (jj_3R_53()) return true;
    }
    if (jj_3R_54()) return true;
    return false;
  }

  final private boolean jj_3R_173() {
    if (jj_scan_token(85)) return true;
    if (jj_3R_37()) return true;
    return false;
  }

  final private boolean jj_3R_92() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_140()) {
    jj_scanpos = xsp;
    if (jj_3R_141()) return true;
    }
    return false;
  }

  final private boolean jj_3R_51() {
    if (jj_3R_54()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3_21()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_168() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_8()) {
    jj_scanpos = xsp;
    if (jj_3R_222()) {
    jj_scanpos = xsp;
    if (jj_3R_223()) return true;
    }
    }
    return false;
  }

  final private boolean jj_3R_261() {
    if (jj_scan_token(K_DESC)) return true;
    return false;
  }

  final private boolean jj_3R_142() {
    if (jj_scan_token(95)) return true;
    if (jj_3R_93()) return true;
    return false;
  }

  final private boolean jj_3R_123() {
    if (jj_3R_37()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_173()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3_20() {
    if (jj_3R_51()) return true;
    return false;
  }

  final private boolean jj_3R_220() {
    if (jj_3R_85()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_241()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_54() {
    if (jj_3R_93()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_142()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_241() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(18)) {
    jj_scanpos = xsp;
    if (jj_3R_261()) return true;
    }
    return false;
  }

  final private boolean jj_3R_138() {
    if (jj_scan_token(82)) return true;
    if (jj_3R_51()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_145() {
    if (jj_3R_139()) return true;
    return false;
  }

  final private boolean jj_3_4() {
    if (jj_3R_37()) return true;
    if (jj_scan_token(84)) return true;
    if (jj_3R_37()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_172()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_137() {
    if (jj_3R_51()) return true;
    return false;
  }

  final private boolean jj_3R_73() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_4()) {
    jj_scanpos = xsp;
    if (jj_3R_123()) return true;
    }
    return false;
  }

  final private boolean jj_3R_221() {
    if (jj_scan_token(81)) return true;
    if (jj_3R_220()) return true;
    return false;
  }

  final private boolean jj_3R_167() {
    if (jj_scan_token(K_ORDER)) return true;
    if (jj_scan_token(K_BY)) return true;
    if (jj_3R_220()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_221()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_259() {
    if (jj_scan_token(84)) return true;
    if (jj_3R_37()) return true;
    return false;
  }

  final private boolean jj_3R_201() {
    if (jj_scan_token(81)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  final private boolean jj_3R_85() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_137()) {
    jj_scanpos = xsp;
    if (jj_3R_138()) return true;
    }
    return false;
  }

  final private boolean jj_3R_218() {
    if (jj_scan_token(K_HAVING)) return true;
    if (jj_3R_224()) return true;
    return false;
  }

  final private boolean jj_3R_254() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(14)) {
    jj_scanpos = xsp;
    if (jj_scan_token(34)) return true;
    }
    if (jj_scan_token(82)) return true;
    if (jj_3R_50()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_240() {
    if (jj_scan_token(K_CONNECT)) return true;
    if (jj_scan_token(K_BY)) return true;
    if (jj_3R_224()) return true;
    return false;
  }

  final private boolean jj_3R_219() {
    if (jj_scan_token(K_START)) return true;
    if (jj_scan_token(K_WITH)) return true;
    if (jj_3R_224()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_240()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_143() {
    if (jj_scan_token(84)) return true;
    if (jj_3R_37()) return true;
    return false;
  }

  final private boolean jj_3R_37() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(74)) {
    jj_scanpos = xsp;
    if (jj_scan_token(78)) return true;
    }
    return false;
  }

  final private boolean jj_3R_253() {
    if (jj_scan_token(K_ALL)) return true;
    if (jj_scan_token(82)) return true;
    if (jj_3R_50()) return true;
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_203() {
    if (jj_scan_token(K_ALL)) return true;
    return false;
  }

  final private boolean jj_3R_103() {
    if (jj_scan_token(84)) return true;
    if (jj_3R_37()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_259()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_239() {
    if (jj_scan_token(81)) return true;
    if (jj_3R_85()) return true;
    return false;
  }

  final private boolean jj_3R_229() {
    if (jj_3R_85()) return true;
    return false;
  }

  final private boolean jj_3R_225() {
    if (jj_scan_token(81)) return true;
    if (jj_3R_36()) return true;
    return false;
  }

  final private boolean jj_3R_228() {
    if (jj_3R_254()) return true;
    return false;
  }

  final private boolean jj_3R_217() {
    if (jj_scan_token(K_GROUP)) return true;
    if (jj_scan_token(K_BY)) return true;
    if (jj_3R_85()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_239()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_227() {
    if (jj_3R_253()) return true;
    return false;
  }

  final private boolean jj_3R_190() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_227()) {
    jj_scanpos = xsp;
    if (jj_3R_228()) {
    jj_scanpos = xsp;
    if (jj_3R_229()) return true;
    }
    }
    return false;
  }

  final private boolean jj_3R_216() {
    if (jj_scan_token(K_WHERE)) return true;
    if (jj_3R_224()) return true;
    return false;
  }

  final private boolean jj_3R_130() {
    if (jj_scan_token(81)) return true;
    return false;
  }

  final private boolean jj_3R_36() {
    if (jj_3R_37()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_103()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_139() {
    if (jj_3R_85()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_201()) { jj_scanpos = xsp; break; }
    }
    return false;
  }

  final private boolean jj_3R_184() {
    if (jj_scan_token(K_USING)) return true;
    if (jj_scan_token(82)) return true;
    if (jj_3R_36()) return true;
    Token xsp;
    while (true) {
      xsp = jj_scanpos;
      if (jj_3R_225()) { jj_scanpos = xsp; break; }
    }
    if (jj_scan_token(83)) return true;
    return false;
  }

  final private boolean jj_3R_88() {
    if (jj_3R_139()) return true;
    return false;
  }

  final private boolean jj_3R_183() {
    if (jj_scan_token(K_ON)) return true;
    if (jj_3R_224()) return true;
    return false;
  }

  final private boolean jj_3R_131() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_183()) {
    jj_scanpos = xsp;
    if (jj_3R_184()) return true;
    }
    return false;
  }

  final private boolean jj_3R_182() {
    if (jj_scan_token(K_INNER)) return true;
    return false;
  }

  final private boolean jj_3R_50() {
    if (jj_3R_92()) return true;
    return false;
  }

  final private boolean jj_3R_181() {
    if (jj_scan_token(K_OUTER)) return true;
    return false;
  }

  final private boolean jj_3R_180() {
    if (jj_scan_token(K_NATURAL)) return true;
    return false;
  }

  final private boolean jj_3R_179() {
    if (jj_scan_token(K_FULL)) return true;
    return false;
  }

  final private boolean jj_3R_129() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_181()) {
    jj_scanpos = xsp;
    if (jj_3R_182()) return true;
    }
    return false;
  }

  final private boolean jj_3R_178() {
    if (jj_scan_token(K_RIGHT)) return true;
    return false;
  }

  final private boolean jj_3R_202() {
    if (jj_scan_token(K_DISTINCT)) return true;
    return false;
  }

  final private boolean jj_3R_177() {
    if (jj_scan_token(K_LEFT)) return true;
    return false;
  }

  final private boolean jj_3R_144() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_202()) {
    jj_scanpos = xsp;
    if (jj_3R_203()) return true;
    }
    return false;
  }

  final private boolean jj_3R_99() {
    if (jj_scan_token(K_REPLACE)) return true;
    return false;
  }

  final private boolean jj_3R_101() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_144()) jj_scanpos = xsp;
    xsp = jj_scanpos;
    if (jj_3R_145()) {
    jj_scanpos = xsp;
    if (jj_3R_146()) return true;
    }
    return false;
  }

  final private boolean jj_3R_98() {
    if (jj_3R_37()) return true;
    return false;
  }

  final private boolean jj_3R_100() {
    if (jj_scan_token(84)) return true;
    if (jj_3R_37()) return true;
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_143()) jj_scanpos = xsp;
    return false;
  }

  final private boolean jj_3R_128() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_177()) {
    jj_scanpos = xsp;
    if (jj_3R_178()) {
    jj_scanpos = xsp;
    if (jj_3R_179()) {
    jj_scanpos = xsp;
    if (jj_3R_180()) return true;
    }
    }
    }
    return false;
  }

  public JpqlParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  public Token token, jj_nt;
  private int jj_ntk;
  private Token jj_scanpos, jj_lastpos;
  private int jj_la;
  public boolean lookingAhead = false;
  private boolean jj_semLA;
  private int jj_gen;
  final private int[] jj_la1 = new int[162];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static private int[] jj_la1_2;
  static private int[] jj_la1_3;
  static {
      jj_la1_0();
      jj_la1_1();
      jj_la1_2();
      jj_la1_3();
   }
   private static void jj_la1_0() {
      jj_la1_0 = new int[] {0x2000000,0x0,0x0,0x0,0x400000,0x0,0x0,0x0,0x0,0x20000,0x400000,0x0,0x0,0x0,0x0,0x20,0x10000020,0x0,0x0,0x0,0x0,0x20,0x0,0x0,0x0,0x0,0x0,0x0,0x800,0x1000,0x1000,0x80000,0x400000,0x0,0x0,0x0,0x0,0x0,0x0,0x1000,0x1000,0x0,0x1000,0x1000,0x0,0x0,0x1000,0x1000,0x0,0x1000,0x1000,0x0,0x0,0x0,0x0,0x20,0x0,0x40800000,0x20,0x0,0x0,0x0,0x20,0xc000000,0x8000000,0x8000000,0x0,0x0,0x4000000,0x0,0x800,0x800,0x0,0x0,0x0,0x240000,0x240000,0x0,0x0,0x0,0x1000,0x0,0x0,0x0,0x0,0x0,0x10000,0x10000,0x10000,0x10000,0x40815000,0x0,0x10000,0x0,0x0,0x0,0x40800000,0x10000,0x40800000,0x10000,0x10000,0x0,0x10000,0x10000,0x0,0x0,0x40805000,0x4000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x40800000,0x0,0x0,0x80000000,0x0,0x80000000,0x0,0xc0800000,0x0,0x0,0x0,0x0,0x1000,0x1000,0x40800000,0x40801000,0x0,0x818000,0x818000,0x0,0x818000,0x8000,0x818000,0x0,0x0,0x0,0x0,0x818000,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
   private static void jj_la1_1() {
      jj_la1_1 = new int[] {0xa18e0010,0x0,0x0,0x40,0x0,0x0,0x0,0x0,0x2080000,0x2080000,0x0,0x0,0x0,0x0,0x2080000,0x0,0x0,0x40,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x10,0x80000,0x0,0x0,0x0,0x0,0x0,0x40,0x200,0x400000,0x0,0x8000,0x102000,0x0,0x0,0x100,0x0,0x0,0x8000,0x102000,0x0,0x0,0x100,0x0,0x0,0x80000,0x0,0x0,0x0,0x0,0x0,0x20000000,0x0,0x0,0x80000,0x0,0x0,0x10015008,0x10010008,0x10010008,0x5000,0x5000,0x0,0x0,0x80,0x80,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x100000,0x102000,0x0,0x0,0x0,0x0,0x0,0x0,0x20000004,0x0,0x0,0x0,0x0,0x0,0x20000000,0x0,0x20000000,0x0,0x0,0x4000000,0x0,0x0,0x0,0x0,0x20000004,0x4,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x2,0x0,0x2,0x20000000,0x0,0x20000000,0x0,0x0,0x0,0x0,0x20000000,0x20000000,0x0,0x8000000,0x8000000,0x0,0x8000000,0x8000800,0x8000000,0x0,0x0,0x0,0x0,0x8000000,0x0,0x0,0x0,0x0,0x820,0x0,};
   }
   private static void jj_la1_2() {
      jj_la1_2 = new int[] {0x40000,0x8000,0x20000,0x0,0x0,0x20000,0x20000,0x20000,0x40000,0x40000,0x0,0x20000,0x20000,0x80000,0x40000,0x4400,0x4400,0x0,0x100000,0x100000,0x4400,0x4400,0x804400,0x200000,0x200000,0x4400,0x0,0x0,0x0,0x1,0x1,0x0,0x0,0x0,0x0,0x0,0x10,0x0,0x0,0x1,0x1,0x0,0x1,0x1,0x0,0x0,0x1,0x1,0x0,0x1,0x1,0x40000,0x20000,0x40000,0x20000,0x4400,0x400000,0x846460,0x0,0x20000,0x40000,0x844400,0x4400,0x20000,0x0,0x0,0x0,0x0,0x20000,0x20000,0x0,0x0,0x20000,0x4,0x20000,0x0,0x0,0x800040,0x800040,0x800040,0x800040,0x800040,0x0,0x0,0x800040,0x840000,0x0,0x40000,0x0,0x40000,0x846468,0x8,0x0,0x30000000,0x7f010000,0x8,0x846460,0x0,0x846460,0x0,0x0,0x0,0x0,0x0,0x20000,0x20000,0x846460,0x0,0x40000,0x80000000,0x0,0x0,0x40000,0x400000,0x40000,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x800000,0x42000,0x800000,0x0,0x0,0x0,0x0,0x846460,0x0,0x4400,0x100000,0x100000,0x1,0x1,0xc46460,0xc46461,0x0,0x52460,0x52460,0x20000,0x52460,0x400,0x52460,0x40000,0x2040,0x2040,0x20000,0x52460,0x2460,0x2460,0x20000,0x20000,0x400,0x400,};
   }
   private static void jj_la1_3() {
      jj_la1_3 = new int[] {0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x400,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xf4c,0x0,0x0,0x0,0x400,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x400,0x0,0x0,0x0,0x0,0xf4c,0x0,0x0,0x0,0x0,0x0,0xf4c,0x0,0xf4c,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0xf4c,0x0,0x0,0x0,0x3,0xc,0x0,0x10,0x0,0x20,0xc,0xc,0xc,0xc,0xc,0xc,0xc,0xc,0xc,0xc,0xc,0xc,0x400,0x34c,0x400,0x0,0x0,0x0,0x0,0xf4c,0x800,0x0,0x0,0x0,0x0,0x0,0xf4c,0xf4c,0x80,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,0x0,};
   }
  final private JJCalls[] jj_2_rtns = new JJCalls[33];
  private boolean jj_rescan = false;
  private int jj_gc = 0;

  public JpqlParser(java.io.InputStream stream) {
     this(stream, null);
  }
  public JpqlParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new JpqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public JpqlParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new JpqlParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public JpqlParser(JpqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  public void ReInit(JpqlParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 162; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  final private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends Error { }
  final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  final private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }

  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

  final public Token getToken(int index) {
    Token t = lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  final private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.Vector<int[]> jj_expentries = new java.util.Vector<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;
  private int[] jj_lasttokens = new int[100];
  private int jj_endpos;

  private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      boolean exists = false;
      for (java.util.Enumeration e = jj_expentries.elements(); e.hasMoreElements();) {
        int[] oldentry = (int[])(e.nextElement());
        if (oldentry.length == jj_expentry.length) {
          exists = true;
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              exists = false;
              break;
            }
          }
          if (exists) break;
        }
      }
      if (!exists) jj_expentries.addElement(jj_expentry);
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  public ParseException generateParseException() {
    jj_expentries.removeAllElements();
    boolean[] la1tokens = new boolean[108];
    for (int i = 0; i < 108; i++) {
      la1tokens[i] = false;
    }
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 162; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
          if ((jj_la1_2[i] & (1<<j)) != 0) {
            la1tokens[64+j] = true;
          }
          if ((jj_la1_3[i] & (1<<j)) != 0) {
            la1tokens[96+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 108; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.addElement(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.elementAt(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  final public void enable_tracing() {
  }

  final public void disable_tracing() {
  }

  final private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 33; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
            case 8: jj_3_9(); break;
            case 9: jj_3_10(); break;
            case 10: jj_3_11(); break;
            case 11: jj_3_12(); break;
            case 12: jj_3_13(); break;
            case 13: jj_3_14(); break;
            case 14: jj_3_15(); break;
            case 15: jj_3_16(); break;
            case 16: jj_3_17(); break;
            case 17: jj_3_18(); break;
            case 18: jj_3_19(); break;
            case 19: jj_3_20(); break;
            case 20: jj_3_21(); break;
            case 21: jj_3_22(); break;
            case 22: jj_3_23(); break;
            case 23: jj_3_24(); break;
            case 24: jj_3_25(); break;
            case 25: jj_3_26(); break;
            case 26: jj_3_27(); break;
            case 27: jj_3_28(); break;
            case 28: jj_3_29(); break;
            case 29: jj_3_30(); break;
            case 30: jj_3_31(); break;
            case 31: jj_3_32(); break;
            case 32: jj_3_33(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  final private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}